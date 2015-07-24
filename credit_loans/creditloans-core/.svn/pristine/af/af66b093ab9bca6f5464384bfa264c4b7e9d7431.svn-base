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
import cn.creditloans.core.dao.af.BaseVariableTransactionDao;
import cn.creditloans.core.dao.af.MetadataFieldDao;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.BaseVariableTransactionDTO;
import cn.creditloans.core.dto.af.MetadataFieldDTO;
import cn.creditloans.core.dto.af.MetadataSchemaDTO;
import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.entity.af.BaseElement;
import cn.creditloans.core.entity.af.BaseVariableRelation;
import cn.creditloans.core.entity.af.BaseVariableTransaction;
import cn.creditloans.core.entity.af.MetadataField;
import cn.creditloans.core.entity.af.MetadataSchema;
import cn.creditloans.core.entity.platform.PlatformDepartment;
import cn.creditloans.core.service.BaseVariableTransactionService;
import cn.creditloans.tools.page.PageModel;
@Service("baseVariableTransactionServie")
public class BaseVariableTransactionServiceImpl implements BaseVariableTransactionService {
	private static final Log logger = LogFactory.getLog(BaseVariableTransactionServiceImpl.class);
	@Autowired
	BaseVariableTransactionDao baseVariableTransactionDao;
	@Autowired
	BaseElementDao baseElementDao;
	@Autowired
	MetadataFieldDao metadataFieldDao;
	@Override
	public PageModel<BaseVariableTransactionDTO> getBaseVariableTransactionPageList(
			BaseVariableTransactionDTO baseVariableTransactionDTO, int currentPage, int pageSize) {
		BaseVariableTransaction baseVariableTransaction = new BaseVariableTransaction();
		BeanUtils.copyProperties(baseVariableTransactionDTO, baseVariableTransaction);
		
		PageModel<BaseVariableTransactionDTO> pm = new PageModel<BaseVariableTransactionDTO>();
		pm.init(currentPage, pageSize);
		int count = baseVariableTransactionDao.selectPageCount(baseVariableTransaction);
		List<BaseVariableTransaction> baseVariableTransactionList = baseVariableTransactionDao.selectPageList(baseVariableTransaction, pm);
		List<BaseVariableTransactionDTO> baseVariableTransactionDtoList = new ArrayList<BaseVariableTransactionDTO>();
		for(BaseVariableTransaction _baseVariableTransaction : baseVariableTransactionList){
			BaseVariableTransactionDTO _baseVariableTransactionDTO = new BaseVariableTransactionDTO();
			BeanUtils.copyProperties(_baseVariableTransaction, _baseVariableTransactionDTO);
			baseVariableTransactionDtoList.add(_baseVariableTransactionDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(baseVariableTransactionDtoList);
		return pm;
	}

	@Override
	public int saveBaseVariableTransaction(BaseVariableTransactionDTO baseVariableTransactionDTO) {
		BaseVariableTransaction baseVariableTransaction = new BaseVariableTransaction();
		BeanUtils.copyProperties(baseVariableTransactionDTO, baseVariableTransaction);
		baseVariableTransactionDao.insert(baseVariableTransaction);
		int variableTransactionId = baseVariableTransaction.getId();
		// 处理该所属的包
		List<Integer> baseIdList = baseVariableTransactionDTO.getBaseIdList();
		List<BaseVariableRelation> baseVariableRelationList = new ArrayList<BaseVariableRelation>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		for(Integer baseId : baseIdList){
			BaseVariableRelation baseVariableRelation = new BaseVariableRelation();
			baseVariableRelation.setBaseId(baseId);
			baseVariableRelation.setVariableId(variableTransactionId);
			baseVariableRelation.setType(2);
			baseVariableRelation.setCreateUserId(baseVariableTransaction.getCreateUserId());
			baseVariableRelation.setUpadteUserId(baseVariableTransaction.getUpadteUserId());
			param.put("baseId", baseId);
			param.put("sequence", "sequence");
			param.put("type", 2);
			Integer sequence = baseVariableTransactionDao.getMaxSequence(param);
			if(sequence == null) {
				sequence = 0;
			}
			baseVariableRelation.setSequence(++sequence);
			baseVariableRelationList.add(baseVariableRelation);
		}
		baseVariableTransactionDao.batchInsertBaseVariableRelation(baseVariableRelationList);
		return variableTransactionId;
	}

	@Override
	public Map<String, Object> getBaseVariableTransactionById(int variableTransactionId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		BaseVariableTransactionDTO baseVariableTransactionDTO = new BaseVariableTransactionDTO();
		BaseVariableTransaction baseVariableTransaction = baseVariableTransactionDao.select(variableTransactionId);
		BeanUtils.copyProperties(baseVariableTransaction, baseVariableTransactionDTO);
		//处理所在的包
		baseVariableTransactionDTO.setOldName(baseVariableTransaction.getName());
		List<BaseElement> baseElementList = baseElementDao.selectAllInfos();
		List<BaseVariableRelation> baseVariableRelationList = baseVariableTransaction.getBaseVariableRelationList();
		List<BaseElementDTO> baseElementDTOList = new ArrayList<BaseElementDTO>();
		for(BaseElement baseElement : baseElementList) {
			BaseElementDTO baseElementDTO = new BaseElementDTO();
			BeanUtils.copyProperties(baseElement, baseElementDTO);
			for(BaseVariableRelation baseVariableRelation : baseVariableRelationList) {
				if(baseElement.getId()== baseVariableRelation.getBaseId()) {
					baseElementDTO.setVariableTransactionFlag(1);
				}
			}
			baseElementDTOList.add(baseElementDTO);
		}
		baseVariableTransactionDTO.setBaseElementDTOList(baseElementDTOList);
		resultMap.put("baseVariableTransactionDTO", baseVariableTransactionDTO);
		List<MetadataField> metadataFieldList = metadataFieldDao.selectAllInfos();
		List<MetadataFieldDTO> metadataFieldDTOList = new  ArrayList<MetadataFieldDTO>();
		for(MetadataField metadataField : metadataFieldList){
			MetadataFieldDTO metadataFieldDTO = new MetadataFieldDTO();
			BeanUtils.copyProperties(metadataField, metadataFieldDTO);
			metadataFieldDTOList.add(metadataFieldDTO);
		}
		resultMap.put("metadataFieldDTOList", metadataFieldDTOList);
		return resultMap;
	}

	@Override
	public void updateBaseVariableTransaction(BaseVariableTransactionDTO baseVariableTransactionDTO) {
		BaseVariableTransaction baseVariableTransaction = new BaseVariableTransaction();
		BeanUtils.copyProperties(baseVariableTransactionDTO, baseVariableTransaction);
		baseVariableTransactionDao.update(baseVariableTransaction);
		int variableTransactionId = baseVariableTransaction.getId();
		Map<String, Object> param1 = new HashMap<String, Object>();
		List<Integer> baseIdList = baseVariableTransactionDTO.getBaseIdList();
		List<BaseVariableRelation> baseVariableRelationList = new ArrayList<BaseVariableRelation>();
		param1.put("type", 2);
		param1.put("variableId", variableTransactionId);
		baseVariableTransactionDao.deleteBaseVariableRelationByVariable(param1);
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		for(Integer baseId : baseIdList){
			BaseVariableRelation baseVariableRelation = new BaseVariableRelation();
			baseVariableRelation.setBaseId(baseId);
			baseVariableRelation.setVariableId(variableTransactionId);
			baseVariableRelation.setType(2);
			baseVariableRelation.setCreateUserId(baseVariableTransaction.getUpadteUserId());
			baseVariableRelation.setUpadteUserId(baseVariableTransaction.getUpadteUserId());
			param2.put("baseId", baseId);
			param2.put("sequence", "sequence");
			param2.put("type", 2);
			Integer sequence = baseVariableTransactionDao.getMaxSequence(param2);
			if(sequence == null) {
				sequence = 0;
			}
			baseVariableRelation.setSequence(++sequence);
			baseVariableRelationList.add(baseVariableRelation);
		}
		baseVariableTransactionDao.batchInsertBaseVariableRelation(baseVariableRelationList);
	}

	@Override
	public void deleteBaseVariableTransaction(int variableTransactionId) {
		baseVariableTransactionDao.delete(variableTransactionId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", 2);
		param.put("variableId", variableTransactionId);
		baseVariableTransactionDao.deleteBaseVariableRelationByVariable(param);
	}

	@Override
	public boolean checkNameIsExist(String name) {
		int countName = baseVariableTransactionDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}

	@Override
	public List<BaseVariableTransactionDTO> getAllInfos() {
		List<BaseVariableTransaction> baseVariableTransactionList = baseVariableTransactionDao.selectAllInfos();
		List<BaseVariableTransactionDTO> baseVariableTransactionDTOList = new ArrayList<BaseVariableTransactionDTO>();
		for(BaseVariableTransaction baseVariableTransaction :  baseVariableTransactionList) {
			BaseVariableTransactionDTO baseVariableTransactionDTO = new BaseVariableTransactionDTO();
			BeanUtils.copyProperties(baseVariableTransaction, baseVariableTransactionDTO);
			baseVariableTransactionDTOList.add(baseVariableTransactionDTO);
		}
		return baseVariableTransactionDTOList;
	}
	@Override
	public List<BaseVariableTransactionDTO> getBaseVariableTransactionByName(String name) {
		List<BaseVariableTransaction> baseVariableTransactionList = baseVariableTransactionDao.selectAllByName(name);
		List<BaseVariableTransactionDTO> baseVariableTransactionDTOList = new ArrayList<BaseVariableTransactionDTO>();
		for(BaseVariableTransaction baseVariableTransaction :  baseVariableTransactionList) {
			BaseVariableTransactionDTO baseVariableTransactionDTO = new BaseVariableTransactionDTO();
			BeanUtils.copyProperties(baseVariableTransaction, baseVariableTransactionDTO);
			baseVariableTransactionDTOList.add(baseVariableTransactionDTO);
		}
		return baseVariableTransactionDTOList;
	}
	
	@Override
	public Map<String, Object> getAllRelationEntity() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<BaseElement> baseElementList = baseElementDao.selectAllInfos();
		List<BaseElementDTO> baseElementDTOList = new  ArrayList<BaseElementDTO>();
		for(BaseElement _baseElement : baseElementList){
			BaseElementDTO _baseElementDTO = new BaseElementDTO();
			BeanUtils.copyProperties(_baseElement, _baseElementDTO);
			baseElementDTOList.add(_baseElementDTO);
		}
		resultMap.put("baseElementDTOList", baseElementDTOList);
		List<MetadataField> metadataFieldList = metadataFieldDao.selectAllInfos();
		List<MetadataFieldDTO> metadataFieldDTOList = new  ArrayList<MetadataFieldDTO>();
		for(MetadataField metadataField : metadataFieldList){
			MetadataFieldDTO metadataFieldDTO = new MetadataFieldDTO();
			BeanUtils.copyProperties(metadataField, metadataFieldDTO);
			metadataFieldDTOList.add(metadataFieldDTO);
		}
		resultMap.put("metadataFieldDTOList", metadataFieldDTOList);
		return resultMap;
	}
}
