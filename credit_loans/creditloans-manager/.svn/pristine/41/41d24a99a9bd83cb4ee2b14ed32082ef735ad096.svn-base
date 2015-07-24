package cn.creditloans.manager.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import cn.creditloans.core.dto.af.BaseActionNameDTO;
import cn.creditloans.core.dto.af.BaseActionVariableDTO;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.BaseVariableSimpleDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.BaseActionVariableService;
import cn.creditloans.core.service.BaseElementService;
import cn.creditloans.core.service.BaseVariableSimpleService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/actionvariable")
public class BaseActionVariableController {
	private static final Log logger = LogFactory.getLog(BaseActionVariableController.class);
	
	@Autowired
	BaseActionVariableService baseActionVariableService;
	
	@Autowired
	BaseVariableSimpleService baseVariableSimpleService;
	@Autowired
	BaseElementService baseElementService;
	@Autowired
	PlatformUserService platformUserService;
	/**
	 * 打开actionName管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getBaseActionVariableList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, BaseActionVariableDTO baseActionVariableDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<BaseActionVariableDTO> baseActionVariablePage = baseActionVariableService.getBaseActionVariablePageList(baseActionVariableDTO,
				currentPage, pageSize);
		model.addAttribute("baseActionVariablePage", baseActionVariablePage);
		model.addAttribute("queryDto", baseActionVariableDTO);
		return "/regulation/basepackage/actionvariable/regulation_actionvariable_list";
	}
	
	/**
	 * 打开actionVariable管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddBaseActionVariable(Model model){
		Map<String, Object> resultMap = baseActionVariableService.getAllElementAndSimple();
		model.addAllAttributes(resultMap);
		return "/regulation/basepackage/actionvariable/regulation_actionvariable_add";
	}
	
	
	/**
	 * 添加actionName
	 * @param baseActionVariableDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addBaseActionVariableId(@RequestBody BaseActionVariableDTO baseActionVariableDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseActionVariableDTO.setCreateUserId(platformUserDto.getId());
		baseActionVariableDTO.setUpadteUserId(platformUserDto.getId());
		int baseActionVariableId = baseActionVariableService.saveBaseActionVariable(baseActionVariableDTO);
		return baseActionVariableId;
	}
	
	/**
	 * 打开actionVariable管理修改的页面
	 * @param model actionVariableId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditBaseActionVariable(Model model,@RequestParam(value = "actionVariableId", required = false) String actionVariableId){
		Map<String, Object> resultMap = baseActionVariableService.getBaseActionVariableById(Integer.parseInt(actionVariableId));
		model.addAllAttributes(resultMap);
		return "/regulation/basepackage/actionvariable/regulation_actionvariable_edit";
	}

	/**
	 * 修改actionVariable
	 * @param baseActionVariableDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editBaseActionVariable(@RequestBody BaseActionVariableDTO baseActionVariableDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseActionVariableDTO.setUpadteUserId(platformUserDto.getId());
		baseActionVariableService.updateBaseActionVariable(baseActionVariableDTO);
	}

	/**
	 * 删除actionVariable
	 * @param actionVariableId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteBaseActionVariable(@RequestParam(value = "actionVariableId", required = false) String actionVariableId){
		baseActionVariableService.deleteBaseActionVariable(Integer.parseInt(actionVariableId));
	}
	/**
	 * 检查name是否存在
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "checkActionVariable" , method = {RequestMethod.GET , RequestMethod.POST})
	public @ResponseBody boolean checkActionVariable(@RequestParam("name") String name,@RequestParam("oldName") String oldName){
		if(name.equals(oldName)) {
			return true;
		}
		if(!StringUtils.isEmpty(name)){
			return baseActionVariableService.checkNameIsExist(name);
		}
		return true;
	}
}
