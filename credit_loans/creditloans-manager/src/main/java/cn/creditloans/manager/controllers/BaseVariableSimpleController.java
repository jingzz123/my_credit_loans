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

import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.BaseVariableSimpleDTO;
import cn.creditloans.core.dto.af.MetadataSchemaDTO;
import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.BaseElementService;
import cn.creditloans.core.service.BaseVariableSimpleService;
import cn.creditloans.core.service.MetadataSchemaService;
import cn.creditloans.core.service.PlatformDepartmentService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/variablesimple")
public class BaseVariableSimpleController {
	private static final Log logger = LogFactory.getLog(BaseVariableSimpleController.class);
	
	@Autowired
	BaseVariableSimpleService baseVariableSimpleService;
	
	@Autowired
	PlatformUserService platformUserService;
	@Autowired
	BaseElementService baseElementService;
	/**
	 * 
	* 打开variable管理的页面
	* @param 
	* @return
	 */
	@RequestMapping(value = "/showAllList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getVariableList(){
		return "/regulation/basepackage/variablesimple/regulation_variable_list";
	}
	/**
	 * 打开variablesimple管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getBaseVariableSimpleList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, BaseVariableSimpleDTO baseVariableSimpleDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<BaseVariableSimpleDTO> baseVariableSimplePage = baseVariableSimpleService.getBaseVariableSimplePageList(baseVariableSimpleDTO,
				currentPage, pageSize);
		model.addAttribute("baseVariableSimplePage", baseVariableSimplePage);
		model.addAttribute("queryDto", baseVariableSimpleDTO);
		return "/regulation/basepackage/variablesimple/regulation_variablesimple_list";
	}
	
	/**
	 * 打开variablesimple管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddBaseVariableSimple(Model model){
		List<BaseElementDTO> baseElementDTOList = baseElementService.selectAllInfos();
		model.addAttribute("baseElementDTOList", baseElementDTOList);
		return "/regulation/basepackage/variablesimple/regulation_variablesimple_add";
	}
	
	
	/**
	 * 添加VariableSimple
	 * @param baseVariableSimpleDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addBaseVariableSimpleId(@RequestBody BaseVariableSimpleDTO baseVariableSimpleDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseVariableSimpleDTO.setCreateUserId(platformUserDto.getId());
		baseVariableSimpleDTO.setUpadteUserId(platformUserDto.getId());
		int baseVariableSimpleId = baseVariableSimpleService.saveBaseVariableSimple(baseVariableSimpleDTO);
		return baseVariableSimpleId;
	}
	
	/**
	 * 打开VariableSimple管理修改的页面
	 * @param model variableSimpleId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditBaseVariableSimple(Model model,@RequestParam(value = "variableSimpleId", required = false) String variableSimpleId){
		BaseVariableSimpleDTO baseVariableSimpleDTO = baseVariableSimpleService.getBaseVariableSimpleById(Integer.parseInt(variableSimpleId));
		model.addAttribute("baseVariableSimpleDTO", baseVariableSimpleDTO);
		return "/regulation/basepackage/variablesimple/regulation_variablesimple_edit";
	}
	
	/**
	 * 修改VariableSimple
	 * @param baseVariableSimpleDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editBaseVariableSimple(@RequestBody BaseVariableSimpleDTO baseVariableSimpleDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseVariableSimpleDTO.setUpadteUserId(platformUserDto.getId());
		baseVariableSimpleService.updateBaseVariableSimple(baseVariableSimpleDTO);
	}

	/**
	 * 删除VariableSimple
	 * @param variableSimpleId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteBaseVariableSimple(@RequestParam(value = "variableSimpleId", required = false) String variableSimpleId){
		baseVariableSimpleService.deleteBaseVariableSimple(Integer.parseInt(variableSimpleId));
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
			return baseVariableSimpleService.checkNameIsExist(name);
		}
		return true;
	}
	
}
