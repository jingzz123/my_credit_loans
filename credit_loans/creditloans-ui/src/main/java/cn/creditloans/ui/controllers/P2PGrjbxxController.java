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

import cn.creditloans.core.dto.p2p.P2PGrjbxxDTO;
import cn.creditloans.core.service.P2PGrjbxxService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Controller
@RequestMapping(value = "/p2p/grjbxx")
public class P2PGrjbxxController {
	private static final Log logger = LogFactory
			.getLog(P2PGrjbxxController.class);

	@Autowired
	P2PGrjbxxService p2pGrjbxxService;

	@RequestMapping(value = "/showList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showP2PGrjbxxList(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String _currentPage,
			P2PGrjbxxDTO p2pGrjbxxDto) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		p2pGrjbxxDto.setOrgCode(orgCode);
		int currentPage = 1;
		if (_currentPage != null && _currentPage != "") {
			currentPage = Integer.parseInt(_currentPage.toString());
		}
		int pageSize = Integer.parseInt(CreditloansPropertyPlaceholderConfigurer
				.getContextProperty(Constants.ENTERPRISE_PAGE_SHOW_COUNT));
		PageModel<P2PGrjbxxDTO> p2pGrjbxxPageModel = p2pGrjbxxService
				.getP2PGrjbxxPageList(p2pGrjbxxDto, currentPage, pageSize);
		model.addAttribute("p2pGrjbxxPageModel", p2pGrjbxxPageModel);
		model.addAttribute("queryDto", p2pGrjbxxDto);
		return "/p2p/grjbxx/p2p_grjbxx_list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showAddP2PGrjbxx(Model model, HttpServletRequest request) {
		// FIXME: 读取当前用户所在机构的组织机构代码
		String orgCode = "000000000000001";
		model.addAttribute("orgCode", orgCode);
		return "/p2p/grjbxx/p2p_grjbxx_add";
	}
	
	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	int addP2PGrjbxx(@RequestBody P2PGrjbxxDTO p2pGrjbxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pGrjbxxDto.setUserId(userId);
		int p2pGrjbxxId = p2pGrjbxxService.addP2PGrjbxx(p2pGrjbxxDto);
		return p2pGrjbxxId;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showEditP2PGrjbxx(
			Model model,
			@RequestParam(value = "p2pGrjbxxId", required = false) String p2pGrjbxxId) {
		P2PGrjbxxDTO p2pGrjbxxDto = p2pGrjbxxService.getP2PGrjbxxById(Integer
				.parseInt(p2pGrjbxxId));
		model.addAttribute("p2pGrjbxxDto", p2pGrjbxxDto);
		return "/p2p/grjbxx/p2p_grjbxx_edit";
	}

	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void editP2PGrjbxx(@RequestBody P2PGrjbxxDTO p2pGrjbxxDto) {
		// FIXME: 读取当前用户信息
		int userId = 1;
		p2pGrjbxxDto.setUserId(userId);
		p2pGrjbxxService.editP2PGrjbxx(p2pGrjbxxDto);
	}

	@RequestMapping(value = "/doDelete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	void deleteP2PGrjbxx(
			@RequestParam(value = "p2pGrjbxxId", required = false) String p2pGrjbxxId) {
		p2pGrjbxxService.deleteP2PGrjbxxById(Integer.parseInt(p2pGrjbxxId));
	}

	@RequestMapping(value = "/checkZjhm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	boolean checkZjhm(@RequestParam(value = "zjlx") String zjlx,
			@RequestParam(value = "zjhm") String zjhm,
			@RequestParam(value = "orgCode") String orgCode,
			@RequestParam(value = "oldZjhm") String oldZjhm) {
		if (StringUtils.isEmpty(zjhm) || StringUtils.isEmpty(zjlx) || StringUtils.isEmpty(orgCode)) {
			return true;
		}
		if (zjhm.equals(oldZjhm)) {
			return true;
		}
		boolean checkResult = p2pGrjbxxService.p2pGrjbxxIsOnly(zjlx, zjhm, orgCode);
		return checkResult;
	}
}
