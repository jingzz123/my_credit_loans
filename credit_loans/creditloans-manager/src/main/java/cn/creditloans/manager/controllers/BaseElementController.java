package cn.creditloans.manager.controllers;

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

import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.BaseElementService;
import cn.creditloans.core.service.PlatformDepartmentService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/element")
public class BaseElementController {
	private static final Log logger = LogFactory.getLog(BaseElementController.class);
	
	@Autowired
	BaseElementService baseElementService;
	
	@Autowired
	PlatformDepartmentService platformDepartmentService;
	
	@Autowired
	PlatformUserService platformUserService;
	
	/**
	 * 打开element管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getBaseElementList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, BaseElementDTO baseElementDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<BaseElementDTO> baseElementPage = baseElementService.getBaseElementPageList(baseElementDTO,
				currentPage, pageSize);
		model.addAttribute("baseElementPage", baseElementPage);
		model.addAttribute("queryDto", baseElementDTO);
		return "/regulation/basepackage/element/regulation_element_list";
	}
	
	/**
	 * 打开element管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddBaseElement(Model model){
		Map<String, Object> resultMap = baseElementService.getAllRelationEntity();
		model.addAllAttributes(resultMap);
		return "/regulation/basepackage/element/regulation_element_add";
	}
	
	
	/**
	 * 添加element
	 * @param baseElementDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addBaseElementId(@RequestBody BaseElementDTO baseElementDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseElementDTO.setCreateUserId(platformUserDto.getId());
		baseElementDTO.setUpadteUserId(platformUserDto.getId());
		int baseElementId = baseElementService.saveBaseElement(baseElementDTO);
		return baseElementId;
	}
	
	/**
	 * 打开element管理修改的页面
	 * @param model elementId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditBaseElement(Model model,@RequestParam(value = "elementId", required = false) String elementId){
		Map<String, Object> resultMap = baseElementService.getBaseElementById(Integer.parseInt(elementId));
		model.addAllAttributes(resultMap);
		return "/regulation/basepackage/element/regulation_element_edit";
	}
	
	/**
	 * 修改element
	 * @param baseElementDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editBaseElement(@RequestBody BaseElementDTO baseElementDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseElementDTO.setUpadteUserId(platformUserDto.getId());
		baseElementService.updateBaseElement(baseElementDTO);
	}

	/**
	 * 删除element
	 * @param elementId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int deleteBaseElement(@RequestParam(value = "elementId", required = false) String elementId){
		int flag = baseElementService.deleteBaseElement(Integer.parseInt(elementId));
		return flag;
	}
	/**
	 * 检查name是否存在
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "checkName" , method = {RequestMethod.GET , RequestMethod.POST})
	public @ResponseBody boolean checkName(@RequestParam("name") String name,@RequestParam("oldName") String oldName){
		if(name.equals(oldName)) {
			return true;
		}
		if(!StringUtils.isEmpty(name)){
			return baseElementService.checkNameIsExist(name);
		}
		return true;
	}
}
