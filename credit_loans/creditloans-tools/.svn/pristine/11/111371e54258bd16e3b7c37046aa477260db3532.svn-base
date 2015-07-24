package cn.creditloans.tools.validatorEx.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;

import cn.creditloans.tools.context.AppContextUtils;
import cn.creditloans.tools.validator.check.Validator;
import cn.creditloans.tools.validator.dataflow.schema.SchemaFactory;
import cn.creditloans.tools.validator.transaction.Schema;
import cn.creditloans.tools.validator.util.ValidatorConstants;


/**
 * 处理dbload.xml的类
 * 
 * @author Hero-wu
 * 
 */
public class DFConfig {
	
	private static final Log logger = LogFactory.getLog(DFConfig.class);

	private Map<String, FlowInfo> flowMap = null;
	
	private int batchCount = 0;

	/**
	 * 配置文件
	 * 
	 * @param xmlFile
	 */
	public void load(InputStream inputStream, ServletContext context) {
		SAXReader saxReader = new SAXReader();
		try {
			InputStreamReader reader = new InputStreamReader(inputStream, ValidatorConstants.ENCODING);
			Document document = saxReader.read(reader);
			Element root = document.getRootElement();
			
			// 所有配置文件所在位置
			String parent = root.attributeValue("parent");
			
			// schema文件名称
			String schemaFileName = root.element("schema_file").attributeValue("name");
			SchemaFactory factory = SchemaFactory.getInstance(parent + schemaFileName, context);
			
			// 错误代码表
			String errorCodeFile = root.element("error_code_file").attributeValue("name");
			String errorFile = parent + errorCodeFile;
			ErrorCode.load(errorFile, context);// 栽入错误代码
			
			//获取每次批次处理Excel文件的数据量
			batchCount = Integer.parseInt(root.element("batch_count").attributeValue("name"));
			
			List<?> elements = root.elements("flow");
			int len = elements.size();
			Element flowElement;
			String name, charset, validateFile, pkColumns;
			FlowInfo flowInfo;
			Schema schema;
			Element tmpElement;
			int gapCount;
			flowMap = new LinkedHashMap<String, FlowInfo>();
			for (int i = 0; i < len; i++) {
				flowElement = (Element) elements.get(i);
				name = flowElement.attributeValue("name");// 名称
				tmpElement = flowElement.element("schema");
				
				flowInfo = new FlowInfo();
				flowInfo.setName(name);
				
				schema = factory.generateSchema(name);
				// 排序和字段和类型设定，这也是PK信息
				pkColumns = tmpElement.element("pk_columms").attributeValue("name");
				setSchemaPKInfos(schema, pkColumns);
				flowInfo.setSchema(schema);
				
				// 编码
				charset = flowElement.element("charset").attributeValue("name");
				flowInfo.setCharset(charset);
				
				// 标题信息
				tmpElement = flowElement.element("title");
				if (tmpElement == null) {
					flowInfo.setHasTitle(false);
				} else {
					flowInfo.setHasTitle(true);
					// 标题信息
					flowInfo.setTitleInfo(tmpElement.attributeValue("info").trim());
					// 标题字段之间间隔
					flowInfo.setTitleDelimiter(tmpElement.attributeValue("delimiter").trim());
					if("true".equalsIgnoreCase(tmpElement.attributeValue("need_check"))){
						flowInfo.setNeedCheck(true);
					}
				}
				
				gapCount = Integer.parseInt(flowElement.element("gap_count").attributeValue("name"));
				flowInfo.setGapCount(gapCount);
				
				if ("true".equalsIgnoreCase(flowElement.element("pk_need_check").attributeValue("name"))) {
					flowInfo.setPkNeedCheck(true);
				}
				
				// 解析，将其读入到内存
				validateFile = flowElement.element("validate_file").attributeValue("name");
				disposeValidator(context, parent + validateFile, flowInfo);
				
				flowMap.put(name, flowInfo);
			}
		} catch (Exception dom4j) {
			logger.error(dom4j.getMessage(), dom4j);
		}

	}

	private void setSchemaPKInfos(Schema schema, String pkColumns) {
		if (pkColumns == null || pkColumns.trim().equals("")) {
			return;
		}
		String[] infos = pkColumns.split(";");
		int len = infos.length;
		String type;
		int index;
		String[] pkNames = new String[len];
		int[] pkTypes = new int[len];
		for (int i = 0; i < len; i++) {
			index = infos[i].indexOf(":");
			if (index < 0) {
				pkNames[i] = infos[i];
				pkTypes[i] = ValidatorConstants.ASCEND;
			} else {
				pkNames[i] = infos[i].substring(0, index);
				type = infos[i].substring(index + 1).trim();
				if (String.valueOf(ValidatorConstants.DESCEND).equals(type)) {
					pkTypes[i] = ValidatorConstants.DESCEND;
				} else {
					pkTypes[i] = ValidatorConstants.ASCEND;
				}
			}
		}
		schema.setSortNames(pkNames);
		schema.setSortTypes(pkTypes);
	}

	private void disposeValidator(ServletContext context, String validatorFile, FlowInfo flowInfo) {
		try {
			Resource r = AppContextUtils.getResource(context, validatorFile);
			Validator validator = Validator.getInstance(r.getURL(), r.getInputStream(), ValidatorConstants.ENCODING);
			
			Schema schema = flowInfo.getSchema();
			String[] names = schema.getColumnNames();//得到schema的所有列名
			String[] vsNames = null;//validate中存在的schema名称
			int[] indexes = null;
			int i;
			String[] validateNames = validator.getConstraintIds();//得到格式校验文件的校验的名称
			int length = validateNames.length;

			List<String> baseList = new LinkedList<String>();
			int index;
			for (i = 0; i < length; i++) {
				index = validateNames[i].indexOf(":");
				if (index < 0) {
					baseList.add(validateNames[i]);
					//System.out.println(validateNames[i]);
				}
			}

			int baseSize = baseList.size();
			if (baseSize > 0) {
				String[] tmpBName = new String[baseSize];
				int[] tmpBIndex = new int[baseSize];
				Arrays.fill(tmpBName, null);
				Arrays.fill(tmpBIndex, -1);
				String name = null;
				int count = 0;
				for (i = 0; i < baseSize; i++) {
					name = baseList.get(i);

					if (!contains(name, names))
						continue;
					tmpBName[count] = name;
					tmpBIndex[(count++)] = schema.findColumnIndex(name);
				}

				int size = 0;
				for (i = 0; i < baseSize; i++) {
					if (tmpBName[i] == null)
						break;
					size++;
				}

				if (size > 0) {
					vsNames = new String[size];
					indexes = new int[size];
					for (i = 0; i < size; i++) {
						vsNames[i] = tmpBName[i];
						indexes[i] = tmpBIndex[i];
					}
				}

			}
			flowInfo.setValidator(validator);
			flowInfo.setValidateNames(vsNames);
			flowInfo.setIndexes(indexes);
		} catch (IOException exp) {
			logger.error(exp.getMessage(), exp);
		}
	}

	private boolean contains(String value, String[] arrays) {
		int len = arrays.length;
		for (int i = 0; i < len; i++) {
			if (value.equals(arrays[i]))
				return true;
		}
		return false;
	}

	public int getBatchCount() {
		return batchCount;
	}

	public FlowInfo getFlowInfo(String name) {
		//System.out.println(name);
		return flowMap.get(name);
	}
	
	public void print(){
//		System.out.println("batch size is " + this.batchCount);
//		Iterator<String> keyIter = flowMap.keySet().iterator();
//		String key;
//		int size = this.flowMap.size();
//		System.out.println("flow size is " + size);
//		FlowInfo info = null;
//		while(keyIter.hasNext()){
//			key = keyIter.next();
//			System.out.println("  key is " + key);
//			info = flowMap.get(key);
//			System.out.println(info.toString());
//		}
	}
}
