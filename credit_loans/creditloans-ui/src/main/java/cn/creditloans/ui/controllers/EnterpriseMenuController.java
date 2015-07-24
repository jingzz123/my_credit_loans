package cn.creditloans.ui.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.creditloans.core.dto.enterprise.EnterpriseMenuDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.core.service.EnterpriseMenuService;
import cn.creditloans.core.service.EnterpriseUserService;
import cn.creditloans.tools.tree.TreeNode;
import cn.creditloans.ui.util.cookie.CookieUtil;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value = "/enterprise/menu")
public class EnterpriseMenuController {
	private static final Log logger = LogFactory.getLog(EnterpriseMenuController.class);
	
	@Autowired
	EnterpriseMenuService enterpriseMenuService;
	
	@Autowired
	EnterpriseUserService enterpriseUserService;
	
	/**
	 * 查询所有菜单信息Tree
	 * @return
	 */
	@RequestMapping(value="/getAllMenuTree", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void getAllMenuTree(HttpServletResponse response, HttpServletRequest request) {
		String token = CookieUtil.getToken(request, "enterprise-token");
		EnterpriseUserDTO enterpriseLoginUserDto = enterpriseUserService.getEnterpriseUserDtoFromCache(token);
		int enterpriseId = enterpriseLoginUserDto.getEnterpriseId();
		List<EnterpriseMenuDTO> enterpriseMenuDtoList = enterpriseMenuService.getEnterpriseMenuListByEnterpriseId(enterpriseId);
		
		List<TreeNode> enterpriseMenuTree = new ArrayList<TreeNode>();
		for(EnterpriseMenuDTO enterpriseMenuDTO : enterpriseMenuDtoList){
			TreeNode treeNode = new TreeNode();
			treeNode.setId(enterpriseMenuDTO.getId());
			treeNode.setPId(enterpriseMenuDTO.getFid());
			treeNode.setName(enterpriseMenuDTO.getName());
			//getFid()==0 展开一级节点
			if(enterpriseMenuDTO.getFid()==0){
				treeNode.setOpen(true);
			}
			enterpriseMenuTree.add(treeNode);
		}
		
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(JSON.toJSON(enterpriseMenuTree));
		} catch (Exception e) {
			logger.error(e);
		}
		
	} 
}
