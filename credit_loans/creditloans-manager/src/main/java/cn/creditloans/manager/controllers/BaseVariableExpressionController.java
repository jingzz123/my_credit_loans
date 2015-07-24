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

import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.BaseVariableExpressionDTO;
import cn.creditloans.core.dto.af.BaseVariableSituationDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.BaseElementService;
import cn.creditloans.core.service.BaseVariableExpressionService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/variableexpression")
public class BaseVariableExpressionController {
	private static final Log logger = LogFactory.getLog(BaseVariableExpressionController.class);
	
	@Autowired
	BaseVariableExpressionService baseVariableExpressionService;
	
	@Autowired
	PlatformUserService platformUserService;
	@Autowired
	BaseElementService  baseElementService;
	/**
	 * 打开variableTransaction管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getBaseVariableExpressionList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, BaseVariableExpressionDTO baseVariableExpressionDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<BaseVariableExpressionDTO> baseVariableExpressionPage = baseVariableExpressionService.getBaseVariableExpressionPageList(baseVariableExpressionDTO,
				currentPage, pageSize);
		model.addAttribute("baseVariableExpressionPage", baseVariableExpressionPage);
		model.addAttribute("queryDto", baseVariableExpressionDTO);
		return "/regulation/basepackage/variableexpression/regulation_variableexpression_list";
	}
	
	/**
	 * 打开variableexpression管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddBaseVariableExpression(Model model){
		List<BaseElementDTO> baseElementDTOList = baseElementService.selectAllInfos();
		model.addAttribute("baseElementDTOList", baseElementDTOList);
		return "/regulation/basepackage/variableexpression/regulation_variableexpression_add";
	}
	
	
	/**
	 * 添加variableExpression
	 * @param baseVariableExpressionDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addBaseVariableExpressionId(@RequestBody BaseVariableExpressionDTO baseVariableExpressionDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseVariableExpressionDTO.setCreateUserId(platformUserDto.getId());
		baseVariableExpressionDTO.setUpadteUserId(platformUserDto.getId());
		int baseVariableExpressionId = baseVariableExpressionService.saveBaseVariableExpression(baseVariableExpressionDTO);
		return baseVariableExpressionId;
	}
	
	/**
	 * 打开VariableExpression管理修改的页面
	 * @param model variableExpressionId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditBaseVariableExpression(Model model,@RequestParam(value = "variableExpressionId", required = false) String variableExpressionId){
		BaseVariableExpressionDTO baseVariableExpressionDTO = baseVariableExpressionService.getBaseVariableExpressionById(Integer.parseInt(variableExpressionId));
		model.addAttribute("baseVariableExpressionDTO", baseVariableExpressionDTO);
		return "/regulation/basepackage/variableexpression/regulation_variableexpression_edit";
	}
	
	/**
	 * 修改variableExpression
	 * @param baseVariableExpressionDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editBaseVariableExpression(@RequestBody BaseVariableExpressionDTO baseVariableExpressionDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseVariableExpressionDTO.setUpadteUserId(platformUserDto.getId());
		baseVariableExpressionService.updateBaseVariableExpression(baseVariableExpressionDTO);
	}

	/**
	 * 删除VariableExpression
	 * @param variableExpressionId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteBaseVariableExpression(@RequestParam(value = "variableExpressionId", required = false) String variableExpressionId){
		baseVariableExpressionService.deleteBaseVariableExpression(Integer.parseInt(variableExpressionId));
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
			return baseVariableExpressionService.checkNameIsExist(name);
		}
		return true;
	}
}
