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

import cn.creditloans.core.dto.enterprise.EnterpriseDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseRoleDTO;
import cn.creditloans.core.service.EnterpriseRoleService;
import cn.creditloans.core.service.EnterpriseService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/enterprise/role")
public class EnterpriseRoleController {
	private static final Log logger = LogFactory.getLog(EnterpriseRoleController.class);
	
	@Autowired
	EnterpriseRoleService enterpriseRoleService;
	
	@Autowired
	EnterpriseService enterpriseService;
	
	/**
	 * 打开角色管理的页面
	 * @param model
	 * @param _currentPage
	 * @param enterpriseRoleDto
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String enterpriseRoleList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, EnterpriseRoleDTO enterpriseRoleDto) {
		enterpriseRoleDto.setType(0);
		int currentPage = 1;
		if (StringUtils.isNotEmpty(_currentPage)) {
			currentPage = Integer.parseInt(_currentPage.toString());
		}  
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<EnterpriseRoleDTO> enterpriseRolePage = enterpriseRoleService.getEnterpriseRolePageList(enterpriseRoleDto, currentPage, pageSize);
		model.addAttribute("enterpriseRolePage", enterpriseRolePage);
		model.addAttribute("queryDto", enterpriseRoleDto);
		return "/enterprise/role/enterprise_role_list";
	}
	
	/**
	 * 打开角色管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddEnterpriseRole(Model model){
		//List<EnterpriseDTO> enterpriseDtoList = enterpriseService.getAllEnterpriseDtoList(); 
		//model.addAttribute("enterpriseDtoList", enterpriseDtoList);
		return "/enterprise/role/enterprise_role_add";
	}
	
	/**
	 * 添加角色
	 * @param enterpriseRoleDto
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addEnterpriseRole(@RequestBody EnterpriseRoleDTO enterpriseRoleDto) {
		enterpriseRoleDto.setType(0);
		int enterpriseRoleId = enterpriseRoleService.saveCascadeEnterpriseRoleAndMenu(enterpriseRoleDto);
		return enterpriseRoleId;
	}
	
	/**
	 * 打开角色管理修改的页面
	 * @param model roleId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditEnterpriseRole(Model model,@RequestParam(value = "roleId", required = false) String roleId){
		EnterpriseRoleDTO enterpriseRoleDto = enterpriseRoleService.getCascadeEnterpriseRoleAndMenuByRoleId(Integer.parseInt(roleId));
		model.addAttribute("enterpriseRoleDto", enterpriseRoleDto);
		//List<EnterpriseDTO> enterpriseDtoList = enterpriseService.getAllEnterpriseDtoList();
		//model.addAttribute("enterpriseDtoList", enterpriseDtoList);
		return "/enterprise/role/enterprise_role_edit";
	}
	
	/**
	 * 修改角色
	 * @param enterpriseRoleDto
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editEnterpriseRole(@RequestBody EnterpriseRoleDTO enterpriseRoleDto){
		enterpriseRoleService.updateEnterpriseRole(enterpriseRoleDto);
	}
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteEnterpriseRole(@RequestParam(value = "roleId", required = false) String roleId){
		try {
			enterpriseRoleService.deleteEnterpriseRole(Integer.parseInt(roleId));
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			throw new RuntimeException(e);
		}
	}
}
