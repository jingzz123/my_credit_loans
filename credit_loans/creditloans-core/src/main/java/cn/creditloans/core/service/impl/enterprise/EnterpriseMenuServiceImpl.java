package cn.creditloans.core.service.impl.enterprise;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.EnterpriseMenuDao;
import cn.creditloans.core.dto.enterprise.EnterpriseMenuDTO;
import cn.creditloans.core.entity.enterprise.EnterpriseMenu;
import cn.creditloans.core.service.EnterpriseMenuService;

@Service("enterpriseMenuService")
public class EnterpriseMenuServiceImpl implements EnterpriseMenuService{
	
	private static final Log logger = LogFactory.getLog(EnterpriseMenuServiceImpl.class);
	
	@Autowired
	EnterpriseMenuDao enterpriseMenuDao;
	
	@Override
	public List<EnterpriseMenuDTO> getEnterpriseMenuList() {
		List<EnterpriseMenu> enterpriseMenuList = enterpriseMenuDao.selectEnterpriseMenuList();
		List<EnterpriseMenuDTO> enterpriseMenuDtoList = new ArrayList<EnterpriseMenuDTO>();
		for(EnterpriseMenu enterpriseMenu : enterpriseMenuList){
			EnterpriseMenuDTO enterpriseMenuDto = new EnterpriseMenuDTO();
			BeanUtils.copyProperties(enterpriseMenu, enterpriseMenuDto);
			enterpriseMenuDtoList.add(enterpriseMenuDto);
		}
		return enterpriseMenuDtoList;
	}

	@Override
	public List<EnterpriseMenuDTO> getEnterpriseMenuListByEnterpriseId(int enterpriseId) {
		List<Integer> menuIdList = enterpriseMenuDao.selectEnterpriseOwnMenuIdsByEnterpriseId(enterpriseId);
		List<EnterpriseMenu> enterpriseMenuList = enterpriseMenuDao.selectEnterpriseMenuListByIds(menuIdList);
		List<EnterpriseMenuDTO> enterpriseMenuDtoList = new ArrayList<EnterpriseMenuDTO>();
		for(EnterpriseMenu enterpriseMenu : enterpriseMenuList){
			EnterpriseMenuDTO enterpriseMenuDto = new EnterpriseMenuDTO();
			BeanUtils.copyProperties(enterpriseMenu, enterpriseMenuDto);
			enterpriseMenuDtoList.add(enterpriseMenuDto);
		}
		return enterpriseMenuDtoList;
	}

}
