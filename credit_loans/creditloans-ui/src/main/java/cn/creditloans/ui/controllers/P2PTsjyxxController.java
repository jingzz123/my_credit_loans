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

import cn.creditloans.core.dto.p2p.P2PTsjyxxDTO;
import cn.creditloans.core.service.P2PTsjyxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/p2p/tsjyxx")
public class P2PTsjyxxController {
	private static final Log logger = LogFactory
			.getLog(P2PTsjyxxController.class);

	@Autowired
	P2PTsjyxxService p2pTsjyxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showP2PTsjyxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			P2PTsjyxxDTO p2pTsjyxxDto) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		p2pTsjyxxDto.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<P2PTsjyxxDTO> p2pTsjyxxPageModel = p2pTsjyxxService
				.getP2PTsjyxxPageList(p2pTsjyxxDto, currentPage, pageSize);
		model.addAttribute("p2pTsjyxxPageModel", p2pTsjyxxPageModel);
		model.addAttribute("queryDto", p2pTsjyxxDto);
		return "/p2p/tsjyxx/p2p_tsjyxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddP2PTsjyxx(Model model, HttpServletRequest request) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		return "/p2p/tsjyxx/p2p_tsjyxx_add";
	}

	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addP2PTsjyxx(@RequestBody P2PTsjyxxDTO p2pTsjyxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pTsjyxxDto.setUserId(userId);
		int p2pTsjyxxId = p2pTsjyxxService.addP2PTsjyxx(p2pTsjyxxDto);
		return p2pTsjyxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditP2PTsjyxx(
			Model model,
			@RequestParam(value = "p2pTsjyxxId", required = false) String p2pTsjyxxId) {
		P2PTsjyxxDTO p2pTsjyxxDto = p2pTsjyxxService.getP2PTsjyxxById(Integer
				.parseInt(p2pTsjyxxId));
		model.addAttribute("p2pTsjyxxDto", p2pTsjyxxDto);
		return "/p2p/tsjyxx/p2p_tsjyxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editP2PTsjyxx(@RequestBody P2PTsjyxxDTO p2pTsjyxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pTsjyxxDto.setUserId(userId);
		p2pTsjyxxService.editP2PTsjyxx(p2pTsjyxxDto);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteP2PTsjyxx(
			@RequestParam(value = "p2pTsjyxxId", required = false) String p2pTsjyxxId) {
		p2pTsjyxxService.deleteP2PTsjyxxById(Integer.parseInt(p2pTsjyxxId));
	}

	@RequestMapping(value = "/checkP2PTsjyxxIsOnly", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	boolean checkP2PTsjyxxIsOnly(@RequestParam(value = "zjlx") String zjlx,
			@RequestParam(value = "zjhm") String zjhm,
			@RequestParam(value = "fsrq") String fsrq,
			@RequestParam(value = "orgCode") String orgCode,
			@RequestParam(value = "oldZjhm") String oldZjhm) {
		if (StringUtils.isEmpty(zjlx) || StringUtils.isEmpty(zjhm) || StringUtils.isEmpty(fsrq)
				|| StringUtils.isEmpty(orgCode)) {
			return true;
		}
		if (zjhm.equals(oldZjhm)) {
			return true;
		}
		boolean result = p2pTsjyxxService.p2pTsjyxxIsOnly(zjlx, zjhm, fsrq, orgCode);
		return result;
	}
}
