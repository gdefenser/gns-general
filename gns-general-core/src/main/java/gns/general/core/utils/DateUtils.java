/**
 * gns-general-core
 * DateUtils.java
 * Lovesha 2017年11月12日
 */
package gns.general.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Lovesha Date util
 */
public class DateUtils {

	public static String formatDateToStr(Date stdDate) {
		return formatDateToStr(stdDate, "yyyy-MM-dd");
	}

	public static String formatDatetTimeToStr(Date stdDate) {
		return formatDateToStr(stdDate, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDateToStr(Date stdDate, String format) {
		return new SimpleDateFormat(format).format(stdDate);
	}

	public static Date formatStrToDate(String strDate) {
		return formatStrToDate(strDate, "yyyy-MM-dd");
	}

	public static Date formatStrToDatetTime(String strDate) {
		return formatStrToDate(strDate, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date formatStrToDate(String strDate, String format) {
		try {
			return new SimpleDateFormat(format).parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date addSeconds(Date stdDate, int value) {
		return addDate(stdDate, Calendar.SECOND, value);
	}

	public static Date addMinutes(Date stdDate, int value) {
		return addDate(stdDate, Calendar.MINUTE, value);
	}

	public static Date addHours(Date stdDate, int value) {
		return addDate(stdDate, Calendar.HOUR_OF_DAY, value);
	}

	public static Date addDays(Date stdDate, int value) {
		return addDate(stdDate, Calendar.DAY_OF_MONTH, value);
	}

	public static Date addDate(Date stdDate, int elm, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stdDate);
		calendar.set(elm, calendar.get(elm) + 1);
		return calendar.getTime();
	}

	/**
	 * compare without time
	 * 
	 * @param stdDate
	 * @param compareDate
	 * @return
	 */
	public static boolean isSameDate(Date stdDate, Date compareDate) {
		return formatDateToStr(stdDate).equals(formatDateToStr(compareDate));
	}

	/**
	 * compare without time
	 * 
	 * @param stdDate
	 * @param compareDate
	 * @return
	 */
	public static boolean isSameDateTime(Date stdDate, Date compareDate) {
		return stdDate.compareTo(compareDate) == 0;
	}

	public static boolean isGreaterThanDate(Date stdDate, Date compareDate) {
		return stdDate.compareTo(compareDate) >= 0;
	}

	public static boolean isLessThanDate(Date stdDate, Date compareDate) {
		return stdDate.compareTo(compareDate) <= 0;
	}

	public static Date getMthFirstDate(Date stdDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stdDate);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getMthLastDate(Date stdDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stdDate);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, maxDay);
		return calendar.getTime();
	}

}
