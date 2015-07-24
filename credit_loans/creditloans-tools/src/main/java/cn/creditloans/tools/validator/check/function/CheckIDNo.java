package cn.creditloans.tools.validator.check.function;

import java.util.Stack;

/**
 * 对身份证进行验证
 * @author Ash
 *
 */
public class CheckIDNo extends Function{
    private static int[] m_monthMax_1 = null;//每个月的最大天,非Leap year
    private static int[] m_monthMax_2 = null;//每个月的最大天,Leap year
    private static int[] m_powers; //身份证的每个位置的权值
    private static char[] m_lastValues; //身份证最后一位的值

	public CheckIDNo(){
		super.numOfParameter = 1;
	}
	
    public boolean run(Stack<Object> stack) {
        if(stack==null||stack.size()!=1){
        	return false;
        }
        Object param = stack.pop();
        if(param.getClass().isArray()){
        	return false;
        }
        return checkNo(String.valueOf(param));
    }

    private boolean checkNo(String certNo) {
        int len = certNo.length();
        if(len!=15&&len!=18){
            return false;
        }
        if(len==15){//对于15位的身份证暂时返回true
            /**
             * 正则验证身份证（15位）
             * 1~2位为省份且第一位不能为 0[1-9]\d
             * 3~4位为地级市、盟、自治州代码 \d{2}
             * 5~6位为位县、县级市、区代码 \d{2}
             *      前六位：[1-9]\d{5}
             * 7~12位出生年月日,比如670401代表1967年4月1日
             *      年份：\d{2}
             *      月份：(0\d)|(1[0-2])
             *      日：([0|1|2]\d)|3[0-1]
             * 13~15位为顺序号，其中15位男为单数，女为双数,最后一位可能是'X' \d{2}([0-9]|X|x)
             */
            if(!certNo.matches("^[1-9]\\d{5}\\d{2}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{2}([0-9]|X|x)$")) {
                return false;
            }
            StringBuffer birthday = new StringBuffer("19").append(certNo.substring(6,12));
            try{
	            if (!checkDate(birthday.toString())) {
	                return false; //如果日期不合法，则返回false
	            }
            }catch(Exception e){
            	return false;
            }
            return true;
        }
        /**
         * 正则验证身份证（18位）
         * 1~2位为省份且第一位不能为 0[1-9]\d
         * 3~4位为地级市、盟、自治州代码 \d{2}
         * 5~6位为位县、县级市、区代码 \d{2}
         *      前六位：[1-9]\d{5}
         * 7~14位为出生年月日,比如19670401代表1967年4月1日
         *      年份：[1|2]\d{3}
         *      月份：(0\d)|(1[0-2])
         *      日：([0|1|2]\d)|3[0-1]
         * 15~17位为顺序号，其中17位（倒数第二位）男为单数，女为双数 \d{3}
         * 18位为校验码，0-9和X。作为尾号的校验码，是由把前十七位数字带入统一的公式计算出来的，计算的结果是0-10 [0-9]|X|x
         *  *注：某人的尾号是0－9，都不会出现X，但如果尾号是10，那么就得用X来代替，因为如果用10做尾号，那么此人的身份证就变成了19位。
         *  X是罗马数字的10，用X来代替10
         */
        if(len == 18) {
            if(!certNo.matches("^[1-9]\\d{5}[1|2]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$")) {
                return false;
            }
        }
        try {
            String districtNo = certNo.substring(0, 6);//行政区号
            String birthday = certNo.substring(6, 14);//出身日期
            String sequence = certNo.substring(14, 17);//序列号
            String lastStr = certNo.substring(17, 18);
            Integer.parseInt(districtNo); //说明前六位是数字//TODO 这儿应该对行政区号进行验证
            Integer.parseInt(birthday);
            Integer.parseInt(sequence);
            if (!checkDate(birthday)) {
                return false; //如果日期不合法，则返回false
            }
            if (!lastStr.equals(getPower(certNo))) {
                return false;
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private boolean checkDate(String sDate) {
        String strYear, strMonth, strDay;
        strYear = sDate.substring(0, 4);
        strMonth = sDate.substring(4, 6);
        strDay = sDate.substring(6, 8);
        int year, month, day;
        year = Integer.parseInt(strYear);
        month = Integer.parseInt(strMonth);
        day = Integer.parseInt(strDay);
        if (year < 1900) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        if (!isLeapYear(year)) {
            if (day < 1 || day > m_monthMax_1[month - 1]) {
                return false;
            }
        }
        else {
            if (day < 1 || day > m_monthMax_2[month - 1]) {
                return false;
            }
        }
        return true;
    }

    private boolean isLeapYear(int year) {
        return ( ( (year % 400) == 0) || ( ( (year % 4) == 0) && ( (year % 100) != 0)));
    }


    public String getPower(String certNo) {
        int sum = 0;
        int idIndex = 0;
        for (int i = 0; i < 17; i++) {
            idIndex = 16 - i;
            sum += m_powers[i + 1] * Integer.parseInt(certNo.substring(idIndex, idIndex + 1));
        }
        sum = sum % 11;
        return new String(m_lastValues, sum, 1);
    }

    static{
        m_monthMax_1 = new int[12];
        m_monthMax_2 = new int[12];
        m_monthMax_1[0] = m_monthMax_2[0] = 31;//1月
        m_monthMax_1[1] = 28;//2月, not leap year
        m_monthMax_2[1] = 29;//2月, leap year
        m_monthMax_1[2] = m_monthMax_2[2] = 31;//3月
        m_monthMax_1[3] = m_monthMax_2[3] = 30;//4月
        m_monthMax_1[4] = m_monthMax_2[4] = 31;//5月
        m_monthMax_1[5] = m_monthMax_2[5] = 30;//6月
        m_monthMax_1[6] = m_monthMax_2[6] = 31;//7月
        m_monthMax_1[7] = m_monthMax_2[7] = 31;//8月
        m_monthMax_1[8] = m_monthMax_2[8] = 30;//9月
        m_monthMax_1[9] = m_monthMax_2[9] = 31;//10月
        m_monthMax_1[10] = m_monthMax_2[10] = 30;//11月
        m_monthMax_1[11] = m_monthMax_2[11] = 31;//12月

        m_powers = new int[] {1, 2, 4, 8, 5, 10, 9, 7, 3, 6, 1, 2, 4, 8, 5, 10, 9, 7}; //身份证的每个位置的权值
        m_lastValues = new char[] {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'}; //身份证最后一位的值
    }

}
