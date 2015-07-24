package cn.creditloans.core.service.impl.af;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.af.BaseActionNameDao;
import cn.creditloans.core.dao.af.RulesetConditionDao;
import cn.creditloans.core.dao.af.RulesetRuleDao;
import cn.creditloans.core.dto.af.BaseActionNameDTO;
import cn.creditloans.core.dto.af.MetadataSchemaDTO;
import cn.creditloans.core.dto.af.RulesetConditionDTO;
import cn.creditloans.core.dto.af.RulesetRuleDTO;
import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.entity.af.BaseActionName;
import cn.creditloans.core.entity.af.MetadataSchema;
import cn.creditloans.core.entity.af.RulesetCondition;
import cn.creditloans.core.entity.af.RulesetRule;
import cn.creditloans.core.entity.platform.PlatformDepartment;
import cn.creditloans.core.service.RulesetRuleService;
import cn.creditloans.tools.page.PageModel;
@Service("rulesetRuleServie")
public class RulesetRuleServiceImpl implements RulesetRuleService {
	private static final Log logger = LogFactory.getLog(RulesetRuleServiceImpl.class);
	@Autowired
	RulesetRuleDao rulesetRuleDao;
	@Autowired
	RulesetConditionDao rulesetConditionDao;
	@Autowired
	BaseActionNameDao baseActionNameDao;
	
	@Override
	public PageModel<RulesetRuleDTO> getRulesetRulePageList(
			RulesetRuleDTO rulesetRuleDTO, int currentPage, int pageSize) {
		RulesetRule rulesetRule = new RulesetRule();
		BeanUtils.copyProperties(rulesetRuleDTO, rulesetRule);
		
		PageModel<RulesetRuleDTO> pm = new PageModel<RulesetRuleDTO>();
		pm.init(currentPage, pageSize);
		int count = rulesetRuleDao.selectPageCount(rulesetRule);
		List<RulesetRule> rulesetRuleList = rulesetRuleDao.selectPageList(rulesetRule, pm);
		List<RulesetRuleDTO> rulesetRuleDtoList = new ArrayList<RulesetRuleDTO>();
		for(RulesetRule _rulesetRule : rulesetRuleList){
			RulesetRuleDTO _rulesetRuleDTO = new RulesetRuleDTO();
			BeanUtils.copyProperties(_rulesetRule, _rulesetRuleDTO);
			rulesetRuleDtoList.add(_rulesetRuleDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(rulesetRuleDtoList);
		return pm;
	}

	@Override
	public int saveRulesetRule(RulesetRuleDTO rulesetRuleDTO) {
		RulesetRule rulesetRule = new RulesetRule();
		BeanUtils.copyProperties(rulesetRuleDTO, rulesetRule);
		List<String> conditionList = rulesetRuleDTO.getConditionList();
		StringBuffer conditionBuffer = new StringBuffer();
		for(String condition : conditionList) {
			conditionBuffer.append(condition+"|");
		}
		String conditions = conditionBuffer.toString().substring(0,conditionBuffer.toString().length()-1);
		rulesetRule.setConditions(conditions);
		List<String> actionList = rulesetRuleDTO.getActionNameList();
		StringBuffer actionBuffer = new StringBuffer();
		for(String action : actionList) {
			actionBuffer.append(action+"|");
		}
		String actions = actionBuffer.toString().substring(0,actionBuffer.toString().length()-1);
		rulesetRule.setActions(actions);
		
		rulesetRuleDao.insert(rulesetRule);
		int ruleId = rulesetRule.getId();
		return ruleId;
	}

	@Override
	public Map<String, Object> getRulesetRuleById(int ruleId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		RulesetRuleDTO rulesetRuleDTO = new RulesetRuleDTO();
		RulesetRule rulesetRule = rulesetRuleDao.select(ruleId); 
		
		BeanUtils.copyProperties(rulesetRule, rulesetRuleDTO);
		String conditions = rulesetRule.getConditions();
		String[] conditionArr = conditions.split("[|]");
		List<String> conditionList = Arrays.asList(conditionArr);
		rulesetRuleDTO.setConditionList(conditionList);
		String actions = rulesetRule.getActions();
		String[] actionArr = actions.split("[|]");
		List<String> actionNameList = Arrays.asList(actionArr);
		rulesetRuleDTO.setActionNameList(actionNameList);
		rulesetRuleDTO.setOldName(rulesetRuleDTO.getName());
		rulesetRuleDTO.setOldSequence(rulesetRuleDTO.getSequence());
		List<BaseActionName> baseActionNameList = baseActionNameDao.selectActionNotRule(actionNameList);
		List<BaseActionNameDTO> rulesetActionDTOList = new ArrayList<BaseActionNameDTO>();
		for(BaseActionName baseActionName : baseActionNameList){
			BaseActionNameDTO baseActionNameDTO = new BaseActionNameDTO();
			BeanUtils.copyProperties(baseActionName, baseActionNameDTO);
			rulesetActionDTOList.add(baseActionNameDTO);
		}
		List<RulesetCondition> rulesetConditionList = rulesetConditionDao.selectConditionNotRule(conditionList);
		List<RulesetConditionDTO> rulesetConditionDTOList = new ArrayList<RulesetConditionDTO>();
		for(RulesetCondition rulesetCondition : rulesetConditionList){
			RulesetConditionDTO rulesetConditionDTO = new RulesetConditionDTO();
			BeanUtils.copyProperties(rulesetCondition, rulesetConditionDTO);
			rulesetConditionDTOList.add(rulesetConditionDTO);
		}
		resultMap.put("rulesetActionDTOList", rulesetActionDTOList);
		resultMap.put("rulesetConditionDTOList", rulesetConditionDTOList);
		resultMap.put("rulesetRuleDTO", rulesetRuleDTO);
		return resultMap;
	}

	@Override
	public void updateRulesetRule(RulesetRuleDTO rulesetRuleDTO) {
		RulesetRule rulesetRule = new RulesetRule();
		BeanUtils.copyProperties(rulesetRuleDTO, rulesetRule);
		List<String> conditionList = rulesetRuleDTO.getConditionList();
		StringBuffer conditionBuffer = new StringBuffer();
		for(String condition : conditionList) {
			conditionBuffer.append(condition+"|");
		}
		String conditions = conditionBuffer.toString().substring(0,conditionBuffer.toString().length()-1);
		rulesetRule.setConditions(conditions);
		List<String> actionList = rulesetRuleDTO.getActionNameList();
		StringBuffer actionBuffer = new StringBuffer();
		for(String action : actionList) {
			actionBuffer.append(action+"|");
		}
		String actions = actionBuffer.toString().substring(0,actionBuffer.toString().length()-1);
		rulesetRule.setActions(actions);
		rulesetRuleDao.update(rulesetRule);
	}

	@Override
	public void deleteRulesetRule(int ruleId) {
		rulesetRuleDao.delete(ruleId);
	}

	@Override
	public List<RulesetRuleDTO> getAllInfos() {
		List<RulesetRule> rulesetRuleList = rulesetRuleDao.selectAllInfos();
		List<RulesetRuleDTO> rulesetRuleDTOList = new ArrayList<RulesetRuleDTO>();
		for(RulesetRule _rulesetRule : rulesetRuleList){
			RulesetRuleDTO _rulesetRuleDTO = new RulesetRuleDTO();
			BeanUtils.copyProperties(_rulesetRule, _rulesetRuleDTO);
			rulesetRuleDTOList.add(_rulesetRuleDTO);
		}
		return rulesetRuleDTOList;
	}

	@Override
	public boolean checkNameIsExist(String name) {
		int countName = rulesetRuleDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}
	@Override
	public boolean checkSequenceIsExist(int sequence) {
		int countName = rulesetRuleDao.selectSequenceIsExit(sequence);
		if(countName > 0){
			return false;
		}
		return true;
	}
	
	@Override
	public Map<String, Object> getAllRelationEntity() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<RulesetCondition> rulesetConditionList = rulesetConditionDao.selectAllInfos();
		List<RulesetConditionDTO> rulesetConditionDTOList = new  ArrayList<RulesetConditionDTO>();
		for(RulesetCondition rulesetCondition : rulesetConditionList){
			RulesetConditionDTO rulesetConditionDTO = new RulesetConditionDTO();
			BeanUtils.copyProperties(rulesetCondition, rulesetConditionDTO);
			rulesetConditionDTOList.add(rulesetConditionDTO);
		}
		resultMap.put("rulesetConditionDTOList", rulesetConditionDTOList);
		List<BaseActionName> baseActionNameList = baseActionNameDao.selectAllInfos();
		List<BaseActionNameDTO> baseActionNameDTOList = new  ArrayList<BaseActionNameDTO>();
		for(BaseActionName baseActionName : baseActionNameList){
			BaseActionNameDTO baseActionNameDTO = new BaseActionNameDTO();
			BeanUtils.copyProperties(baseActionName, baseActionNameDTO);
			baseActionNameDTOList.add(baseActionNameDTO);
		}
		resultMap.put("baseActionNameDTOList", baseActionNameDTOList);
		return resultMap;
	}
}
