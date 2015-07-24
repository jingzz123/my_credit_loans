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

import cn.creditloans.core.dao.af.BaseActionVariableDao;
import cn.creditloans.core.dao.af.BaseElementDao;
import cn.creditloans.core.dao.af.BaseVariableSimpleDao;
import cn.creditloans.core.dto.af.BaseActionVariableDTO;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.BaseVariableSimpleDTO;
import cn.creditloans.core.entity.af.BaseActionRelation;
import cn.creditloans.core.entity.af.BaseActionVariable;
import cn.creditloans.core.entity.af.BaseElement;
import cn.creditloans.core.entity.af.BaseVariableSimple;
import cn.creditloans.core.service.BaseActionVariableService;
import cn.creditloans.tools.page.PageModel;
@Service("baseActionVariableServie")
public class BaseActionVariableServiceImpl implements BaseActionVariableService {
	private static final Log logger = LogFactory.getLog(BaseActionVariableServiceImpl.class);
	@Autowired
	BaseActionVariableDao baseActionVariableDao;
	@Autowired
	BaseElementDao baseElementDao;
	@Autowired
	BaseVariableSimpleDao  baseVariableSimpleDao;
	@Override
	public PageModel<BaseActionVariableDTO> getBaseActionVariablePageList(
			BaseActionVariableDTO baseActionVariableDTO, int currentPage, int pageSize) {
		BaseActionVariable baseActionVariable = new BaseActionVariable();
		BeanUtils.copyProperties(baseActionVariableDTO, baseActionVariable);
		
		PageModel<BaseActionVariableDTO> pm = new PageModel<BaseActionVariableDTO>();
		pm.init(currentPage, pageSize);
		int count = baseActionVariableDao.selectPageCount(baseActionVariable);
		List<BaseActionVariable> baseActionVariableList = baseActionVariableDao.selectPageList(baseActionVariable, pm);
		List<BaseActionVariableDTO> baseActionVariableDtoList = new ArrayList<BaseActionVariableDTO>();
		for(BaseActionVariable _baseActionVariable : baseActionVariableList){
			BaseActionVariableDTO _baseActionVariableDTO = new BaseActionVariableDTO();
			BeanUtils.copyProperties(_baseActionVariable, _baseActionVariableDTO);
			baseActionVariableDtoList.add(_baseActionVariableDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(baseActionVariableDtoList);
		return pm;
	}

	@Override
	public int saveBaseActionVariable(BaseActionVariableDTO baseActionVariableDTO) {
		BaseActionVariable baseActionVariable = new BaseActionVariable();
		BeanUtils.copyProperties(baseActionVariableDTO, baseActionVariable);
		baseActionVariableDao.insert(baseActionVariable);
		int actionVariableId = baseActionVariable.getId();
		// 处理该动作变量所属的包
		List<Integer> baseIdList = baseActionVariableDTO.getBaseIdList();
		List<BaseActionRelation> baseActionRelationList = new ArrayList<BaseActionRelation>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		for(Integer baseId : baseIdList){
			BaseActionRelation baseActionRelation = new BaseActionRelation();
			baseActionRelation.setBaseId(baseId);
			baseActionRelation.setActionId(actionVariableId);
			baseActionRelation.setType(2);
			baseActionRelation.setCreateUserId(baseActionVariable.getCreateUserId());
			baseActionRelation.setUpadteUserId(baseActionVariable.getUpadteUserId());
			param.put("baseId", baseId);
			param.put("sequence", "sequence");
			param.put("type", 2);
			Integer sequence = baseActionVariableDao.getMaxSequence(param);
			if(sequence == null) {
				sequence = 0;
			}
			baseActionRelation.setSequence(++sequence);
			baseActionRelationList.add(baseActionRelation);
		}
		baseActionVariableDao.batchInsertBaseActionRelation(baseActionRelationList);
		
		return actionVariableId;
	}

	@Override
	public Map<String, Object> getBaseActionVariableById(int actionVariableId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		BaseActionVariableDTO baseActionVariableDTO = new BaseActionVariableDTO();
		BaseActionVariable baseActionVariable = baseActionVariableDao.select(actionVariableId);
		BeanUtils.copyProperties(baseActionVariable, baseActionVariableDTO);
		baseActionVariableDTO.setOldName(baseActionVariable.getName());
		//回显动作变量所在的包
		List<BaseElement> baseElementList = baseElementDao.selectAllInfos();
		List<BaseActionRelation> baseActionRelationList = baseActionVariable.getBaseActionRelationList();
		List<BaseElementDTO> baseElementDTOList = new ArrayList<BaseElementDTO>();
		for(BaseElement baseElement : baseElementList) {
			BaseElementDTO baseElementDTO = new BaseElementDTO();
			BeanUtils.copyProperties(baseElement, baseElementDTO);
			for(BaseActionRelation baseActionRelation : baseActionRelationList) {
				if(baseElement.getId()== baseActionRelation.getBaseId()) {
					baseElementDTO.setActionNameFlag(1);
				}
			}
			baseElementDTOList.add(baseElementDTO);
		}
		baseActionVariableDTO.setBaseElementDTOList(baseElementDTOList);
		resultMap.put("baseActionVariableDTO", baseActionVariableDTO);
		List<BaseVariableSimple> baseVariableSimpleList = baseVariableSimpleDao.selectAllInfos();
		List<BaseVariableSimpleDTO> baseVariableSimpleDTOList = new ArrayList<BaseVariableSimpleDTO>();
		for(BaseVariableSimple baseVariableSimple : baseVariableSimpleList){
			BaseVariableSimpleDTO baseVariableSimpleDTO = new BaseVariableSimpleDTO();
			BeanUtils.copyProperties(baseVariableSimple, baseVariableSimpleDTO);
			baseVariableSimpleDTOList.add(baseVariableSimpleDTO);
		}
		resultMap.put("baseVariableSimpleDTOList", baseVariableSimpleDTOList);
		return resultMap;
	}

	@Override
	public void updateBaseActionVariable(BaseActionVariableDTO baseActionVariableDTO) {
		BaseActionVariable baseActionVariable = new BaseActionVariable();
		BeanUtils.copyProperties(baseActionVariableDTO, baseActionVariable);
		baseActionVariableDao.update(baseActionVariable);
		int actionNameId = baseActionVariable.getId();
		Map<String, Object> param1 = new HashMap<String, Object>();
		List<Integer> baseIdList = baseActionVariableDTO.getBaseIdList();
		List<BaseActionRelation> baseActionRelationList = new ArrayList<BaseActionRelation>();
		param1.put("type", 2);
		param1.put("actionId", actionNameId);
		baseActionVariableDao.deleteBaseActionRelationByActionVariable(param1);
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		for(Integer baseId : baseIdList){
			BaseActionRelation baseActionRelation = new BaseActionRelation();
			baseActionRelation.setBaseId(baseId);
			baseActionRelation.setActionId(actionNameId);
			baseActionRelation.setType(2);
			baseActionRelation.setCreateUserId(baseActionVariable.getUpadteUserId());
			baseActionRelation.setUpadteUserId(baseActionVariable.getUpadteUserId());
			param2.put("baseId", baseId);
			param2.put("sequence", "sequence");
			param2.put("type", 2);
			Integer sequence = baseActionVariableDao.getMaxSequence(param2);
			if(sequence == null) {
				sequence = 0;
			}
			baseActionRelation.setSequence(++sequence);
			baseActionRelationList.add(baseActionRelation);
		}
		baseActionVariableDao.batchInsertBaseActionRelation(baseActionRelationList);
	}

	@Override
	public void deleteBaseActionVariable(int actionVariableId) {
		baseActionVariableDao.delete(actionVariableId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", 2);
		param.put("actionId", actionVariableId);
		baseActionVariableDao.deleteBaseActionRelationByActionVariable(param);
	}

	@Override
	public List<BaseActionVariableDTO> selectAllInfos() {
		List<BaseActionVariable> baseActionVariableList = baseActionVariableDao.selectAllInfos();
		List<BaseActionVariableDTO> baseActionVariableDtoList = new ArrayList<BaseActionVariableDTO>();
		for(BaseActionVariable _baseActionVariable : baseActionVariableList){
			BaseActionVariableDTO _baseActionVariableDTO = new BaseActionVariableDTO();
			BeanUtils.copyProperties(_baseActionVariable, _baseActionVariableDTO);
			baseActionVariableDtoList.add(_baseActionVariableDTO);
		}
		return baseActionVariableDtoList;
	}

	@Override
	public boolean checkNameIsExist(String name) {
		int countName = baseActionVariableDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}

	@Override
	public Map<String, Object> getAllElementAndSimple() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<BaseVariableSimple> baseVariableSimpleList = baseVariableSimpleDao.selectAllInfos();
		List<BaseVariableSimpleDTO> baseVariableSimpleDTOList = new ArrayList<BaseVariableSimpleDTO>();
		for(BaseVariableSimple baseVariableSimple : baseVariableSimpleList){
			BaseVariableSimpleDTO baseVariableSimpleDTO = new BaseVariableSimpleDTO();
			BeanUtils.copyProperties(baseVariableSimple, baseVariableSimpleDTO);
			baseVariableSimpleDTOList.add(baseVariableSimpleDTO);
		}
		resultMap.put("baseVariableSimpleDTOList", baseVariableSimpleDTOList);
		List<BaseElement> baseElementList = baseElementDao.selectAllInfos();
		List<BaseElementDTO> baseElementDTOList = new ArrayList<BaseElementDTO>();
		for(BaseElement baseElement : baseElementList){
			BaseElementDTO baseElementDTO = new BaseElementDTO();
			BeanUtils.copyProperties(baseElement, baseElementDTO);
			baseElementDTOList.add(baseElementDTO);
		}
		resultMap.put("baseElementDTOList", baseElementDTOList);
		return resultMap;
	}
}
