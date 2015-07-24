package cn.creditloans.core.service.impl.af;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.af.BaseVariableExpressionDao;
import cn.creditloans.core.dao.af.BaseVariableSimpleDao;
import cn.creditloans.core.dao.af.BaseVariableSituationDao;
import cn.creditloans.core.dao.af.BaseVariableTransactionDao;
import cn.creditloans.core.dao.af.RulesetConditionDao;
import cn.creditloans.core.dto.af.RulesetConditionDTO;
import cn.creditloans.core.entity.af.BaseVariableExpression;
import cn.creditloans.core.entity.af.BaseVariableSimple;
import cn.creditloans.core.entity.af.BaseVariableSituation;
import cn.creditloans.core.entity.af.BaseVariableTransaction;
import cn.creditloans.core.entity.af.RulesetCondition;
import cn.creditloans.core.service.RulesetConditionService;
import cn.creditloans.tools.page.PageModel;
@Service("rulesetConditionServie")
public class RulesetConditionServiceImpl implements RulesetConditionService {
	private static final Log logger = LogFactory.getLog(RulesetConditionServiceImpl.class);
	@Autowired
	RulesetConditionDao rulesetConditionDao;
	@Autowired
	BaseVariableSimpleDao baseVariableSimpleDao;
	@Autowired
	BaseVariableTransactionDao baseVariableTransactionDao;
	@Autowired
	BaseVariableSituationDao baseVariableSituationDao;
	@Autowired
	BaseVariableExpressionDao baseVariableExpressionDao;
	@Override
	public PageModel<RulesetConditionDTO> getRulesetConditionPageList(
			RulesetConditionDTO rulesetConditionDTO, int currentPage, int pageSize) {
		RulesetCondition rulesetCondition = new RulesetCondition();
		BeanUtils.copyProperties(rulesetConditionDTO, rulesetCondition);
		
		PageModel<RulesetConditionDTO> pm = new PageModel<RulesetConditionDTO>();
		pm.init(currentPage, pageSize);
		int count = rulesetConditionDao.selectPageCount(rulesetCondition);
		List<RulesetCondition> rulesetConditionList = rulesetConditionDao.selectPageList(rulesetCondition, pm);
		List<RulesetConditionDTO> rulesetConditionDtoList = new ArrayList<RulesetConditionDTO>();
		for(RulesetCondition _rulesetCondition : rulesetConditionList){
			RulesetConditionDTO _rulesetConditionDTO = new RulesetConditionDTO();
			BeanUtils.copyProperties(_rulesetCondition, _rulesetConditionDTO);
			rulesetConditionDtoList.add(_rulesetConditionDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(rulesetConditionDtoList);
		return pm;
	}

	@Override
	public int saveRulesetCondition(RulesetConditionDTO rulesetConditionDTO) {
		RulesetCondition rulesetCondition = new RulesetCondition();
		BeanUtils.copyProperties(rulesetConditionDTO, rulesetCondition);
		rulesetConditionDao.insert(rulesetCondition);
		int conditionId = rulesetCondition.getId();
		return conditionId;
	}

	@Override
	public RulesetConditionDTO getRulesetConditionById(int conditionId) {
		RulesetConditionDTO rulesetConditionDTO = new RulesetConditionDTO();
		RulesetCondition rulesetCondition = rulesetConditionDao.select(conditionId);
		BeanUtils.copyProperties(rulesetCondition, rulesetConditionDTO);
		rulesetConditionDTO.setOldName(rulesetConditionDTO.getName());
		rulesetConditionDTO.setOldSequence(rulesetConditionDTO.getSequence());
		return rulesetConditionDTO;
	}

	@Override
	public void updateRulesetCondition(RulesetConditionDTO rulesetConditionDTO) {
		RulesetCondition rulesetCondition = new RulesetCondition();
		BeanUtils.copyProperties(rulesetConditionDTO, rulesetCondition);
		rulesetConditionDao.update(rulesetCondition);
	}

	@Override
	public void deleteRulesetCondition(int conditionId) {
		rulesetConditionDao.delete(conditionId);
	}

	@Override
	public List<RulesetConditionDTO> getAllInfos() {
		List<RulesetCondition> rulesetConditionList = rulesetConditionDao.selectAllInfos();
		List<RulesetConditionDTO> rulesetConditionDTOList = new ArrayList<RulesetConditionDTO>();
		for(RulesetCondition _rulesetCondition : rulesetConditionList){
			RulesetConditionDTO _rulesetConditionDTO = new RulesetConditionDTO();
			BeanUtils.copyProperties(_rulesetCondition, _rulesetConditionDTO);
			rulesetConditionDTOList.add(_rulesetConditionDTO);
		}
		return rulesetConditionDTOList;
	}

	@Override
	public boolean checkNameIsExist(String name) {
		int countName = rulesetConditionDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}
	@Override
	public boolean checkSequenceIsExist(int sequence) {
		int countName = rulesetConditionDao.selectSequenceIsExit(sequence);
		if(countName > 0){
			return false;
		}
		return true;
	}
	public Map<Integer, String> getVariable(String name) {
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		int i = 0;
		List<BaseVariableSimple> baseVariableSimpleList = baseVariableSimpleDao.selectAllByName(name);
		for(BaseVariableSimple baseVariableSimple : baseVariableSimpleList) {
			resultMap.put(i, baseVariableSimple.getName());
			i++;
		}
		List<BaseVariableTransaction> baseVariableTransactionList = baseVariableTransactionDao.selectAllByName(name);
		for(BaseVariableTransaction baseVariableTransaction : baseVariableTransactionList) {
			resultMap.put(i, baseVariableTransaction.getName());
			i++;
		}
		List<BaseVariableSituation> baseVariableSituationList = baseVariableSituationDao.selectAllByName(name);
		for(BaseVariableSituation baseVariableSituation : baseVariableSituationList) {
			resultMap.put(i, baseVariableSituation.getName());
			i++;
		}
		List<BaseVariableExpression> baseVariableExpressionList = baseVariableExpressionDao.selectAllByName(name);
		for(BaseVariableExpression baseVariableExpression : baseVariableExpressionList) {
			resultMap.put(i, baseVariableExpression.getName());
			i++;
		}
		return resultMap;
	}
}
