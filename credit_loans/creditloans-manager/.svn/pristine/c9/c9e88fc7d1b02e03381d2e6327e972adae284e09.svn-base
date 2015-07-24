package cn.creditloans.manager.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.creditloans.core.dto.platform.PlatformMenuDTO;
import cn.creditloans.core.service.PlatformMenuService;
import cn.creditloans.tools.tree.TreeNode;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value = "/platform/menu")
public class PlatformMenuController {
	private static final Log logger = LogFactory.getLog(PlatformMenuController.class);
	
	@Autowired
	PlatformMenuService platformMenuService;
	
	/**
	 * 查询所有菜单信息Tree
	 * @return
	 */
	@RequestMapping(value="/getAllMenuTree", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void getAllMenuTree(HttpServletResponse response) {
		List<PlatformMenuDTO> platformMenuDtoList = platformMenuService.getPlatformMenuList();
		
		List<TreeNode> platformMenuTree = new ArrayList<TreeNode>();
		for(PlatformMenuDTO platformMenuDTO : platformMenuDtoList){
			TreeNode treeNode = new TreeNode();
			treeNode.setId(platformMenuDTO.getId());
			treeNode.setPId(platformMenuDTO.getFid());
			treeNode.setName(platformMenuDTO.getName());
			//getFid()==0 展开一级节点
			if(platformMenuDTO.getFid()==0){
				treeNode.setOpen(true);
			}
			platformMenuTree.add(treeNode);
		}
		
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(JSON.toJSONString(platformMenuTree));
		} catch (Exception e) {
			logger.error(e);
		}
		
	} 
}
