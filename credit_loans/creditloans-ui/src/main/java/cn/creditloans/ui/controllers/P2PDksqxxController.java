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

import cn.creditloans.core.dto.p2p.P2PDksqxxDTO;
import cn.creditloans.core.service.P2PDksqxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/p2p/dksqxx")
public class P2PDksqxxController {
	private static final Log logger = LogFactory
			.getLog(P2PDksqxxController.class);

	@Autowired
	P2PDksqxxService p2pDksqxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showP2PDksqxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			P2PDksqxxDTO p2pDksqxxDto) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		p2pDksqxxDto.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<P2PDksqxxDTO> p2pDksqxxPageModel = p2pDksqxxService
				.getP2PDksqxxPageList(p2pDksqxxDto, currentPage, pageSize);
		model.addAttribute("p2pDksqxxPageModel", p2pDksqxxPageModel);
		model.addAttribute("queryDto", p2pDksqxxDto);
		return "/p2p/dksqxx/p2p_dksqxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddP2PDksqxx(Model model, HttpServletRequest request) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		return "/p2p/dksqxx/p2p_dksqxx_add";
	}
	
	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addP2PDksqxx(@RequestBody P2PDksqxxDTO p2pDksqxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pDksqxxDto.setUserId(userId);
		int p2pDksqxxId = p2pDksqxxService.addP2PDksqxx(p2pDksqxxDto);
		return p2pDksqxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditP2PDksqxx(
			Model model,
			@RequestParam(value = "p2pDksqxxId", required = false) String p2pDksqxxId) {
		P2PDksqxxDTO p2pDksqxxDto = p2pDksqxxService.getP2PDksqxxById(Integer
				.parseInt(p2pDksqxxId));
		model.addAttribute("p2pDksqxxDto", p2pDksqxxDto);
		return "/p2p/dksqxx/p2p_dksqxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editP2PDksqxx(@RequestBody P2PDksqxxDTO p2pDksqxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pDksqxxDto.setUserId(userId);
		p2pDksqxxService.editP2PDksqxx(p2pDksqxxDto);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteP2PDksqxx(
			@RequestParam(value = "p2pDksqxxId", required = false) String p2pDksqxxId) {
		p2pDksqxxService.deleteP2PDksqxxById(Integer.parseInt(p2pDksqxxId));
	}

	@RequestMapping(value = "/checkDksqh", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	boolean checkDksqh(@RequestParam(value = "dksqh") String dksqh,
			@RequestParam(value = "orgCode") String orgCode,
			@RequestParam(value = "oldDksqh") String oldDksqh) {
		if (StringUtils.isEmpty(dksqh) || StringUtils.isEmpty(orgCode)) {
			return true;
		}
		if (dksqh.equals(oldDksqh)) {
			return true;
		}
		boolean checkResult = p2pDksqxxService.p2pDksqxxIsOnly(dksqh, orgCode);
		return checkResult;
	}
}
