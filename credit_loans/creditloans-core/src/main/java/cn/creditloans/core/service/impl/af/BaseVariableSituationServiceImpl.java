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

import cn.creditloans.core.dao.af.BaseElementDao;
import cn.creditloans.core.dao.af.BaseVariableSituationDao;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.BaseVariableSituationDTO;
import cn.creditloans.core.entity.af.BaseElement;
import cn.creditloans.core.entity.af.BaseVariableRelation;
import cn.creditloans.core.entity.af.BaseVariableSituation;
import cn.creditloans.core.service.BaseVariableSituationService;
import cn.creditloans.tools.page.PageModel;
@Service("baseVariableSituationServie")
public class BaseVariableSituationServiceImpl implements BaseVariableSituationService {
	private static final Log logger = LogFactory.getLog(BaseVariableSituationServiceImpl.class);
	@Autowired
	BaseVariableSituationDao baseVariableSituationDao;
	@Autowired
	BaseElementDao baseElementDao;
	@Override
	public PageModel<BaseVariableSituationDTO> getBaseVariableSituationPageList(
			BaseVariableSituationDTO baseVariableSituationDTO, int currentPage, int pageSize) {
		BaseVariableSituation baseVariableSituation = new BaseVariableSituation();
		BeanUtils.copyProperties(baseVariableSituationDTO, baseVariableSituation);
		
		PageModel<BaseVariableSituationDTO> pm = new PageModel<BaseVariableSituationDTO>();
		pm.init(currentPage, pageSize);
		int count = baseVariableSituationDao.selectPageCount(baseVariableSituation);
		List<BaseVariableSituation> baseVariableSituationList = baseVariableSituationDao.selectPageList(baseVariableSituation, pm);
		List<BaseVariableSituationDTO> baseVariableSituationDtoList = new ArrayList<BaseVariableSituationDTO>();
		for(BaseVariableSituation _baseVariableSituation : baseVariableSituationList){
			BaseVariableSituationDTO _baseVariableSituationDTO = new BaseVariableSituationDTO();
			BeanUtils.copyProperties(_baseVariableSituation, _baseVariableSituationDTO);
			baseVariableSituationDtoList.add(_baseVariableSituationDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(baseVariableSituationDtoList);
		return pm;
	}

	@Override
	public int saveBaseVariableSituation(BaseVariableSituationDTO baseVariableSituationDTO) {
		BaseVariableSituation baseVariableSituation = new BaseVariableSituation();
		BeanUtils.copyProperties(baseVariableSituationDTO, baseVariableSituation);
		baseVariableSituationDao.insert(baseVariableSituation);
		int variableSituationId = baseVariableSituation.getId();
		// 处理该动作名称所属的包
		List<Integer> baseIdList = baseVariableSituationDTO.getBaseIdList();
		List<BaseVariableRelation> baseVariableRelationList = new ArrayList<BaseVariableRelation>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		for(Integer baseId : baseIdList){
			BaseVariableRelation baseVariableRelation = new BaseVariableRelation();
			baseVariableRelation.setBaseId(baseId);
			baseVariableRelation.setVariableId(variableSituationId);
			baseVariableRelation.setType(3);
			baseVariableRelation.setCreateUserId(baseVariableSituation.getCreateUserId());
			baseVariableRelation.setUpadteUserId(baseVariableSituation.getUpadteUserId());
			param.put("baseId", baseId);
			param.put("sequence", "sequence");
			param.put("type", 3);
			Integer sequence = baseVariableSituationDao.getMaxSequence(param);
			if(sequence == null) {
				sequence = 0;
			}
			baseVariableRelation.setSequence(++sequence);
			baseVariableRelationList.add(baseVariableRelation);
		}
		baseVariableSituationDao.batchInsertBaseVariableRelation(baseVariableRelationList);
		return variableSituationId;
	}

	@Override
	public BaseVariableSituationDTO getBaseVariableSituationById(int variableSituationId) {
		BaseVariableSituationDTO baseVariableSituationDTO = new BaseVariableSituationDTO();
		BaseVariableSituation baseVariableSituation = baseVariableSituationDao.select(variableSituationId);
		BeanUtils.copyProperties(baseVariableSituation, baseVariableSituationDTO);
		//处理动作名称所在的包
		baseVariableSituationDTO.setOldName(baseVariableSituation.getName());
		List<BaseElement> baseElementList = baseElementDao.selectAllInfos();
		List<BaseVariableRelation> baseVariableRelationList = baseVariableSituation.getBaseVariableRelationList();
		List<BaseElementDTO> baseElementDTOList = new ArrayList<BaseElementDTO>();
		for(BaseElement baseElement : baseElementList) {
			BaseElementDTO baseElementDTO = new BaseElementDTO();
			BeanUtils.copyProperties(baseElement, baseElementDTO);
			for(BaseVariableRelation baseVariableRelation : baseVariableRelationList) {
				if(baseElement.getId()== baseVariableRelation.getBaseId()) {
					baseElementDTO.setVariableSituationFlag(1);
				}
			}
			baseElementDTOList.add(baseElementDTO);
		}
		baseVariableSituationDTO.setBaseElementDTOList(baseElementDTOList);
		return baseVariableSituationDTO;
	}

	@Override
	public void updateBaseVariableSituation(BaseVariableSituationDTO baseVariableSituationDTO) {
		BaseVariableSituation baseVariableSituation = new BaseVariableSituation();
		BeanUtils.copyProperties(baseVariableSituationDTO, baseVariableSituation);
		baseVariableSituationDao.update(baseVariableSituation);
		int variableSituationId = baseVariableSituation.getId();
		Map<String, Object> param1 = new HashMap<String, Object>();
		List<Integer> baseIdList = baseVariableSituationDTO.getBaseIdList();
		List<BaseVariableRelation> baseVariableRelationList = new ArrayList<BaseVariableRelation>();
		param1.put("type", 3);
		param1.put("variableId", variableSituationId);
		baseVariableSituationDao.deleteBaseVariableRelationByVariable(param1);
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		for(Integer baseId : baseIdList){
			BaseVariableRelation baseVariableRelation = new BaseVariableRelation();
			baseVariableRelation.setBaseId(baseId);
			baseVariableRelation.setVariableId(variableSituationId);
			baseVariableRelation.setType(3);
			baseVariableRelation.setCreateUserId(baseVariableSituation.getUpadteUserId());
			baseVariableRelation.setUpadteUserId(baseVariableSituation.getUpadteUserId());
			param2.put("baseId", baseId);
			param2.put("sequence", "sequence");
			param2.put("type", 3);
			Integer sequence = baseVariableSituationDao.getMaxSequence(param2);
			if(sequence == null) {
				sequence = 0;
			}
			baseVariableRelation.setSequence(++sequence);
			baseVariableRelationList.add(baseVariableRelation);
		}
		baseVariableSituationDao.batchInsertBaseVariableRelation(baseVariableRelationList);
	}

	@Override
	public void deleteBaseVariableSituation(int variableSituationId) {
		baseVariableSituationDao.delete(variableSituationId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", 3);
		param.put("variableId", variableSituationId);
		baseVariableSituationDao.deleteBaseVariableRelationByVariable(param);
	}
	
	@Override
	public List<BaseVariableSituationDTO> getAllInfos() {
		List<BaseVariableSituation> baseVariableSituationList = baseVariableSituationDao.selectAllInfos();
		List<BaseVariableSituationDTO> baseVariableSituationDTOList = new ArrayList<BaseVariableSituationDTO>();
		for(BaseVariableSituation baseVariableSituation :  baseVariableSituationList) {
			BaseVariableSituationDTO baseVariableSituationDTO = new BaseVariableSituationDTO();
			BeanUtils.copyProperties(baseVariableSituation, baseVariableSituationDTO);
			baseVariableSituationDTOList.add(baseVariableSituationDTO);
		}
		return baseVariableSituationDTOList;
	}
	@Override
	public List<BaseVariableSituationDTO> getBaseVariableSituationByName(String name) {
		List<BaseVariableSituation> baseVariableSituationList = baseVariableSituationDao.selectAllByName(name);
		List<BaseVariableSituationDTO> baseVariableSituationDTOList = new ArrayList<BaseVariableSituationDTO>();
		for(BaseVariableSituation baseVariableSituation :  baseVariableSituationList) {
			BaseVariableSituationDTO baseVariableSituationDTO = new BaseVariableSituationDTO();
			BeanUtils.copyProperties(baseVariableSituation, baseVariableSituationDTO);
			baseVariableSituationDTOList.add(baseVariableSituationDTO);
		}
		return baseVariableSituationDTOList;
	}
	
	@Override
	public boolean checkNameIsExist(String name) {
		int countName = baseVariableSituationDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}
	
	
}
