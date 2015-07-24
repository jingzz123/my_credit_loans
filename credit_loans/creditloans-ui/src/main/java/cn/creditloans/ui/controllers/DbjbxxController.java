package cn.creditloans.ui.controllers;

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

import cn.creditloans.core.dto.db.DbjbxxDTO;
import cn.creditloans.core.dto.p2p.P2PDkjbxxDTO;
import cn.creditloans.core.service.DbjbxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/db/dbjbxx")
public class DbjbxxController {
	private static final Log logger = LogFactory
			.getLog(DbjbxxController.class);

	@Autowired
	DbjbxxService dbjbxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showDbjbxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			DbjbxxDTO dbjbxxDto) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		dbjbxxDto.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<DbjbxxDTO> dbjbxxPageModel = dbjbxxService
				.getDbjbxxPageList(dbjbxxDto, currentPage, pageSize);
		model.addAttribute("dbjbxxPageModel", dbjbxxPageModel);
		model.addAttribute("queryDto", dbjbxxDto);
		return "/db/dbjbxx/db_dbjbxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddDbjbxx(Model model, HttpServletRequest request) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		return "/db/dbjbxx/db_dbjbxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addDbjbxx(@RequestBody DbjbxxDTO dbjbxxDTO) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbjbxxDTO.setUserId(userId);
		int dbjbxxId = dbjbxxService.addDbjbxx(dbjbxxDTO);
		return dbjbxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditDbjbxx(
			Model model,
			@RequestParam(value = "dbjbxxId", required = false) String dbjbxxId) {
		DbjbxxDTO dbjbxxDto = dbjbxxService.getDbjbxxById(Integer
				.parseInt(dbjbxxId));
		model.addAttribute("dbjbxxDto", dbjbxxDto);
		return "/db/dbjbxx/db_dbjbxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editDbjbxx(@RequestBody DbjbxxDTO dbjbxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		dbjbxxDto.setUserId(userId);
		dbjbxxService.editDbjbxx(dbjbxxDto);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteDbjbxx(
			@RequestParam(value = "dbjbxxId", required = false) String dbjbxxId) {
		dbjbxxService.deleteDbjbxxById(Integer.parseInt(dbjbxxId));
	}

}
