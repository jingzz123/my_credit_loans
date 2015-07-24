package com.intellicredit.exttools.component.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.function.PostfixMathCommand;

public class CheckCertNo2
    extends PostfixMathCommand {

    private static int[] m_monthMax_1 = null;//每个月的最大天,非Leap year
    private static int[] m_monthMax_2 = null;//每个月的最大天,Leap year
    private static int[] m_powers; //身份证的每个位置的权值
    private static char[] m_lastValues; //身份证最后一位的值

    public CheckCertNo2() {
        numberOfParameters = 2;
    }

    public void run(Stack<Object> stack) throws ParseException {
        //System.out.println("begin check");
        if (numberOfParameters != 2) {
            throw new ParseException("Stack argument null");
        }
        Object param = stack.pop();
        String[] guaranteeNo = (String[])param;
        param = stack.pop();
        String[] guaranteeType = (String[])param;
        String result = "true";
        for(int i=0; i<guaranteeType.length;i++){
            if(guaranteeType[i].equals("0")){
                if (!checkNo(String.valueOf(guaranteeNo[i]))) {
                    result = "false";
                    break;
                }
            }
        }
        stack.push(result);
    }

    private boolean checkNo(String certNo) {
        int len = certNo.length();
        if(len!=15&&len!=18){
            return false;
        }
        if(len==15){//对于15位的身份证暂时返回true
            StringBuffer birthday = new StringBuffer("19").append(certNo.substring(6,12));
            if (!checkDate(birthday.toString())) {
                return false; //如果日期不合法，则返回false
            }
            return true;
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
