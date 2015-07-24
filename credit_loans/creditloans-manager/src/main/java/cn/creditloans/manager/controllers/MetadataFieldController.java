package cn.creditloans.manager.controllers;

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

import cn.creditloans.core.dto.af.MetadataFieldDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.service.MetadataFieldService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.manager.util.cookie.CookieUtil;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/regulation/metadata/field")
public class MetadataFieldController {
	private static final Log logger = LogFactory.getLog(MetadataFieldController.class);
	@Autowired
	MetadataFieldService metadataFieldService;
	
	@Autowired
	PlatformUserService platformUserService;
	
	/**
	 * 打开field管理的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getMetadataFieldList(Model model, @RequestParam(value = "currentPage", required = false) String _currentPage, MetadataFieldDTO metadataFieldDTO) {
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORM_PAGE_SHOW_COUNT));
		
		PageModel<MetadataFieldDTO> metadataFieldPage = metadataFieldService.getMetadataFieldPageList(metadataFieldDTO,
				currentPage, pageSize);
		model.addAttribute("metadataFieldPage", metadataFieldPage);
		model.addAttribute("queryDto", metadataFieldDTO);
		return "/regulation/metadata/field/regulation_field_list";
	}
	
	/**
	 * 打开field管理添加的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddMetadataField(Model model, @RequestParam(value="schemaId", required=false) String schemaId){
		model.addAttribute("schemaId", schemaId);
		return "/regulation/metadata/field/regulation_field_add";
	}
	
	
	/**
	 * 添加field
	 * @param metadataFieldDTO
	 * @return
	 */
	@RequestMapping(value="/doAdd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int addMetadataFieldId(@RequestBody MetadataFieldDTO metadataFieldDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		metadataFieldDTO.setCreateUserId(platformUserDto.getId());
		metadataFieldDTO.setUpadteUserId(platformUserDto.getId());
		int metadataFieldId = metadataFieldService.saveMetadataField(metadataFieldDTO);
		return metadataFieldId;
	}
	
	/**
	 * 打开field管理修改的页面
	 * @param model fieldId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditMetadataField(Model model,@RequestParam(value = "fieldId", required = false) String fieldId){
		MetadataFieldDTO metadataFieldDTO = metadataFieldService.getMetadataFieldById(Integer.parseInt(fieldId));
		model.addAttribute("metadataFieldDTO", metadataFieldDTO);
		return "/regulation/metadata/field/regulation_field_edit";
	}
	
	/**
	 * 修改field
	 * @param metadataFieldDTO
	 * @return
	 */
	@RequestMapping(value="/doEdit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void editMetadataField(@RequestBody MetadataFieldDTO metadataFieldDTO, HttpServletRequest request) {
		PlatformUserDTO platformUserDto = platformUserService.getPlatformUserDtoFromCache(CookieUtil.getToken(request, "platform-token"));
		metadataFieldDTO.setUpadteUserId(platformUserDto.getId());
		metadataFieldService.updateMetadataField(metadataFieldDTO);
	}

	/**
	 * 删除field
	 * @param fieldId
	 * @return
	 */
	@RequestMapping(value="/doDelete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void deleteMetadataField(@RequestParam(value = "fieldId", required = false) String fieldId){
		metadataFieldService.deleteMetadataField(Integer.parseInt(fieldId));
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
			return metadataFieldService.checkNameIsExist(name);
		}
		return true;
	}
}
