package cn.creditloans.manager.controllers;

import java.util.List;

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
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.BaseActionNameService;
import cn.creditloans.core.service.BaseElementService;
import cn.creditloans.core.service.PlatformDepartmentService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/actionname")
public class BaseActionNameController {
	private static final Log logger = LogFactory.getLog(BaseActionNameController.class);
	
	@Autowired
	BaseActionNameService baseActionNameService;
	
	@Autowired
	PlatformUserService platformUserService;
	
	@Autowired
	BaseElementService baseElementService;
	
	
	/**
	 * 
	* 打开action管理的页面
	* @param 
	* @return
	 */
	@RequestMapping(value = "/showAllList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getActionList(){
		return "/regulation/basepackage/actionname/regulation_action_list";
	}
	
	/**
	 * 打开actionName管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getBaseActionList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, BaseActionNameDTO baseActionNameDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<BaseActionNameDTO> baseActionNamePage = baseActionNameService.getBaseActionNamePageList(baseActionNameDTO,
				currentPage, pageSize);
		model.addAttribute("baseActionNamePage", baseActionNamePage);
		model.addAttribute("queryDto", baseActionNameDTO);
		return "/regulation/basepackage/actionname/regulation_actionname_list";
	}
	
	/**
	 * 打开actionName管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddBaseActionName(Model model){
		List<BaseElementDTO> baseElementDTOList = baseElementService.selectAllInfos();
		model.addAttribute("baseElementDTOList", baseElementDTOList);
		return "/regulation/basepackage/actionname/regulation_actionname_add";
	}
	
	
	/**
	 * 添加actionName
	 * @param baseActionNameDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addBaseActionNameId(@RequestBody BaseActionNameDTO baseActionNameDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseActionNameDTO.setCreateUserId(platformUserDto.getId());
		baseActionNameDTO.setUpadteUserId(platformUserDto.getId());
		int baseActionNameId = baseActionNameService.saveBaseActionName(baseActionNameDTO);
		return baseActionNameId;
	}
	
	/**
	 * 打开actionName管理修改的页面
	 * @param model actionNameId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditBaseActionName(Model model,@RequestParam(value = "actionNameId", required = false) String actionNameId){
		BaseActionNameDTO baseActionNameDTO = baseActionNameService.getBaseActionNameById(Integer.parseInt(actionNameId));
		model.addAttribute("baseActionNameDTO", baseActionNameDTO);
		return "/regulation/basepackage/actionname/regulation_actionname_edit";
	}
	
	/**
	 * 修改actionName
	 * @param baseActionNameDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editBaseActionName(@RequestBody BaseActionNameDTO baseActionNameDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseActionNameDTO.setUpadteUserId(platformUserDto.getId());
		baseActionNameService.updateBaseActionName(baseActionNameDTO);
	}

	/**
	 * 删除actionName
	 * @param actionNameId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteBaseActionName(@RequestParam(value = "actionNameId", required = false) String actionNameId){
		baseActionNameService.deleteBaseActionName(Integer.parseInt(actionNameId));
	}
	
	/**
	 * 检查name是否存在
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "checkActionName" , method = {RequestMethod.GET , RequestMethod.POST})
	public @ResponseBody boolean checkActionName(@RequestParam("name") String name,@RequestParam("oldName") String oldName){
		if(name.equals(oldName)) {
			return true;
		}
		if(!StringUtils.isEmpty(name)){
			return baseActionNameService.checkNameIsExist(name);
		}
		return true;
	}
	
}
