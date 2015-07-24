package com.intellicredit.platform.component.situation2;

public class GeneralAction implements Action {

	private String m_name;

	public GeneralAction(String name) {
		m_name = name;
	}

	public String getName() {
		return m_name;
	}

	public int go() {
		return 0;
	}
}
