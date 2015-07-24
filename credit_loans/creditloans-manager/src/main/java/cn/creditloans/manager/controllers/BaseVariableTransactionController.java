package cn.creditloans.manager.controllers;

import java.util.Map;

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

import cn.creditloans.core.dto.af.BaseVariableTransactionDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.BaseVariableTransactionService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/basepackage/variabletransaction")
public class BaseVariableTransactionController {
	private static final Log logger = LogFactory.getLog(BaseVariableTransactionController.class);
	
	@Autowired
	BaseVariableTransactionService baseVariableTransactionService;
	
	@Autowired
	PlatformUserService platformUserService;
	/**
	 * 打开variableTransaction管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getBaseVariableTransactionList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, BaseVariableTransactionDTO baseVariableTransactionDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<BaseVariableTransactionDTO> baseVariableTransactionPage = baseVariableTransactionService.getBaseVariableTransactionPageList(baseVariableTransactionDTO,
				currentPage, pageSize);
		model.addAttribute("baseVariableTransactionPage", baseVariableTransactionPage);
		model.addAttribute("queryDto", baseVariableTransactionDTO);
		return "/regulation/basepackage/variabletransaction/regulation_variabletransaction_list";
	}
	
	/**
	 * 打开variabletransaction管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddBaseVariableTransaction(Model model){
		Map<String, Object> resultMap = baseVariableTransactionService.getAllRelationEntity();
		model.addAllAttributes(resultMap);
		return "/regulation/basepackage/variabletransaction/regulation_variabletransaction_add";
	}
	
	
	/**
	 * 添加variabletransaction
	 * @param baseVariableTransactionDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addBaseVariableTransactionId(@RequestBody BaseVariableTransactionDTO baseVariableTransactionDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseVariableTransactionDTO.setCreateUserId(platformUserDto.getId());
		baseVariableTransactionDTO.setUpadteUserId(platformUserDto.getId());
		int baseVariableTransactionId = baseVariableTransactionService.saveBaseVariableTransaction(baseVariableTransactionDTO);
		return baseVariableTransactionId;
	}
	
	/**
	 * 打开VariableTransaction管理修改的页面
	 * @param model variableTransactionId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditBaseVariableTransaction(Model model,@RequestParam(value = "variableTransactionId", required = false) String variableTransactionId){
		Map<String, Object> resultMap = baseVariableTransactionService.getBaseVariableTransactionById(Integer.parseInt(variableTransactionId));
		model.addAllAttributes(resultMap);
		return "/regulation/basepackage/variabletransaction/regulation_variabletransaction_edit";
	}
	
	/**
	 * 修改variabletransaction
	 * @param baseVariableTransactionDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editBaseVariableTransaction(@RequestBody BaseVariableTransactionDTO baseVariableTransactionDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		baseVariableTransactionDTO.setUpadteUserId(platformUserDto.getId());
		baseVariableTransactionService.updateBaseVariableTransaction(baseVariableTransactionDTO);
	}

	/**
	 * 删除VariableTransaction
	 * @param variableTransactionId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteBaseVariableTransaction(@RequestParam(value = "variableTransactionId", required = false) String variableTransactionId){
		baseVariableTransactionService.deleteBaseVariableTransaction(Integer.parseInt(variableTransactionId));
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
			return baseVariableTransactionService.checkNameIsExist(name);
		}
		return true;
	}
}
