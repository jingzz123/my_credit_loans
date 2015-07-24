package cn.creditloans.manager.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.entity.platform.PlatformDepartment;
import cn.creditloans.core.service.PlatformDepartmentService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.tree.TreeNode;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value ="/platform/dept")
public class PlatformDeptController {
	//日志输出类
	private static final Log loger = LogFactory.getLog(PlatformDeptController.class);
	
	// 删除失败
	private static final int DELETE_FAIL = 1;
	
	//注入平台用户service
	@Autowired
	PlatformUserService platformUserService;
	
	//注入平台部门service
	@Autowired
	PlatformDepartmentService platformDepartmentService;
	
	// 获取企业组织结构信息列表
	@RequestMapping(value = "platformDepartList", method = {RequestMethod.GET, RequestMethod.POST })
	public String platformDepartList(Model model, @RequestParam(value = "currentPage", required = false)String _currentPage, String departName) {
		// 默认当前页信息
		int currentPage = 1;
		if (StringUtils.isNotEmpty(_currentPage)) {
			currentPage = Integer.parseInt(_currentPage);
		}
		// 获取每页最大条数
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
						.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		PageModel<PlatformDepartmentDTO> pageModel = platformDepartmentService.selectDepartmentList(currentPage, pageSize, departName);
		model.addAttribute("department",pageModel);
		model.addAttribute("departName", departName);
		return "/platform/dept/platform_dept_list";
	}

	/**
	 * 获取组织结构
	 */
	@RequestMapping(value = "departmentTree", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody void departmentTree(HttpServletResponse response) {
		List<TreeNode> results = platformDepartmentService.selectDepartmentTree();
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(JSON.toJSONString(results));
		} catch (IOException e) {
			loger.info("<<<<<<<<<<<<<<<<<<<<<<部门树json转化失败");
			e.printStackTrace();
		}
	}

	// 跳转部门信息输入页
	@RequestMapping(value = "departmentInput", method = { RequestMethod.GET, RequestMethod.POST })
	public String departmentInput() {
		return "/platform/dept/platform_dept_input";
	}

	/**
	 * 保存或者更新部门信息
	 * @param department
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdateDepartment", method = {RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody boolean saveOrUpdateDepartment(@ModelAttribute PlatformDepartmentDTO department) {
		return platformDepartmentService.saveOrUpdateDepartment(department);
	}

	/**
	 * 重名验证
	 * @param name
	 * @param fid
	 * @return
	 */
	@RequestMapping(value = "checkDepartmentName", method = {RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody boolean checkDepartmentName(@RequestParam("name") String name, @RequestParam("fid") String fid,
			@RequestParam("id") String id) {
		if (name != null) {
			int parentId = 0;
			int departId = 0;
			if (fid != null && "" != fid) {
				parentId = Integer.parseInt(fid);
			}
			if (id != null && "" != id) {
				departId = Integer.parseInt(id);
			}
			return platformDepartmentService.checkDepartmentName(name, parentId, departId);
		}
		return true;
	}
	
	/**删除部门信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteDepartmentName", method = {RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody int deleteDepartmentName(@RequestParam("id") String id) {
		if(id != null && "" != id){
			return platformDepartmentService.deleteDepartmentInfo(Integer.parseInt(id));
		}
		return DELETE_FAIL;
	}

	/**
	 * 验证部门编号不能重复
	 * @return
	 */
	@RequestMapping(value = "checkDepartmentCode", method = {RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody boolean checkDepartmentCode(@RequestParam("code") String code, @RequestParam("id") String id){
		int departCode = 0;
		int departId = 0;
		if(code != null && "" != code){
			departCode  = Integer.parseInt(code);
		}
		if(id != null && "" != id){
			departId = Integer.parseInt(id);
		}
		return platformDepartmentService.checkDepartmentCode(departCode, departId);
	}
	
	/**
	 * 编辑部门信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateDepartmentInfo", method = {RequestMethod.GET, RequestMethod.POST })
	public String updateDepartmentInfo(Model model, @RequestParam("id") String id){
		if(id != null && "" != id){			
			PlatformDepartmentDTO department = platformDepartmentService.selectPlatformDepart(Integer.parseInt(id));
			model.addAttribute("department", department);
		}
		return "/platform/dept/platform_dept_input";
	}
}
