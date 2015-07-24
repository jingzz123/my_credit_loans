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

import cn.creditloans.core.dto.db.DbFdbrxxDTO;
import cn.creditloans.core.service.DbFdbrxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/db/fdbrxx")
public class DbFdbrxxController {
	private static final Log logger = LogFactory
			.getLog(DbFdbrxxController.class);

	@Autowired
	DbFdbrxxService dbFdbrxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showDbFdbrxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			DbFdbrxxDTO dbFdbrxxDTO) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		dbFdbrxxDTO.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<DbFdbrxxDTO> dbFdbrxxPageModel = dbFdbrxxService
				.getDbFdbrxxPageList(dbFdbrxxDTO, currentPage, pageSize);
		model.addAttribute("dbFdbrxxPageModel", dbFdbrxxPageModel);
		model.addAttribute("queryDto", dbFdbrxxDTO);
		return "/db/fdbrxx/db_fdbrxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddFdbrxx(
			Model model, HttpServletRequest request,
			@RequestParam(value = "dbhtxxId", required = false) String dbhtxxId) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		model.addAttribute("dbhtxxId",dbhtxxId);
		return "/db/fdbrxx/db_fdbrxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addDbFdbrxx(@RequestBody DbFdbrxxDTO dbFdbrxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbFdbrxxDTO.setUserId(userId);
		int fdbrxxId = dbFdbrxxService.addDbFdbrxx(dbFdbrxxDTO);
		return fdbrxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditDbFdbrxx(
			Model model,
			@RequestParam(value = "fdbrxxId", required = false) String fdbrxxId) {
		DbFdbrxxDTO dbFdbrxxDTO = dbFdbrxxService.getDbFdbrxxById(Integer
				.parseInt(fdbrxxId));
		model.addAttribute("dbFdbrxxDTO", dbFdbrxxDTO);
		return "/db/fdbrxx/db_fdbrxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editDbFdbrxx(@RequestBody DbFdbrxxDTO dbFdbrxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbFdbrxxDTO.setUserId(userId);
		dbFdbrxxService.editDbFdbrxx(dbFdbrxxDTO);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteDbFdbrxx(
			@RequestParam(value = "fdbrxxId", required = false) String fdbrxxId) {
		dbFdbrxxService.deleteDbFdbrxxById(Integer.parseInt(fdbrxxId));
	}

}
