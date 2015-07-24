package cn.creditloans.core.service.impl.enterprise;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.creditloans.core.cache.redis.UploadResultInfoCache;
import cn.creditloans.core.dao.BlackInfoDao;
import cn.creditloans.core.dao.EnterpriseDao;
import cn.creditloans.core.dao.UploadSummariesDao;
import cn.creditloans.core.dto.enterprise.BlackInfoDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.core.dto.enterprise.QueryCompareCondition;
import cn.creditloans.core.dto.enterprise.QueryCompareResult;
import cn.creditloans.core.dto.enterprise.QueryCompareResultItem;
import cn.creditloans.core.dto.enterprise.UploadResultInfoDTO;
import cn.creditloans.core.entity.enterprise.BlackInfo;
import cn.creditloans.core.entity.enterprise.Enterprise;
import cn.creditloans.core.entity.enterprise.UploadSummaries;
import cn.creditloans.core.service.BasicCacheQueryService;
import cn.creditloans.core.service.BasicQueryService;
import cn.creditloans.core.service.BlackInfoService;
import cn.creditloans.core.service.EnterpriseUserService;
import cn.creditloans.tools.cache.datas.MemoryCorrectDatas;
import cn.creditloans.tools.cache.datas.MemoryErrorDatas;
import cn.creditloans.tools.cache.redis.HomeAddressCache;
import cn.creditloans.tools.cache.redis.HomeTelCache;
import cn.creditloans.tools.cache.redis.IdNumberCache;
import cn.creditloans.tools.cache.redis.MobileCache;
import cn.creditloans.tools.cache.redis.NameCache;
import cn.creditloans.tools.cache.redis.UploadCorrectDatasCache;
import cn.creditloans.tools.cache.redis.UploadErrorDatasCache;
import cn.creditloans.tools.cache.redis.WorkAddressCache;
import cn.creditloans.tools.cache.redis.WorkNameCache;
import cn.creditloans.tools.cache.redis.WorkTelCache;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.context.AppContext;
import cn.creditloans.tools.fuzzy.address.AddressFuzzyThread;
import cn.creditloans.tools.fuzzy.address.HomeAddressFuzzyApp;
import cn.creditloans.tools.fuzzy.address.WorkAddressFuzzyApp;
import cn.creditloans.tools.fuzzy.corp.CorpFuzzyApp;
import cn.creditloans.tools.fuzzy.corp.CorpFuzzyThread;
import cn.creditloans.tools.fuzzy.util.FuzzyConstants;
import cn.creditloans.tools.fuzzy.util.FuzzyUtil;
import cn.creditloans.tools.fuzzy.util.IndexValue;
import cn.creditloans.tools.parameters.ParameterConfig;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;
import cn.creditloans.tools.util.DateUtils;
import cn.creditloans.tools.validator.transaction.Schema;
import cn.creditloans.tools.validator.transaction.Transaction;
import cn.creditloans.tools.validatorEx.excel.ExcelSource;
import cn.creditloans.tools.validatorEx.excel.ExcelValidator;
import cn.creditloans.tools.validatorEx.util.DFConfig;
import cn.creditloans.tools.validatorEx.util.FlowInfo;

@Service("blackInfoService")
public class BlackInfoServiceImpl implements BlackInfoService {
	
	/**
	 * 用作redis缓存的key的前缀
	 */
	private static final String KEY_PREFIX = "BLACK_TYPE_";
	
	@Autowired
	private UploadSummariesDao uploadSummariesDao;
	
	@Autowired
	private EnterpriseUserService enterpriseUserService;
	
	@Autowired
	private BlackInfoDao blackInfoDao;
	
	@Autowired
	private BasicQueryService basicQueryService;
	
	@Autowired
	private BasicCacheQueryService basicCacheQueryService;
	
	@Autowired
	private EnterpriseDao enterpriseDao;
	
	// 保存黑名单信息
	@Override
	public int saveSingleBlackInfo(BlackInfoDTO blackInfoDTO, String token) {
		EnterpriseUserDTO enterpriseUserDTO = enterpriseUserService.getEnterpriseUserDtoFromCache(token);
		int userId = enterpriseUserDTO.getId();
		int depId = enterpriseUserDTO.getDepId();
		UploadSummaries uploadSummaries = new UploadSummaries();
		uploadSummaries.setRecordCount(1);
		uploadSummaries.setValidatedRecordCount(1);
		uploadSummaries.setUserId(userId);
		uploadSummaries.setDepId(depId);
		uploadSummariesDao.saveUploadSummaries(uploadSummaries);
		List<String> contactNamesList = blackInfoDTO.getContactNames();
		List<String> contactTellsList = blackInfoDTO.getContactTells();
		List<String> contactWorksList = blackInfoDTO.getContactWorks();
		List<Integer> contactRelationshipsList = blackInfoDTO.getContactRelationships();
		BlackInfo blackInfo = new BlackInfo();
		BeanUtils.copyProperties(blackInfoDTO, blackInfo);
		blackInfo.setUserId(userId);
		blackInfo.setDepId(depId);
		blackInfo.setBatchId(uploadSummaries.getId());
		blackInfo.standard();
		for (int i = 0; i < contactNamesList.size(); i++) {
			String contactName = contactNamesList.get(i);
			if (i == 0) {
				blackInfo.setContactName1(contactName);
			}
			if (i == 1) {
				blackInfo.setContactName2(contactName);
			}
			if (i == 2) {
				blackInfo.setContactName3(contactName);
			}
		}
		for (int i = 0; i < contactTellsList.size(); i++) {
			String contactTell = contactTellsList.get(i);
			if (i == 0) {
				blackInfo.setContactTell1(contactTell);
			}
			if (i == 1) {
				blackInfo.setContactTell2(contactTell);
			}
			if (i == 2) {
				blackInfo.setContactTell3(contactTell);
			}
		}
		for (int i = 0; i < contactWorksList.size(); i++) {
			String contactWork = contactWorksList.get(i);
			if (i == 0) {
				blackInfo.setContactWork1(contactWork);
			}
			if (i == 1) {
				blackInfo.setContactWork2(contactWork);
			}
			if (i == 2) {
				blackInfo.setContactWork3(contactWork);
			}
		}
		for (int i = 0; i < contactRelationshipsList.size(); i++) {
			int contactRelationship = contactRelationshipsList.get(i);
			if (i == 0) {
				blackInfo.setContactRelationship1(contactRelationship);
			}
			if (i == 1) {
				blackInfo.setContactRelationship2(contactRelationship);
			}
			if (i == 2) {
				blackInfo.setContactRelationship3(contactRelationship);
			}
		}
		// 保存 
		blackInfoDao.saveBlackInfo(blackInfo);
		int blackInfoId = blackInfo.getId();
		if (CreditloansPropertyPlaceholderConfigurer.getContextProperty("saveincache").equals("0")) {
			addToCache(blackInfo);
		}
		return blackInfoId;
	}

	// 查询比对
	@Override
	public QueryCompareResult queryBlackInfo(QueryCompareCondition queryCompareConditionDTO, String token) {
		// 返回结果
		QueryCompareResult queryCompareResult = new QueryCompareResult();
		List<QueryCompareResultItem> queryCompareResultItemList;
		if (CreditloansPropertyPlaceholderConfigurer.getContextProperty("saveincache").equals("0")) {
			// 从缓存查
			queryCompareResultItemList = queryBlackInfoFromCache(queryCompareConditionDTO, token);
		} else {
			// 查库
			queryCompareResultItemList = queryBlaclInfoFromDB(queryCompareConditionDTO, token);
		}
		queryCompareResult.setQueryCompareResultItemList(queryCompareResultItemList);
		return queryCompareResult;
	}
	
	// 查询比对 联合查询
	@Override
	public QueryCompareResultItem jointQueryBlackInfo(QueryCompareCondition queryCompareConditionDTO, String token) {
		// 查询的时间条件
		Map<Integer, Date> dateConditionMap = getDateCondition();
		// 查询的银行id list条件
		List<Integer> bankIdCondition = getBankCondition(queryCompareConditionDTO.getDepCondition(), token);
		// 返回结果
		QueryCompareResultItem queryCompareResultItem;
		if (CreditloansPropertyPlaceholderConfigurer.getContextProperty("saveincache").equals("0")) {
			// 从缓存查
			queryCompareResultItem = basicCacheQueryService.jointQueryBlackInfo(queryCompareConditionDTO, dateConditionMap, bankIdCondition);
		} else {
			// 查库
			queryCompareResultItem = basicQueryService.jointQueryBlackInfo(queryCompareConditionDTO, dateConditionMap, bankIdCondition);
		}
		return queryCompareResultItem;
	}

	// 上传黑名单
	@Override
	public UploadResultInfoDTO uploadFile(CommonsMultipartFile file, String token) {
		long begin = System.currentTimeMillis();
		// 返回值
		UploadResultInfoDTO resultInfoDto = new UploadResultInfoDTO();
		MemoryErrorDatas errorDatas = new MemoryErrorDatas();
		MemoryCorrectDatas correctDatas = new MemoryCorrectDatas();
		FileItem fileItem = file.getFileItem();
		String fileName = fileItem.getName();
		if (fileName == null || "".equals(fileName)) {
			resultInfoDto.setSuccess(false);
			resultInfoDto.setErrorInfo("文件不合法，可能被损坏，上传失败");
			UploadResultInfoCache.getInstance().set(token, resultInfoDto);
			return resultInfoDto;
		}
		if (!fileName.endsWith(".xls")) {
			resultInfoDto.setSuccess(false);
			resultInfoDto.setErrorInfo("文件格式不正确，您只能选择Excel 2003/1997格式数据文件上传");
			UploadResultInfoCache.getInstance().set(token, resultInfoDto);
			return resultInfoDto;
		}
		errorDatas.setFileName(fileName);
		errorDatas.setBusinessType("01");
		correctDatas.setFileName(fileName);
		
		// schema name 和 flow name是相同的
		String schemaName = AppContext.getInstance().getPrCongif().getParameterInfo(ParameterConfig.PARAMETER_FLOW).getValue("01");
		DFConfig config = AppContext.getInstance().getDfConfig();
		
		// source设定参数
		ExcelSource source = new ExcelSource();
		source.setDfConfig(config);
		source.setParameter(Constants.FLOW_NAME, schemaName);
		source.setMemoryCorrectDatas(correctDatas);
		source.setMemoryErrorDatas(errorDatas);
		source.setNeedBatch(false); // 这个没有必要分批处理
		source.setBytes(file.getBytes());
		
		// validator设定参数
		// int bankCode = outDepartmentDao.selectOuterDeparmentById(userDto.getDepId()).getCode();
		// int bankCode = userDto.getDepartmentDto().getCode();
		// String bankName = AppContext.getInstance().getPrCongif().getParameterInfo(ParameterConfig.PARAMETER_BANK).getValue(String.valueOf(bankCode));
		
		Map<String, Object> validatorMap = new HashMap<String, Object>();
		//validatorMap.put("businessType", typeCode);
		//validatorMap.put("bankID", bankCode);
		//validatorMap.put("bankName", bankName);
		validatorMap.put("begin_date", AppContext.getInstance().getConstants().getProperty(Constants.INFORMATION_BEGIN_DATE));
		validatorMap.put("end_date", DateUtils.formatIntegerDate(new Date()));
		
		ExcelValidator validator = new ExcelValidator();
		validator.setDfConfig(config);
		validator.setMemoryCorrectDatas(correctDatas);
		validator.setMemoryErrorDatas(errorDatas);
		validator.setParameter(Constants.FLOW_NAME, schemaName);
		validator.setValidatorMap(validatorMap);
		
		// source init
		boolean correct = source.init();
		if (!correct) {
			resultInfoDto.setSuccess(false);
			resultInfoDto.setErrorInfo(source.getErrorInfo());
			source.cleanUp();
			return resultInfoDto;
		}
		
		validator.init();
		try {
			while (source.readBatch()) {
				// 数据校验
				validator.process();
			}
		}catch(Exception e){
			resultInfoDto.setSuccess(false);
			resultInfoDto.setErrorInfo("上传文件内部格式错误！");
			source.cleanUp();
			correctDatas = null;
			errorDatas = null;
			return resultInfoDto;
		}
		UploadErrorDatasCache.getInstance().delete(token); // 删除原有
		if (errorDatas.hasDatas()) { // 如果存在正确数据
			UploadErrorDatasCache.getInstance().set(token, errorDatas);
		}
		UploadCorrectDatasCache.getInstance().delete(token);
		if (correctDatas.hasDatas()) {
			UploadCorrectDatasCache.getInstance().set(token, correctDatas);
		}
		source.cleanUp();
        // 正确数据记录数
        int correctCount = correctDatas.getCorrects().size();
        // 错误数据记录数
        int errorCount = errorDatas.getSameInPks().size() + errorDatas.getErrorRules().size();
        // 文件类型记录数
        // String type = AppContext.getInstance().getPrCongif().getParameterInfo(ParameterConfig.PARAMETER_BUSINESS)
        //       .getValue(typeCode);
        // 当全部正确时直接加载到数据库
        if (errorCount == 0 && correctCount != 0) {
        	// 插入数据到数据库（返回boolean true为成功保存，false为保存失败）调用insertDatas确保redis缓存中也有数据
        	insertDatas(token);
        	resultInfoDto.setSuccess(true);
        } else {// 包含错误数据，不入库
        	resultInfoDto.setSuccess(false);
        }
		// 计算耗时
		String time = DateUtils.getMinuAndSecondInfo(System.currentTimeMillis() - begin);
		resultInfoDto.setTotalTime(time);
		// 通过的条数
		resultInfoDto.setCorrectCount(correctCount);
		// 未通过的条数
		resultInfoDto.setErrorCount(errorCount);
		// 界面显示的错误信息
		List<String> errorInformation = getErrorHtmlShows(errorDatas);
		if (errorInformation != null && errorInformation.size() != 0) {
			resultInfoDto.setTotalCount(errorInformation.size());
			resultInfoDto.setErrorInformation(errorInformation);
		}
		resultInfoDto.setFileName(fileName);
		UploadResultInfoCache.getInstance().delete(token);
		// 将上传结果放入缓存用于在页面上取值显示
		UploadResultInfoCache.getInstance().set(token, resultInfoDto);
		return resultInfoDto;
	}
	
	// 插入正确数据
	@Override
	public boolean insertDatas(String token) {
		UploadResultInfoDTO uploadResultInfoDTO = UploadResultInfoCache.getInstance().get(token);
		EnterpriseUserDTO userDto = enterpriseUserService.getEnterpriseUserDtoFromCache(token);
		// 从缓存中得到数据和数据类型
		MemoryCorrectDatas correctDatas = UploadCorrectDatasCache.getInstance().get(token);
		if (correctDatas == null) {
			return false;
		}
		Map<String, Transaction> corrects = correctDatas.getCorrects();
		List<Transaction> transList = new ArrayList<Transaction>();
		if (corrects != null && corrects.size() > 0 ) {
		    transList.addAll(corrects.values());
		}
		Transaction[] trans = transList.toArray(new Transaction[0]);
		int size = trans.length;
		
		// 插入upload_summaries
		UploadSummaries summary = new UploadSummaries();
		summary.setUserId(userDto.getId());
		// int bankID = outDepartmentDao.selectOuterDeparmentById(userDto.getDepId()).getCode();
		int depId = userDto.getDepId();
		summary.setDepId(depId);
		summary.setValidatedRecordCount(size);
		uploadResultInfoDTO.getFileName();
		summary.setFileName(uploadResultInfoDTO.getFileName());
		
		summary.setRecordCount(size + uploadResultInfoDTO.getErrorCount());
		uploadSummariesDao.saveUploadSummaries(summary);
		int batchID = summary.getId();
		saveBlackInfos(size, batchID, trans, userDto);
		return true;
	}

	private void saveBlackInfos(int size, int uploadBatchId, Transaction[] trans, EnterpriseUserDTO userDto) {
		//每一次批量插入的数量
		int batchCount = Integer.parseInt(String.valueOf(AppContext.getInstance().getConstants().get(Constants.BATCH_INSERT_COUNT)));
		int quotient = size / batchCount; // 商数
		int residual = size % batchCount; // 余数
		int userId = userDto.getId();
		int depId = userDto.getDepId();
		List<BlackInfo> blackInfosList = null;
		for (int i = 0; i < quotient; i++) {
			blackInfosList = new ArrayList<BlackInfo>();
			for (int j=0; j < batchCount; j++) {
				Transaction tran = trans[i * batchCount + j];
				BlackInfo blackInfo = new BlackInfo();
				blackInfo.setBatchId(uploadBatchId);
				blackInfo.setData(tran);
				blackInfo.setUserId(userId);
				blackInfo.setDepId(depId);
				blackInfosList.add(blackInfo);
			}
			
			blackInfoDao.batchInsertBlackInfos(blackInfosList);
		}
		// 处理剩余数据
		if (residual > 0) {
			blackInfosList = new ArrayList<BlackInfo>();
			for(int i = quotient * batchCount; i < size; i++){
				Transaction tran = trans[i];
				BlackInfo blackInfo = new BlackInfo();
				blackInfo.setBatchId(uploadBatchId);
				blackInfo.setData(tran);
				blackInfo.setUserId(userId);
				blackInfo.setDepId(depId);
				blackInfosList.add(blackInfo);
			}
			
			blackInfoDao.batchInsertBlackInfos(blackInfosList);
		}
		// 存入redis缓存
		if (CreditloansPropertyPlaceholderConfigurer.getContextProperty("saveincache").equals("0")) {
			// 查询刚才批量插入的数据
			// FIXME : 这个查询很消耗时间,因为目前没有索引
			blackInfosList = blackInfoDao.selectBlackInfosListByUploadBatchId(uploadBatchId);
			
			for (BlackInfo blackInfo : blackInfosList) {
				addToCache(blackInfo);
			}
	        //FIXME:
			if(FuzzyConstants.RUN_LUCENE_INDEX){
				this.index(blackInfosList, FuzzyConstants.INDEX_ADD);
			}
		}else{//即使没有做
	        //FIXME:
			if(FuzzyConstants.RUN_LUCENE_INDEX){
				List<BlackInfo> _agentInfosList = blackInfoDao.selectBlackInfosListByUploadBatchId(uploadBatchId);
				this.index(_agentInfosList, FuzzyConstants.INDEX_ADD);
			}
		}
	}
	
	private void index(List<BlackInfo> infoList, int type){
		if(infoList==null||infoList.size()==0){
			return;
		}
    	List<IndexValue> workNameList = new ArrayList<IndexValue>();
    	List<IndexValue> workAddressList = new ArrayList<IndexValue>();
    	List<IndexValue> homeAddressList = new ArrayList<IndexValue>();
    	for (BlackInfo info : infoList) {
			IndexValue ivWorkName = new IndexValue();
			ivWorkName.setId(info.getDepId());
			ivWorkName.setValue(info.getStandardWorkName());
			workNameList.add(ivWorkName);
			IndexValue ivWorkAddress = new IndexValue();
			ivWorkAddress.setId(info.getDepId());
			ivWorkAddress.setValue(info.getStandardWorkAddress());
			workAddressList.add(ivWorkAddress);
			IndexValue ivHomeAddress = new IndexValue();
			ivHomeAddress.setId(info.getId());
			ivHomeAddress.setValue(info.getStandardHomeAddress());
			homeAddressList.add(ivHomeAddress);
    	}
    	ExecutorService otherThreadPool = FuzzyUtil.createService(3);
		
		CorpFuzzyThread otherWorkNameFuzzyThread = new CorpFuzzyThread(CorpFuzzyApp.getInstance(), type, workNameList);
		AddressFuzzyThread otherWorkAddressFuzzyThread = new AddressFuzzyThread(WorkAddressFuzzyApp.getInstance(), type, workAddressList);
		AddressFuzzyThread otherHomeAddressFuzzyThread = new AddressFuzzyThread(HomeAddressFuzzyApp.getInstance(), type, homeAddressList);

		otherThreadPool.execute(otherWorkNameFuzzyThread);
		otherThreadPool.execute(otherWorkAddressFuzzyThread);
		otherThreadPool.execute(otherHomeAddressFuzzyThread);
		
		FuzzyUtil.shutdownService(otherThreadPool);
		
		
    	workNameList = null;
    	workAddressList = null;
	}
	
	/**
	 * 获取错误信息
	 * @param datas
	 * @return
	 */
	private List<String> getErrorHtmlShows(MemoryErrorDatas datas) {
		int size = datas.getAllSize();
		if(size==0){
			return null;
		}
		int restrictedSize = Integer.parseInt(String.valueOf(AppContext
				.getInstance().getConstants()
				.get(Constants.UPLOAD_ERROR_SHOW_COUNT)));
		String type = datas.getBusinessType();
		String flowName = AppContext.getInstance().getPrCongif().getParameterInfo(ParameterConfig.PARAMETER_FLOW).getValue(type);
		FlowInfo flow = AppContext.getInstance().getDfConfig().getFlowInfo(flowName);
		Schema  schema = flow.getSchema();
		String title = flow.getTitleInfo();
		String delimiter = flow.getTitleDelimiter();
		String[] titles = title.split(delimiter);
		//输入标题头
		StringBuilder titlesBuf = new StringBuilder("<table class=\"table table-striped table-bordered\">\r\n");
		titlesBuf.append("\t<thead>\r\n");
		titlesBuf.append("\t\t<tr>\r\n");
		int len = titles.length;
		for(int i=0; i<len; i++){
			titlesBuf.append("\t\t\t<th>").append(titles[i]).append("</th>\r\n");
		}
		titlesBuf.append("\t\t</tr>\r\n");
		titlesBuf.append("\t</thead>\r\n");
		titlesBuf.append("\t<tbody>\r\n");
		//输入标题尾部
		StringBuilder endBuf = new StringBuilder("\t</tbody>\r\n");
		endBuf.append("</table>\r\n");
		//显示
		StringBuilder buf = new StringBuilder(titlesBuf);
		List<String>list = new ArrayList<String>();
		int count = 0;
		List<Transaction> trans = datas.getSameInPks();
		for(int i=0; i<trans.size(); i++){
			if(count % restrictedSize == 0 && count > 0){
				buf.append(endBuf);
				list.add(buf.toString());
				buf = new StringBuilder(titlesBuf);
				//buf.append("table_end");
				buf.append(titlesBuf);
			}
			appendString(buf,trans.get(i),schema,1);
			count++;
		}
		trans = datas.getSameDbPks();
		for(int i=0; i<trans.size(); i++){
			if(count % restrictedSize == 0 && count > 0){
				buf.append(endBuf);
				list.add(buf.toString());
				buf = new StringBuilder(titlesBuf);
				//buf.append("table_end");
				buf.append(titlesBuf);
			}
			appendString(buf,trans.get(i),schema,2);
			count++;
		}
		trans = datas.getErrorRules();
		for(int i=0; i<trans.size(); i++){
			if(count % restrictedSize == 0 && count > 0){
				buf.append(endBuf);
				list.add(buf.toString());
				buf = new StringBuilder(titlesBuf);
				//buf.append("table_end");
				buf.append(titlesBuf);
			}
			appendString(buf,trans.get(i),schema,3);
			count++;
		}
		if (!buf.toString().endsWith("table_end")) {
			buf.append(endBuf);
		}
		if (count != 0) {
			list.add(buf.toString());
		}
		return list;
	}
	
	private void appendString(StringBuilder buf, Transaction tran, Schema schema, int type){
		buf.append("\t\t<tr>\r\n");
		int len = schema.getColumnNum();
		for(int i=0; i<len; i++){
			buf.append("\t\t\t");
			if(type==1){
				if(i==0){
					buf.append("<td class=\"blue\" title=\"上传文件中已经有与此唯一码相同的数据\">");
				}else{
					buf.append("<td>");
				}
				buf.append(tran.getString(i));
			}else if(type==2){
				if(i==0){
					buf.append("<td style='background-color: #FFFF93'; title=\"该条记录数据库中已存在\">");
				}else{
					buf.append("<td>");
				}
				buf.append(tran.getString(i));
			}else{
				if(tran.isErroColumn(i)){//如果是错误数据
					buf.append("<td class=\"red\" title=\"");
					buf.append(tran.getErrorsMap().get(schema.getColumnNames()[i])).append("\">");
				}else{//如果不是错误数据
					buf.append("<td>");
				}
				buf.append(tran.getString(i));
			}
			buf.append("</td>\r\n");
		}
		buf.append("\t\t</tr>\r\n");
	}
		
	// 从缓存中查询
	private List<QueryCompareResultItem> queryBlackInfoFromCache(QueryCompareCondition queryCompareConditionDTO, String token) {
		List<QueryCompareResultItem> queryCompareResultItemList = new ArrayList<QueryCompareResultItem>();
		// 获取查询条件
		String name = queryCompareConditionDTO.getName(); // 姓名
		String idNumber = queryCompareConditionDTO.getIdNumber(); // 身份证号
		String phone = queryCompareConditionDTO.getMobile(); // 手机
		String tel = queryCompareConditionDTO.getTell(); // 固定电话
		String address = queryCompareConditionDTO.getAddress(); // 地址
		String companyName = queryCompareConditionDTO.getWorkName(); // 单位名称
		int isFuzzy = queryCompareConditionDTO.getIsFuzzy();
		// 查询的时间条件
		Map<Integer, Date> dateConditionMap = getDateCondition();
		// 查询的银行id list条件
		List<Integer> bankIdCondition = getBankCondition(queryCompareConditionDTO.getDepCondition(), token);
		// 姓名比较
		if (name != null && !"".equals(name)) {
			queryCompareResultItemList.add(basicCacheQueryService.queryName(name, dateConditionMap, bankIdCondition));
		}
		// 证件号码比较
		if (idNumber != null && !"".equals(idNumber)) {
			queryCompareResultItemList.add(basicCacheQueryService.queryIdNumber(idNumber, dateConditionMap, bankIdCondition));
		}
		// 手机比较
		if (phone != null && !"".equals(phone)) {
			queryCompareResultItemList.add(basicCacheQueryService.queryMobile(phone, dateConditionMap, bankIdCondition));
		}
		// 固定电话比较
		if (tel != null && !"".equals(tel)) {
			queryCompareResultItemList.add(basicCacheQueryService.queryTell(tel, dateConditionMap, bankIdCondition));
		}
		// 地址比较
		if (address != null && !"".equals(address)) {
			//  FIXME 单位地址标准化
			//String standardAddress = AddressFuzzyTool.formatAddr(address);
			queryCompareResultItemList.add(basicCacheQueryService.queryAddress(address, dateConditionMap, bankIdCondition));
		}
		// 单位名称比较
		if (companyName != null && !"".equals(companyName)) {
			// 单位名称标准化
			//String standardWorkName = CorpFuzzyTool.prepareCorpName(companyName);
			queryCompareResultItemList.add(basicCacheQueryService.queryWorkName(companyName, dateConditionMap, bankIdCondition));
		}
		return queryCompareResultItemList;
	}
	
	// 从数据库中查询
	private List<QueryCompareResultItem> queryBlaclInfoFromDB(QueryCompareCondition queryCompareConditionDTO, String token) {
		List<QueryCompareResultItem> queryCompareResultItemList = new ArrayList<QueryCompareResultItem>();
		// 获取查询条件
		String name = queryCompareConditionDTO.getName(); // 姓名
		String idNumber = queryCompareConditionDTO.getIdNumber(); // 身份证号
		String phone = queryCompareConditionDTO.getMobile(); // 手机
		String tel = queryCompareConditionDTO.getTell(); // 固定电话
		String address = queryCompareConditionDTO.getAddress(); // 地址
		String companyName = queryCompareConditionDTO.getWorkName(); // 单位名称
		int isFuzzy = queryCompareConditionDTO.getIsFuzzy();
		// 查询的时间条件
		Map<Integer, Date> dateConditionMap = getDateCondition();
		// 查询的银行id list条件
		List<Integer> bankIdCondition = getBankCondition(queryCompareConditionDTO.getDepCondition(), token);
		// 姓名比较
		if (name != null && !"".equals(name)) {
			queryCompareResultItemList.add(basicQueryService.queryName(name, dateConditionMap, bankIdCondition));
		}
		// 证件号码比较
		if (idNumber != null && !"".equals(idNumber)) {
			queryCompareResultItemList.add(basicQueryService.queryIdNumber(idNumber, dateConditionMap, bankIdCondition));
		}
		// 手机比较
		if (phone != null && !"".equals(phone)) {
			queryCompareResultItemList.add(basicQueryService.queryMobile(phone, dateConditionMap, bankIdCondition));
		}
		// 固定电话比较
		if (tel != null && !"".equals(tel)) {
			queryCompareResultItemList.add(basicQueryService.queryTell(tel, dateConditionMap, bankIdCondition));
		}
		// 地址比较
		if (address != null && !"".equals(address)) {
			//  FIXME 单位地址标准化   以后模糊匹配要改
			//String standardAddress = AddressFuzzyTool.formatAddr(address);
			queryCompareResultItemList.add(basicQueryService.queryAddress(address, dateConditionMap, bankIdCondition));
		}
		// 单位名称比较
		if (companyName != null && !"".equals(companyName)) {
			// 单位名称标准化
			//String standardWorkName = CorpFuzzyTool.prepareCorpName(companyName);
			queryCompareResultItemList.add(basicQueryService.queryWorkName(companyName, dateConditionMap, bankIdCondition));
		}
		return queryCompareResultItemList;
	}
	
	protected Map<Integer, Date> getDateCondition() {
		Date currentDate = Calendar.getInstance().getTime();
		
		// 10天前
		Date date10 = DateUtils.calcBeforeDateByDay(currentDate, 10);
		// 30天前
		Date date30 = DateUtils.calcBeforeDateByDay(currentDate, 30);
		// 90天前
		Date date90 = DateUtils.calcBeforeDateByDay(currentDate, 90);
		// 365天前
		Date date365 = DateUtils.calcBeforeDateByDay(currentDate, 365);
		
		// 结果
		Map<Integer, Date> dateConditionMap = new HashMap<Integer, Date>();
		dateConditionMap.put(0, date10);
		dateConditionMap.put(1, date30);
		dateConditionMap.put(2, date90);
		dateConditionMap.put(3, date365);
		
		return dateConditionMap;
	}
	
	/**
	 * @param backIdCondition
	 * 0: 所有银行
	 * 1: 不含本行
	 * 2: 只本行
	 * @return
	 */
	protected List<Integer> getBankCondition(int bankIdCondition, String token) {
		EnterpriseUserDTO userDto = enterpriseUserService.getEnterpriseUserDtoFromCache(token);
		int depId = userDto.getDepId();
		List<Enterprise> enterpriseList = enterpriseDao.selectEnterpriseList();
		List<Integer> listId = new ArrayList<Integer>();
		for (Enterprise enterprise : enterpriseList) {
			int enterpriseId = enterprise.getId();
			if (bankIdCondition == 1) { // 所有银行
				listId.add(enterpriseId);
			} else if (bankIdCondition == 2) { // 只本行
				listId.add(depId);
				return listId;
			} else if (bankIdCondition == 3 && enterpriseId != depId) { // 不包含本行
				listId.add(enterpriseId);
			}
		}
		return listId;
	}
	
	/**
	 * 将黑名单存入缓存
	 * @param blackInfo
	 */
	private void addToCache(BlackInfo blackInfo) {
		int id = blackInfo.getId();
		int bankId = blackInfo.getDepId();
		String strId = String.valueOf(id);
		String producedOn = DateFormatUtils.format(blackInfo.getConfirmedDate(), "yyyy-MM-dd");
		
		// 处理redis中的值
		StringBuffer sb = new StringBuffer();
		sb.append(id).append(",").append(bankId).append(",").append(producedOn);
		String info = sb.toString();
		
		// 姓名
		String name = blackInfo.getName();
		// 证件号码
		String idNumber = blackInfo.getIdNumber();
		// 手机
		String mobile = blackInfo.getMobile();
		// 单位名称
		String standardWorkName = blackInfo.getStandardWorkName();
		// 单位地址
		String standardWorkAddress = blackInfo.getStandardWorkAddress();
		// 单位电话
		String workTel = blackInfo.getWorkTell();
		// 家庭地址
		String standardHomeAddress = blackInfo.getStandardHomeAddress();
		// 家庭电话
		String homeTel = blackInfo.getHomeTell();
		
		// 姓名
		NameCache.getInstance().hset(KEY_PREFIX + name, strId, info);
		// 证件号码
		IdNumberCache.getInstance().hset(KEY_PREFIX + idNumber, strId, info);
		// 手机
		MobileCache.getInstance().hset(KEY_PREFIX + mobile, strId, info);
		// 单位名称
		WorkNameCache.getInstance().hset(KEY_PREFIX + standardWorkName, strId, info);
		// 单位地址
		WorkAddressCache.getInstance().hset(KEY_PREFIX + standardWorkAddress, strId, info);
		// 单位电话
		WorkTelCache.getInstance().hset(KEY_PREFIX + workTel, strId, info);
		// 家庭地址
		HomeAddressCache.getInstance().hset(KEY_PREFIX + standardHomeAddress, strId, info);
		// 家庭电话
		HomeTelCache.getInstance().hset(KEY_PREFIX + homeTel, strId, info);
	}

}
