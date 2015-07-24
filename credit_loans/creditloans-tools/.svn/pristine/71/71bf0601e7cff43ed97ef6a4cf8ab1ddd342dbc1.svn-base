package cn.creditloans.tools.fuzzy.util;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
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
import cn.creditloans.tools.validator.util.ValidatorConstants;

/**
 * 模糊匹配所需要的配置
 * @author Ash
 *
 */
public class FuzzyConfig {
	private static final Log logger = LogFactory.getLog(FuzzyConfig.class);

	private Map<String, PartitionInfo> partitionMap = null;
	
	public void load(InputStream inputStream,ServletContext context) {
		SAXReader saxReader = new SAXReader();
		try {
			InputStreamReader reader = new InputStreamReader(inputStream, ValidatorConstants.ENCODING);
			Document document = saxReader.read(reader);
			Element root = document.getRootElement();
			List<?> elements = root.elements("fuzzy");
			int size = elements.size();
			partitionMap = new HashMap<String,PartitionInfo>();
			for(int i=0; i<size; i++){
				Element fElement = (Element)elements.get(i);
				PartitionInfo partition = new PartitionInfo();
				partition.setName(fElement.attributeValue("name"));//名称
				partition.setPartition("true".equalsIgnoreCase(fElement.attributeValue("partition")));//是否分区
				Element childEle = fElement.element("parent");//父目录
				partition.setParentDirectory(childEle.attributeValue("name"));
				File file = null;
				if(partition.getParentDirectory().startsWith("classpath")){
					Resource r = AppContextUtils.getResource(context, partition.getParentDirectory());
					file = r.getFile();
				}else{
					file = new File(partition.getParentDirectory());
				}
				//System.out.println(file.getAbsolutePath());
				//不存在获取不是目录
				if(!file.exists()|| !file.isDirectory()){
					file.mkdir();//建立目录
				}
				if(partition.isPartition()){//如果分区，那么设定分区信息
					childEle = fElement.element("partitions");
					//得到无法分区lucene索引存放的目录
					String other = childEle.element("other").attributeValue("directory");
					File cFile = new File(file,other);
					if(!cFile.exists()||!cFile.isDirectory()){
						cFile.mkdir();
					}
					partition.setOtherDirectory(cFile.getAbsolutePath());
					//得到所有分区lucene索引存放的目录
					List<?> childrenEle = childEle.elements("partition");
					int len = childrenEle.size();
					Map<String,String> partitionDirectoryMap = new HashMap<String,String>();
					for(int j=0; j<len; j++){
						Element partitionEle = (Element)childrenEle.get(j);
						String key = partitionEle.attributeValue("startWith");
						String name = partitionEle.attributeValue("directory");
						File tFile = new File(file,name);
						//System.out.println(tFile.getAbsolutePath());
						if(!tFile.exists()||!tFile.isDirectory()){
							tFile.mkdir();
						}
						partitionDirectoryMap.put(key, name);
					}
					partition.setPartitionDirectoryMap(partitionDirectoryMap);
				}
				partitionMap.put(partition.getName(), partition);
			}
		} catch (Exception dom4j) {
			logger.error(dom4j.getMessage(), dom4j);
		}
	}
	
	public PartitionInfo getPartitionInfo(String name){
		return this.partitionMap.get(name);
	}

}
