package cn.creditloans.ui.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.creditloans.core.dto.enterprise.EnterpriseDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseRoleDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.core.service.EnterpriseRoleService;
import cn.creditloans.core.service.EnterpriseService;
import cn.creditloans.core.service.EnterpriseUserService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;
import cn.creditloans.ui.util.cookie.CookieUtil;

@Controller
@RequestMapping(value = "/enterprise/user")
public class EnterpriseUserController {
	private static final Log logger = LogFactory.getLog(EnterpriseUserController.class);
	
	@Autowired
	EnterpriseUserService enterpriseUserService;

	@Autowired
	EnterpriseService enterpriseService;
	
	@Autowired
	EnterpriseRoleService enterpriseRoleService;
	

	/**
	 * 打开用户管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String enterpriseUserList(Model model, HttpServletRequest request, @RequestParam(value = "currentPage", required = false) String _currentPage, EnterpriseUserDTO enterpriseUserDto) {
		String token = CookieUtil.getToken(request, "enterprise-token");
		EnterpriseUserDTO enterpriseLoginUserDto = enterpriseUserService.getEnterpriseUserDtoFromCache(token);
		enterpriseUserDto.setEnterpriseId(enterpriseLoginUserDto.getEnterpriseId());
		enterpriseUserDto.setType(1);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}  
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		
		PageModel<EnterpriseUserDTO> enterpriseUserPage = enterpriseUserService.getEnterpriseUserPageList(enterpriseUserDto,
				currentPage, pageSize);
		model.addAttribute("enterpriseUserPage", enterpriseUserPage);
		model.addAttribute("queryDto", enterpriseUserDto);
		return "/enterprise/user/enterprise_user_list";
	}
	
	/**
	 * 打开用户管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddEnterpriseUser(Model model, HttpServletRequest request){
		String token = CookieUtil.getToken(request, "enterprise-token");
		EnterpriseUserDTO enterpriseLoginUserDto = enterpriseUserService.getEnterpriseUserDtoFromCache(token);
		List<EnterpriseDTO> enterpriseDtoList = enterpriseService.selectEnterpriseDtoList(enterpriseLoginUserDto.getEnterpriseId());
		List<EnterpriseRoleDTO> enterpriseRoleDtoList = enterpriseRoleService.getEnterpriseRoleDtoListByEnterpriseId(enterpriseLoginUserDto.getEnterpriseId(),1);
		
		model.addAttribute("enterpriseDtoList", enterpriseDtoList);
		model.addAttribute("enterpriseRoleDtoList", enterpriseRoleDtoList);
		return "/enterprise/user/enterprise_user_add";
	}
	
	@RequestMapping(value = "/getRoleList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<EnterpriseRoleDTO> getRoletList(
			@RequestParam(value = "enterpriseId", required = false) String enterpriseId) {
		int _enterpriseId =Integer.parseInt(enterpriseId);
		List<EnterpriseRoleDTO> enterpriseRoleDtoList = enterpriseRoleService.getEnterpriseRoleDtoListByEnterpriseId(_enterpriseId,1);
		return enterpriseRoleDtoList;
	}
	
	/**
	 * 判断邮箱是否注册
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/checkEmail", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody boolean checkEmail(@RequestParam(value="email") String email) {
	    if(email == null || "".equals(email)) {
	        return true;
	    }
	    EnterpriseUserDTO enterpriseUserDto = enterpriseUserService.getEnterpriseUserByEmial(email);
	    if(enterpriseUserDto == null) {
	        return true;
	    } else {
	        return false;
	    }
	    
	}
	/**
	 * 添加用户
	 * @param enterpriseUserDto
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addEnterpriseUser(@RequestBody EnterpriseUserDTO enterpriseUserDto) {
		enterpriseUserDto.setType(1);
		int enterpriseUserId = enterpriseUserService.saveEnterpriseUser(enterpriseUserDto);
		return enterpriseUserId;
	}
	
	/**
	 * 打开用户管理修改的页面
	 * @param model userId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditEnterpriseUser(Model model, HttpServletRequest request, @RequestParam(value = "userId", required = false) String userId){
		EnterpriseUserDTO enterpriseUserDto = enterpriseUserService.getCascadeEnterpriseUserByUserId(Integer.parseInt(userId),1);
		String token = CookieUtil.getToken(request, "enterprise-token");
		EnterpriseUserDTO enterpriseLoginUserDto = enterpriseUserService.getEnterpriseUserDtoFromCache(token);
		List<EnterpriseDTO> enterpriseDtoList = enterpriseService.selectEnterpriseDtoList(enterpriseLoginUserDto.getEnterpriseId());
		String resetPwd = CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.ENTERPRISEUSER_RESET_PASSWORD);
		
		model.addAttribute("enterpriseUserDto", enterpriseUserDto);
		model.addAttribute("enterpriseDtoList", enterpriseDtoList);
		model.addAttribute("resetPwd", resetPwd);
		return "/enterprise/user/enterprise_user_edit";
	}
	
	/**
	 * 修改用户
	 * @param enterpriseUserDto
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editEnterpriseUser(@RequestBody EnterpriseUserDTO enterpriseUserDto) {
		enterpriseUserService.updateEnterpriseUser(enterpriseUserDto);
	}

	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteEnterpriseUser(@RequestParam(value = "userId", required = false) String userId){
		enterpriseUserService.deleteEnterpriseUser(Integer.parseInt(userId));
	}
}
