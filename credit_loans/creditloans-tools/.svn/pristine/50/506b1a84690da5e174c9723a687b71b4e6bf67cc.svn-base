package com.intellicredit.platform.component.validator2;

import java.util.LinkedList;
import java.util.List;

/*
 *检查格式为YYYYMMDD[8位]和YYYYMMDDhhmmss(14位)的日期是否正确
 */
public class StrDateCheck {
    private static int[] m_monthMax_1 = null; //每个月的最大天,非Leap year
    private static int[] m_monthMax_2 = null; //每个月的最大天,Leap year

    private List<String> m_nullList = null;
    //private LinkedList m_bufferDate = null;
    private static StrDateCheck strDateCheck;

    public static StrDateCheck getInstants() {
        if (strDateCheck == null) {
            strDateCheck = new StrDateCheck();
        }
        return strDateCheck;
    }

    public StrDateCheck() {
        m_nullList = new LinkedList<String>();
        //m_bufferDate = new LinkedList();
        m_nullList.add("00000000");
        m_nullList.add("00000000000000");
        //m_bufferDate = new LinkedList();
    }

    //缺省不为空
    public boolean check(String strDate) {
        return check(strDate, false);
    }

    /*isNull:表示此strDate是否可以为空,8位为空的表示为"00000000",14位为空的表示为"00000000000000"
     */
    public boolean check(String strDate, boolean isNull) {
        //0.如果可以为空,那对于空值就正确
        if (isNull) {
            if (m_nullList.contains(strDate)) {
                return true;
            }
        }
        //0.5 首先看缓存中有没有
        /*
        if (m_bufferDate.contains(strDate)) {
            return true;
        }*/
        //1.长度必须为8或者16
        int len = strDate.length();
        if (len != 8 && len != 14) {
            return false;
        }
        boolean bResult;
        try {
            Long.parseLong(strDate); //必须全部位整数
        }
        catch (Exception e) {
            return false;
        }
        if (len == 8) {
            bResult = checkDate(strDate);
        }
        else {
            bResult = checkDateTime(strDate);
        }
        /*
        if (bResult) {
            //m_bufferDate.add(strDate);
        }*/
        return bResult;
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

    public boolean checkDateTime(String dtDate) {
        String sDate = dtDate.substring(0, 8);
        if (!checkDate(sDate)) {
            return false;
        }
        String strHour = dtDate.substring(8, 10);
        String strMinite = dtDate.substring(10, 12);
        String strSecond = dtDate.substring(12, 14);
        int hour, minite, second;
        try {
            hour = Integer.parseInt(strHour);
            minite = Integer.parseInt(strMinite);
            second = Integer.parseInt(strSecond);
        }
        catch (Exception e) {
            return false;
        }
        if (hour < 0 || hour >= 24
            || minite < 0 || minite >= 60
            || second < 0 || second >= 60) {
            return false;
        }
        return true;
    }

    private boolean isLeapYear(int year) {
        return ( ( (year % 400) == 0) || ( ( (year % 4) == 0) && ( (year % 100) != 0)));
    }

    static {
        m_monthMax_1 = new int[12];
        m_monthMax_2 = new int[12];
        m_monthMax_1[0] = m_monthMax_2[0] = 31; //1月
        m_monthMax_1[1] = 28; //2月, not leap year
        m_monthMax_2[1] = 29; //2月, leap year
        m_monthMax_1[2] = m_monthMax_2[2] = 31; //3月
        m_monthMax_1[3] = m_monthMax_2[3] = 30; //4月
        m_monthMax_1[4] = m_monthMax_2[4] = 31; //5月
        m_monthMax_1[5] = m_monthMax_2[5] = 30; //6月
        m_monthMax_1[6] = m_monthMax_2[6] = 31; //7月
        m_monthMax_1[7] = m_monthMax_2[7] = 31; //8月
        m_monthMax_1[8] = m_monthMax_2[8] = 30; //9月
        m_monthMax_1[9] = m_monthMax_2[9] = 31; //10月
        m_monthMax_1[10] = m_monthMax_2[10] = 30; //11月
        m_monthMax_1[11] = m_monthMax_2[11] = 31; //12月
    }

    public static void main(String[] args) {
        StrDateCheck check = new StrDateCheck();
        boolean b;
        /*
                 String date1 = "00000000";
                 b = check.check(date1,true);
                 if(b){
            System.out.println("correct!");
                 }else{
            System.out.println("error!");
                 }*/
        String date2 = "19990412";
        b = check.check(date2, true);
        if (b) {
            System.out.println("correct!");
        }
        else {
            System.out.println("error!");
        }
        String date3 = "00010204";
        b = check.check(date3, true);
        if (b) {
            System.out.println("correct!");
        }
        else {
            System.out.println("error!");
        }
        String date4 = "19990231";
        b = check.check(date4, true);
        if (b) {
            System.out.println("correct!");
        }
        else {
            System.out.println("error!");
        }
        String date5 = "20000229";
        b = check.check(date5, true);
        if (b) {
            System.out.println("correct!");
        }
        else {
            System.out.println("error!");
        }
    }
}
