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
import cn.creditloans.core.dto.db.DbZcmxxxDTO;
import cn.creditloans.core.service.DbZcmxxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/db/zcmxxx")
public class DbZcmxxxController {
	private static final Log logger = LogFactory
			.getLog(DbZcmxxxController.class);

	@Autowired
	DbZcmxxxService dbZcmxxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showDbZcmxxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			DbZcmxxxDTO dbZcmxxxDTO) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		dbZcmxxxDTO.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<DbZcmxxxDTO> dbZcmxxxPageModel = dbZcmxxxService
				.getDbZcmxxxPageList(dbZcmxxxDTO, currentPage, pageSize);
		model.addAttribute("dbZcmxxxPageModel", dbZcmxxxPageModel);
		model.addAttribute("queryDto", dbZcmxxxDTO);
		return "/db/zcmxxx/db_zcmxxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddZcmxxx(
			Model model, HttpServletRequest request,
			@RequestParam(value = "dbhtxxId", required = false) String dbhtxxId) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		model.addAttribute("dbhtxxId",dbhtxxId);
		return "/db/zcmxxx/db_zcmxxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addDbZcmxxx(@RequestBody DbZcmxxxDTO dbZcmxxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbZcmxxxDTO.setUserId(userId);
		int zcmxxxId = dbZcmxxxService.addDbZcmxxx(dbZcmxxxDTO);
		return zcmxxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditDbZcmxxx(
			Model model,
			@RequestParam(value = "zcmxxxId", required = false) String zcmxxxId) {
		DbZcmxxxDTO dbZcmxxxDTO = dbZcmxxxService.getDbZcmxxxById(Integer
				.parseInt(zcmxxxId));
		model.addAttribute("dbZcmxxxDTO", dbZcmxxxDTO);
		return "/db/zcmxxx/db_zcmxxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editDbZcmxxx(@RequestBody DbZcmxxxDTO dbZcmxxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbZcmxxxDTO.setUserId(userId);
		dbZcmxxxService.editDbZcmxxx(dbZcmxxxDTO);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteDbZcmxxx(
			@RequestParam(value = "zcmxxxId", required = false) String zcmxxxId) {
		dbZcmxxxService.deleteDbZcmxxxById(Integer.parseInt(zcmxxxId));
	}

}
