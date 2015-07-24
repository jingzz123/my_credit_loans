package cn.creditloans.core.service.impl.af;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.af.MetadataFieldDao;
import cn.creditloans.core.dto.af.MetadataFieldDTO;
import cn.creditloans.core.dto.af.MetadataSchemaDTO;
import cn.creditloans.core.entity.af.MetadataField;
import cn.creditloans.core.entity.af.MetadataSchema;
import cn.creditloans.core.service.MetadataFieldService;
import cn.creditloans.tools.page.PageModel;
@Service("metadataFieldServie")
public class MetadataFieldServiceImpl implements MetadataFieldService {
	private static final Log logger = LogFactory.getLog(MetadataFieldServiceImpl.class);
	@Autowired
	MetadataFieldDao metadataFieldDao;
	
	@Override
	public PageModel<MetadataFieldDTO> getMetadataFieldPageList(
			MetadataFieldDTO metadataFieldDTO, int currentPage, int pageSize) {
		MetadataField metadataField = new MetadataField();
		BeanUtils.copyProperties(metadataFieldDTO, metadataField);
		
		PageModel<MetadataFieldDTO> pm = new PageModel<MetadataFieldDTO>();
		pm.init(currentPage, pageSize);
		int count = metadataFieldDao.selectPageCount(metadataField);
		List<MetadataField> metadataFieldList = metadataFieldDao.selectPageList(metadataField, pm);
		List<MetadataFieldDTO> metadataFieldDtoList = new ArrayList<MetadataFieldDTO>();
		for(MetadataField _metadataField : metadataFieldList){
			MetadataFieldDTO _metadataFieldDTO = new MetadataFieldDTO();
			BeanUtils.copyProperties(_metadataField, _metadataFieldDTO);
			metadataFieldDtoList.add(_metadataFieldDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(metadataFieldDtoList);
		return pm;
	}

	@Override
	public int saveMetadataField(MetadataFieldDTO metadataFieldDTO) {
		MetadataField metadataField = new MetadataField();
		BeanUtils.copyProperties(metadataFieldDTO, metadataField);
		metadataFieldDao.insert(metadataField);
		int fieldId = metadataField.getId();
		return fieldId;
	}

	@Override
	public MetadataFieldDTO getMetadataFieldById(int fieldId) {
		MetadataFieldDTO metadataFieldDTO = new MetadataFieldDTO();
		MetadataField metadataField = metadataFieldDao.select(fieldId);
		BeanUtils.copyProperties(metadataField, metadataFieldDTO);
		return metadataFieldDTO;
	}

	@Override
	public void updateMetadataField(MetadataFieldDTO metadataFieldDTO) {
		MetadataField metadataField = new MetadataField();
		BeanUtils.copyProperties(metadataFieldDTO, metadataField);
		metadataFieldDao.update(metadataField);
	}

	@Override
	public void deleteMetadataField(int fieldId) {
		metadataFieldDao.delete(fieldId);
	}

	@Override
	public List<MetadataFieldDTO> getAllInfos() {
		List<MetadataField> metadataFieldList = metadataFieldDao.selectAllInfos();
		List<MetadataFieldDTO> metadataFieldDTOList = new ArrayList<MetadataFieldDTO>();
		MetadataSchemaDTO metadataSchemaDTO = new  MetadataSchemaDTO();
		for(MetadataField _metadataField : metadataFieldList){
			MetadataFieldDTO _metadataFieldDTO = new MetadataFieldDTO();
			_metadataFieldDTO.setId(_metadataField.getId());
			_metadataFieldDTO.setCreateTime(_metadataField.getCreateTime());
			_metadataFieldDTO.setCreateUserId(_metadataField.getCreateUserId());
			_metadataFieldDTO.setDescription(_metadataField.getDescription());
			_metadataFieldDTO.setName(_metadataField.getMetadataSchema().getName()+ "." +_metadataField.getName());
			_metadataFieldDTO.setSchemaId(_metadataField.getSchemaId());
			_metadataFieldDTO.setType(_metadataField.getType());
			_metadataFieldDTO.setUpadteUserId(_metadataField.getUpadteUserId());
			_metadataFieldDTO.setUpdateTime(_metadataField.getUpdateTime());
			BeanUtils.copyProperties(_metadataField.getMetadataSchema(), metadataSchemaDTO);
			_metadataFieldDTO.setMetadataSchemaDTO(metadataSchemaDTO);
			metadataFieldDTOList.add(_metadataFieldDTO);
		}
		return metadataFieldDTOList;
	}
	@Override
	public boolean checkNameIsExist(String name) {
		int countName = metadataFieldDao.selectNameIsExit(name);
		if(countName > 0){
			return false;
		}
		return true;
	}
}
