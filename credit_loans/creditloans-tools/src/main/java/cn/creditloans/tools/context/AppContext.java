package cn.creditloans.tools.context;

import java.util.Properties;

import cn.creditloans.tools.fuzzy.util.FuzzyConfig;
import cn.creditloans.tools.parameters.ParameterConfig;
import cn.creditloans.tools.validatorEx.util.DFConfig;

/**
 * 存放所有的配置信息
 * @author Hero.wu
 *
 */
public class AppContext {
	
	private static AppContext instance = new AppContext();
	
	private static Object lock = new Object();
	
	private AppContext(){}
	
	public static AppContext getInstance(){
		if (instance == null) {
			synchronized (lock){
				if (instance == null) {
					instance = new AppContext();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * 校验的实体类
	 */
	private DFConfig dfConfig = null;
	
	/**
	 * 参数存放的类
	 * @return
	 */
	private ParameterConfig prCongif = null;
	
	/**
	 * 一些key / value的常量
	 */
	private Properties constants = null;
	
	/**
	 * 一些service中的key / value的常量
	 */
	private Properties service_constants = null;

	/**
	 * 这个需要移植过去
	 */
	private FuzzyConfig fuzzyConfig;

	public DFConfig getDfConfig() {
		return dfConfig;
	}

	public void setDfConfig(DFConfig dfConfig) {
		this.dfConfig = dfConfig;
	}

	public ParameterConfig getPrCongif() {
		return prCongif;
	}

	public void setPrCongif(ParameterConfig prCongif) {
		this.prCongif = prCongif;
	}

	public Properties getConstants() {
		return constants;
	}

	public void setConstants(Properties constants) {
		this.constants = constants;
	}

	public FuzzyConfig getFuzzyConfig() {
		return fuzzyConfig;
	}

	public void setFuzzyConfig(FuzzyConfig fuzzyConfig) {
		this.fuzzyConfig = fuzzyConfig;
	}

	public Properties getService_constants() {
		return service_constants;
	}

	public void setService_constants(Properties service_constants) {
		this.service_constants = service_constants;
	}
}
