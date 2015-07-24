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
import cn.creditloans.core.dao.af.MetadataFieldDao;
import cn.creditloans.core.dao.af.MetadataSchemaDao;
import cn.creditloans.core.dto.af.MetadataSchemaDTO;
import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.entity.af.MetadataRelation;
import cn.creditloans.core.entity.af.MetadataSchema;
import cn.creditloans.core.entity.platform.PlatformDepartment;
import cn.creditloans.core.service.MetadataSchemaService;
import cn.creditloans.tools.page.PageModel;
@Service("metadataSchemaServie")
public class MetadataSchemaServiceImpl implements MetadataSchemaService {
	private static final Log logger = LogFactory.getLog(MetadataSchemaServiceImpl.class);
	@Autowired
	MetadataSchemaDao metadataSchemaDao;
	@Autowired
	PlatformDepartmentDao platformDepartmentDao;
	@Autowired
	MetadataFieldDao metadataFieldDao;
	
	@Override
	public PageModel<MetadataSchemaDTO> getMetadataSchemaPageList(
			MetadataSchemaDTO metadataSchemaDTO, int currentPage, int pageSize) {
		MetadataSchema metadataSchema = new MetadataSchema();
		BeanUtils.copyProperties(metadataSchemaDTO, metadataSchema);
		
		PageModel<MetadataSchemaDTO> pm = new PageModel<MetadataSchemaDTO>();
		pm.init(currentPage, pageSize);
		int count = metadataSchemaDao.selectPageCount(metadataSchema);
		List<MetadataSchema> metadataSchemaList = metadataSchemaDao.selectPageList(metadataSchema, pm);
		List<MetadataSchemaDTO> metadataSchemaDtoList = new ArrayList<MetadataSchemaDTO>();
		for(MetadataSchema _metadataSchema : metadataSchemaList){
			MetadataSchemaDTO _metadataSchemaDTO = new MetadataSchemaDTO();
			BeanUtils.copyProperties(_metadataSchema, _metadataSchemaDTO);
			metadataSchemaDtoList.add(_metadataSchemaDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(metadataSchemaDtoList);
		return pm;
	}

	@Override
	public int saveMetadataSchema(MetadataSchemaDTO metadataSchemaDTO) {
		MetadataSchema metadataSchema = new MetadataSchema();
		BeanUtils.copyProperties(metadataSchemaDTO, metadataSchema);
		metadataSchemaDao.insert(metadataSchema);
		int schemaId = metadataSchema.getId();
		// 处理该schame所属的schema
		List<Integer> mainSchemaIdList = metadataSchemaDTO.getMainSchemaIdList();
		if(mainSchemaIdList != null && mainSchemaIdList.size() != 0) {
			List<MetadataRelation> metadataRelationList = new ArrayList<MetadataRelation>();
			Map<String, Object> param = new HashMap<String, Object>();

			for(Integer mainSchemaId : mainSchemaIdList){
				MetadataRelation metadataRelation = new MetadataRelation();
				metadataRelation.setMainSchemaId(mainSchemaId);
				metadataRelation.setChildSchemaId(schemaId);
				metadataRelation.setCreateUserId(metadataSchema.getCreateUserId());
				metadataRelation.setUpadteUserId(metadataSchema.getUpadteUserId());
				param.put("mainSchemaId", mainSchemaId);
				param.put("sequence", "sequence");
				Integer sequence = metadataSchemaDao.getMaxSequence(param);
				if(sequence == null) {
					sequence = 0;
				}
				metadataRelation.setSequence(++sequence);
				metadataRelationList.add(metadataRelation);
			}
			metadataSchemaDao.batchInsertMetadataRelation(metadataRelationList);
		}
		return schemaId;
	}

	@Override
	public Map<String, Object> getMetadataSchemaById(int schemaId, String isNested) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MetadataSchemaDTO metadataSchemaDTO = new MetadataSchemaDTO();
		MetadataSchema metadataSchema = null;
		if(isNested.equals("N")){
			metadataSchema = metadataSchemaDao.selectChildById(schemaId);
		}else{
			metadataSchema = metadataSchemaDao.select(schemaId);
		}
		BeanUtils.copyProperties(metadataSchema, metadataSchemaDTO);
		metadataSchemaDTO.setOldName(metadataSchema.getName());
		//处理schema所在的schema
		List<MetadataSchema> metadataSchemaList = metadataSchemaDao.selectMainSchema();
		List<MetadataRelation> metadataRelationList = metadataSchema.getMetadataRelationList();
		List<MetadataSchemaDTO> metadataSchemaDTOList = new ArrayList<MetadataSchemaDTO>();
		for(MetadataSchema _metadataSchema : metadataSchemaList) {
			MetadataSchemaDTO _metadataSchemaDTO = new MetadataSchemaDTO();
			BeanUtils.copyProperties(_metadataSchema, _metadataSchemaDTO);
			if(isNested.equals("N")) {
				for(MetadataRelation metadataRelation : metadataRelationList) {
					if(_metadataSchema.getId()== metadataRelation.getMainSchemaId()) {
						_metadataSchemaDTO.setSchemaFlag(1);
					}
				}
			}
			if(isNested.equals("N") || _metadataSchema.getId() != schemaId) {
				metadataSchemaDTOList.add(_metadataSchemaDTO);
			}
		}
		metadataSchemaDTO.setMetadataSchemaDTOList(metadataSchemaDTOList);
		resultMap.put("metadataSchemaDTO", metadataSchemaDTO);
		List<PlatformDepartment> platformDepartmentList = platformDepartmentDao.selectPlatformDepartmentList();
		List<PlatformDepartmentDTO> platformDepartmentDtoList = new  ArrayList<PlatformDepartmentDTO>();
		for(PlatformDepartment platformDepartment : platformDepartmentList){
			PlatformDepartmentDTO platformDepartmentDTO = new PlatformDepartmentDTO();
			BeanUtils.copyProperties(platformDepartment, platformDepartmentDTO);
			platformDepartmentDtoList.add(platformDepartmentDTO);
		}
		resultMap.put("platformDepartmentDtoList", platformDepartmentDtoList);
		return resultMap;
	}

	@Override
	public void updateMetadataSchema(MetadataSchemaDTO metadataSchemaDTO) {
		MetadataSchema metadataSchema = new MetadataSchema();
		BeanUtils.copyProperties(metadataSchemaDTO, metadataSchema);
		metadataSchemaDao.update(metadataSchema);
		//更新关系
		int schemaId = metadataSchema.getId();
		List<Integer> mainSchemaIdList = metadataSchemaDTO.getMainSchemaIdList();
		List<MetadataRelation> metadataRelationList = new ArrayList<MetadataRelation>();
		metadataSchemaDao.deleteMetadataRelationByChildSchemaId(schemaId);
		
		if(mainSchemaIdList != null && mainSchemaIdList.size() != 0) {
			Map<String, Object> param = new HashMap<String, Object>();

			for(Integer mainSchemaId : mainSchemaIdList){
				MetadataRelation metadataRelation = new MetadataRelation();
				metadataRelation.setMainSchemaId(mainSchemaId);
				metadataRelation.setChildSchemaId(schemaId);
				metadataRelation.setCreateUserId(metadataSchema.getCreateUserId());
				metadataRelation.setUpadteUserId(metadataSchema.getUpadteUserId());
				param.put("mainSchemaId", mainSchemaId);
				param.put("sequence", "sequence");
				Integer sequence = metadataSchemaDao.getMaxSequence(param);
				if(sequence == null) {
					sequence = 0;
				}
				metadataRelation.setSequence(++sequence);
				metadataRelationList.add(metadataRelation);
			}
			metadataSchemaDao.batchInsertMetadataRelation(metadataRelationList);
		}
	}

	@Override
	public int deleteMetadataSchema(int schemaId, String isNested) {
		int childSchemaCount = 0;
		if(isNested.equals("Y")) {
			childSchemaCount = metadataSchemaDao.selectCountBySchemaId(schemaId);
		}
		int fieldCount = metadataFieldDao.selectCountBySchemaId(schemaId);
		if(childSchemaCount > 0 || fieldCount  > 0) {
			return 1;
		}
		metadataSchemaDao.delete(schemaId);
		if(isNested.equals("N")) {
			metadataSchemaDao.deleteMetadataRelationByChildSchemaId(schemaId);
		}else{
			metadataSchemaDao.deleteMetadataRelationByMainSchemaId(schemaId);
		}
		return 0;
	}

	@Override
	public List<MetadataSchemaDTO> selectAllInfos() {
		List<MetadataSchema> metadataSchemaList = metadataSchemaDao.selectAllInfos();
		List<MetadataSchemaDTO> metadataSchemaDtoList = new ArrayList<MetadataSchemaDTO>();
		for(MetadataSchema _metadataSchema : metadataSchemaList){
			MetadataSchemaDTO _metadataSchemaDTO = new MetadataSchemaDTO();
			BeanUtils.copyProperties(_metadataSchema, _metadataSchemaDTO);
			metadataSchemaDtoList.add(_metadataSchemaDTO);
		}
		return metadataSchemaDtoList;
	}

	@Override
	public List<MetadataSchemaDTO> selectMainSchema() {
		List<MetadataSchema> metadataSchemaList = metadataSchemaDao.selectMainSchema();
		List<MetadataSchemaDTO> metadataSchemaDtoList = new ArrayList<MetadataSchemaDTO>();
		for(MetadataSchema _metadataSchema : metadataSchemaList){
			MetadataSchemaDTO _metadataSchemaDTO = new MetadataSchemaDTO();
			BeanUtils.copyProperties(_metadataSchema, _metadataSchemaDTO);
			metadataSchemaDtoList.add(_metadataSchemaDTO);
		}
		return metadataSchemaDtoList;
	}

	@Override
	public boolean checkNameIsExist(String name) {
		int countName = metadataSchemaDao.selectNameIsExit(name);
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
		List<MetadataSchema> metadataSchemaList = metadataSchemaDao.selectAllInfos();
		List<MetadataSchemaDTO> metadataSchemaDTOList = new  ArrayList<MetadataSchemaDTO>();
		for(MetadataSchema metadataSchema : metadataSchemaList){
			MetadataSchemaDTO metadataSchemaDTO = new MetadataSchemaDTO();
			BeanUtils.copyProperties(metadataSchema, metadataSchemaDTO);
			metadataSchemaDTOList.add(metadataSchemaDTO);
		}
		resultMap.put("metadataSchemaDTOList", metadataSchemaDTOList);
		return resultMap;
	}
}
