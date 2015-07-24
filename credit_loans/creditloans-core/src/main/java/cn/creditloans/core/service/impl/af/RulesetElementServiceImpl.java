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

import cn.creditloans.core.dao.PlatformDepartmentDao;
import cn.creditloans.core.dao.af.BaseElementDao;
import cn.creditloans.core.dao.af.RulesetConditionDao;
import cn.creditloans.core.dao.af.RulesetElementDao;
import cn.creditloans.core.dao.af.RulesetRuleDao;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.RulesetElementDTO;
import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.entity.af.BaseElement;
import cn.creditloans.core.entity.af.RulesetElement;
import cn.creditloans.core.entity.platform.PlatformDepartment;
import cn.creditloans.core.service.RulesetElementService;
import cn.creditloans.tools.page.PageModel;
@Service("rulesetElementServie")
public class RulesetElementServiceImpl implements RulesetElementService {
	private static final Log logger = LogFactory.getLog(RulesetElementServiceImpl.class);
	@Autowired
	RulesetElementDao rulesetElementDao;
	@Autowired
	PlatformDepartmentDao platformDepartmentDao;
	@Autowired
	BaseElementDao baseElementDao;
	@Autowired
	RulesetRuleDao rulesetRuleDao;
	@Autowired
	RulesetConditionDao rulesetConditionDao;
	@Override
	public PageModel<RulesetElementDTO> getRulesetElementPageList(
			RulesetElementDTO rulesetElementDTO, int currentPage, int pageSize) {
		RulesetElement rulesetElement = new RulesetElement();
		BeanUtils.copyProperties(rulesetElementDTO, rulesetElement);
		
		PageModel<RulesetElementDTO> pm = new PageModel<RulesetElementDTO>();
		pm.init(currentPage, pageSize);
		int count = rulesetElementDao.selectPageCount(rulesetElement);
		List<RulesetElement> rulesetElementList = rulesetElementDao.selectPageList(rulesetElement, pm);
		List<RulesetElementDTO> rulesetElementDtoList = new ArrayList<RulesetElementDTO>();
		for(RulesetElement _rulesetElement : rulesetElementList){
			RulesetElementDTO _rulesetElementDTO = new RulesetElementDTO();
			BeanUtils.copyProperties(_rulesetElement, _rulesetElementDTO);
			rulesetElementDtoList.add(_rulesetElementDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(rulesetElementDtoList);
		return pm;
	}

	@Override
	public int saveRulesetElement(RulesetElementDTO rulesetElementDTO) {
		RulesetElement rulesetElement = new RulesetElement();
		BeanUtils.copyProperties(rulesetElementDTO, rulesetElement);
		rulesetElementDao.insert(rulesetElement);
		int rulesetElementId = rulesetElement.getId();
		return rulesetElementId;
	}

	@Override
	public Map<String, Object> getRulesetElementById(int rulesetElementId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		RulesetElementDTO rulesetElementDTO = new RulesetElementDTO();
		RulesetElement rulesetElement = rulesetElementDao.select(rulesetElementId);
		BeanUtils.copyProperties(rulesetElement, rulesetElementDTO);
		rulesetElementDTO.setOldName(rulesetElement.getName());
		resultMap.put("rulesetElementDTO", rulesetElementDTO);
		Map<String, Object> relationMap = getAllRelationEntity();
		resultMap.putAll(relationMap);
		return resultMap;
	}

	@Override
	public void updateRulesetElement(RulesetElementDTO rulesetElementDTO) {
		RulesetElement rulesetElement = new RulesetElement();
		BeanUtils.copyProperties(rulesetElementDTO, rulesetElement);
		rulesetElementDao.update(rulesetElement);
	}

	@Override
	public int deleteRulesetElement(int rulesetElementId) {
		int ruleCount = rulesetRuleDao.selectCountByRulesetElementId(rulesetElementId);
		
		int conditionCount = rulesetConditionDao.selectCountByRulesetElementId(rulesetElementId);
		if(ruleCount > 0 || conditionCount > 0) {
			return 1;
		}
		rulesetElementDao.delete(rulesetElementId);
		return 0;
	}
	@Override
	public boolean checkNameIsExist(String name) {
		int countName = rulesetElementDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}
	@Override
	public Map<String, Object> getAllRelationEntity() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PlatformDepartment> platformDepartmentList = platformDepartmentDao.selectPlatformDepartmentList();
		List<PlatformDepartmentDTO> platformDepartmentDtoList = new  ArrayList<PlatformDepartmentDTO>();
		for(PlatformDepartment platformDepartment : platformDepartmentList){
			PlatformDepartmentDTO platformDepartmentDTO = new PlatformDepartmentDTO();
			BeanUtils.copyProperties(platformDepartment, platformDepartmentDTO);
			platformDepartmentDtoList.add(platformDepartmentDTO);
		}
		resultMap.put("platformDepartmentDtoList", platformDepartmentDtoList);
		List<BaseElement> baseElementList = baseElementDao.selectAllInfos();
		List<BaseElementDTO> baseElementDTOList = new  ArrayList<BaseElementDTO>();
		for(BaseElement _baseElement : baseElementList){
			BaseElementDTO _baseElementDTO = new BaseElementDTO();
			BeanUtils.copyProperties(_baseElement, _baseElementDTO);
			baseElementDTOList.add(_baseElementDTO);
		}
		resultMap.put("baseElementDTOList", baseElementDTOList);
		return resultMap;
	}
}
