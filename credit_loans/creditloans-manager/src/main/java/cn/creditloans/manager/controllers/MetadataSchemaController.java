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

import cn.creditloans.core.dto.af.MetadataSchemaDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.MetadataSchemaService;
import cn.creditloans.core.service.PlatformDepartmentService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/metadata/schema")
public class MetadataSchemaController {
	private static final Log logger = LogFactory.getLog(MetadataSchemaController.class);
	
	@Autowired
	MetadataSchemaService metadataSchemaService;
	
	@Autowired
	PlatformDepartmentService platformDepartmentService;
	@Autowired
	PlatformUserService platformUserService;
	
	/**
	 * 打开schema管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getMetadataSchemaList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, MetadataSchemaDTO metadataSchemaDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<MetadataSchemaDTO> metadataSchemaPage = metadataSchemaService.getMetadataSchemaPageList(metadataSchemaDTO,
				currentPage, pageSize);
		model.addAttribute("metadataSchemaPage", metadataSchemaPage);
		model.addAttribute("queryDto", metadataSchemaDTO);
		return "/regulation/metadata/schema/regulation_schema_list";
	}
	
	/**
	 * 打开schema管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddRegulationSchema(Model model){
		Map<String, Object> resultMap = metadataSchemaService.getAllRelationEntity();
		model.addAllAttributes(resultMap);
		return "/regulation/metadata/schema/regulation_schema_add";
	}
	
	
	/**
	 * 添加schema
	 * @param metadataSchemaDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addMetadataSchemaId(@RequestBody MetadataSchemaDTO metadataSchemaDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		metadataSchemaDTO.setCreateUserId(platformUserDto.getId());
		metadataSchemaDTO.setUpadteUserId(platformUserDto.getId());
		int metadataSchemaId = metadataSchemaService.saveMetadataSchema(metadataSchemaDTO);
		return metadataSchemaId;
	}
	
	/**
	 * 打开schema管理修改的页面
	 * @param model schemaId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditMetadataSchema(Model model,@RequestParam(value = "schemaId", required = false) String schemaId,
			@RequestParam(value = "isNested", required = false) String isNested){
		Map<String, Object> resultMap = metadataSchemaService.getMetadataSchemaById(Integer.parseInt(schemaId),isNested);
		model.addAllAttributes(resultMap);
		return "/regulation/metadata/schema/regulation_schema_edit";
	}
	
	/**
	 * 修改schema
	 * @param metadataSchemaDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editMetadataSchema(@RequestBody MetadataSchemaDTO metadataSchemaDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		metadataSchemaDTO.setUpadteUserId(platformUserDto.getId());
		metadataSchemaService.updateMetadataSchema(metadataSchemaDTO);
	}

	/**
	 * 删除schema
	 * @param schemaId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int deleteMetadataSchema(@RequestParam(value = "schemaId", required = false) String schemaId,
			@RequestParam(value = "isNested", required = false) String isNested){
		int flag = metadataSchemaService.deleteMetadataSchema(Integer.parseInt(schemaId),isNested);
		return flag;
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
			return metadataSchemaService.checkNameIsExist(name);
		}
		return true;
	}
}
