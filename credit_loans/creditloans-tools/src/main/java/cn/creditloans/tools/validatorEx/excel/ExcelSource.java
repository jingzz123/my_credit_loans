package cn.creditloans.tools.validatorEx.excel;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import cn.creditloans.tools.cache.datas.MemoryCorrectDatas;
import cn.creditloans.tools.cache.datas.MemoryErrorDatas;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.context.AppContext;
import cn.creditloans.tools.util.UtilMethod;
import cn.creditloans.tools.validator.transaction.Schema;
import cn.creditloans.tools.validator.transaction.Transaction;
import cn.creditloans.tools.validatorEx.util.DFConfig;
import cn.creditloans.tools.validatorEx.util.FlowInfo;


/**
 * 实现Excel的读取数据功能
 * @author Ash
 *
 */
public class ExcelSource {
	
	private byte[] bytes;
	
	private InputStream source;//原始文件，inputstream没有序列化
	
	/**
	 * 存放传递给Excel的参数
	 */
	private Map<String,Object> parameterMap = null;
	
	private int batchCount = 0;//每一次批次处理的数量
	
	/**
	 * 是否需要批次处理，缺省为需要；如果不需要，那么全部读入就可以了
	 */
	private boolean needBatch = true;
	
	private String errorInfo = null;//错误信息
	
	private DFConfig dfConfig =  null;
	
	private int totalRow = 0;//总行数
	
	private int currentRow = 0;//当前行数
	
	private Schema schema = null;
	
	//private FileInputStream inputStream = null;
	
	private Workbook workbook = null;
	
	private Sheet sheet = null;
	
	/**
	 * 是否需要检测PK
	 */
	private boolean needCheckPk = false;
	
	/**
	 * 存放所有的不同PK信息，这个一直不清理，直到调用cleanup
	 */
	private List<String> pkList = null;
	
	/**
	 * 在整个处理过程中传递正确数据
	 */
	private MemoryCorrectDatas memoryCorrectDatas = null;
	
	/**
	 * 处理整个过程中的错误数据
	 */
	private MemoryErrorDatas memoryErrorDatas = null;
	
	public ExcelSource(){
		parameterMap = new LinkedHashMap<String,Object>();
	}
	
	/**
	 * 初始化
	 * @return
	 */
	public boolean init(){
		Object obj = this.getParameter(Constants.FLOW_NAME);
		if(obj==null){
			errorInfo = "Excel文件读取时，配置参数有问题！";
			return false;
		}
		String flowName = String.valueOf(obj);
		FlowInfo flowInfo  = dfConfig.getFlowInfo(flowName);
		if(flowInfo==null){
			errorInfo = "Excel文件读取时，配置参数有问题！";
			return false;
		}
		batchCount = dfConfig.getBatchCount();//得到每次批次处理的数量
		needCheckPk = flowInfo.isNeedCheck();
		try{
			schema = flowInfo.getSchema();
			//String charset = flowInfo.getCharset();//Excel文件的编码方式
			//inputStream = new FileInputStream(source);
			source = new ByteArrayInputStream(bytes);
			//workbook = WorkbookFactory.create(source);
			workbook = WorkbookFactory.create(source);
			sheet = workbook.getSheetAt(0);
			boolean hasTitle = flowInfo.isHasTitle();
			int gapCount = flowInfo.getGapCount();
			//得到行数
			totalRow = this.getRowNum(sheet);
			int restrictedNum = Integer.parseInt(String.valueOf(AppContext.getInstance().getConstants().
					get(Constants.EXCEL_MAX_COUNT)));
			//判断是否存在数据
			if(!hasTitle){//不存在标题
				currentRow = gapCount;//需要检查一下
				if(totalRow<=gapCount){
					errorInfo = "当前Excel不存在数据！";
					return false;
				}
				//判断数据总量不能超过规定数据量
				if(totalRow-gapCount>restrictedNum){
					errorInfo = "每次上传的数据数量不能超过"+restrictedNum+"条！";
					return false;
				}
			}else{
				currentRow = gapCount + 1 ;//需要检查一下
				if(totalRow+1<=gapCount + 1){// 修改bug，上传空文件報錯
					errorInfo = "当前Excel不存在数据！";
					return false;
				}
				//判断数据总量不能超过规定数据量
				if(totalRow-gapCount-1>=restrictedNum){
					errorInfo = "每次上传的数据数量不能超过"+restrictedNum+"条！";
					return false;
				}
			}
			//得到第一行的列数
			int columnNum = this.getColumnNum(sheet);
			if(columnNum!=schema.getColumnNum()){
				errorInfo = "当前Excel文件的首行只有"+columnNum+"列，实际应该"+schema.getColumnNum()+"列！";
				return false;
			}
			//如果需要校验标题
			if(flowInfo.isNeedCheck()){
				String delimiter = flowInfo.getTitleDelimiter();//标题的分隔符
				String info = flowInfo.getTitleInfo();//标题的信息
				Row row = sheet.getRow(0);//得到第一行
				Cell cell = null;
				StringBuilder builder = new StringBuilder("");
				for(int i=0; i<columnNum; i++){
					row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					if(i>0){
						builder.append(delimiter);
					}
					builder.append(cell.getStringCellValue().replaceAll(" ", ""));
				}
				//如果标题不正确，那么应该显示错误信息
				if(!info.equals(builder.toString())){
					errorInfo = "Excel标题有错误，正确的合并标题为："+info;
					return false;
				}
				pkList = new LinkedList<String>();
			}
		}catch(Exception e){
			errorInfo = "文件格式有错！";
			e.printStackTrace();
			return false;
		}
		return  true;
	}
	
	/**
	 * 一个批次读取数据
	 * @return
	 */
	public boolean readBatch(){
		if(currentRow>totalRow){
			return false;
		}
		int endRow = totalRow;
		memoryCorrectDatas.init();//对memoryDatas进行初始化操作
		memoryErrorDatas.init();//错误数据内存初始化
		if(needBatch){
			if(currentRow+batchCount>totalRow){
				endRow = totalRow+1;
			}else{
				endRow = currentRow + batchCount;
			}
			
		}else{
			endRow = totalRow+1;
		}
		Transaction tran = null;
		//开始读取数据
		String[] values = null;
		int i,j;
		int columnNum = schema.getColumnNum();
		Cell cell;
		Row row;
		String pk;
		String value;
		for(i=currentRow; i<endRow; i++){
			row = sheet.getRow(i);
			values = new String[columnNum];//定义数据数量
			for(j=0;j<columnNum;j++){
				try {
                    Cell _cell = row.getCell(j);
                    if (_cell == null) {
                    	_cell = row.createCell(j);
                    }
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                    cell = row.getCell(j, Row.CREATE_NULL_AS_BLANK);
                    value = cell.getStringCellValue();
                    value = UtilMethod.replace(value, " ", "");
                    value = UtilMethod.replace(value, ",", "");
                    value = UtilMethod.replace(value, "，", "");
                    values[j] = value;
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    // FIXME : 修改上传Excel文件最后一行为空格导致抛错
                    values[j] = "";
                }
			}
			tran = new Transaction(schema);//创建一个Transaction对象
			tran.setData(values);
			if(needCheckPk){//如果需要校验PK
				pk = tran.getPkCombine();
				if(this.pkList.contains(pk)){
					memoryErrorDatas.addSameInPk(tran);//PK相同的加入
				}else{
					this.pkList.add(pk);
					memoryCorrectDatas.addCorrect(tran);//插入正确中
				}
			}else{
                memoryCorrectDatas.addCorrect(tran);//插入正确中
			}
		}
		currentRow = endRow;
		return true;
	}
	
	/**
	 * 做一些处理工作
	 * @return
	 */
	public boolean cleanUp(){
		try{
			bytes = null;
			source.close();
			source = null;
			workbook = null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	public void setParameter(String key,Object value){
		parameterMap.put(key, value);
	}
	
	public Object getParameter(String key){
		return parameterMap.get(key);
	}

	public InputStream getSource() {
		return source;
	}

	public void setSource(InputStream source) {
		this.source = source;
	}

	public DFConfig getDfConfig() {
		return dfConfig;
	}

	public void setDfConfig(DFConfig dfConfig) {
		this.dfConfig = dfConfig;
	}
	
	public boolean isNeedBatch() {
		return needBatch;
	}

	public void setNeedBatch(boolean needBatch) {
		this.needBatch = needBatch;
	}

	/**
	 * 返回Excel最大行index值，实际行数要加1
	 * @param sheet
	 * @return
	 */
	public int getRowNum(Sheet sheet){
		return sheet.getLastRowNum();
	}
	
	/**
	 * 返回数据的列数
	 * @param sheet
	 * @return
	 */
	public int getColumnNum(Sheet sheet){
		Row row = sheet.getRow(0);//获取第一行
		if(row!=null&&row.getLastCellNum()>0){
			return row.getLastCellNum();
		}
		return 0;
	}

	public MemoryCorrectDatas getMemoryCorrectDatas() {
		return memoryCorrectDatas;
	}

	public void setMemoryCorrectDatas(MemoryCorrectDatas memoryCorrectDatas) {
		this.memoryCorrectDatas = memoryCorrectDatas;
	}

	public MemoryErrorDatas getMemoryErrorDatas() {
		return memoryErrorDatas;
	}

	public void setMemoryErrorDatas(MemoryErrorDatas memoryErrorDatas) {
		this.memoryErrorDatas = memoryErrorDatas;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
}
