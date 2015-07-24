package cn.creditloans.tools.validator.dataflow.transform;

public class DFConstant {
	/** dataflow status */
	public static final int ERROR = -2;
	public static final int UNCHECKED = -1;
	public static final int BEFOREINIT = 0;
	public static final int INIT = 1;
	public static final int START = 2;
	public static final int STOP = 3;

	/** other constant */
	public static final int ERROR_CODE_LEN = 4;
	public static final String ERROR_SEPERATOR = "--";
	// public static final int BLOCKLENGTH = 9;
	// public static final int RECORDLENGTH = 4;

}
