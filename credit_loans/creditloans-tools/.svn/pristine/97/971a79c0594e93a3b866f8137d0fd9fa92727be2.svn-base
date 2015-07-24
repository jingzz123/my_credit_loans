package cn.creditloans.tools.fuzzy.address;

import java.util.List;

import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.fuzzy.util.IndexValue;

public class AddressFuzzyThread implements Runnable{
	
	private AddressFuzzyAbstractApp instance = null;
	
	private int indexType;
	
	private String businessCode = "code";
	
	private List<IndexValue> addressList = null;
	
	public AddressFuzzyThread(AddressFuzzyAbstractApp addressFuzzyAbstractApp, int type, List<IndexValue> list){
		instance = addressFuzzyAbstractApp;
		indexType = type;
		addressList = list;
		
	}
	
	@Override
    public void run() {
		instance.index(indexType,businessCode, addressList, Constants.ADDRESS_FUZZY_KEY);
	}
}
