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
import cn.creditloans.core.dao.af.BaseActionNameDao;
import cn.creditloans.core.dao.af.BaseElementDao;
import cn.creditloans.core.dao.af.BaseVariableSimpleDao;
import cn.creditloans.core.dao.af.MetadataSchemaDao;
import cn.creditloans.core.dao.af.RulesetElementDao;
import cn.creditloans.core.dao.af.ValidatorElementDao;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.MetadataSchemaDTO;
import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.entity.af.BaseElement;
import cn.creditloans.core.entity.af.MetadataSchema;
import cn.creditloans.core.entity.platform.PlatformDepartment;
import cn.creditloans.core.service.BaseElementService;
import cn.creditloans.tools.page.PageModel;
/**
 * @author cong
 *
 */
@Service("baseElementServie")
public class BaseElementServiceImpl implements BaseElementService {
	private static final Log logger = LogFactory.getLog(BaseElementServiceImpl.class);
	@Autowired
	BaseElementDao baseElementDao;
	@Autowired
	PlatformDepartmentDao platformDepartmentDao;
	@Autowired
	MetadataSchemaDao metadataSchemaDao;
	@Autowired
	BaseActionNameDao baseActionNameDao;
	@Autowired
	BaseVariableSimpleDao baseVariableSimpleDao;
	@Autowired
	RulesetElementDao rulesetElementDao;
	@Autowired
	ValidatorElementDao validatorElementDao;
	@Override
	public PageModel<BaseElementDTO> getBaseElementPageList(
			BaseElementDTO baseElementDTO, int currentPage, int pageSize) {
		BaseElement baseElement = new BaseElement();
		BeanUtils.copyProperties(baseElementDTO, baseElement);
		
		PageModel<BaseElementDTO> pm = new PageModel<BaseElementDTO>();
		pm.init(currentPage, pageSize);
		int count = baseElementDao.selectPageCount(baseElement);
		List<BaseElement> baseElementList = baseElementDao.selectPageList(baseElement, pm);
		List<BaseElementDTO> baseElementDtoList = new ArrayList<BaseElementDTO>();
		for(BaseElement _baseElement : baseElementList){
			BaseElementDTO _baseElementDTO = new BaseElementDTO();
			BeanUtils.copyProperties(_baseElement, _baseElementDTO);
			baseElementDtoList.add(_baseElementDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(baseElementDtoList);
		return pm;
	}

	@Override
	public int saveBaseElement(BaseElementDTO baseElementDTO) {
		BaseElement baseElement = new BaseElement();
		BeanUtils.copyProperties(baseElementDTO, baseElement);
		baseElementDao.insert(baseElement);
		int elementId = baseElement.getId();
		return elementId;
	}

	@Override
	public Map<String, Object> getBaseElementById(int elementId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		BaseElementDTO baseElementDTO = new BaseElementDTO();
		BaseElement baseElement = baseElementDao.select(elementId);
		BeanUtils.copyProperties(baseElement, baseElementDTO);
		resultMap.put("baseElementDTO", baseElementDTO);
		Map<String, Object> relationMap = getAllRelationEntity();
		resultMap.putAll(relationMap); 
		return resultMap;
	}

	@Override
	public void updateBaseElement(BaseElementDTO baseElementDTO) {
		BaseElement baseElement = new BaseElement();
		BeanUtils.copyProperties(baseElementDTO, baseElement);
		baseElementDao.update(baseElement);
	}

	@Override
	public int deleteBaseElement(int elementId) {
		int actionCount = baseActionNameDao.selectCountByBaseId(elementId);
		int variableCount = baseVariableSimpleDao.selectCountByBaseId(elementId);
		int rulesetCount = rulesetElementDao.selectCountByBaseId(elementId);
		int validatorCount = validatorElementDao.selectCountByBaseId(elementId);
		if(actionCount>0 || variableCount>0 || rulesetCount>0 || validatorCount>0) {
			return 1;
		}
		baseElementDao.delete(elementId);
		return 0;
	}

	@Override
	public List<BaseElementDTO> selectAllInfos() {
		List<BaseElement> baseElementList = baseElementDao.selectAllInfos();
		List<BaseElementDTO> baseElementDTOList = new  ArrayList<BaseElementDTO>();
		for(BaseElement _baseElement : baseElementList){
			BaseElementDTO _baseElementDTO = new BaseElementDTO();
			BeanUtils.copyProperties(_baseElement, _baseElementDTO);
			baseElementDTOList.add(_baseElementDTO);
		}
		return baseElementDTOList;
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
	
	@Override
	public boolean checkNameIsExist(String name) {
		int countName = baseElementDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}
}
