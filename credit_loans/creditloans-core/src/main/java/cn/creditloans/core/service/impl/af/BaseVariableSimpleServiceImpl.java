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
import cn.creditloans.core.dao.af.BaseVariableSimpleDao;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.BaseVariableSimpleDTO;
import cn.creditloans.core.entity.af.BaseActionRelation;
import cn.creditloans.core.entity.af.BaseElement;
import cn.creditloans.core.entity.af.BaseVariableRelation;
import cn.creditloans.core.entity.af.BaseVariableSimple;
import cn.creditloans.core.service.BaseVariableSimpleService;
import cn.creditloans.tools.page.PageModel;
@Service("baseVariableSimpleServie")
public class BaseVariableSimpleServiceImpl implements BaseVariableSimpleService {
	private static final Log logger = LogFactory.getLog(BaseVariableSimpleServiceImpl.class);
	@Autowired
	BaseVariableSimpleDao baseVariableSimpleDao;
	@Autowired
	BaseElementDao baseElementDao;
	@Override
	public PageModel<BaseVariableSimpleDTO> getBaseVariableSimplePageList(
			BaseVariableSimpleDTO baseVariableSimpleDTO, int currentPage, int pageSize) {
		BaseVariableSimple baseVariableSimple = new BaseVariableSimple();
		BeanUtils.copyProperties(baseVariableSimpleDTO, baseVariableSimple);
		
		PageModel<BaseVariableSimpleDTO> pm = new PageModel<BaseVariableSimpleDTO>();
		pm.init(currentPage, pageSize);
		int count = baseVariableSimpleDao.selectPageCount(baseVariableSimple);
		List<BaseVariableSimple> baseVariableSimpleList = baseVariableSimpleDao.selectPageList(baseVariableSimple, pm);
		List<BaseVariableSimpleDTO> baseVariableSimpleDtoList = new ArrayList<BaseVariableSimpleDTO>();
		for(BaseVariableSimple _baseVariableSimple : baseVariableSimpleList){
			BaseVariableSimpleDTO _baseVariableSimpleDTO = new BaseVariableSimpleDTO();
			BeanUtils.copyProperties(_baseVariableSimple, _baseVariableSimpleDTO);
			baseVariableSimpleDtoList.add(_baseVariableSimpleDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(baseVariableSimpleDtoList);
		return pm;
	}

	@Override
	public int saveBaseVariableSimple(BaseVariableSimpleDTO baseVariableSimpleDTO) {
		BaseVariableSimple baseVariableSimple = new BaseVariableSimple();
		BeanUtils.copyProperties(baseVariableSimpleDTO, baseVariableSimple);
		baseVariableSimpleDao.insert(baseVariableSimple);
		int variableSimpleId = baseVariableSimple.getId();
		// 处理该所属的包
		List<Integer> baseIdList = baseVariableSimpleDTO.getBaseIdList();
		List<BaseVariableRelation> baseVariableRelationList = new ArrayList<BaseVariableRelation>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		for(Integer baseId : baseIdList){
			BaseVariableRelation baseVariableRelation = new BaseVariableRelation();
			baseVariableRelation.setBaseId(baseId);
			baseVariableRelation.setVariableId(variableSimpleId);
			baseVariableRelation.setType(1);
			baseVariableRelation.setCreateUserId(baseVariableSimple.getCreateUserId());
			baseVariableRelation.setUpadteUserId(baseVariableSimple.getUpadteUserId());
			param.put("baseId", baseId);
			param.put("sequence", "sequence");
			param.put("type", 1);
			Integer sequence = baseVariableSimpleDao.getMaxSequence(param);
			if(sequence == null) {
				sequence = 0;
			}
			baseVariableRelation.setSequence(++sequence);
			baseVariableRelationList.add(baseVariableRelation);
		}
		baseVariableSimpleDao.batchInsertBaseVariableRelation(baseVariableRelationList);
		return variableSimpleId;
	}

	@Override
	public BaseVariableSimpleDTO getBaseVariableSimpleById(int variableSimpleId) {
		BaseVariableSimpleDTO baseVariableSimpleDTO = new BaseVariableSimpleDTO();
		BaseVariableSimple baseVariableSimple = baseVariableSimpleDao.select(variableSimpleId);
		BeanUtils.copyProperties(baseVariableSimple, baseVariableSimpleDTO);
		//处理所在的包
		baseVariableSimpleDTO.setOldName(baseVariableSimple.getName());
		List<BaseElement> baseElementList = baseElementDao.selectAllInfos();
		List<BaseVariableRelation> baseVariableRelationList = baseVariableSimple.getBaseVariableRelationList();
		List<BaseElementDTO> baseElementDTOList = new ArrayList<BaseElementDTO>();
		for(BaseElement baseElement : baseElementList) {
			BaseElementDTO baseElementDTO = new BaseElementDTO();
			BeanUtils.copyProperties(baseElement, baseElementDTO);
			for(BaseVariableRelation baseVariableRelation : baseVariableRelationList) {
				if(baseElement.getId()== baseVariableRelation.getBaseId()) {
					baseElementDTO.setVariableSimpleFlag(1);
				}
			}
			baseElementDTOList.add(baseElementDTO);
		}
		baseVariableSimpleDTO.setBaseElementDTOList(baseElementDTOList);
		return baseVariableSimpleDTO;
	}

	@Override
	public void updateBaseVariableSimple(BaseVariableSimpleDTO baseVariableSimpleDTO) {
		BaseVariableSimple baseVariableSimple = new BaseVariableSimple();
		BeanUtils.copyProperties(baseVariableSimpleDTO, baseVariableSimple);
		baseVariableSimpleDao.update(baseVariableSimple);
		int variableSimpleId = baseVariableSimple.getId();
		Map<String, Object> param1 = new HashMap<String, Object>();
		List<Integer> baseIdList = baseVariableSimpleDTO.getBaseIdList();
		List<BaseVariableRelation> baseVariableRelationList = new ArrayList<BaseVariableRelation>();
		param1.put("type", 1);
		param1.put("variableId", variableSimpleId);
		baseVariableSimpleDao.deleteBaseVariableRelationByVariable(param1);
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		for(Integer baseId : baseIdList){
			BaseVariableRelation baseVariableRelation = new BaseVariableRelation();
			baseVariableRelation.setBaseId(baseId);
			baseVariableRelation.setVariableId(variableSimpleId);
			baseVariableRelation.setType(1);
			baseVariableRelation.setCreateUserId(baseVariableSimple.getUpadteUserId());
			baseVariableRelation.setUpadteUserId(baseVariableSimple.getUpadteUserId());
			param2.put("baseId", baseId);
			param2.put("sequence", "sequence");
			param2.put("type", 1);
			Integer sequence = baseVariableSimpleDao.getMaxSequence(param2);
			if(sequence == null) {
				sequence = 0;
			}
			baseVariableRelation.setSequence(++sequence);
			baseVariableRelationList.add(baseVariableRelation);
		}
		baseVariableSimpleDao.batchInsertBaseVariableRelation(baseVariableRelationList);
	}

	@Override
	public void deleteBaseVariableSimple(int variableSimpleId) {
		baseVariableSimpleDao.delete(variableSimpleId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", 1);
		param.put("variableId", variableSimpleId);
		baseVariableSimpleDao.deleteBaseVariableRelationByVariable(param);
	}

	@Override
	public List<BaseVariableSimpleDTO> getAllInfos() {
		List<BaseVariableSimple> baseVariableSimpleList = baseVariableSimpleDao.selectAllInfos();
		List<BaseVariableSimpleDTO> baseVariableSimpleDTOList = new ArrayList<BaseVariableSimpleDTO>();
		for(BaseVariableSimple baseVariableSimple :  baseVariableSimpleList) {
			BaseVariableSimpleDTO baseVariableSimpleDTO = new BaseVariableSimpleDTO();
			BeanUtils.copyProperties(baseVariableSimple, baseVariableSimpleDTO);
			baseVariableSimpleDTOList.add(baseVariableSimpleDTO);
		}
		return baseVariableSimpleDTOList;
	}
	
	public List<BaseVariableSimpleDTO> getBaseVariableSimpleByName(String name) {
		List<BaseVariableSimple> baseVariableSimpleList = baseVariableSimpleDao.selectAllByName(name);
		List<BaseVariableSimpleDTO> baseVariableSimpleDTOList = new ArrayList<BaseVariableSimpleDTO>();
		for(BaseVariableSimple baseVariableSimple :  baseVariableSimpleList) {
			BaseVariableSimpleDTO baseVariableSimpleDTO = new BaseVariableSimpleDTO();
			BeanUtils.copyProperties(baseVariableSimple, baseVariableSimpleDTO);
			baseVariableSimpleDTOList.add(baseVariableSimpleDTO);
		}
		return baseVariableSimpleDTOList;
	}
	@Override
	public boolean checkNameIsExist(String name) {
		int countName = baseVariableSimpleDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}
}
