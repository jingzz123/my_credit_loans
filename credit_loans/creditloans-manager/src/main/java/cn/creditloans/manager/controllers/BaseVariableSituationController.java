package cn.creditloans.manager.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import cn.creditloans.core.dao.af.BaseElementDao;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.BaseVariableSituationDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.BaseElementService;
import cn.creditloans.core.service.BaseVariableSituationService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/variablesituation")
public class BaseVariableSituationController {
	private static final Log logger = LogFactory.getLog(BaseVariableSituationController.class);
	
	@Autowired
	BaseVariableSituationService baseVariableSituationService;
	
	@Autowired
	PlatformUserService platformUserService;
	@Autowired
	BaseElementService baseElementService;
	/**
	 * 打开variableTransaction管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getBaseVariableSituationList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, BaseVariableSituationDTO baseVariableSituationDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<BaseVariableSituationDTO> baseVariableSituationPage = baseVariableSituationService.getBaseVariableSituationPageList(baseVariableSituationDTO,
				currentPage, pageSize);
		model.addAttribute("baseVariableSituationPage", baseVariableSituationPage);
		model.addAttribute("queryDto", baseVariableSituationDTO);
		return "/regulation/basepackage/variablesituation/regulation_variablesituation_list";
	}
	
	/**
	 * 打开variablesituation管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddBaseVariableSituation(Model model){
		List<BaseElementDTO> baseElementDTOList = baseElementService.selectAllInfos();
		model.addAttribute("baseElementDTOList", baseElementDTOList);
		return "/regulation/basepackage/variablesituation/regulation_variablesituation_add";
	}
	
	
	/**
	 * 添加variableSituation
	 * @param baseVariableSituationDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addBaseVariableSituationId(@RequestBody BaseVariableSituationDTO baseVariableSituationDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseVariableSituationDTO.setCreateUserId(platformUserDto.getId());
		baseVariableSituationDTO.setUpadteUserId(platformUserDto.getId());
		int baseVariableSituationId = baseVariableSituationService.saveBaseVariableSituation(baseVariableSituationDTO);
		return baseVariableSituationId;
	}
	
	/**
	 * 打开VariableSituation管理修改的页面
	 * @param model variableSituationId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditBaseVariableSituation(Model model,@RequestParam(value = "variableSituationId", required = false) String variableSituationId){
		BaseVariableSituationDTO baseVariableSituationDTO = baseVariableSituationService.getBaseVariableSituationById(Integer.parseInt(variableSituationId));
		model.addAttribute("baseVariableSituationDTO", baseVariableSituationDTO);
		return "/regulation/basepackage/variablesituation/regulation_variablesituation_edit";
	}
	
	/**
	 * 修改variablesituation
	 * @param baseVariableSituationDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editBaseVariableSituation(@RequestBody BaseVariableSituationDTO baseVariableSituationDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseVariableSituationDTO.setUpadteUserId(platformUserDto.getId());
		baseVariableSituationService.updateBaseVariableSituation(baseVariableSituationDTO);
	}

	/**
	 * 删除VariableSituation
	 * @param variableSituationId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteBaseVariableSituation(@RequestParam(value = "variableSituationId", required = false) String variableSituationId){
		baseVariableSituationService.deleteBaseVariableSituation(Integer.parseInt(variableSituationId));
	}
	/**
	 * 检查name是否存在
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "checkName" , method = {RequestMethod.GET , RequestMethod.POST})
	public @ResponseBody boolean checkName(@RequestParam("name") String name,@RequestParam("oldName") String oldName){
		if(name.equals(oldName)) {
			return true;
		}
		if(!StringUtils.isEmpty(name)){
			return baseVariableSituationService.checkNameIsExist(name);
		}
		return true;
	}
}
