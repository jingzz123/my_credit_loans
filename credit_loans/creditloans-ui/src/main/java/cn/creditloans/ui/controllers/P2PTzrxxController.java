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

import cn.creditloans.core.dto.p2p.P2PTzrxxDTO;
import cn.creditloans.core.service.P2PTzrxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/p2p/tzrxx")
public class P2PTzrxxController {
	private static final Log logger = LogFactory
			.getLog(P2PTzrxxController.class);

	@Autowired
	P2PTzrxxService p2pTzrxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showP2PTzrxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			P2PTzrxxDTO p2pTzrxxDto) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		p2pTzrxxDto.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<P2PTzrxxDTO> p2pTzrxxPageModel = p2pTzrxxService
				.getP2PTzrxxPageList(p2pTzrxxDto, currentPage, pageSize);
		model.addAttribute("p2pTzrxxPageModel", p2pTzrxxPageModel);
		model.addAttribute("queryDto", p2pTzrxxDto);
		return "/p2p/tzrxx/p2p_tzrxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddP2PTzrxx(Model model, HttpServletRequest request,
			@RequestParam(value = "dkjbxxId") String dkjbxxId) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("dkjbxxId", dkjbxxId);
		model.addAttribute("orgCode", orgCode);
		return "/p2p/tzrxx/p2p_tzrxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addP2PTzrxx(@RequestBody P2PTzrxxDTO p2pTzrxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pTzrxxDto.setUserId(userId);
		int p2pTzrxxId = p2pTzrxxService.addP2PTzrxx(p2pTzrxxDto);
		return p2pTzrxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditP2PTzrxx(
			Model model,
			@RequestParam(value = "p2pTzrxxId", required = false) String p2pTzrxxId) {
		P2PTzrxxDTO p2pTzrxxDto = p2pTzrxxService.getP2PTzrxxById(Integer
				.parseInt(p2pTzrxxId));
		model.addAttribute("p2pTzrxxDto", p2pTzrxxDto);
		return "/p2p/tzrxx/p2p_tzrxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editP2PTzrxx(@RequestBody P2PTzrxxDTO p2pTzrxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pTzrxxDto.setUserId(userId);
		p2pTzrxxService.editP2PTzrxx(p2pTzrxxDto);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteP2PTzrxx(
			@RequestParam(value = "p2pTzrxxId", required = false) String p2pTzrxxId) {
		p2pTzrxxService.deleteP2PTzrxxById(Integer.parseInt(p2pTzrxxId));
	}

	@RequestMapping(value = "/checkP2PTzrxxIsOnly", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	boolean checkP2PTzrxxIsOnly(@RequestParam(value = "tzrzjlx") String tzrzjlx,
			@RequestParam(value = "tzrzjhm") String tzrzjhm,
			@RequestParam(value = "dkjbxxId") String dkjbxxId,
			@RequestParam(value = "orgCode") String orgCode,
			@RequestParam(value = "oldTzrzjhm") String oldTzrzjhm) {
		if (StringUtils.isEmpty(tzrzjlx) || StringUtils.isEmpty(tzrzjhm)
				|| StringUtils.isEmpty(dkjbxxId)
				|| StringUtils.isEmpty(orgCode)) {
			return true;
		}
		if (tzrzjhm.equals(oldTzrzjhm)) {
			return true;
		}
		boolean result = p2pTzrxxService.p2pTzrxxIsOnly(tzrzjlx, tzrzjhm, Integer.parseInt(dkjbxxId), orgCode);
		return result;
	}
}
