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
import cn.creditloans.core.dao.af.BaseVariableExpressionDao;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.BaseVariableExpressionDTO;
import cn.creditloans.core.entity.af.BaseElement;
import cn.creditloans.core.entity.af.BaseVariableExpression;
import cn.creditloans.core.entity.af.BaseVariableRelation;
import cn.creditloans.core.service.BaseVariableExpressionService;
import cn.creditloans.tools.page.PageModel;
@Service("baseVariableExpressionServie")
public class BaseVariableExpressionServiceImpl implements BaseVariableExpressionService {
	private static final Log logger = LogFactory.getLog(BaseVariableExpressionServiceImpl.class);
	@Autowired
	BaseVariableExpressionDao baseVariableExpressionDao;
	@Autowired
	BaseElementDao baseElementDao;
	@Override
	public PageModel<BaseVariableExpressionDTO> getBaseVariableExpressionPageList(
			BaseVariableExpressionDTO baseVariableExpressionDTO, int currentPage, int pageSize) {
		BaseVariableExpression baseVariableExpression = new BaseVariableExpression();
		BeanUtils.copyProperties(baseVariableExpressionDTO, baseVariableExpression);
		
		PageModel<BaseVariableExpressionDTO> pm = new PageModel<BaseVariableExpressionDTO>();
		pm.init(currentPage, pageSize);
		int count = baseVariableExpressionDao.selectPageCount(baseVariableExpression);
		List<BaseVariableExpression> baseVariableExpressionList = baseVariableExpressionDao.selectPageList(baseVariableExpression, pm);
		List<BaseVariableExpressionDTO> baseVariableExpressionDtoList = new ArrayList<BaseVariableExpressionDTO>();
		for(BaseVariableExpression _baseVariableExpression : baseVariableExpressionList){
			BaseVariableExpressionDTO _baseVariableExpressionDTO = new BaseVariableExpressionDTO();
			BeanUtils.copyProperties(_baseVariableExpression, _baseVariableExpressionDTO);
			baseVariableExpressionDtoList.add(_baseVariableExpressionDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(baseVariableExpressionDtoList);
		return pm;
	}

	@Override
	public int saveBaseVariableExpression(BaseVariableExpressionDTO baseVariableExpressionDTO) {
		BaseVariableExpression baseVariableExpression = new BaseVariableExpression();
		BeanUtils.copyProperties(baseVariableExpressionDTO, baseVariableExpression);
		baseVariableExpressionDao.insert(baseVariableExpression);
		int variableExpressionId = baseVariableExpression.getId();
		// 处理该所属的包
		List<Integer> baseIdList = baseVariableExpressionDTO.getBaseIdList();
		List<BaseVariableRelation> baseVariableRelationList = new ArrayList<BaseVariableRelation>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		for(Integer baseId : baseIdList){
			BaseVariableRelation baseVariableRelation = new BaseVariableRelation();
			baseVariableRelation.setBaseId(baseId);
			baseVariableRelation.setVariableId(variableExpressionId);
			baseVariableRelation.setType(4);
			baseVariableRelation.setCreateUserId(baseVariableExpression.getCreateUserId());
			baseVariableRelation.setUpadteUserId(baseVariableExpression.getUpadteUserId());
			param.put("baseId", baseId);
			param.put("sequence", "sequence");
			param.put("type", 4);
			Integer sequence = baseVariableExpressionDao.getMaxSequence(param);
			if(sequence == null) {
				sequence = 0;
			}
			baseVariableRelation.setSequence(++sequence);
			baseVariableRelationList.add(baseVariableRelation);
		}
		baseVariableExpressionDao.batchInsertBaseVariableRelation(baseVariableRelationList);
		return variableExpressionId;
	}

	@Override
	public BaseVariableExpressionDTO getBaseVariableExpressionById(int variableExpressionId) {
		BaseVariableExpressionDTO baseVariableExpressionDTO = new BaseVariableExpressionDTO();
		BaseVariableExpression baseVariableExpression = baseVariableExpressionDao.select(variableExpressionId);
		BeanUtils.copyProperties(baseVariableExpression, baseVariableExpressionDTO);
		//处理所在的包
		baseVariableExpressionDTO.setOldName(baseVariableExpression.getName());
		List<BaseElement> baseElementList = baseElementDao.selectAllInfos();
		List<BaseVariableRelation> baseVariableRelationList = baseVariableExpression.getBaseVariableRelationList();
		List<BaseElementDTO> baseElementDTOList = new ArrayList<BaseElementDTO>();
		for(BaseElement baseElement : baseElementList) {
			BaseElementDTO baseElementDTO = new BaseElementDTO();
			BeanUtils.copyProperties(baseElement, baseElementDTO);
			for(BaseVariableRelation baseVariableRelation : baseVariableRelationList) {
				if(baseElement.getId()== baseVariableRelation.getBaseId()) {
					baseElementDTO.setVariableExpressionFlag(1);
				}
			}
			baseElementDTOList.add(baseElementDTO);
		}
		baseVariableExpressionDTO.setBaseElementDTOList(baseElementDTOList);
		return baseVariableExpressionDTO;
	}

	@Override
	public void updateBaseVariableExpression(BaseVariableExpressionDTO baseVariableExpressionDTO) {
		BaseVariableExpression baseVariableExpression = new BaseVariableExpression();
		BeanUtils.copyProperties(baseVariableExpressionDTO, baseVariableExpression);
		baseVariableExpressionDao.update(baseVariableExpression);
		int variableExpressionId = baseVariableExpression.getId();
		Map<String, Object> param1 = new HashMap<String, Object>();
		List<Integer> baseIdList = baseVariableExpressionDTO.getBaseIdList();
		List<BaseVariableRelation> baseVariableRelationList = new ArrayList<BaseVariableRelation>();
		param1.put("type", 4);
		param1.put("variableId", variableExpressionId);
		baseVariableExpressionDao.deleteBaseVariableRelationByVariable(param1);
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		for(Integer baseId : baseIdList){
			BaseVariableRelation baseVariableRelation = new BaseVariableRelation();
			baseVariableRelation.setBaseId(baseId);
			baseVariableRelation.setVariableId(variableExpressionId);
			baseVariableRelation.setType(4);
			baseVariableRelation.setCreateUserId(baseVariableExpression.getUpadteUserId());
			baseVariableRelation.setUpadteUserId(baseVariableExpression.getUpadteUserId());
			param2.put("baseId", baseId);
			param2.put("sequence", "sequence");
			param2.put("type", 4);
			Integer sequence = baseVariableExpressionDao.getMaxSequence(param2);
			if(sequence == null) {
				sequence = 0;
			}
			baseVariableRelation.setSequence(++sequence);
			baseVariableRelationList.add(baseVariableRelation);
		}
		baseVariableExpressionDao.batchInsertBaseVariableRelation(baseVariableRelationList);
	}

	@Override
	public void deleteBaseVariableExpression(int variableExpressionId) {
		baseVariableExpressionDao.delete(variableExpressionId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", 4);
		param.put("variableId", variableExpressionId);
		baseVariableExpressionDao.deleteBaseVariableRelationByVariable(param);
	}

	@Override
	public List<BaseVariableExpressionDTO> getAllInfos() {
		List<BaseVariableExpression> baseVariableExpressionList = baseVariableExpressionDao.selectAllInfos();
		List<BaseVariableExpressionDTO> baseVariableExpressionDTOList = new ArrayList<BaseVariableExpressionDTO>();
		for(BaseVariableExpression baseVariableExpression :  baseVariableExpressionList) {
			BaseVariableExpressionDTO baseVariableExpressionDTO = new BaseVariableExpressionDTO();
			BeanUtils.copyProperties(baseVariableExpression, baseVariableExpressionDTO);
			baseVariableExpressionDTOList.add(baseVariableExpressionDTO);
		}
		return baseVariableExpressionDTOList;
	}
	@Override
	public List<BaseVariableExpressionDTO> getBaseVariableExpressionByName(String name) {
		List<BaseVariableExpression> baseVariableExpressionList = baseVariableExpressionDao.selectAllByName(name);
		List<BaseVariableExpressionDTO> baseVariableExpressionDTOList = new ArrayList<BaseVariableExpressionDTO>();
		for(BaseVariableExpression baseVariableExpression :  baseVariableExpressionList) {
			BaseVariableExpressionDTO baseVariableExpressionDTO = new BaseVariableExpressionDTO();
			BeanUtils.copyProperties(baseVariableExpression, baseVariableExpressionDTO);
			baseVariableExpressionDTOList.add(baseVariableExpressionDTO);
		}
		return baseVariableExpressionDTOList;
	}
	
	@Override
	public boolean checkNameIsExist(String name) {
		int countName = baseVariableExpressionDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}
}
