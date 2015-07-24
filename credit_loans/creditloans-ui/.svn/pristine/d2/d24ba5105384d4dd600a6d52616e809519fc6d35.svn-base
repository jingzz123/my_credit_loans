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

import cn.creditloans.core.dto.db.DbBdbrxxDTO;
import cn.creditloans.core.service.DbBdbrxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/db/bdbrxx")
public class DbBdbrxxController {
	private static final Log logger = LogFactory
			.getLog(DbBdbrxxController.class);

	@Autowired
	DbBdbrxxService dbBdbrxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showDbBdbrxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			DbBdbrxxDTO dbBdbrxxDTO) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		dbBdbrxxDTO.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<DbBdbrxxDTO> dbBdbrxxPageModel = dbBdbrxxService
				.getDbBdbrxxPageList(dbBdbrxxDTO, currentPage, pageSize);
		model.addAttribute("dbBdbrxxPageModel", dbBdbrxxPageModel);
		model.addAttribute("queryDto", dbBdbrxxDTO);
		return "/db/bdbrxx/db_bdbrxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddDbBdbrxx(
			Model model, HttpServletRequest request,
			@RequestParam(value = "dbhtxxId", required = false) String dbhtxxId) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		model.addAttribute("dbhtxxId",dbhtxxId);
		return "/db/bdbrxx/db_bdbrxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addDbBdbrxx(@RequestBody DbBdbrxxDTO dbBdbrxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbBdbrxxDTO.setUserId(userId);
		int bdbrxxId = dbBdbrxxService.addDbBdbrxx(dbBdbrxxDTO);
		return bdbrxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditDbBdbrxx(
			Model model,
			@RequestParam(value = "bdbrxxId", required = false) String bdbrxxId) {
		DbBdbrxxDTO dbBdbrxxDTO = dbBdbrxxService.getDbBdbrxxById(Integer
				.parseInt(bdbrxxId));
		model.addAttribute("dbBdbrxxDTO", dbBdbrxxDTO);
		return "/db/bdbrxx/db_bdbrxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editDbBdbrxx(@RequestBody DbBdbrxxDTO dbBdbrxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbBdbrxxDTO.setUserId(userId);
		dbBdbrxxService.editDbBdbrxx(dbBdbrxxDTO);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteDbBdbrxx(
			@RequestParam(value = "bdbrxxId", required = false) String bdbrxxId) {
		dbBdbrxxService.deleteDbBdbrxxById(Integer.parseInt(bdbrxxId));
	}

}
