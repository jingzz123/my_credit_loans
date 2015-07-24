package cn.creditloans.core.service.impl.platform;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.PlatformMenuDao;
import cn.creditloans.core.dto.platform.PlatformMenuDTO;
import cn.creditloans.core.entity.platform.PlatformMenu;
import cn.creditloans.core.service.PlatformMenuService;

@Service("platformMenuService")
public class PlatformMenuServiceImpl implements PlatformMenuService {
	private final static Log logger = LogFactory.getLog(PlatformMenuServiceImpl.class);
	
	@Autowired
	PlatformMenuDao platformMenuDao;
	
	@Override
	public List<PlatformMenuDTO> getPlatformMenuList() {
		List<PlatformMenu> platformMenuList = platformMenuDao.selectPlatformMenuList();
		List<PlatformMenuDTO> platformMenuDtoList = new ArrayList<PlatformMenuDTO>();
		for(PlatformMenu platformMenu : platformMenuList){
			PlatformMenuDTO platformMenuDto = new PlatformMenuDTO();
			BeanUtils.copyProperties(platformMenu, platformMenuDto);
			platformMenuDtoList.add(platformMenuDto);
		}
		return platformMenuDtoList;
	}

}
