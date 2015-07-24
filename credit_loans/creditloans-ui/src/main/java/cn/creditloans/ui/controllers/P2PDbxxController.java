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

import cn.creditloans.core.dto.p2p.P2PDbxxDTO;
import cn.creditloans.core.service.P2PDbxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/p2p/dbxx")
public class P2PDbxxController {
	private static final Log logger = LogFactory
			.getLog(P2PDbxxController.class);

	@Autowired
	P2PDbxxService p2pDbxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showP2PDbxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			P2PDbxxDTO p2pDbxxDto) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		p2pDbxxDto.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<P2PDbxxDTO> p2pDbxxPageModel = p2pDbxxService
				.getP2PDbxxPageList(p2pDbxxDto, currentPage, pageSize);
		model.addAttribute("p2pDbxxPageModel", p2pDbxxPageModel);
		model.addAttribute("queryDto", p2pDbxxDto);
		return "/p2p/dbxx/p2p_dbxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddP2PDbxx(Model model, HttpServletRequest request,
			@RequestParam(value = "dkjbxxId") String dkjbxxId) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("dkjbxxId", dkjbxxId);
		model.addAttribute("orgCode", orgCode);
		return "/p2p/dbxx/p2p_dbxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addP2PDbxx(@RequestBody P2PDbxxDTO p2pDbxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pDbxxDto.setUserId(userId);
		int p2pDbxxId = p2pDbxxService.addP2PDbxx(p2pDbxxDto);
		return p2pDbxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditP2PDbxx(
			Model model,
			@RequestParam(value = "p2pDbxxId", required = false) String p2pDbxxId) {
		P2PDbxxDTO p2pDbxxDto = p2pDbxxService.getP2PDbxxById(Integer
				.parseInt(p2pDbxxId));
		model.addAttribute("p2pDbxxDto", p2pDbxxDto);
		return "/p2p/dbxx/p2p_dbxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editP2PDbxx(@RequestBody P2PDbxxDTO p2pDbxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pDbxxDto.setUserId(userId);
		p2pDbxxService.editP2PDbxx(p2pDbxxDto);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteP2PDbxx(
			@RequestParam(value = "p2pDbxxId", required = false) String p2pDbxxId) {
		p2pDbxxService.deleteP2PDbxxById(Integer.parseInt(p2pDbxxId));
	}

	@RequestMapping(value = "/checkP2PDbxxIsOnly", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	boolean checkP2PDbxxIsOnly(@RequestParam(value = "dbrzjlx") String dbrzjlx,
			@RequestParam(value = "dbrzjhm") String dbrzjhm,
			@RequestParam(value = "dkjbxxId") String dkjbxxId,
			@RequestParam(value = "orgCode") String orgCode,
			@RequestParam(value = "oldDbrzjhm") String oldDbrzjhm) {
		if (StringUtils.isEmpty(dbrzjlx) || StringUtils.isEmpty(dbrzjhm)
				|| StringUtils.isEmpty(dkjbxxId)
				|| StringUtils.isEmpty(orgCode)) {
			return true;
		}
		if (dbrzjhm.equals(oldDbrzjhm)) {
			return true;
		}
		boolean result = p2pDbxxService.p2pDbxxIsOnly(dbrzjlx, dbrzjhm, Integer.parseInt(dkjbxxId), orgCode);
		return result;
	}
}
