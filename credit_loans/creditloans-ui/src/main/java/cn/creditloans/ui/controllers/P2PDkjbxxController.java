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

import cn.creditloans.core.dto.p2p.P2PDkjbxxDTO;
import cn.creditloans.core.service.P2PDkjbxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/p2p/dkjbxx")
public class P2PDkjbxxController {
	private static final Log logger = LogFactory
			.getLog(P2PDkjbxxController.class);

	@Autowired
	P2PDkjbxxService p2pDkjbxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showP2PDkjbxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			P2PDkjbxxDTO p2pDkjbxxDto) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		p2pDkjbxxDto.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<P2PDkjbxxDTO> p2pDkjbxxPageModel = p2pDkjbxxService
				.getP2PDkjbxxPageList(p2pDkjbxxDto, currentPage, pageSize);
		model.addAttribute("p2pDkjbxxPageModel", p2pDkjbxxPageModel);
		model.addAttribute("queryDto", p2pDkjbxxDto);
		return "/p2p/dkjbxx/p2p_dkjbxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddP2PDkjbxx(Model model, HttpServletRequest request) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		return "/p2p/dkjbxx/p2p_dkjbxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addP2PDkjbxx(@RequestBody P2PDkjbxxDTO p2pDkjbxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pDkjbxxDto.setUserId(userId);
		int p2pDkjbxxId = p2pDkjbxxService.addP2PDkjbxx(p2pDkjbxxDto);
		return p2pDkjbxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditP2PDkjbxx(
			Model model,
			@RequestParam(value = "p2pDkjbxxId", required = false) String p2pDkjbxxId) {
		P2PDkjbxxDTO p2pDkjbxxDto = p2pDkjbxxService.getP2PDkjbxxById(Integer
				.parseInt(p2pDkjbxxId));
		model.addAttribute("p2pDkjbxxDto", p2pDkjbxxDto);
		return "/p2p/dkjbxx/p2p_dkjbxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editP2PDkjbxx(@RequestBody P2PDkjbxxDTO p2pDkjbxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pDkjbxxDto.setUserId(userId);
		p2pDkjbxxService.editP2PDkjbxx(p2pDkjbxxDto);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteP2PDkjbxx(
			@RequestParam(value = "p2pDkjbxxId", required = false) String p2pDkjbxxId) {
		p2pDkjbxxService.deleteP2PDkjbxxById(Integer.parseInt(p2pDkjbxxId));
	}

	@RequestMapping(value = "/checkP2PDkjbxxIsOnly", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	boolean checkP2PDkjbxxIsOnly(@RequestParam(value = "dkhthm") String dkhthm,
			@RequestParam(value = "ywh") String ywh,
			@RequestParam(value = "jsyhkrq") String jsyhkrq,
			@RequestParam(value = "orgCode") String orgCode) {
		if (StringUtils.isEmpty(dkhthm) && StringUtils.isEmpty(ywh)
				&& StringUtils.isEmpty(jsyhkrq) && StringUtils.isEmpty(orgCode)) {
			return true;
		}
		boolean result = p2pDkjbxxService.p2pDkjbxxIsOnly(dkhthm, ywh, jsyhkrq,
				orgCode);
		return result;
	}
}
