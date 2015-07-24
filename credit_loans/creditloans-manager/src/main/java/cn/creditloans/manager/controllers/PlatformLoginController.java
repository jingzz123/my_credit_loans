package cn.creditloans.manager.controllers;

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

import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.PlatformLoginService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.manager.util.cookie.CookieUtil;


@Controller
@RequestMapping(value = "")
public class PlatformLoginController {
	private static final Log logger = LogFactory.getLog(PlatformLoginController.class);
	
	@Autowired
	PlatformLoginService platformLoginService;
	
	@Autowired
	PlatformUserService platformUserService;
	
	/**
	 * 掉转到login页面
	 * @return
	 */
	@RequestMapping(value="/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login() {
		return "/login";
	}
	
	@RequestMapping(value="/doLogin", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String doLogin(PlatformUserDTO platformUserDto, HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model) {
		int loginResult = 0;
		String url = "";
		String loginResultJson = "";
		try {
			platformUserDto = platformLoginService.login(platformUserDto);
			
			if(platformUserDto.getStatus()==1){
				//loginResult = 1 用户已禁用
				loginResult = 1;
				loginResultJson = "({\"loginResult\":"+loginResult+",\"url\":\"\"})";
				return loginResultJson;
			}else{
				// FIXME : platform-token 放入常量吧
				CookieUtil.addCookie("platform-token", platformUserDto.getToken(), true, request, response);
				model.addAttribute("platformuser", platformUserDto);
				url = platformUserDto.getOwnerPlatformMenuDtoList().get(0).getUrl();
				loginResultJson = "({\"loginResult\":"+loginResult+",\"url\":\""+url+"\"})";
				return loginResultJson;
			}
			
		} catch (RuntimeException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			//loginResult = 2 用户名或密码错误
			loginResult = 2;
			loginResultJson = "({\"loginResult\":"+loginResult+",\"url\":\"\"})";
			return loginResultJson;
		}
	}
	
	@RequestMapping(value="/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		String token = CookieUtil.getToken(request,"platform-token");
		// 删除用户的缓存内容
		platformUserService.deleteTempDatas(token);
		
		// 删除 cookie
		CookieUtil.delCookie("platform-token", request, response); //FIXME : platform-token 放入常量吧
		return "redirect:/login";
	}
	
	@RequestMapping(value="/notFount", method = {RequestMethod.GET, RequestMethod.POST})
	public String notFount(){
		return "/not_fount";
	}
}
