package cn.creditloans.ui.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.core.service.EnterpriseLoginService;
import cn.creditloans.core.service.EnterpriseUserService;
import cn.creditloans.ui.util.cookie.CookieUtil;

@Controller
@RequestMapping(value = "")
public class EnterpriseLoginController {
	private static final Log logger = LogFactory.getLog(EnterpriseLoginController.class);
	
	@Autowired
	EnterpriseLoginService enterpriseLoginService;
	
	@Autowired
	EnterpriseUserService enterpriseUserService;
	
	/**
	 * 跳转到登陆页面
	 * @return
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/doLogin", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String doLogin(EnterpriseUserDTO enterpriseUserDto, HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model) {
		int loginResult = 0;
		String url = "";
		String loginResultJson = "";
		try {
			loginResult = enterpriseLoginService.loginCheck(enterpriseUserDto);
			//loginResult == 0 登录校验成功
			if(loginResult == 0){
				// FIXME : enterprise-token 放入常量吧
				enterpriseUserDto = enterpriseUserService.setEnterpriseUserInCache(enterpriseUserDto.getEmail());
				CookieUtil.addCookie("enterprise-token", enterpriseUserDto.getToken(), true, request, response);
				//获取该用户默认加载的第一个菜单链接
				url = enterpriseUserDto.getOwnerEnterpriseMenuDtoList().get(0).getUrl();
			}
			loginResultJson = "({\"loginResult\":"+loginResult+",\"url\":\""+url+"\"})";
			return loginResultJson;
			
		} catch (RuntimeException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			loginResultJson = "({\"loginResult\":"+-1+",\"url\":\"\"})";
			return "/login";
		}
	}
	
	@RequestMapping(value="/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		String token = CookieUtil.getToken(request,"enterprise-token");
		// 删除用户的缓存内容
		enterpriseUserService.deleteTempDatas(token);
		
		// 删除 cookie
		CookieUtil.delCookie("enterprise-token", request, response); //FIXME : enterprise-token 放入常量吧
		return "redirect:/login";
	}
	
	@RequestMapping(value="/notFount", method = {RequestMethod.GET, RequestMethod.POST})
	public String notFount(){
		return "/not_fount";
	}
}
