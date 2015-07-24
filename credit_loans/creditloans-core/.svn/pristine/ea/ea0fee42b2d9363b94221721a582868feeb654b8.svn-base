package cn.creditloans.core.service.impl.enterprise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dto.enterprise.QueryCompareCondition;
import cn.creditloans.core.dto.enterprise.SingleQueryResultDTO;
import cn.creditloans.core.service.BasicCacheQueryService;
import cn.creditloans.core.service.BasicQueryService;
import cn.creditloans.core.service.BlackInfoSearchService;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

/**
 * 外部接口调用实现类
 * @author Administrator
 *
 */
@Service("blackInfoSearchService")
public class BlackInfoSearchServiceImpl implements BlackInfoSearchService {
	
	@Autowired
	private BasicQueryService basicQueryService;
	
	@Autowired
	private BasicCacheQueryService basicCacheQueryService;

	// 对外接口单条查询比对
	@Override
	public List<SingleQueryResultDTO> singleQuery(QueryCompareCondition queryCompareCondition) {
		if (CreditloansPropertyPlaceholderConfigurer.getContextProperty("saveincache").equals("0")) {
			// 从缓存查
			return basicCacheQueryService.singleQuery(queryCompareCondition);
		} else {
			// 查库
			return basicQueryService.singleQuery(queryCompareCondition);
		}
	}

	// 对外接口查询比对 联合查询
	@Override
	public SingleQueryResultDTO jointSingleQuery(QueryCompareCondition queryCompareCondition) {
		if (CreditloansPropertyPlaceholderConfigurer.getContextProperty("saveincache").equals("0")) {
			// 从缓存查
			return basicCacheQueryService.jointSingleQuery(queryCompareCondition);
		} else {
			// 查库
			return basicQueryService.jointSingleQuery(queryCompareCondition);
		}
	}

}
