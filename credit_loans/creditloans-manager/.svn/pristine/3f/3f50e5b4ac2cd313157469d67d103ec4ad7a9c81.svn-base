package cn.creditloans.manager.controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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

import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.dto.platform.PlatformRoleDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.PlatformDepartmentService;
import cn.creditloans.core.service.PlatformRoleService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/platform/user")
public class PlatformUserController {
	private static final Log logger = LogFactory.getLog(PlatformUserController.class);
	
	@Autowired
	PlatformUserService platformUserService;
	
	@Autowired
	PlatformDepartmentService platformDepartmentService;
	
	@Autowired
	PlatformRoleService platformRoleService;
	
	/**
	 * 打开用户管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String platformUserList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, PlatformUserDTO platformUserDto) {
		int currentPage = 1;
		if (StringUtils.isNotEmpty(_currentPage)) {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<PlatformUserDTO> platformUserPage = platformUserService.getPlatformUserPageList(platformUserDto,
				currentPage, pageSize);
		model.addAttribute("platformUserPage", platformUserPage);
		model.addAttribute("queryDto", platformUserDto);
		return "/platform/user/platform_user_list";
	}
	
	/**
	 * 打开用户管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddPlatformUser(Model model){
		List<PlatformDepartmentDTO> platformDepartmentDtoList = platformDepartmentService.getPlatformDepartmentDtoList();
		model.addAttribute("platformDepartmentDtoList", platformDepartmentDtoList);
		List<PlatformRoleDTO> platformRoleDtoList = platformRoleService.getPlatformRoleDtoList();
		model.addAttribute("platformRoleDtoList", platformRoleDtoList);
		return "/platform/user/platform_user_add";
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
	    PlatformUserDTO platformUserDto = platformUserService.getPlatformUserByEmial(email);
	    if(platformUserDto == null) {
	        return true;
	    } else {
	        return false;
	    }
	    
	}
	/**
	 * 添加用户
	 * @param platformUserDto
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addPlatformUser(@RequestBody PlatformUserDTO platformUserDto) {
		int platformUserId = platformUserService.savePlatformUser(platformUserDto);
		return platformUserId;
	}
	
	/**
	 * 打开用户管理修改的页面
	 * @param model userId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditPlatformUser(Model model,@RequestParam(value = "userId", required = false) String userId){
		PlatformUserDTO platformUserDto = platformUserService.getCascadePlatformUserByUserId(Integer.parseInt(userId));
		List<PlatformDepartmentDTO> platformDepartmentDtoList = platformDepartmentService.getPlatformDepartmentDtoList();
		String resetPwd = CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORMUSER__RESET_PASSWORD);
		model.addAttribute("platformUserDto", platformUserDto);
		model.addAttribute("platformDepartmentDtoList", platformDepartmentDtoList);
		model.addAttribute("resetPwd", resetPwd);
		return "/platform/user/platform_user_edit";
	}
	
	/**
	 * 修改用户
	 * @param platformUserDto
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editPlatformUser(@RequestBody PlatformUserDTO platformUserDto) {
		platformUserService.updatePlatformUser(platformUserDto);
	}

	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deletePlatformUser(@RequestParam(value = "userId", required = false) String userId){
		platformUserService.deletePlatformUser(Integer.parseInt(userId));
	}
	
}
