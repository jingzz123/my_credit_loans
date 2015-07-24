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

import cn.creditloans.core.dto.af.ValidatorElementDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.BaseElementService;
import cn.creditloans.core.service.PlatformDepartmentService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.core.service.ValidatorElementService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/validatorelement")
public class ValidatorElementController {
	private static final Log logger = LogFactory.getLog(ValidatorElementController.class);
	
	@Autowired
	ValidatorElementService validatorElementService;
	
	@Autowired
	PlatformDepartmentService platformDepartmentService;
	
	@Autowired
	PlatformUserService platformUserService;
	
	@Autowired
	BaseElementService baseElementService;
	
	/**
	 * 打开rulesetElement管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getValidatorElementList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, ValidatorElementDTO validatorElementDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<ValidatorElementDTO> validatorElementPage = validatorElementService.getValidatorElementPageList(validatorElementDTO,
				currentPage, pageSize);
		model.addAttribute("validatorElementPage", validatorElementPage);
		model.addAttribute("queryDto", validatorElementDTO);
		return "/regulation/basepackage/validatorelement/regulation_validatorelement_list";
	}
	
	/**
	 * 打开rulesetElement管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddValidatorElement(Model model){
		Map<String, Object> resultMap = validatorElementService.getAllInfos();
		model.addAllAttributes(resultMap);
		return "/regulation/basepackage/validatorelement/regulation_validatorelement_add";
	}
	
	
	/**
	 * 添加ValidatorElement
	 * @param ValidatorElementDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addValidatorElementId(@RequestBody ValidatorElementDTO validatorElementDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		validatorElementDTO.setCreateUserId(platformUserDto.getId());
		validatorElementDTO.setUpadteUserId(platformUserDto.getId());
		int validatorElementId = validatorElementService.saveValidatorElement(validatorElementDTO);
		return validatorElementId;
	}
	
	/**
	 * 打开validatorElement管理修改的页面
	 * @param model validatorElementId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditValidatorElement(Model model,@RequestParam(value = "validatorElementId", required = false) String validatorElementId){
		Map<String, Object> resultMap = validatorElementService.getValidatorElementById(Integer.parseInt(validatorElementId));
		model.addAllAttributes(resultMap);
		return "/regulation/basepackage/validatorelement/regulation_validatorelement_edit";
	}
	
	/**
	 * 修改validatorElement
	 * @param validatorElementDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editRulesetElement(@RequestBody ValidatorElementDTO validatorElementDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		validatorElementDTO.setUpadteUserId(platformUserDto.getId());
		validatorElementService.updateValidatorElement(validatorElementDTO);
	}

	/**
	 * 删除validatorElement
	 * @param validatorElementId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteValidatorElement(@RequestParam(value = "validatorElementId", required = false) String validatorElementId){
		validatorElementService.deleteValidatorElement(Integer.parseInt(validatorElementId));
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
			return validatorElementService.checkNameIsExist(name);
		}
		return true;
	}
}
