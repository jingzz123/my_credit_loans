package cn.creditloans.manager.controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.creditloans.core.dto.enterprise.EnterpriseDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseTypeDTO;
import cn.creditloans.core.service.EnterpriseService;
import cn.creditloans.core.service.EnterpriseTypeService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;
/**
 * 企业信息
 * @author haowg
 *
 */

@Controller
@RequestMapping(value = "/enterprise/info")
public class EnterpriseInfoController {
	private static final Log logger = LogFactory.getLog(EnterpriseInfoController.class);
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	EnterpriseTypeService enterpriseTypeService;

	//删除失败
	private static final int DELETE_FAIL = 1;
	
	/**
	 * 跳转企业信息添加页面 
	 * @return
	 */
	@RequestMapping(value = "enterpriseInfoInput",method = {RequestMethod.GET, RequestMethod.POST})
	public String enterpriseInfoInput(Model model){
		List<EnterpriseTypeDTO> enterpriseTypes = enterpriseTypeService.selectEnterpriseTypeList();
		model.addAttribute("enterpriseTypes",enterpriseTypes);
		return "enterprise/info/enterprise_info_input";
	}
	
	/**
	 * 获取企业信息列表
	 * @param model
	 * @param enterpriseType  企业类型
	 * @param enterpriseName  企业名称
	 * @param _currentPage    当前页
	 * @return
	 */
	
	@RequestMapping(value = "enterpriseInfoList",method = {RequestMethod.GET, RequestMethod.POST})
	public String enterpriseInfoList(Model model, EnterpriseDTO enterpriseDto, String currentPage){
		//默认当前页信息
		if(StringUtils.isEmpty(currentPage)){
		    currentPage = "1";
		}
		//获取每页最大条数
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<EnterpriseDTO> pageModel = enterpriseService.enterpriseInfoList(enterpriseDto, Integer.parseInt(currentPage), pageSize);
		model.addAttribute("enterprisePages", pageModel);
		List<EnterpriseTypeDTO> enterpriseTypes = enterpriseTypeService.selectEnterpriseTypeList();
		model.addAttribute("enterpriseTypes",enterpriseTypes);
		model.addAttribute("queryDto",enterpriseDto);
		return "enterprise/info/enterprise_info_list";
	}  	
	
	/**
	 * 检查企业名称是否存在
	 * @param enterpriseName   企业名称
	 * @param id               企业信息在数据库中的唯一标识
	 * @return
	 */
	@RequestMapping(value = "checkEnterpriseName",method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody boolean checkEnterpriseName(@RequestParam("name") String name,@RequestParam("oldName") String oldName){
		if (StringUtils.isEmpty(name)) {
			return true;
		}
		if(name.equals(oldName)){
			return true;
		}
		boolean result = enterpriseService.checkEnterpriseName(name);
		return result;
	} 
	
	/**
	 * 新增企业信息
	 * @param enterprise
	 * @return
	 */
	@RequestMapping(value = "saveEnterprise",method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody boolean saveEnterprise(@RequestBody EnterpriseDTO enterpriseDto){
		enterpriseDto.setCreateType(1);
		boolean isSucc = enterpriseService.saveEnterpriseUserInfo(enterpriseDto);
		return isSucc;
	}
	
	/**
	 * 修改企业信息
	 * @param enterprise
	 * @return
	 */
	@RequestMapping(value = "updateEnterprise",method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody boolean updateEnterprise(@RequestBody EnterpriseDTO enterpriseDto){
		boolean isSucc = enterpriseService.updateEnterpriseUserInfo(enterpriseDto);
		return isSucc;
	}
	
	/**
	 * 删除企业信息
	 * @param strId
	 * @return
	 */
	
	@RequestMapping(value = "deleteEnterpriseInfo",method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int deleteEnterpriseInfo(@RequestParam("strId") String strId){
		if(!StringUtils.isEmpty(strId)){
			int id = Integer.parseInt(strId);
			return enterpriseService.deleteEnterpriseInfo(id);
		}
		return DELETE_FAIL;
	}
	
	/**
	 * 跳转企业更新信息页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateEnterpriseInfo" , method = {RequestMethod.GET , RequestMethod.POST})
	public String updateEnterpriseInfo(Model model,@RequestParam("id") String id){
		if(!StringUtils.isEmpty(id)){
			List<EnterpriseTypeDTO> enterpriseTypes = enterpriseTypeService.selectEnterpriseTypeList();
			model.addAttribute("enterpriseTypes",enterpriseTypes);
			EnterpriseDTO enterprise = enterpriseService.selectEnterpriseUserInfo(Integer.parseInt(id));
			String resetPwd = CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.ENTERPRISEUSER_RESET_PASSWORD);
			
			model.addAttribute("resetPwd",resetPwd);
			model.addAttribute("enterprise",enterprise);
		}
		return "enterprise/info/enterprise_info_update";
	}
}

