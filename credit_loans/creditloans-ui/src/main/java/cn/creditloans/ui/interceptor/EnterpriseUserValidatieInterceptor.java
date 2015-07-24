package cn.creditloans.ui.interceptor;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.core.service.EnterpriseUserService;
import cn.creditloans.ui.util.cookie.CookieUtil;

public class EnterpriseUserValidatieInterceptor extends HandlerInterceptorAdapter  {
	private static Log logger = LogFactory.getLog(EnterpriseUserValidatieInterceptor.class);
	
	@Autowired
	EnterpriseUserService enterpriseUserService;
	
	private static Pattern pattern = Pattern.compile("^.*\\.css$|^.*\\.js$|^.*\\.jpg$|^.*\\.png$|^.*\\.map$|^.*/login$|^.*\\.gif$|^.*\\.htm$|^.*/doLogin$|^.*\\.apk$|^.*/refreshValidateImage$|^.*\\.woff$|^.*\\.ttf$|^.*\\.eot$|^.*/checkEmail$|^.*/checkVin$");
	
	@Override
	// TODO : 继续完成, 权限的判断
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		boolean isProceed = true;
		
		try {
			String requestUrl = request.getRequestURI();
			// TODO : 过滤资源文件 使用default servlet 
			Matcher matcher = pattern.matcher(requestUrl);
			if (matcher.find()) { // 匹配成功
				return super.preHandle(request, response, handler);
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("intercept url : " + requestUrl);
				}
				
				String token = CookieUtil.getToken(request, "enterprise-token");
				boolean checkResult = enterpriseUserService.enterpriseInterceptorCheck(token);
				if(checkResult){
					EnterpriseUserDTO enterpriseUserDto = enterpriseUserService.getEnterpriseUserDtoFromCache(token);
					request.setAttribute("enterpriseuser", enterpriseUserDto);
					String currentToken = enterpriseUserDto.getToken();
					if (!token.equals(currentToken)) {
						isProceed = false;
					}
				}else{
					isProceed = false;
				}
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			isProceed = false;
		}
		
		PrintWriter out = null;
		try {
			if (!isProceed) {
				response.setContentType("text/html; charset=utf-8");
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				StringBuilder sb = new StringBuilder();
				
				// FIXME: 如果是ajax请求访问的话，有问题,页面不能自动跳转
				sb.append("<script>alert('账户异常，请重新登录');");
				sb.append("window.location.href='" + request.getContextPath() + "/login';");
				sb.append("</script>");
				
				out.println(sb.toString());
				
				return isProceed;
			} else {
				return super.preHandle(request, response, handler);
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			
			return false;
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		
	}
}
