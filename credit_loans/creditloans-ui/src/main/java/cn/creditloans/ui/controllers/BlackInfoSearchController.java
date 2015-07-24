package cn.creditloans.ui.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;

import cn.creditloans.core.dto.enterprise.QueryCompareCondition;
import cn.creditloans.core.dto.enterprise.SingleQueryResultDTO;
import cn.creditloans.core.service.BlackInfoSearchService;
import cn.creditloans.tools.encrypt.MD5Util;

@Controller
@RequestMapping(value = "/blacklist/search")
public class BlackInfoSearchController {
	
	@Autowired
	private BlackInfoSearchService blackInfoSearchService;
	
	@RequestMapping(value="/singleQuery", method={RequestMethod.GET, RequestMethod.POST})
	public void singleQuery(@RequestBody QueryCompareCondition queryCompareConditionDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = queryCompareConditionDTO.getToken();
		if (token == null || "".equals(token)) {
			return;
		}
		String depCode = queryCompareConditionDTO.getDepCode();
		String oldToken = MD5Util.md5(depCode);
		String jsonStr = null;
		if (oldToken.equals(token)) {
			List<SingleQueryResultDTO> list = blackInfoSearchService.singleQuery(queryCompareConditionDTO);
			if (list != null && list.size() > 0) {
				jsonStr = JSONArray.toJSONString(list);
			}
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(jsonStr);
		printWriter.flush();
		printWriter.close();
	}
	
	@RequestMapping(value="/jointSingleQuery", method={RequestMethod.GET, RequestMethod.POST})
	public void jointSingleQuery(@RequestBody QueryCompareCondition queryCompareConditionDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = queryCompareConditionDTO.getToken();
		if (token == null || "".equals(token)) {
			return;
		}
		String depCode = queryCompareConditionDTO.getDepCode();
		String oldToken = MD5Util.md5(depCode);
		String jsonStr = null;
		if (oldToken.equals(token)) {
			SingleQueryResultDTO singleQueryResultDTO = blackInfoSearchService.jointSingleQuery(queryCompareConditionDTO);
			if (singleQueryResultDTO != null) {
				jsonStr = JSONArray.toJSONString(singleQueryResultDTO);
			}
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(jsonStr);
		printWriter.flush();
		printWriter.close();
	}

}
