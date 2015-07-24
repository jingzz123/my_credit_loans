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

import cn.creditloans.core.dao.af.BaseActionNameDao;
import cn.creditloans.core.dao.af.BaseElementDao;
import cn.creditloans.core.dto.af.BaseActionNameDTO;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.entity.af.BaseActionName;
import cn.creditloans.core.entity.af.BaseActionRelation;
import cn.creditloans.core.entity.af.BaseElement;
import cn.creditloans.core.service.BaseActionNameService;
import cn.creditloans.tools.page.PageModel;
@Service("baseActionNameServie")
public class BaseActionNameServiceImpl implements BaseActionNameService {
	private static final Log logger = LogFactory.getLog(BaseActionNameServiceImpl.class);
	@Autowired
	BaseActionNameDao baseActionNameDao;
	@Autowired
	BaseElementDao baseElementDao;
	@Override
	public PageModel<BaseActionNameDTO> getBaseActionNamePageList(
			BaseActionNameDTO baseActionNameDTO, int currentPage, int pageSize) {
		BaseActionName baseActionName = new BaseActionName();
		BeanUtils.copyProperties(baseActionNameDTO, baseActionName);
		
		PageModel<BaseActionNameDTO> pm = new PageModel<BaseActionNameDTO>();
		pm.init(currentPage, pageSize);
		int count = baseActionNameDao.selectPageCount(baseActionName);
		List<BaseActionName> baseActionNameList = baseActionNameDao.selectPageList(baseActionName, pm);
		List<BaseActionNameDTO> baseActionNameDtoList = new ArrayList<BaseActionNameDTO>();
		for(BaseActionName _baseActionName : baseActionNameList){
			BaseActionNameDTO _baseActionNameDTO = new BaseActionNameDTO();
			BeanUtils.copyProperties(_baseActionName, _baseActionNameDTO);
			baseActionNameDtoList.add(_baseActionNameDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(baseActionNameDtoList);
		return pm;
	}

	@Override
	public int saveBaseActionName(BaseActionNameDTO baseActionNameDTO) {
		// 添加动作名称
		BaseActionName baseActionName = new BaseActionName();
		BeanUtils.copyProperties(baseActionNameDTO, baseActionName);
		baseActionNameDao.insert(baseActionName);
		int actionNameId = baseActionName.getId();
		// 处理该动作名称所属的包
		List<Integer> baseIdList = baseActionNameDTO.getBaseIdList();
		List<BaseActionRelation> baseActionRelationList = new ArrayList<BaseActionRelation>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		for(Integer baseId : baseIdList){
			BaseActionRelation baseActionRelation = new BaseActionRelation();
			baseActionRelation.setBaseId(baseId);
			baseActionRelation.setActionId(actionNameId);
			baseActionRelation.setType(1);
			baseActionRelation.setCreateUserId(baseActionName.getCreateUserId());
			baseActionRelation.setUpadteUserId(baseActionName.getUpadteUserId());
			param.put("baseId", baseId);
			param.put("sequence", "sequence");
			param.put("type", 1);
			Integer sequence = baseActionNameDao.getMaxSequence(param);
			if(sequence == null) {
				sequence = 0;
			}
			baseActionRelation.setSequence(++sequence);
			baseActionRelationList.add(baseActionRelation);
		}
		baseActionNameDao.batchInsertBaseActionRelation(baseActionRelationList);
		return actionNameId;
	}

	@Override
	public BaseActionNameDTO getBaseActionNameById(int actionNameId) {
		BaseActionNameDTO baseActionNameDTO = new BaseActionNameDTO();
		BaseActionName baseActionName = baseActionNameDao.select(actionNameId);
		BeanUtils.copyProperties(baseActionName, baseActionNameDTO);
		baseActionNameDTO.setOldName(baseActionName.getName());
		//处理动作名称所在的包
		List<BaseElement> baseElementList = baseElementDao.selectAllInfos();
		List<BaseActionRelation> baseActionRelationList = baseActionName.getBaseActionRelationList();
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
		baseActionNameDTO.setBaseElementDTOList(baseElementDTOList);
		return baseActionNameDTO;
	}

	@Override
	public void updateBaseActionName(BaseActionNameDTO baseActionNameDTO) {
		BaseActionName baseActionName = new BaseActionName();
		BeanUtils.copyProperties(baseActionNameDTO, baseActionName);
		baseActionNameDao.update(baseActionName);
		int actionNameId = baseActionName.getId();
		Map<String, Object> param1 = new HashMap<String, Object>();
		List<Integer> baseIdList = baseActionNameDTO.getBaseIdList();
		List<BaseActionRelation> baseActionRelationList = new ArrayList<BaseActionRelation>();
		param1.put("type", 1);
		param1.put("actionId", actionNameId);
		baseActionNameDao.deleteBaseActionRelationByActionName(param1);
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		for(Integer baseId : baseIdList){
			BaseActionRelation baseActionRelation = new BaseActionRelation();
			baseActionRelation.setBaseId(baseId);
			baseActionRelation.setActionId(actionNameId);
			baseActionRelation.setType(1);
			baseActionRelation.setCreateUserId(baseActionName.getUpadteUserId());
			baseActionRelation.setUpadteUserId(baseActionName.getUpadteUserId());
			param2.put("baseId", baseId);
			param2.put("sequence", "sequence");
			param2.put("type", 1);
			Integer sequence = baseActionNameDao.getMaxSequence(param2);
			if(sequence == null) {
				sequence = 0;
			}
			baseActionRelation.setSequence(++sequence);
			baseActionRelationList.add(baseActionRelation);
		}
		baseActionNameDao.batchInsertBaseActionRelation(baseActionRelationList);
	}

	@Override
	public void deleteBaseActionName(int actionNameId) {
		baseActionNameDao.delete(actionNameId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", 1);
		param.put("actionId", actionNameId);
		baseActionNameDao.deleteBaseActionRelationByActionName(param);
	}

	@Override
	public List<BaseActionNameDTO> selectAllInfos() {
		List<BaseActionName> baseActionNameList = baseActionNameDao.selectAllInfos();
		List<BaseActionNameDTO> baseActionNameDtoList = new ArrayList<BaseActionNameDTO>();
		for(BaseActionName _baseActionName : baseActionNameList){
			BaseActionNameDTO _baseActionNameDTO = new BaseActionNameDTO();
			BeanUtils.copyProperties(_baseActionName, _baseActionNameDTO);
			baseActionNameDtoList.add(_baseActionNameDTO);
		}
		return baseActionNameDtoList;
	}

	@Override
	public boolean checkNameIsExist(String name) {
		int countName = baseActionNameDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}

}
