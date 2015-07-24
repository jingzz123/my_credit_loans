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

import cn.creditloans.core.dto.db.DbBfjnmxxxDTO;
import cn.creditloans.core.dto.db.DbZcmxxxDTO;
import cn.creditloans.core.service.DbBfjnmxxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/db/bfjnmxxx")
public class DbBfjnmxxxController {
	private static final Log logger = LogFactory
			.getLog(DbBfjnmxxxController.class);

	@Autowired
	DbBfjnmxxxService dbBfjnmxxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showDbBfjnmxxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			DbBfjnmxxxDTO dbBfjnmxxxDTO) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		dbBfjnmxxxDTO.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<DbBfjnmxxxDTO> dbBfjnmxxxPageModel = dbBfjnmxxxService
				.getDbBfjnmxxxPageList(dbBfjnmxxxDTO, currentPage, pageSize);
		model.addAttribute("dbBfjnmxxxPageModel", dbBfjnmxxxPageModel);
		model.addAttribute("queryDto", dbBfjnmxxxDTO);
		return "/db/bfjnmxxx/db_bfjnmxxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddBfjnmxxx(
			Model model, HttpServletRequest request,
			@RequestParam(value = "dbhtxxId", required = false) String dbhtxxId) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		model.addAttribute("dbhtxxId",dbhtxxId);
		return "/db/bfjnmxxx/db_bfjnmxxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addDbBfjnmxxx(@RequestBody DbBfjnmxxxDTO dbBfjnmxxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbBfjnmxxxDTO.setUserId(userId);
		int bfjnmxxxId = dbBfjnmxxxService.addDbBfjnmxxx(dbBfjnmxxxDTO);
		return bfjnmxxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditDbfjnmxxx(
			Model model,
			@RequestParam(value = "bfjnmxxxId", required = false) String bfjnmxxxId) {
		DbBfjnmxxxDTO dbBfjnmxxxDTO = dbBfjnmxxxService.getDbBfjnmxxxById(Integer
				.parseInt(bfjnmxxxId));
		model.addAttribute("dbBfjnmxxxDTO", dbBfjnmxxxDTO);
		return "/db/bfjnmxxx/db_bfjnmxxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editDbBfjnmxxx(@RequestBody DbBfjnmxxxDTO dbBfjnmxxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbBfjnmxxxDTO.setUserId(userId);
		dbBfjnmxxxService.editDbBfjnmxxx(dbBfjnmxxxDTO);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteDbBfjnmxxx(
			@RequestParam(value = "bfjnmxxxId", required = false) String bfjnmxxxId) {
		dbBfjnmxxxService.deleteDbBfjnmxxxById(Integer.parseInt(bfjnmxxxId));
	}

}
