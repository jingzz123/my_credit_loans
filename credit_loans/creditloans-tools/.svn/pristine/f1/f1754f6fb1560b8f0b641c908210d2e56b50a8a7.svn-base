package cn.creditloans.tools.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期
 * 
 * @author Ash
 * 
 */
// FIXME : 放弃SimpleDateFormat吧, 使用apache的DateUtil
public class DateUtils {
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");// 设定日期格式

	public static DateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");// 设定日期格式

	public static DateFormat MONTH_FORMAT = new SimpleDateFormat("yyyy-MM");// 设定日期格式

	public static DateFormat DATETIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	public static DateFormat INTEGER_DATETIME_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	
	public static DateFormat INTEGET_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");// 设定日期格式
	
	public static DateFormat EXPORT_RESULT_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");// 查询结果导出日期格式

	/**
	 * 将日期转换为yyyy-MM-dd hh:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		if(date == null){
			return "";
		}
		return DATETIME_FORMAT.format(date);
	}
	
	public static String formatIntegerDateTime(Date date) {
		if(date == null){
			return "";
		}
		return INTEGER_DATETIME_FORMAT.format(date);
	}
	
	public static String formatIntegerDate(Date date){
		if(date == null){
			return "";
		}
	    return INTEGET_DATE_FORMAT.format(date);
	}

	public static Date getDateFromIDate(String iDate) {
		try {
			int year = Integer.parseInt(iDate.substring(0, 4));
			int month = Integer.parseInt(iDate.substring(4, 6));
			int day = Integer.parseInt(iDate.substring(6, 8));
			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, year);
			date.set(Calendar.MONTH, month-1);
			date.set(Calendar.DATE, day);
			date.set(Calendar.HOUR_OF_DAY, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.SECOND, 1);
			return date.getTime();
		} catch (Exception e) {
			return null;
		}

	}
	
	public static String getMinuAndSecondInfo(long time){
		time = time / 1000;
		long minute = time / 60;
		long second = time % 60;
		return minute+"分"+second+"秒";
	}
	
	/**
	 * 计算距离(point)日期之前(day)天的日期 (因为需求需要查询10, 30, 90, 365, all天内的条件)
	 * 
	 * @param point 时间点
	 * @param day 在时间点之前多少天
	 * @return
	 */
	public static Date calcBeforeDateByDay(Date point, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(point);
		
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
		Date date = calendar.getTime();

		return date;
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的字符串日期转换为Date(在下载最新时需要此条件)
	 * @param stringDate
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String stringDate) throws ParseException {
	    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return formatDate.parse(stringDate);
	}
	
	/**
	 * 获取当前月份的1号，若当天即为1号则获取上个月的1号
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getAgoDate(Date date) throws ParseException {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-01"); 
	    String dayOfFirst = format.format(date);
	    String tody = DATE_FORMAT.format(new Date());
	    //String tody = "2014-11-01";
	    String[] strArr = dayOfFirst.split("-");
	    int year = Integer.parseInt(strArr[0]);
	    int month = Integer.parseInt(strArr[1]);
	    int day = Integer.parseInt(strArr[2]);
	    // 如果当前日期就是这个月的1号
	    if(dayOfFirst.equals(tody)) {
	        // 如果当前日期就是1月
	        if(month == 1) {
	            year = year - 1;
	            month = 12;
	        } else {
	            month = month - 1;
	        }
	        day = 1;
	    }
	    StringBuilder builder = new StringBuilder();
	    builder.append(year);
	    builder.append("-");
	    builder.append(month);
	    builder.append("-");
	    builder.append("0");
	    builder.append(day);
	    return builder.toString();
	}
	
	// 获取当月第一天 00:00:00
	public static Date getMonthFirst(Date date) {
		Calendar rDate = Calendar.getInstance();
		rDate.setTime(date);
		rDate.set(Calendar.DATE, 1);// 设为当前月的1号，DATE和DAY_OF_MONTH相同
		rDate.set(Calendar.HOUR_OF_DAY, 0);
		rDate.set(Calendar.MINUTE, 0);
		rDate.set(Calendar.SECOND, 0);
		return rDate.getTime();
	}
	
	// 获取当月最后一天 23:59:59
	public static Date getMonthLast(Date date) {
		Calendar rDate = Calendar.getInstance();
		rDate.setTime(date);
		rDate.set(Calendar.DATE, 1);// 设为当前月的1号，DATE和DAY_OF_MONTH相同
		rDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		rDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		rDate.set(Calendar.HOUR_OF_DAY, 23);
		rDate.set(Calendar.MINUTE, 59);
		rDate.set(Calendar.SECOND, 59);
		return rDate.getTime();
	}
	
	 /**
     * 获取某年某月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        // 需要注意的是：月份是从0开始的，比如说如果输入5的话，实际上显示的是4月份的最后一天（因此月份要减一）
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }

    /**
     * 获取某年某月的第一天
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        // 需要注意的是：月份是从0开始的，比如说如果输入5的话，实际上显示的是4月份的最后一天（因此月份要减一）
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return cal.getTime();
    }
    
    /**
     * 根据传入的Date返回与之对应的年，月， 日
     *              map.get("year")获取年份
     *              map.get("month")获取月份
     *              map.get("day")获取天
     * @param date
     * @return
     */
    public static Map<String, Integer> getYearMonthDate(Date date) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String strDate = DateUtils.DATE_FORMAT.format(date);
        String[] strDates = strDate.split("-");
        map.put("year", Integer.parseInt(strDates[0]));
        map.put("month", Integer.parseInt(strDates[1]));
        map.put("day", Integer.parseInt(strDates[2]));
        return map;
    }
	
	public static void main(String[] args){
		Date date = getDateFromIDate("20090619");
		Date monthFirst = getMonthFirst(date);
		Date monthLast = getMonthLast(date);
		System.out.println(formatDateTime(date));
		System.out.println(formatDateTime(monthFirst));
		System.out.println(formatDateTime(monthLast));
	}
	
}
