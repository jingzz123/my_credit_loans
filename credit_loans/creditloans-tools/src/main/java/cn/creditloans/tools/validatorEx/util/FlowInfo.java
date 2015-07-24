package cn.creditloans.tools.validatorEx.util;

import cn.creditloans.tools.validator.check.Validator;
import cn.creditloans.tools.validator.transaction.Schema;

/**
 * 一个流程的配置信息
 * @author Hero-wu
 *
 */
public class FlowInfo {
	
	/**
	 * 流程名称
	 */
	private String name;
	
	private Schema schema;
	
	/**
	 * 编码方式
	 */
	private String charset;
	
	/**
	 * 标题参数
	 */
	private boolean hasTitle = true;//是否有标题
	
	private String titleInfo;//标题通过DELIMITER合并所得的数据
	
	private String titleDelimiter;//标题的分隔符好
	
	private boolean needCheck = false;
	
	/**
	 * 文件标题的数量
	 */
	private int gapCount = 0;
	
	/**
	 * 是否需要按照PK对数据进行统一校验
	 */
	private boolean pkNeedCheck = false;
	
	/**
	 * 格式校验的文件生成的对象
	 */
	private Validator validator;//初始化的校验对象
	
	private String[] validateNames;//校验文件中包含的所有校验字段名称。
	
	private int[] indexes;//校验字段在position
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public boolean isHasTitle() {
		return hasTitle;
	}

	public void setHasTitle(boolean hasTitle) {
		this.hasTitle = hasTitle;
	}

	public String getTitleInfo() {
		return titleInfo;
	}

	public void setTitleInfo(String titleInfo) {
		this.titleInfo = titleInfo;
	}

	public String getTitleDelimiter() {
		return titleDelimiter;
	}

	public void setTitleDelimiter(String titleDelimiter) {
		this.titleDelimiter = titleDelimiter;
	}

	public boolean isNeedCheck() {
		return needCheck;
	}

	public void setNeedCheck(boolean needCheck) {
		this.needCheck = needCheck;
	}

	public int getGapCount() {
		return gapCount;
	}

	public void setGapCount(int gapCount) {
		this.gapCount = gapCount;
	}

	public boolean isPkNeedCheck() {
		return pkNeedCheck;
	}

	public void setPkNeedCheck(boolean pkNeedCheck) {
		this.pkNeedCheck = pkNeedCheck;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public String[] getValidateNames() {
		return validateNames;
	}

	public void setValidateNames(String[] validateNames) {
		this.validateNames = validateNames;
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}
	
	public String toString(){
		StringBuilder buf = new StringBuilder("");
		buf.append(" schema name is "+schema.getName()).append("\r\n");
		buf.append(" charset is "+charset).append("\r\n");
		buf.append(" validator ID size is  "+validator.getConstraintIds().length);
		return buf.toString();
	}

}
