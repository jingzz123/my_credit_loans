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
import cn.creditloans.core.dto.af.RulesetConditionDTO;
import cn.creditloans.core.dto.af.RulesetRuleDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.entity.af.BaseActionName;
import cn.creditloans.core.service.BaseActionNameService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.core.service.RulesetConditionService;
import cn.creditloans.core.service.RulesetRuleService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/rule")
public class RulesetRuleController {
	private static final Log logger = LogFactory.getLog(RulesetRuleController.class);
	@Autowired
	RulesetRuleService rulesetRuleService;
	
	@Autowired
	PlatformUserService platformUserService;
	@Autowired
	RulesetConditionService rulesetConditionService;
	@Autowired
	BaseActionNameService baseActionNameService;
	
	/**
	 * 打开rule管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getRulesetRuleList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, RulesetRuleDTO rulesetRuleDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<RulesetRuleDTO> rulesetRulePage = rulesetRuleService.getRulesetRulePageList(rulesetRuleDTO,
				currentPage, pageSize);
		model.addAttribute("rulesetRulePage", rulesetRulePage);
		model.addAttribute("queryDto", rulesetRuleDTO);
		return "/regulation/basepackage/rule/regulation_rule_list";
	}
	
	/**
	 * 打开Rule管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddRulesetCondition(Model model, @RequestParam(value="rulesetId", required=false) String rulesetId){
		Map<String, Object> resultMap = rulesetRuleService.getAllRelationEntity();
		model.addAllAttributes(resultMap);
		model.addAttribute("rulesetId", rulesetId);
		return "/regulation/basepackage/rule/regulation_rule_add";
	}
	
	
	/**
	 * 添加rule
	 * @param rulesetRuleDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addRulesetRuleId(@RequestBody RulesetRuleDTO rulesetRuleDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		rulesetRuleDTO.setCreateUserId(platformUserDto.getId());
		rulesetRuleDTO.setUpadteUserId(platformUserDto.getId());
		int rulesetRuleId = rulesetRuleService.saveRulesetRule(rulesetRuleDTO);
		return rulesetRuleId;
	}
	
	/**
	 * 打开Rule管理修改的页面
	 * @param model ruleId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditRulesetRule(Model model,@RequestParam(value = "ruleId", required = false) String ruleId){
		Map<String, Object> rulesetRuleMap = rulesetRuleService.getRulesetRuleById(Integer.parseInt(ruleId));
		model.addAllAttributes(rulesetRuleMap);
		return "/regulation/basepackage/rule/regulation_rule_edit";
	}
	
	/**
	 * 修改Rule
	 * @param rulesetRuleDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editRulesetRule(@RequestBody RulesetRuleDTO rulesetRuleDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		rulesetRuleDTO.setUpadteUserId(platformUserDto.getId());
		rulesetRuleService.updateRulesetRule(rulesetRuleDTO);
	}

	/**
	 * 删除Rule
	 * @param ruleId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteRulesetRule(@RequestParam(value = "ruleId", required = false) String ruleId){
		rulesetRuleService.deleteRulesetRule(Integer.parseInt(ruleId));
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
			return rulesetRuleService.checkNameIsExist(name);
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
			return rulesetRuleService.checkSequenceIsExist(Integer.parseInt(sequence));
		}
		return true;
	}
	
}
