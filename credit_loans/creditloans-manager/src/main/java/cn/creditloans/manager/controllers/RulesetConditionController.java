package cn.creditloans.manager.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import cn.creditloans.core.dto.af.BaseVariableExpressionDTO;
import cn.creditloans.core.dto.af.BaseVariableSimpleDTO;
import cn.creditloans.core.dto.af.BaseVariableSituationDTO;
import cn.creditloans.core.dto.af.BaseVariableTransactionDTO;
import cn.creditloans.core.dto.af.RulesetConditionDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.BaseVariableExpressionService;
import cn.creditloans.core.service.BaseVariableSimpleService;
import cn.creditloans.core.service.BaseVariableSituationService;
import cn.creditloans.core.service.BaseVariableTransactionService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.core.service.RulesetConditionService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/condition")
public class RulesetConditionController {
	private static final Log logger = LogFactory.getLog(RulesetConditionController.class);
	@Autowired
	RulesetConditionService rulesetConditionService;
	
	@Autowired
	PlatformUserService platformUserService;
	/**
	 * 打开condition管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getRulesetConditionList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, RulesetConditionDTO rulesetConditionDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<RulesetConditionDTO> rulesetConditionPage = rulesetConditionService.getRulesetConditionPageList(rulesetConditionDTO,
				currentPage, pageSize);
		model.addAttribute("rulesetConditionPage", rulesetConditionPage);
		model.addAttribute("queryDto", rulesetConditionDTO);
		return "/regulation/basepackage/condition/regulation_condition_list";
	}
	
	/**
	 * 打开condition管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddRulesetCondition(Model model, @RequestParam(value="rulesetId", required=false) String rulesetId){
		model.addAttribute("rulesetId", rulesetId);
		return "/regulation/basepackage/condition/regulation_condition_add";
	}
	
	
	/**
	 * 添加condition
	 * @param metadataFieldDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addRulesetConditionId(@RequestBody RulesetConditionDTO rulesetConditionDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		rulesetConditionDTO.setCreateUserId(platformUserDto.getId());
		rulesetConditionDTO.setUpadteUserId(platformUserDto.getId());
		int rulesetConditionId = rulesetConditionService.saveRulesetCondition(rulesetConditionDTO);
		return rulesetConditionId;
	}
	
	/**
	 * 打开condition管理修改的页面
	 * @param model conditionId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditRulesetCondition(Model model,@RequestParam(value = "conditionId", required = false) String conditionId){
		RulesetConditionDTO rulesetConditionDTO = rulesetConditionService.getRulesetConditionById(Integer.parseInt(conditionId));
		model.addAttribute("rulesetConditionDTO", rulesetConditionDTO);
		return "/regulation/basepackage/condition/regulation_condition_edit";
	}
	
	/**
	 * 修改condition
	 * @param rulesetConditionDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editRulesetCondition(@RequestBody RulesetConditionDTO rulesetConditionDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		rulesetConditionDTO.setUpadteUserId(platformUserDto.getId());
		rulesetConditionService.updateRulesetCondition(rulesetConditionDTO);
	}

	/**
	 * 删除condition
	 * @param conditionId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteRulesetCondition(@RequestParam(value = "conditionId", required = false) String conditionId){
		rulesetConditionService.deleteRulesetCondition(Integer.parseInt(conditionId));
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
			return rulesetConditionService.checkNameIsExist(name);
		}
		return true;
	}
	/**
	 * 检查sequence是否存在
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "checkSequence" , method = {RequestMethod.GET , RequestMethod.POST})
	public @ResponseBody boolean checkSequence(@RequestParam("sequence") String sequence,@RequestParam("oldSequence") String oldSequence){
		if(sequence.equals(oldSequence)) {
			return true;
		}
		if(!StringUtils.isEmpty(sequence)){
			return rulesetConditionService.checkSequenceIsExist(Integer.parseInt(sequence));
		}
		return true;
	}
	
	@RequestMapping(value = "getVariable" , method = {RequestMethod.GET , RequestMethod.POST})
	public @ResponseBody Map<Integer, String> getVariable(@RequestParam("name") String name, HttpServletResponse response){
		return rulesetConditionService.getVariable(name);
	}
}
