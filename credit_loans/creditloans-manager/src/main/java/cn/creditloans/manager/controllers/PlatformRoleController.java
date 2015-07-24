package cn.creditloans.manager.controllers;

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

import cn.creditloans.core.dto.platform.PlatformRoleDTO;
import cn.creditloans.core.service.PlatformRoleService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/platform/role")
public class PlatformRoleController {
	private static final Log logger = LogFactory.getLog(PlatformRoleController.class);
	
	@Autowired
	PlatformRoleService platformRoleService;
	
	/**
	 * 打开角色管理的页面
	 * @param model
	 * @param _currentPage
	 * @param platformRoleDto
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String enterpriseRoleList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage,PlatformRoleDTO platformRoleDto) {
		int currentPage = 1;
		if (StringUtils.isNotEmpty(_currentPage)) {
			currentPage = Integer.parseInt(_currentPage.toString());
		}  
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<PlatformRoleDTO> platformRolePage = platformRoleService.getPlatformeRolePageList(platformRoleDto, currentPage, pageSize);
		model.addAttribute("platformRolePage", platformRolePage);
		model.addAttribute("queryDto", platformRoleDto);
		return "/platform/role/platform_role_list";
	}
	
	/**
	 * 打开角色管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddPlatformRole(Model model){
		return "/platform/role/platform_role_add";
	}
	
	/**
	 * 添加角色
	 * @param platformRoleDto
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addPlatformRole(@RequestBody PlatformRoleDTO platformRoleDto) {
		int platformRoleId = platformRoleService.saveCascadePlatformRoleAndMenu(platformRoleDto);
		return platformRoleId;
	}
	
	/**
	 * 打开角色管理修改的页面
	 * @param model roleId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditPlatformRole(Model model,@RequestParam(value = "roleId", required = false) String roleId){
		PlatformRoleDTO platformRoleDto = platformRoleService.getCascadePlatformRoleAndMenuByRoleId(Integer.parseInt(roleId));
		model.addAttribute("platformRoleDto", platformRoleDto);
		return "/platform/role/platform_role_edit";
	}
	
	/**
	 * 修改角色
	 * @param platformRoleDto
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editPlatformRole(@RequestBody PlatformRoleDTO platformRoleDto){
		platformRoleService.updatePlatformRole(platformRoleDto);
	}
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deletePlatformRole(@RequestParam(value = "roleId", required = false) String roleId){
		try {
			platformRoleService.deletePlatformRole(Integer.parseInt(roleId));
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			throw new RuntimeException(e);
		}
	}
}
