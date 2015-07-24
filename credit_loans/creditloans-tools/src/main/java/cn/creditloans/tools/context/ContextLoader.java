package cn.creditloans.tools.context;

import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.core.io.Resource;

import cn.creditloans.tools.fuzzy.util.FuzzyConfig;
import cn.creditloans.tools.parameters.ParameterConfig;
import cn.creditloans.tools.validatorEx.util.DFConfig;

/**
 * 获取配置文件信息
 * @author Ash
 *
 */
public class ContextLoader {
	
	/**
	 * 校验入口文件
	 */
	private static String VALIDATOR_ENTRANCE_FILE = "classpath:conf/validator/dbload.xml";
	
	/**
	 * 参数定义文件
	 */
	private static String PARAMETERS_FILE = "classpath:conf/common/parameters.xml";
	
	/**
	 * 定义了一些key、value的常量
	 */
	private static String APP_CONSTANTS = "classpath:conf/common/common.properties";
	
	/**
	 * lucene分区定义文件
	 */
	private static String APP_LUCENE = "classpath:conf/common/fuzzy.xml";
	
	/**
	 * 初始化所有预定义的xml或者properties数据
	 * @param context
	 */
	public void initWebAppContext(ServletContext context){
		try {
			AppContext appContext = AppContext.getInstance();
			
			//导入校验配置文件
			Resource resource = AppContextUtils.getResource(context, VALIDATOR_ENTRANCE_FILE);
			DFConfig config = new DFConfig();
			config.load(resource.getInputStream(), context);
			config.print();
			appContext.setDfConfig(config);
			
			//导入参数定义文件
			resource = AppContextUtils.getResource(context, PARAMETERS_FILE);
			ParameterConfig pConfig = new ParameterConfig();
			pConfig.load(resource.getInputStream());
			appContext.setPrCongif(pConfig);
			
			//导入key、value的常量
			resource = AppContextUtils.getResource(context, APP_CONSTANTS);
			Properties constants = new Properties();
			constants.load(resource.getInputStream());
			appContext.setConstants(constants);
			
			//导入lucene配置文件
			resource = AppContextUtils.getResource(context, APP_LUCENE);
			FuzzyConfig fc = new FuzzyConfig();
			fc.load(resource.getInputStream(), context);
			appContext.setFuzzyConfig(fc);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void closeWebAppContext(ServletContext servletContext) {
		
	}
	
}
