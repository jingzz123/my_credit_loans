package cn.creditloans.tools.phone.tools;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间的一些方法
 * @author Ash
 *
 */
public class TimeMethodUtil {
	
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");//设定日期格式
	
	public static DateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");//设定日期格式
	
	public static DateFormat YEAR_MONTH_FORMAT = new SimpleDateFormat("yyyyMM");//设定年月格式
	
	// 获取当月第一天
	public static String getCurrentMonthFirst() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号，DATE和DAY_OF_MONTH相同
		return DATE_FORMAT.format(lastDate.getTime());
	}
	
	/**
	 * 获取当前日的年月
	 * @return
	 */
	public static String getCurrentYearMonth(){
		Calendar date = Calendar.getInstance();
		return YEAR_MONTH_FORMAT.format(date.getTime());
	}
	
	// 计算当月最后一天,返回字符串
	public static String getCurrentMonthLast() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		return DATE_FORMAT.format(lastDate.getTime());
	}

	/**
	 * 获取N个之前的年月
	 * @return
	 */
	public static String getProviousNYearMonth(int gap){
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, 0-gap);// 减一个月，变为下月的1号
		return YEAR_MONTH_FORMAT.format(date.getTime());
	}

	// 上月第一天
	public static String getPreviousNMonthFirst(int gap) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 0-gap);// 减一个月，变为下月的1号
		return DATE_FORMAT.format(lastDate.getTime());
	}

	// 计算N月最后一天,返回字符串
	public static String getPreviousNMonthLast(int gap) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1-gap);// 减去一天，变为当月最后一天
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天		
		return DATE_FORMAT.format(lastDate.getTime());
	}
	
	// 上月第一天
	public static String getPreviousMonthFirst() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		return DATE_FORMAT.format(lastDate.getTime());
	}

	// 上月第2天
	public static Date getTwoPreviousMonthFirst() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -2);// 减2个月，变为上两个月的1号
		return lastDate.getTime();
	}

	// 计算上月最后一天,返回字符串
	public static String getPreviousMonthLast() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		return DATE_FORMAT.format(lastDate.getTime());
	}
	
	// 获得下个月第一天的日期
	public String getNextMonthFirst() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		return DATE_FORMAT.format(lastDate.getTime());
	}

	// 获得下个月最后一天的日期
	public String getNextMonthEnd() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 加一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		return DATE_FORMAT.format(lastDate.getTime());
	}

	//获取当前日期
	public static String getCurrentDate(){
		return DATE_FORMAT.format(new java.util.Date());
	}
	
	// 获得当前日期与本周日相差的天数
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 获得本周一的日期
	public static String getCurrentWeekMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		return DATE_FORMAT.format(monday);
	}

	// 获得本周星期日的日期
	public static String getCurrentWeekSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		return DATE_FORMAT.format(monday);
	}

	// 获得上周星期一的日期
	public static String getPreviousWeekMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus - 7);
		Date monday = currentDate.getTime();
		return DATE_FORMAT.format(monday);
	}

	// 获得上周星期日的日期
	public static String getPreviousWeekSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus -1);
		Date monday = currentDate.getTime();
		return DATE_FORMAT.format(monday);
	}

	// 获得下周星期一的日期
	public static String getNextWeekMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		return DATE_FORMAT.format(monday);
	}

	// 获得下周星期日的日期
	public static String getNextWeekSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date monday = currentDate.getTime();
		return DATE_FORMAT.format(monday);
	}

	// 获得本年第一天的日期
	public static String getCurrentYearFirst() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.set(Calendar.MONTH, Calendar.JANUARY);
		currentDate.set(Calendar.DATE, 1);// 设为当前月的1号
		return DATE_FORMAT.format(currentDate.getTime());
	}

	// 获得本年最后一天的日期 *
	public static String getCurrentYearLast() {
		String years = YEAR_FORMAT.format(new Date());
		return years + "-12-31";
	}

	// 获得本年第一個月份
	public static String getCurrentYearMonthFirst() {
		String years = YEAR_FORMAT.format(new Date());
		return years + "-01";
	}

	// 获得本年最後一個月份
	public static String getCurrentYearMonthLast() {
		String years = YEAR_FORMAT.format(new Date());
		return years + "-12";
	}

	// 获得上年第一天的日期 *
	public static String getPreviousYearFirst() {
		String years = YEAR_FORMAT.format(new Date());
		int years_value = Integer.parseInt(years) - 1;
		return years_value + "-01-01";
	}

	// 获得上年最后一天的日期
	public static String getPreviousYearLast() {
		String years = YEAR_FORMAT.format(new Date());
		int years_value = Integer.parseInt(years) - 1;
		return years_value + "-12-31";
	}

	// 获得明年第一天的日期
	public static String getNextYearFirst() {
		String years = YEAR_FORMAT.format(new Date());
		int years_value = Integer.parseInt(years) + 1;
		return years_value + "-01-01";
	}

	// 获得明年最后一天的日期
	public static String getNextYearLast() {
		String years = YEAR_FORMAT.format(new Date());
		int years_value = Integer.parseInt(years) + 1;
		return years_value + "-12-31";
	}

	// 获得本季度第一天
	public static String getCurrentSeasonFirst() {
		int array[][] = { { Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH }, 
				{ Calendar.APRIL, Calendar.MAY, Calendar.JUNE}, 
				{ Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER }, 
				{ Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER} };
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int season = 1;
		if (month >= Calendar.JANUARY && month <= Calendar.MARCH) {
			season = 1;
		}
		if (month >= Calendar.APRIL && month <= Calendar.JUNE) {
			season = 2;
		}
		if (month >= Calendar.JULY&& month <= Calendar.SEPTEMBER) {
			season = 3;
		}
		if (month >= Calendar.OCTOBER&& month <= Calendar.DECEMBER) {
			season = 4;
		}
		month = array[season - 1][0];
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, 1);
		return DATE_FORMAT.format(cal.getTime());
	}

	// 获得本季度最后一天
	public static String getCurrentSeasonLast() {
		int array[][] = { { Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH }, 
				{ Calendar.APRIL, Calendar.MAY, Calendar.JUNE}, 
				{ Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER }, 
				{ Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER} };
		Calendar cal = Calendar.getInstance();
		
		int month = cal.get(Calendar.MONTH);
		int season = 1;
		if (month >= Calendar.JANUARY && month <= Calendar.MARCH) {
			season = 1;
		}
		if (month >= Calendar.APRIL && month <= Calendar.JUNE) {
			season = 2;
		}
		if (month >= Calendar.JULY&& month <= Calendar.SEPTEMBER) {
			season = 3;
		}
		if (month >= Calendar.OCTOBER&& month <= Calendar.DECEMBER) {
			season = 4;
		}
		month = array[season - 1][2];
		int day = getLastDayOfMonth(cal.get(Calendar.YEAR), month+1);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, day);
		return DATE_FORMAT.format(cal.getTime());
	}
	
	// 获得上季度第一天
	public static String getPreviousSeasonFirst() {
		int array[][] = { { Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH }, 
				{ Calendar.APRIL, Calendar.MAY, Calendar.JUNE}, 
				{ Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER }, 
				{ Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER} };
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int season = 1;
		if (month >= Calendar.JANUARY && month <= Calendar.MARCH) {
			season = 4;
			cal.add(Calendar.YEAR, -1);
		}
		if (month >= Calendar.APRIL && month <= Calendar.JUNE) {
			season = 1;
		}
		if (month >= Calendar.JULY&& month <= Calendar.SEPTEMBER) {
			season = 2;
		}
		if (month >= Calendar.OCTOBER&& month <= Calendar.DECEMBER) {
			season = 3;
		}
		month = array[season - 1][0];
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, 1);
		return DATE_FORMAT.format(cal.getTime());
	}

	// 获得本季度最后一天
	public static String getPreviousSeasonLast() {
		int array[][] = { { Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH }, 
				{ Calendar.APRIL, Calendar.MAY, Calendar.JUNE}, 
				{ Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER}, 
				{ Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER} };
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int season = 1;
		if (month >= Calendar.JANUARY && month <= Calendar.MARCH) {
			season = 4;
			cal.add(Calendar.YEAR, -1);
		}
		if (month >= Calendar.APRIL && month <= Calendar.JUNE) {
			season = 1;
		}
		if (month >= Calendar.JULY&& month <= Calendar.SEPTEMBER) {
			season = 2;
		}
		if (month >= Calendar.OCTOBER&& month <= Calendar.DECEMBER) {
			season = 3;
		}
		month = array[season - 1][2];
		int day = getLastDayOfMonth(cal.get(Calendar.YEAR), month+1);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, day);
		return DATE_FORMAT.format(cal.getTime());
	}
	
	//近3天初
	public static String getRecent3First(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -2);
		return DATE_FORMAT.format(cal.getTime());
	}
	
	//近3天末
	public static String getRecent3Last(){
		return getCurrentDate();
	}
	
	//近7天初
	public static String getRecent7First(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -6);
		return DATE_FORMAT.format(cal.getTime());
	}
	
	//近7天末
	public static String getRecent7Last(){
		return getCurrentDate();
	}
	
	//近30天初
	public static String getRecent30First(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -29);
		return DATE_FORMAT.format(cal.getTime());
	}
	
	//近30天末
	public static String getRecent30Last(){
		return getCurrentDate();
	}
	
	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = DATE_FORMAT.parse(date1);
			mydate = DATE_FORMAT.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	// 获取当天时间
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		return hehe;
	}

	/**
	 * 获取某年某月的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最后一天
	 */
	public static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
	
	//根据诸如2011-11（年-月），得到下一个月的第一日
	public static String getNextMonthDayFirst(String yearMonth){
		int index = yearMonth.indexOf("-");
		int year = Integer.parseInt(yearMonth.substring(0,index));
		String sMonth = yearMonth.substring(index+1);
		int month;
		if(sMonth.substring(0,1).equals("0")){
			month = Integer.parseInt(sMonth.substring(1));
		}else{
			month = Integer.parseInt(sMonth);
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		return DATE_FORMAT.format(cal.getTime());
	}
	
	/**
	 * 输入的日期基础上 + 或者 -
	 * @param date
	 * @param gapDay
	 * @return
	 */
	public static Date addOrMinusDays(Date date, int gapDay){
		 Calendar calendar=Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.DAY_OF_MONTH,gapDay);
		 return calendar.getTime();
	}

	/**
	 * 输入的日期基础上 + 或者 -
	 * @param date
	 * @param gapDay
	 * @return
	 */
	public static Date addOrMinusMonths(Date date, int gapMonth){
		 Calendar calendar=Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.MONTH,gapMonth);
		 return calendar.getTime();
	}
	
	/**
	 * 判断传入日期是否在当前日期之后
	 * @param date
	 * @return
	 */
	public static boolean isAfter(Date date){
		if(date==null){
			return false;
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		if(cal1.getTimeInMillis()-cal2.getTimeInMillis()>=0){
			return true;
		}
		return false;
	}

}
