package cn.creditloans.ui.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import cn.creditloans.core.dto.enterprise.EnterpriseDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseTypeDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.core.service.EnterpriseService;
import cn.creditloans.core.service.EnterpriseTypeService;
import cn.creditloans.core.service.EnterpriseUserService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;
import cn.creditloans.ui.util.cookie.CookieUtil;

@Controller
@RequestMapping(value = "/subsidiary/info")
public class SubsidiaryInfoController {
	
	private static final Log logger = LogFactory.getLog(SubsidiaryInfoController.class);
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private EnterpriseTypeService enterpriseTypeService;
	
	// 企业用户信息service
	@Autowired
	private EnterpriseUserService enterpriseUserService;
	
	//删除失败
	private static final int DELETE_FAIL = 1;
	
	/**
	 * 跳转企业信息添加页面 
	 * @return
	 */
	@RequestMapping(value = "enterpriseInfoInput",method = {RequestMethod.GET, RequestMethod.POST})
	public String enterpriseInfoInput(Model model, HttpServletRequest request){
		List<EnterpriseTypeDTO> enterpriseTypes = enterpriseTypeService.selectEnterpriseTypeList();
		model.addAttribute("enterpriseTypes",enterpriseTypes);
		EnterpriseUserDTO enterpriseUserDTO = selectloginUser(request);
		int enterpriseId = enterpriseUserDTO.getEnterpriseId();
		EnterpriseDTO enterprises = enterpriseService.getEnterpriseById(enterpriseId);
		model.addAttribute("enterprises",enterprises);
		
		return "enterprise/info/subsidiary_info_input";
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
	public String enterpriseInfoList(HttpServletRequest request, Model model, EnterpriseDTO enterpriseDto, String currentPage){
		EnterpriseUserDTO enterpriseUserDTO = selectloginUser(request);
		int enterpriseId = enterpriseUserDTO.getEnterpriseId();
		enterpriseDto.setFid(enterpriseId);
		//默认当前页信息
		if(StringUtils.isEmpty(currentPage)){
			currentPage = "1";
		}
		
		//获取每页最大条数
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<EnterpriseDTO> pageModel = enterpriseService.subsidiaryInfoList(enterpriseDto, Integer.parseInt(currentPage), pageSize);
		List<EnterpriseTypeDTO> enterpriseTypes = enterpriseTypeService.selectEnterpriseTypeList();
		model.addAttribute("enterpriseTypes",enterpriseTypes);
		model.addAttribute("enterprisePages", pageModel);
		model.addAttribute("queryDto", enterpriseDto);
		return "/enterprise/info/subsidiary_info_list";
	}  	
	
	/**
	 * 检查企业名称是否存在
	 * @param enterpriseName   企业名称
	 * @param id               企业信息在数据库中的唯一标识
	 * @return
	 */
	@RequestMapping(value = "checkEnterpriseName",method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody boolean checkEnterpriseName(@RequestParam("name") String name,@RequestParam("oldName") String oldName){
		if(StringUtils.isEmpty(name)){
			return true;
		}
		if(name.equals(oldName)){
			return true;
		}
		boolean result = enterpriseService.checkEnterpriseName(name);
		return result;
	} 
	
	/**
	 * 保存或者更新企业信息
	 * @param enterprise
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdateEnterprise",method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody boolean saveOrUpdateEnterprise(@ModelAttribute EnterpriseDTO enterpriseDto){
		boolean isSucc = false;
		if(enterpriseDto != null){
			if(enterpriseDto.getStrId() != null && "" != enterpriseDto.getStrId()){
				isSucc = enterpriseService.updateEnterpriseInfo(enterpriseDto);
			}else{
				enterpriseDto.setCreateType(2);
				isSucc = enterpriseService.saveEnterpriseInfo(enterpriseDto);
			}
		}
		return isSucc;
	} 
	/**
	 * 删除企业信息
	 * @param strId
	 * @return
	 */
	
	@RequestMapping(value = "deleteEnterpriseInfo",method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int deleteEnterpriseInfo(@RequestParam("strId") String strId){
		if(strId != null && "" != strId){
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
	public String updateEnterpriseInfo(Model model, HttpServletRequest request,@RequestParam("id") String id){
		if(id != null && "" != id){
			List<EnterpriseTypeDTO> enterpriseTypes = enterpriseTypeService.selectEnterpriseTypeList();
			model.addAttribute("enterpriseTypes",enterpriseTypes);
			EnterpriseUserDTO enterpriseUserDTO = selectloginUser(request);
			int enterpriseId = enterpriseUserDTO.getEnterpriseId();
			EnterpriseDTO enterprises = enterpriseService.getEnterpriseById(enterpriseId);
			model.addAttribute("enterprises",enterprises);
			
			EnterpriseDTO enterprise = enterpriseService.selectEnterpriseInfo(Integer.parseInt(id));
			model.addAttribute("enterprise",enterprise);
		}
		return "enterprise/info/subsidiary_info_input";
	}
	
	
	// 获取登陆用户信息
	private EnterpriseUserDTO selectloginUser(HttpServletRequest request) {
		String token = CookieUtil.getToken(request, "enterprise-token");
		EnterpriseUserDTO enterpriseLoginUserDto = enterpriseUserService.getEnterpriseUserDtoFromCache(token);
		return enterpriseLoginUserDto;
	}
}
