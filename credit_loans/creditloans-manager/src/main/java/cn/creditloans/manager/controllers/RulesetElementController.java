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

import cn.creditloans.core.dto.af.RulesetElementDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.BaseElementService;
import cn.creditloans.core.service.PlatformDepartmentService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.core.service.RulesetElementService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/rulesetelement")
public class RulesetElementController {
	private static final Log logger = LogFactory.getLog(RulesetElementController.class);
	
	@Autowired
	RulesetElementService rulesetElementService;
	
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
	public String getRulesetElementList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, RulesetElementDTO rulesetElementDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<RulesetElementDTO> rulesetElementPage = rulesetElementService.getRulesetElementPageList(rulesetElementDTO,
				currentPage, pageSize);
		model.addAttribute("rulesetElementPage", rulesetElementPage);
		model.addAttribute("queryDto", rulesetElementDTO);
		return "/regulation/basepackage/rulesetelement/regulation_rulesetelement_list";
	}
	
	/**
	 * 打开rulesetElement管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddRulesetElement(Model model){
		Map<String, Object> resultMap = rulesetElementService.getAllRelationEntity();
		model.addAllAttributes(resultMap);
		return "/regulation/basepackage/rulesetelement/regulation_rulesetelement_add";
	}
	
	
	/**
	 * 添加rulesetElement
	 * @param rulesetElementDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addRulesetElementId(@RequestBody RulesetElementDTO rulesetElementDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		rulesetElementDTO.setCreateUserId(platformUserDto.getId());
		rulesetElementDTO.setUpadteUserId(platformUserDto.getId());
		int rulesetElementId = rulesetElementService.saveRulesetElement(rulesetElementDTO);
		return rulesetElementId;
	}
	
	/**
	 * 打开rulesetElement管理修改的页面
	 * @param model rulesetElementId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditRulesetElement(Model model,@RequestParam(value = "rulesetElementId", required = false) String rulesetElementId){
		Map<String, Object> resultMap = rulesetElementService.getRulesetElementById(Integer.parseInt(rulesetElementId));
		model.addAllAttributes(resultMap);
		return "/regulation/basepackage/rulesetelement/regulation_rulesetelement_edit";
	}
	
	/**
	 * 修改rulesetElement
	 * @param rulesetElementDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editRulesetElement(@RequestBody RulesetElementDTO rulesetElementDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		rulesetElementDTO.setUpadteUserId(platformUserDto.getId());
		rulesetElementService.updateRulesetElement(rulesetElementDTO);
	}

	/**
	 * 删除rulesetElement
	 * @param rulesetElementId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int deleteRulesetElement(@RequestParam(value = "rulesetElementId", required = false) String rulesetElementId){
		int flag = rulesetElementService.deleteRulesetElement(Integer.parseInt(rulesetElementId));
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
			return rulesetElementService.checkNameIsExist(name);
		}
		return true;
	}
}
