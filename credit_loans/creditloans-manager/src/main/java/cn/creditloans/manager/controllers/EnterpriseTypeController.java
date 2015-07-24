package cn.creditloans.manager.controllers;

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

import cn.creditloans.core.dto.enterprise.EnterpriseTypeDTO;
import cn.creditloans.core.entity.enterprise.EnterpriseType;
import cn.creditloans.core.service.EnterpriseTypeService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/enterprise/type")
public class EnterpriseTypeController {
	private static final Log logger = LogFactory.getLog(EnterpriseTypeController.class);
	
	//注入企业类型service
	@Autowired
	EnterpriseTypeService enterpriseTypeService;
	
	/**
	 * 获取企业类型管理信息
	 * @return
	 */
	@RequestMapping(value="typeList", method = {RequestMethod.GET, RequestMethod.POST})
	public String typeList(Model model,String _currentPage,EnterpriseType enterpriseType){
		//默认当前页信息
		int currentPage = 1;
		if(StringUtils.isNotEmpty(_currentPage)){
			currentPage = Integer.parseInt(_currentPage);
		}
		//获取每页最大条数
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
	    PageModel<EnterpriseTypeDTO> pageModel = enterpriseTypeService.selectEnterpriseTypePageList(enterpriseType, currentPage, pageSize);
		model.addAttribute("enterpriseTypePage",pageModel);
		return "enterprise/type/enterprise_type_list";
	}
	
	@RequestMapping(value="enterpriseTypeInput", method = {RequestMethod.GET, RequestMethod.POST})
	public String enterpriseTypeInput(){
		return "enterprise/type/enterprise_type_input";
	}
	
	/**
	 * 添加,编辑企业类型信息
	 * @param enterpriseType
	 * @return
	 */
	@RequestMapping(value="saveOrUpdateEnterpriseType", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody boolean saveOrUpdateEnterpriseType(@ModelAttribute EnterpriseTypeDTO enterpriseType){
		//保存成功返回true  失败false
		boolean isSucc = false;
		if(enterpriseType != null){
			if(!StringUtils.isEmpty(enterpriseType.getIdString())){
				//更新企业信息
				isSucc = enterpriseTypeService.updateEnterpriseType(enterpriseType);
			}else{
				//添加企业信息
				isSucc = enterpriseTypeService.insertEnterpriseType(enterpriseType);
			}
		}
		return isSucc;
	}
	
	/**
	 * 获取要更新的企业类型信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="updateEnterpriseType",method={RequestMethod.GET,RequestMethod.POST})
	public String updateEnterpriseType(Model model,@RequestParam String id){
		if(!StringUtils.isEmpty(id)){
			EnterpriseTypeDTO enterpriseType = enterpriseTypeService.selectEnterpriseTypeById(Integer.parseInt(id));
			model.addAttribute(enterpriseType);
		}	
		return "enterprise/type/enterprise_type_input";
	}
	
	/**
	 * 企业类型重名验证
	 * @param name
	 * @return
	 */
	@RequestMapping(value ="checkEnterpriseName",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody boolean checkEnterpriseName(@RequestParam(value = "name") String name){
		if(StringUtils.isEmpty(name)){
			return true;
		}
		return enterpriseTypeService.selectEnterpriseName(name);
	}
	
	/**
	 * 删除企业类型信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="deleteEnterpriseType",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int deleteEnterpriseType(@RequestParam(value = "id") int id){
		int tipType = enterpriseTypeService.deleteEnterpriseType(id);
		return tipType;
	}
}
