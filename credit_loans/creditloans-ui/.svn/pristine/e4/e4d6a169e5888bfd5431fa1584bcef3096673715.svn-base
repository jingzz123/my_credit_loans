package cn.creditloans.ui.controllers;

import javax.servlet.http.HttpServletRequest;

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

import cn.creditloans.core.dto.db.DbDcmxxxDTO;
import cn.creditloans.core.dto.db.DbFdbrxxDTO;
import cn.creditloans.core.service.DbDcmxxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/db/dcmxxx")
public class DbDcmxxxController {
	private static final Log logger = LogFactory
			.getLog(DbDcmxxxController.class);

	@Autowired
	DbDcmxxxService dbDcmxxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showDbDcmxxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			DbDcmxxxDTO dbDcmxxxDTO) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		dbDcmxxxDTO.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<DbDcmxxxDTO> dbDcmxxxPageModel = dbDcmxxxService
				.getDbDcmxxxPageList(dbDcmxxxDTO, currentPage, pageSize);
		model.addAttribute("dbDcmxxxPageModel", dbDcmxxxPageModel);
		model.addAttribute("queryDto", dbDcmxxxDTO);
		return "/db/dcmxxx/db_dcmxxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddDcmxxx(
			Model model, HttpServletRequest request,
			@RequestParam(value = "dbhtxxId", required = false) String dbhtxxId) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		model.addAttribute("dbhtxxId",dbhtxxId);
		return "/db/dcmxxx/db_dcmxxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addDbDcmxxx(@RequestBody DbDcmxxxDTO dbDcmxxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbDcmxxxDTO.setUserId(userId);
		int dcmxxxId = dbDcmxxxService.addDbDcmxxx(dbDcmxxxDTO);
		return dcmxxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditDbDcmxxx(
			Model model,
			@RequestParam(value = "dcmxxxId", required = false) String dcmxxxId) {
		DbDcmxxxDTO dbDcmxxxDTO = dbDcmxxxService.getDbDcmxxxById(Integer
				.parseInt(dcmxxxId));
		model.addAttribute("dbDcmxxxDTO", dbDcmxxxDTO);
		return "/db/dcmxxx/db_dcmxxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editDbDcmxxx(@RequestBody DbDcmxxxDTO dbDcmxxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbDcmxxxDTO.setUserId(userId);
		dbDcmxxxService.editDbDcmxxx(dbDcmxxxDTO);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteDbDcmxxx(
			@RequestParam(value = "dcmxxxId", required = false) String dcmxxxId) {
		dbDcmxxxService.deleteDbDcmxxxById(Integer.parseInt(dcmxxxId));
	}

}
