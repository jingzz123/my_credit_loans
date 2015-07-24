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
import cn.creditloans.core.dao.af.ValidatorElementDao;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.ValidatorElementDTO;
import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.entity.af.BaseElement;
import cn.creditloans.core.entity.af.ValidatorElement;
import cn.creditloans.core.entity.platform.PlatformDepartment;
import cn.creditloans.core.service.ValidatorElementService;
import cn.creditloans.tools.page.PageModel;
@Service("validatorElementServie")
public class ValidatorElementServiceImpl implements ValidatorElementService {
	private static final Log logger = LogFactory.getLog(ValidatorElementServiceImpl.class);
	@Autowired
	ValidatorElementDao validatorElementDao;
	@Autowired
	PlatformDepartmentDao platformDepartmentDao;
	@Autowired
	BaseElementDao baseElementDao;
	@Override
	public PageModel<ValidatorElementDTO> getValidatorElementPageList(
			ValidatorElementDTO validatorElementDTO, int currentPage, int pageSize) {
		ValidatorElement validatorElement = new ValidatorElement();
		BeanUtils.copyProperties(validatorElementDTO, validatorElement);
		
		PageModel<ValidatorElementDTO> pm = new PageModel<ValidatorElementDTO>();
		pm.init(currentPage, pageSize);
		int count = validatorElementDao.selectPageCount(validatorElement);
		List<ValidatorElement> validatorElementList = validatorElementDao.selectPageList(validatorElement, pm);
		List<ValidatorElementDTO> validatorElementDtoList = new ArrayList<ValidatorElementDTO>();
		for(ValidatorElement _validatorElement : validatorElementList){
			ValidatorElementDTO _validatorElementDTO = new ValidatorElementDTO();
			BeanUtils.copyProperties(_validatorElement, _validatorElementDTO);
			validatorElementDtoList.add(_validatorElementDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(validatorElementDtoList);
		return pm;
	}

	@Override
	public int saveValidatorElement(ValidatorElementDTO validatorElementDTO) {
		ValidatorElement validatorElement = new ValidatorElement();
		BeanUtils.copyProperties(validatorElementDTO, validatorElement);
		validatorElementDao.insert(validatorElement);
		int validatorElementId = validatorElement.getId();
		return validatorElementId;
	}

	@Override
	public Map<String, Object> getValidatorElementById(int validatorElementId) {
		Map<String, Object> resultMap = new HashMap<>();
		ValidatorElementDTO validatorElementDTO = new ValidatorElementDTO();
		ValidatorElement validatorElement = validatorElementDao.select(validatorElementId);
		BeanUtils.copyProperties(validatorElement, validatorElementDTO);
		validatorElementDTO.setOldName(validatorElement.getName());
		resultMap.put("validatorElementDTO", validatorElementDTO);
		List<PlatformDepartment> platformDepartmentList = platformDepartmentDao.selectPlatformDepartmentList();
		List<PlatformDepartmentDTO> platformDepartmentDTOList = new ArrayList<PlatformDepartmentDTO>();
		for(PlatformDepartment platformDepartment : platformDepartmentList){
			PlatformDepartmentDTO platformDepartmentDTO = new PlatformDepartmentDTO();
			BeanUtils.copyProperties(platformDepartment, platformDepartmentDTO);
			platformDepartmentDTOList.add(platformDepartmentDTO);
		}
		resultMap.put("platformDepartmentDTOList", platformDepartmentDTOList);
		
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

	@Override
	public void updateValidatorElement(ValidatorElementDTO validatorElementDTO) {
		ValidatorElement validatorElement = new ValidatorElement();
		BeanUtils.copyProperties(validatorElementDTO, validatorElement);
		validatorElementDao.update(validatorElement);
	}

	@Override
	public void deleteValidatorElement(int validatorElementId) {
		validatorElementDao.delete(validatorElementId);
	}
	@Override
	public boolean checkNameIsExist(String name) {
		int countName = validatorElementDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}

	@Override
	public Map<String, Object> getAllInfos() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PlatformDepartment> platformDepartmentList = platformDepartmentDao.selectPlatformDepartmentList();
		List<PlatformDepartmentDTO> platformDepartmentDTOList = new ArrayList<PlatformDepartmentDTO>();
		for(PlatformDepartment platformDepartment : platformDepartmentList){
			PlatformDepartmentDTO platformDepartmentDTO = new PlatformDepartmentDTO();
			BeanUtils.copyProperties(platformDepartment, platformDepartmentDTO);
			platformDepartmentDTOList.add(platformDepartmentDTO);
		}
		resultMap.put("platformDepartmentDTOList", platformDepartmentDTOList);
		
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
