package cn.creditloans.core.service.impl.enterprise;

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

import cn.creditloans.core.dao.EnterpriseMenuDao;
import cn.creditloans.core.dao.EnterpriseRoleDao;
import cn.creditloans.core.dao.EnterpriseUserDao;
import cn.creditloans.core.dto.enterprise.EnterpriseMenuDTO;
import cn.creditloans.core.entity.enterprise.EnterpriseMenu;
import cn.creditloans.core.entity.enterprise.EnterpriseRole;
import cn.creditloans.core.entity.enterprise.EnterpriseRoleMenu;
import cn.creditloans.core.entity.enterprise.EnterpriseUser;
import cn.creditloans.core.entity.enterprise.EnterpriseUserRole;
import cn.creditloans.core.service.EnterpriseAdminService;

@Service("enterpriseAdminService")
public class EnterpriseAdminServiceImpl implements EnterpriseAdminService {
	private static final Log logger = LogFactory
			.getLog(EnterpriseAdminServiceImpl.class);

	@Autowired
	EnterpriseUserDao enterpriseUserDao;

	@Autowired
	EnterpriseRoleDao enterpriseRoleDao;

	@Autowired
	EnterpriseMenuDao enterpriseMenuDao;

	@Override
	public List<EnterpriseMenuDTO> obtainEnterpriseUserMenuByUserId(
			int enterpriseUserId) {
		// 获取enterpriseUser的权限
		EnterpriseUser _enterpriseUser = enterpriseUserDao
				.selectCascadeEnterpriseUserByUserId(enterpriseUserId);
		List<EnterpriseUserRole> enterpriseUserRoleList = _enterpriseUser
				.getEnterpriseUserRoleList();

		// 根据enterpriseUserRoleList 查询 该用户的role, 并处理该用户的菜单
		List<EnterpriseRoleMenu> enterpriseRoleMenuList = new ArrayList<EnterpriseRoleMenu>();
		for (EnterpriseUserRole enterpriseUserRole : enterpriseUserRoleList) {
			EnterpriseRole _enterpriseRole = enterpriseRoleDao
					.selectCascadeEnterpriseRoleMenuByRoleId(enterpriseUserRole
							.getRoleId());
			enterpriseRoleMenuList.addAll(_enterpriseRole
					.getEnterpriseRoleMenuList());
		}

		// 去重处理
		Set<Integer> menuIdSet = new HashSet<Integer>();
		for (EnterpriseRoleMenu enterpriseRoleMenu : enterpriseRoleMenuList) {
			menuIdSet.add(enterpriseRoleMenu.getMenuId());
		}

		// FIXME : 在用户没有权限的时候(就是menuIdSet中没有数据时会报错),但这种情况应该不会出现
		List<EnterpriseMenu> enterpriseMenuList = enterpriseMenuDao
				.selectEnterpriseMenuListByIds((new ArrayList<Integer>(
						menuIdSet)));

		// 转menu dto
		ArrayList<EnterpriseMenuDTO> enterpriseMenuDtoList = new ArrayList<EnterpriseMenuDTO>();
		for (EnterpriseMenu enterpriseMenu : enterpriseMenuList) {
			EnterpriseMenuDTO _enterpriseMenuDto = new EnterpriseMenuDTO();
			BeanUtils.copyProperties(enterpriseMenu, _enterpriseMenuDto);

			enterpriseMenuDtoList.add(_enterpriseMenuDto);
		}

		// 排序
		Collections.sort(enterpriseMenuDtoList,
				new Comparator<EnterpriseMenuDTO>() {

					@Override
					public int compare(EnterpriseMenuDTO o1,
							EnterpriseMenuDTO o2) {
						return o1.getSequence() - o2.getSequence();
					}
				});

		return enterpriseMenuDtoList;
	}

}
