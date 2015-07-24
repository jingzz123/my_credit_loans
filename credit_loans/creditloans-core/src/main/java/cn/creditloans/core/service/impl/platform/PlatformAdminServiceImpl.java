package cn.creditloans.core.service.impl.platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.PlatformMenuDao;
import cn.creditloans.core.dao.PlatformRoleDao;
import cn.creditloans.core.dao.PlatformUserDao;
import cn.creditloans.core.dto.platform.PlatformMenuDTO;
import cn.creditloans.core.entity.platform.PlatformMenu;
import cn.creditloans.core.entity.platform.PlatformRole;
import cn.creditloans.core.entity.platform.PlatformRoleMenu;
import cn.creditloans.core.entity.platform.PlatformUser;
import cn.creditloans.core.entity.platform.PlatformUserRole;
import cn.creditloans.core.service.PlatformAdminService;

@Service("platformAdminService")
public class PlatformAdminServiceImpl implements PlatformAdminService {
	private static final Log logger = LogFactory.getLog(PlatformAdminServiceImpl.class);
	
	@Autowired
	PlatformUserDao platformUserDao;
	
	@Autowired
	PlatformRoleDao platformRoleDao;
	
	@Autowired
	PlatformMenuDao platformMenuDao;
	
	@Override
	public List<PlatformMenuDTO> obtainPlatformUserMenuByUserId(int platformUserId) {
		// 获取platformUser的权限
		PlatformUser _platformUser = platformUserDao.selectCascadePlatformUserByUserId(platformUserId);
		List<PlatformUserRole> platformUserRoleList = _platformUser.getPlatformUserRoleList();
		
		// 根据platformUserRoleList 查询 该用户的role, 并处理该用户的菜单
		List<PlatformRoleMenu> platformRoleMenuList = new ArrayList<PlatformRoleMenu>();
		for(PlatformUserRole platformUserRole : platformUserRoleList){
			PlatformRole _platformRole = platformRoleDao.selectCascadePlatformRoleMenuByRoleId(platformUserRole.getRoleId());
			platformRoleMenuList.addAll(_platformRole.getPlatformRoleMenuList());
		}
		
		// 去重处理
		Set<Integer> menuIdSet = new HashSet<Integer>();
		for (PlatformRoleMenu platformRoleMenu : platformRoleMenuList) {
			menuIdSet.add(platformRoleMenu.getMenuId());
		}

		// FIXME : 在用户没有权限的时候(就是menuIdSet中没有数据时会报错),但这种情况应该不会出现
		List<PlatformMenu> platformMenuList = platformMenuDao.selectPlatformMenuListByIds((new ArrayList<Integer>(menuIdSet)));

		// 转menu dto
		ArrayList<PlatformMenuDTO> platformMenuDtoList = new ArrayList<PlatformMenuDTO>();
		for (PlatformMenu platformMenu : platformMenuList) {
			PlatformMenuDTO _platformMenuDto = new PlatformMenuDTO();
			BeanUtils.copyProperties(platformMenu, _platformMenuDto);
			
			platformMenuDtoList.add(_platformMenuDto);
		}
		
		// 排序
		Collections.sort(platformMenuDtoList, new Comparator<PlatformMenuDTO>() {

			@Override
			public int compare(PlatformMenuDTO o1, PlatformMenuDTO o2) {
				return o1.getSequence() - o2.getSequence();
			}
		});
		
		return platformMenuDtoList;
	}

}
