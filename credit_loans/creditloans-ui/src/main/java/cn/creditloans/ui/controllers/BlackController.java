package cn.creditloans.ui.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.creditloans.core.cache.redis.UploadResultInfoCache;
import cn.creditloans.core.dto.enterprise.BlackInfoDTO;
import cn.creditloans.core.dto.enterprise.QueryCompareCondition;
import cn.creditloans.core.dto.enterprise.QueryCompareResult;
import cn.creditloans.core.dto.enterprise.QueryCompareResultItem;
import cn.creditloans.core.dto.enterprise.UploadResultInfoDTO;
import cn.creditloans.core.service.BlackInfoService;
import cn.creditloans.tools.context.AppContext;
import cn.creditloans.tools.parameters.ParameterConfig;
import cn.creditloans.ui.util.cookie.CookieUtil;

@Controller
@RequestMapping(value = "/black")
public class BlackController {
	
	private static final Log logger = LogFactory.getLog(BlackController.class);
	
	@Autowired
	private BlackInfoService blackInfoService;
	
	/**
	 * 跳转到黑名单增加页面 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/singleAddBlack", method = {RequestMethod.GET, RequestMethod.POST})
	public String showAddBlack(Model model) {
		ParameterConfig parameterConfig = AppContext.getInstance().getPrCongif();
		Map<String, String> loanMap = parameterConfig.getParameterInfo(ParameterConfig.LOAN_TYPE).getKeyValueMap();
		Map<String, String> confirmedTypeMap = parameterConfig.getParameterInfo(ParameterConfig.CONFIRMED_TYPE).getKeyValueMap();
		Map<String, String> contactRelationshipMap = parameterConfig.getParameterInfo(ParameterConfig.CONTACTRELATIONSHIP).getKeyValueMap();
		model.addAttribute("loanMap", loanMap);
		model.addAttribute("confirmedTypeMap", confirmedTypeMap);
		model.addAttribute("contactRelationshipMap", contactRelationshipMap);
		logger.debug("load singleAddBlack page success");
		return "/black/upload/single_add_black";
	}
	
	/**
	 * 加载显示确认信息详情的选择范围
	 * @param val
	 * @return
	 */
	@RequestMapping(value="/showConfirmedDetailsMap", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Map<String, List<String>> showConfirmedDetailsMap(@RequestParam("val")String val) {
		List<String>keyList = new ArrayList<String>();
		List<String>valList = new ArrayList<String>();
		ParameterConfig parameterConfig = AppContext.getInstance().getPrCongif();
		Map<String, String> confirmedTypeMap = parameterConfig.getParameterInfo(ParameterConfig.CONFIRMED_TYPE).getKeyValueMap();
		String values = confirmedTypeMap.get(val);
		String startKey = parameterConfig.getParameterInfo(ParameterConfig.CONFIRMED_TYPE_CONFIRMED_DETAILS).getValue(values);
		Map<String, List<String>>map = new HashMap<String, List<String>>();
		if(startKey != null && !"".equals(startKey)) {
			Map<String, String> confirmedDetailsMap = parameterConfig.getParameterInfo(ParameterConfig.CONFIRMED_DETAILS).getKeyValueMap();
			Set<String> set = confirmedDetailsMap.keySet();
			for (String string : set) {
				if(string.startsWith(startKey)) {
					keyList.add(string);
					valList.add(confirmedDetailsMap.get(string));
				}
			}
			map.put("keyList", keyList);
			map.put("valList", valList);
		}
		return map;
	}
	
	/**
	 * 页面添加黑名单信息
	 * @param blackInfoDTO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doSingleAddBlack", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody int doSingleAddBlack(@RequestBody BlackInfoDTO blackInfoDTO, HttpServletRequest request) {
		String token = CookieUtil.getToken(request,"enterprise-token");
		int id = blackInfoService.saveSingleBlackInfo(blackInfoDTO, token);
		if(id > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 跳转到查询比对页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showQueryBlackInfo", method = {RequestMethod.GET, RequestMethod.POST})
	public String showQueryBlackInfo(Model model) {
		return "/black/query/query_compare";
	}
	
	/**
	 * 查询比对
	 * @param queryCompareConditionDTO 查询条件DTO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/query", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody QueryCompareResult queryBlackInfo(@RequestBody QueryCompareCondition queryCompareConditionDTO, HttpServletRequest request) {
		String token = CookieUtil.getToken(request,"enterprise-token");
		QueryCompareResult queryCompareResult = blackInfoService.queryBlackInfo(queryCompareConditionDTO, token);
		return queryCompareResult;
	}
	
	/**
	 * 查询比对 联合查询
	 * @param queryCompareConditionDTO 查询条件DTO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/jointQuery", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody QueryCompareResultItem jointQueryBlackInfo(@RequestBody QueryCompareCondition queryCompareConditionDTO, HttpServletRequest request) {
		String token = CookieUtil.getToken(request,"enterprise-token");
		QueryCompareResultItem queryCompareResultItem = blackInfoService.jointQueryBlackInfo(queryCompareConditionDTO, token);
		return queryCompareResultItem;
	}
	
	@RequestMapping(value="/notFount", method = {RequestMethod.GET, RequestMethod.POST})
	public String notFount(){
		return "/not_fount";
	}
	
	@RequestMapping(value="/showUploadCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public String showUploadCheck() {
		return "/black/upload/batch_upload_black";
	}
	/**
	 * 上传文件，返回错误数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadCheck", method = RequestMethod.POST)
	public @ResponseBody void ajaxUpload(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) req.getFile("fileField");
		StringBuffer buf = new StringBuffer();
		String token = CookieUtil.getToken(request, "enterprise-token");
		try {
			UploadResultInfoDTO result = blackInfoService.uploadFile(file, token);
			// 上传失败信息
			String errInfo = result.getErrorInfo();
			buf.append("<response>");
			buf.append("<tips>").append(errInfo).append("</tips>\r\n");
			buf.append("</response>");
		} catch (Exception e) {
			buf.append("<response>");
			buf.append("<tips>上传失败").append("</tips>\r\n");
			buf.append("</response>");
			e.printStackTrace();
		} finally {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			try {
				response.getWriter().println(buf);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/showNoPassInfo", method = {RequestMethod.GET, RequestMethod.POST})
	public String showNoPassInfo(@RequestParam int pageIndex, HttpServletRequest request, Model model) {
		String token = CookieUtil.getToken(request, "enterprise-token");
		UploadResultInfoDTO result = UploadResultInfoCache.getInstance().get(token);
		// 检验不通过的数据
		List<String> errInformation = result.getErrorInformation();
		// 上传失败信息
		String errInfo = result.getErrorInfo();
		String[] errInfoArr = null;
		if(errInfo == null || "".equals(errInfo)) {
			// 校验不同时跳转到上传结果显示（upload_result）页面
			if (errInformation != null && errInformation.size() != 0) {
				if(errInformation.indexOf("</table>") != -1) {
					model.addAttribute("count", errInfoArr.length);
					model.addAttribute("errInfoArr", errInfoArr);
					model.addAttribute("pageInd", errInfoArr);
				} else {
					model.addAttribute("count", 0);
					model.addAttribute("errInfoArr", "所有数据都已经加载到数据库。");
				}
			}
			model.addAttribute("time", JSONArray.toJSONString(result.getTotalTime()));
			model.addAttribute("infoDTO", result);
		}
		return "/black/upload/batch_upload_result";
	}
	
}
