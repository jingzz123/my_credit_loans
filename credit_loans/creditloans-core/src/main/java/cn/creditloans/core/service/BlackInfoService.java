package cn.creditloans.core.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.creditloans.core.dto.enterprise.BlackInfoDTO;
import cn.creditloans.core.dto.enterprise.QueryCompareCondition;
import cn.creditloans.core.dto.enterprise.QueryCompareResult;
import cn.creditloans.core.dto.enterprise.QueryCompareResultItem;
import cn.creditloans.core.dto.enterprise.UploadResultInfoDTO;

/**
 * 黑名单信息Service
 * @author Administrator
 *
 */
public interface BlackInfoService {
	
	/**
	 * 保存黑名单返回Id
	 * @param blackInfoDTO
	 * @return
	 */
	public int saveSingleBlackInfo(BlackInfoDTO blackInfoDTO, String token);
	
	/**
	 * 查询比对
	 * @param queryCompareConditionDTO
	 * @param token
	 * @return
	 */
	public QueryCompareResult queryBlackInfo(QueryCompareCondition queryCompareConditionDTO, String token);
	
	/**
	 * 查询比对 联合查询
	 * @param queryCompareConditionDTO
	 * @param token
	 * @return
	 */
	public QueryCompareResultItem jointQueryBlackInfo(QueryCompareCondition queryCompareConditionDTO, String token);

	/**
	 * 上传黑名单
	 * @param file
	 * @param isCover
	 * @param token
	 * @return
	 */
	public UploadResultInfoDTO uploadFile(CommonsMultipartFile file, String token);
	
	/**
	 * 插入正确数据
	 * @param token
	 * @return
	 */
	public boolean insertDatas(String token);

}
