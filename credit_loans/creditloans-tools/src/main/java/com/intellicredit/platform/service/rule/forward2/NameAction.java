package com.intellicredit.platform.service.rule.forward2;

public class NameAction extends RuleAction {

    //之所以加入此参数,就是为了在碰到名为"TERMINATE!"的NameAction时,告诉engine中断信息
    private InferenceEngine m_engine;

    public void setEngine(InferenceEngine eng) {
        m_engine = eng;
    }

    public NameAction(String name) {
        super(name);
    }

    public void go() {
        if(m_engine!=null)
            m_engine.addNameAction(this);
        if(getName().equals("TERMINATE!")){
            m_engine.setIsError(true);
        }
    }
}
