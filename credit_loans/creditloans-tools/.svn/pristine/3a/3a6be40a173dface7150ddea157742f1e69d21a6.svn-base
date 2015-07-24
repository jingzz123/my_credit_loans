package cn.creditloans.tools.fuzzy.address;

import cn.creditloans.tools.context.AppContext;
import cn.creditloans.tools.fuzzy.util.FuzzyConstants;

/**
 * 公司地址模糊匹配的应用类 确保新增索引、修改索引、删除的
 *  TODO：初始化索引单独利用 需要测试一下删除功能
 * 
 * @author Ash
 * 
 */
public class WorkAddressFuzzyApp extends AddressFuzzyAbstractApp {
	
	private static WorkAddressFuzzyApp instance = new WorkAddressFuzzyApp();

	private WorkAddressFuzzyApp() {
		super();
	}

	public void setPartitionInfo() {
		partitionInfo = AppContext.getInstance().getFuzzyConfig()
				.getPartitionInfo(FuzzyConstants.FUZZY_NAME_WORK_ADDRESS);
	}
	
	public static WorkAddressFuzzyApp getInstance() {
		return instance;
	}


}
