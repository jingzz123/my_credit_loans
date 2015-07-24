package cn.creditloans.tools.parameters;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * 处理parameters.xml的类
 * 
 * @author Hero-wu
 * 
 */
public class ParameterConfig {
	
	/** 贷款申请类型 */
	public static final String LOAN_TYPE = "loanType";
	/** 黑名单确认状态 */
	public static final String CONFIRMED_TYPE = "confirmedType";
	/** 联系人与其关系 */
	public static final String CONTACTRELATIONSHIP = "contactRelationship";
	/** 黑名单确认状态详细 */
	public static final String CONFIRMED_DETAILS = "confirmedDetails";
	/** 黑名单确认状态与详细对应关系 */
	public static final String CONFIRMED_TYPE_CONFIRMED_DETAILS = "confirmedType_confirmedDetails";
	/** 根据手机号码匹配运营公司 */
	public static final String MOBILE_TYPE = "mobile_type";
	/** 根据手机号确定运营公司的访问方式 */
	public static final String MOBILE_NAME = "mobile_name";
	
	/**
	 * 业务代码和流程name，也就是schema名称的对应关系
	 */
	public static final String PARAMETER_FLOW = "business_flow";

	private Map<String, ParameterInfo> parameterMap = null;
	
	/**
	 * 配置文件
	 * 
	 * @param xmlFile
	 */
	public void load(InputStream inputStream) {
		SAXReader saxReader = new SAXReader();
		try {
			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			Document document = saxReader.read(reader);
			Element root = document.getRootElement();
			// 所有配置文件所在位置
			List<?> elements = root.elements("parameter");
			int len = elements.size();
			Element element;
			List<?> tmpElements = null;
			String name;
			ParameterInfo parameterInfo;
			Element tmpElement;
			parameterMap = new LinkedHashMap<String, ParameterInfo>();
			for (int i = 0; i < len; i++) {
				element = (Element) elements.get(i);
				name = element.attributeValue("name");// 名称
				System.out.println("parameter name is "+name);
				tmpElements = element.elements("info");
				int size = tmpElements.size();
				parameterInfo = new ParameterInfo();
				parameterInfo.setName(name);
				for(int j=0; j<size; j++){
					tmpElement = (Element)tmpElements.get(j);
					parameterInfo.setValue(tmpElement.attributeValue("no").trim(), tmpElement.attributeValue("name").trim());
				}
				parameterMap.put(name, parameterInfo);
			}
		} catch (Exception dom4j) {
			dom4j.printStackTrace();
		}

	}
	
	public ParameterInfo getParameterInfo(String name) {
		return parameterMap.get(name);
	}
	
	public void print(){
		//TODO
	}
}
