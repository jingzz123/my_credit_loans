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

import cn.creditloans.core.dto.db.DbZqrjzhtxxDTO;
import cn.creditloans.core.service.DbZqrjzhtxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/db/zqrjzhtxx")
public class DbZqrjzhtxxController {
	private static final Log logger = LogFactory
			.getLog(DbZqrjzhtxxController.class);

	@Autowired
	DbZqrjzhtxxService dbZqrjzhtxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showDbZqrjzhtxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			DbZqrjzhtxxDTO dbZqrjzhtxxDTO) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		dbZqrjzhtxxDTO.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<DbZqrjzhtxxDTO> dbZqrjzhtxxPageModel = dbZqrjzhtxxService
				.getDbZqrjzhtxxPageList(dbZqrjzhtxxDTO, currentPage, pageSize);
		model.addAttribute("dbZqrjzhtxxPageModel", dbZqrjzhtxxPageModel);
		model.addAttribute("queryDto", dbZqrjzhtxxDTO);
		return "/db/zqrjzhtxx/db_zqrjzhtxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddZqrjzhtxx(
			Model model, HttpServletRequest request,
			@RequestParam(value = "dbhtxxId", required = false) String dbhtxxId) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		model.addAttribute("dbhtxxId",dbhtxxId);
		return "/db/zqrjzhtxx/db_zqrjzhtxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addDbZqrjzhtxx(@RequestBody DbZqrjzhtxxDTO dbZqrjzhtxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbZqrjzhtxxDTO.setUserId(userId);
		int bdbrxxId = dbZqrjzhtxxService.addDbZqrjzhtxx(dbZqrjzhtxxDTO);
		return bdbrxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditDbZqrjzhtxx(
			Model model,
			@RequestParam(value = "zqrjzhtxxId", required = false) String zqrjzhtxxId) {
		DbZqrjzhtxxDTO dbZqrjzhtxxDTO = dbZqrjzhtxxService.getDbZqrjzhtxxById(Integer
				.parseInt(zqrjzhtxxId));
		model.addAttribute("dbZqrjzhtxxDTO", dbZqrjzhtxxDTO);
		return "/db/zqrjzhtxx/db_zqrjzhtxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editDbZqrjzhtxx(@RequestBody DbZqrjzhtxxDTO dbZqrjzhtxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbZqrjzhtxxDTO.setUserId(userId);
		dbZqrjzhtxxService.editDbZqrjzhtxx(dbZqrjzhtxxDTO);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteDbZqrjzhtxx(
			@RequestParam(value = "zqrjzhtxxId", required = false) String zqrjzhtxxId) {
		dbZqrjzhtxxService.deleteDbZqrjzhtxxById(Integer.parseInt(zqrjzhtxxId));
	}

}
