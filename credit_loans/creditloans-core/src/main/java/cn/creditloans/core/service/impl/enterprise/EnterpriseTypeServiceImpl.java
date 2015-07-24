package cn.creditloans.core.service.impl.enterprise;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.EnterpriseTypeDao;
import cn.creditloans.core.dto.enterprise.EnterpriseTypeDTO;
import cn.creditloans.core.entity.enterprise.EnterpriseType;
import cn.creditloans.core.service.EnterpriseTypeService;
import cn.creditloans.tools.page.PageModel;

@Service("enterpriseTypeService")
public class EnterpriseTypeServiceImpl implements EnterpriseTypeService {

	private static final Log logger = LogFactory
			.getLog(EnterpriseTypeServiceImpl.class);

	// 删除成功
	private static final int DELETE_SUCCESS = 0;
	// 删除失败
	private static final int DELETE_FAIL = 1;
	// 删除提示
	private static final int DELETE_TIP = 2;

	// 注入企业类型DAO
	@Autowired
	EnterpriseTypeDao enterpriseTypeDao;

	@Override
	public PageModel<EnterpriseTypeDTO> selectEnterpriseTypePageList(
			EnterpriseType enterpriseType, int currentPage, int pageSize) {
		PageModel<EnterpriseTypeDTO> pageModel = new PageModel<EnterpriseTypeDTO>();
		// 更新当前页信息
		pageModel.init(currentPage, pageSize);
		// 获取企业类型总条数
		int pageCount = enterpriseTypeDao.selectEnterpriseTypePageCount();
		// 查询出企业类型结果集
		pageModel.setTotal(pageCount);
		List<EnterpriseType> enterpriseTypes = enterpriseTypeDao
				.selectEnterpriseTypeList(pageModel, enterpriseType);
		List<EnterpriseTypeDTO> enterpriseTypeDTOs = new ArrayList<EnterpriseTypeDTO>();
		if (enterpriseTypes != null && enterpriseTypes.size() > 0) {
			EnterpriseTypeDTO enterpriseTypeDTO = null;
			for (EnterpriseType type : enterpriseTypes) {
				enterpriseTypeDTO = new EnterpriseTypeDTO();
				BeanUtils.copyProperties(type, enterpriseTypeDTO);
			}
		}
		pageModel.setDatas(enterpriseTypeDTOs);
		return pageModel;
	}

	@Override
	public int deleteEnterpriseType(int id) {
		// 判断企业类型是否已做关联,如果已做关联禁止删除
		try {
			int count = enterpriseTypeDao.selectEnterpriseType(id);
			if (count > 0) {
				return DELETE_TIP;
			}
			// 删除
			enterpriseTypeDao.deleteEnterpriseTypeById(id);
		} catch (Exception e) {
			logger.info("删除企业类型失败");
			return DELETE_FAIL;
		}
		return DELETE_SUCCESS;
	}

	@Override
	public boolean insertEnterpriseType(EnterpriseTypeDTO enterpriseTypeDTO) {
		EnterpriseType enterpriseType = new EnterpriseType();
		BeanUtils.copyProperties(enterpriseTypeDTO, enterpriseType);
		boolean isSuccess = false;
		try {
			enterpriseTypeDao.insertEnterpriseType(enterpriseType);
			isSuccess = true;
		} catch (Exception e) {
			isSuccess = false;
			logger.info("<<<<<<<<<<<<<<<<<<<<<<添加企业类型失败");
		}
		return isSuccess;
	}

	@Override
	public boolean updateEnterpriseType(EnterpriseTypeDTO enterpriseTypeDTO) {
		EnterpriseType enterpriseType = new EnterpriseType();
		BeanUtils.copyProperties(enterpriseTypeDTO, enterpriseType);
		boolean isSucc = false;
		try {
			enterpriseTypeDao.updateEnterpriseType(enterpriseType);
			isSucc = true;
		} catch (Exception e) {
			isSucc = false;
			logger.info("<<<<<<<<<<<<<<<<<<<<<<更新企业类型失败");
		}
		return isSucc;
	}

	@Override
	public EnterpriseTypeDTO selectEnterpriseTypeById(int id) {
		EnterpriseType enterpriseType = enterpriseTypeDao
				.selectEnterpriseTypeById(id);
		EnterpriseTypeDTO enterpriseTypeDTO = new EnterpriseTypeDTO();
		if (enterpriseType != null) {
			BeanUtils.copyProperties(enterpriseType, enterpriseTypeDTO);
		}
		return enterpriseTypeDTO;
	}

	@Override
	public boolean selectEnterpriseName(String name) {
		int nameCount = enterpriseTypeDao.selectEnterpriseName(name);
		if (nameCount > 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<EnterpriseTypeDTO> selectEnterpriseTypeList() {
		List<EnterpriseType> enterpriseTypes = enterpriseTypeDao
				.selectTypeList();
		List<EnterpriseTypeDTO> enterpriseTypeDTOs = new ArrayList<EnterpriseTypeDTO>();
		if (enterpriseTypes != null && enterpriseTypes.size() > 0) {
			EnterpriseTypeDTO enterpriseTypeDTO = null;
			for (EnterpriseType enterpriseType : enterpriseTypes) {
				enterpriseTypeDTO = new EnterpriseTypeDTO();
				BeanUtils.copyProperties(enterpriseType, enterpriseTypeDTO);
				enterpriseTypeDTOs.add(enterpriseTypeDTO);
			}
		}
		return enterpriseTypeDTOs;
	}

}