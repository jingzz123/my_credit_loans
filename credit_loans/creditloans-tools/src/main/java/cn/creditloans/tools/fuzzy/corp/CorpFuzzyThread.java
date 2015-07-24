package cn.creditloans.tools.fuzzy.corp;

import java.util.List;

import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.fuzzy.util.IndexValue;

public class CorpFuzzyThread implements Runnable{
	
	private CorpFuzzyApp instance = null;
	
	private int indexType;
	
	private String businessCode = "code";
	
	private List<IndexValue> corpList = null;
	
	public CorpFuzzyThread(CorpFuzzyApp corpFuzzyApp,int type, List<IndexValue> list){
		instance = corpFuzzyApp;
		indexType = type;
		corpList = list;
	}
	
	@Override
    public void run() {
		instance.index(indexType,businessCode, corpList, Constants.CORP_FUZZY_KEY);
	}
}
