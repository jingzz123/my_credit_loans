package com.intellicredit.platform.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.*;
import java.util.*;

// Referenced classes of package com.paynedatasystems.erules.util:
//            ComparableCalendar

public class CalendarUtil {

	public static SimpleDateFormat dateAndTimeFormatter;
	public static SimpleDateFormat localDateAndTimeFormatter;
	public static SimpleDateFormat dateFormatter;
	public static SimpleDateFormat timeFormatter;
	public static SimpleDateFormat localTimeFormatter;
	public static Properties calProps = null;
	private static String vid;

	public static SimpleDateFormat defaultSimpleDateTimeFormatter;
	public static SimpleDateFormat defaultSimpleDateFormatter;
	public static SimpleDateFormat simpleDateTimeFormatter;
	public static SimpleDateFormat simpleDateFormatter;

	public CalendarUtil() {
	}

	public static long getDateAndTimeAsLong(String s) throws ParseException,
			ParseException {
		Date date = null;
		date = dateAndTimeFormatter.parse(s);
		return date.getTime();
	}

	public static long getDateAsLong(String s) throws ParseException,
			ParseException {
		Date date = null;
		date = dateFormatter.parse(s);
		ComparableCalendar comparablecalendar = new ComparableCalendar(date);
		return comparablecalendar.clearTimeFields().getTime().getTime();
	}

	public static long getTimeAsLong(String s) throws ParseException,
			ParseException {
		Date date = null;
		ComparableCalendar comparablecalendar = new ComparableCalendar();
		date = timeFormatter.parse(s);
		comparablecalendar.setTime(date);
		return comparablecalendar.getTimeAsLong();
	}

	public static long getLocalDateAndTimeAsLong(String s)
			throws ParseException, ParseException {
		Date date = null;
		date = localDateAndTimeFormatter.parse(s);
		return date.getTime();
	}

	public static long getLocalTimeAsLong(String s) throws ParseException,
			ParseException {
		Date date = null;
		ComparableCalendar comparablecalendar = new ComparableCalendar();
		date = localTimeFormatter.parse(s);
		comparablecalendar.setTime(date);
		return comparablecalendar.getTimeAsLong();
	}

	public static String getLongTime(String s, String s1, String s2, String s3) {
		String s4 = null;
		String s5 = null;
		String s6 = null;
		String s7 = null;
		StringBuffer stringbuffer = null;
		StringBuffer stringbuffer1 = null;
		s1 = s1 + '_' + vid + ':' + s3;
		int i = 0;
		s4 = Integer.toString((new String(s + s1 + s2)).hashCode(), 26);
		s5 = Integer.toString((new String(s1 + s2 + s)).hashCode(), 13);
		s6 = Integer.toString((new String(s2 + s + s1)).hashCode(), 23);
		s7 = Integer.toString((new String(s2 + s + s1)).hashCode(), 22);
		stringbuffer = new StringBuffer(s5);
		s5 = stringbuffer.reverse().toString();
		stringbuffer = new StringBuffer(s6);
		s6 = stringbuffer.reverse().toString();
		i = s4.length();
		i = Math.max(i, s5.length());
		i = Math.max(i, s6.length());
		i = Math.max(i, s7.length());
		stringbuffer1 = new StringBuffer();
		for (int j = 0; j < i; j++) {
			if (j < s4.length())
				stringbuffer1.append(s4.charAt(j));
			if (j < s5.length())
				stringbuffer1.append(s5.charAt(j));
			if (j < s6.length())
				stringbuffer1.append(s6.charAt(j));
			if (j < s7.length())
				stringbuffer1.append(s7.charAt(j));
		}

		stringbuffer1.insert(stringbuffer1.length() / 2, '-');
		stringbuffer1.insert(stringbuffer1.length() / 4, '-');
		return stringbuffer1.toString().toLowerCase();
	}

	public static void setLongTime() {
		String s = null;
		Properties properties = null;
		String s1 = null;
		String s2 = null;
		String s3 = null;
		String s4 = null;
		String s5 = null;
		properties = new Properties();
		try {
			properties
					.load(ClassLoader.getSystemResourceAsStream("erules.lic"));
		} catch (Exception exception) {
			// throw new Exception("Invalid license for RuleRuntime");
		}
		s1 = properties.getProperty("Owner");
		s2 = properties.getProperty("RegistrationID");
		s3 = properties.getProperty("ExpirationDate");
		s4 = properties.getProperty("LicenseKey");
		s5 = properties.getProperty("Units");
		if (s1 == null || s2 == null || s3 == null || s4 == null || s5 == null)
			// throw new
			// Exception("Invalid license for e-Rules detected.\nTo obtain a new license, please visit www.paynedatasystems.com.");
			s = getLongTime(s1, s2, s3, s5);
		if (!s.equals(s4)) {
			// throw new
			// Exception("Invalid license for e-Rules detected.\nTo obtain a new license, please visit www.paynedatasystems.com.");
		}
		if (s3.equals("Never")) {
			calProps = properties;
			return;
		}
		try {
			Date date = dateFormatter.parse(s3);
			if (date.before(new Date())) {
			}
			// throw new Exception("The license for e-Rules expired on " +
			// date.toString() + "." + "\n" +
			// "To obtain a new license, please visit www.paynedatasystems.com.");
			calProps = properties;
		} catch (ParseException parseexception) {
			// throw new
			// Exception("Invalid license for e-Rules detected.\nTo obtain a new license, please visit www.paynedatasystems.com.");
		}
	}

	public static void setLongTime(String s, String s1, String s2, String s3,
			String s4, String s5) throws Exception {
		Properties properties = new Properties();
		File file = new File(s);
		StringBuffer stringbuffer = new StringBuffer("#");
		StringTokenizer stringtokenizer = new StringTokenizer(s1, "\n");
		FileOutputStream fileoutputstream = null;
		try {
			for (; stringtokenizer.hasMoreTokens(); stringbuffer.append('\n')
					.append("# ").append(stringtokenizer.nextToken()))
				;
			file.mkdirs();
			properties.setProperty("Owner", s2);
			properties.setProperty("RegistrationID", s3);
			properties.setProperty("ExpirationDate", s4);
			properties.setProperty("Units", s5);
			properties.setProperty("LicenseKey", getLongTime(s2, s3, s4, s5));
			fileoutputstream = new FileOutputStream(new File(s, "erules.lic"));
			properties.store(fileoutputstream, stringbuffer.toString());
		} finally {
			try {
				fileoutputstream.close();
			} catch (Exception exception1) {
			}
		}
	}

	static {
		dateAndTimeFormatter = null;
		localDateAndTimeFormatter = null;
		dateFormatter = null;
		timeFormatter = null;
		localTimeFormatter = null;
		dateAndTimeFormatter = new SimpleDateFormat(
				"MMMMMMMM d, yyyy 'at' h:mm a z");
		dateAndTimeFormatter.setLenient(false);
		localDateAndTimeFormatter = new SimpleDateFormat(
				"MMMMMMMM d, yyyy 'at' h:mm a");
		localDateAndTimeFormatter.setLenient(false);
		dateFormatter = new SimpleDateFormat("MMMMMMMM d, yyyy");
		dateFormatter.setLenient(false);
		timeFormatter = new SimpleDateFormat("h:mm a z");
		timeFormatter.setLenient(false);
		localTimeFormatter = new SimpleDateFormat("h:mm a");
		localTimeFormatter.setLenient(false);
		vid = "1";// Version.ERULES_VERSION_MAJOR_NUMBER;

		defaultSimpleDateTimeFormatter = new SimpleDateFormat(
				"yyyy-M-d H:mm:ss");
		defaultSimpleDateFormatter = new SimpleDateFormat("yyyy-M-d");
	}
}
