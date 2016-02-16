package lihu.zlm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * 日期操作工具类
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月26日 下午8:54:10
 * 
 */
public class DateUtil {

	/**
	 * 把时间字符串转成指定格式的时间
	 * 
	 * @param datePattern
	 *            转换格式
	 * @param date
	 *            时间
	 * @return 时间Date
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String datePattern, String dateStr) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(datePattern);
		try {
			date = df.parse(dateStr);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return (date);
	}

	/**
	 * 把字符串转换成日期类型 比如: 把字符串20121212转换成日期类型
	 * 
	 * @param dateString
	 *            要转换的字符串
	 * @param fomart
	 *            转换成的格式
	 * @return
	 */
	public static Date fomartStringToDate(String dateString, String fomart) {
		SimpleDateFormat sdf = new SimpleDateFormat(fomart);
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 把日期转换成字符串
	 * 
	 * @param date
	 * @param fomart
	 * @return
	 */
	public static String fomartDateToString(Date date, String fomart) {
		SimpleDateFormat sdf = new SimpleDateFormat(fomart);
		return sdf.format(date);
	}

	/**
	 * 根据一个日期, 获得这个日期后的某一天的日期, 就是日期的计算 比如: 日期20121213 + 30天的日期
	 * 
	 * @param date
	 *            指定日期
	 * @param formart
	 *            格式化的格式
	 * @param days
	 *            天数
	 * @return
	 */
	public static String getSpecifiedDayAfterDate(Date date, String formart, Integer days) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
		return fomartDateToString(now.getTime(), formart);
	}

	/**
	 * 获得当前日期时间
	 * 
	 * @return
	 */
	public static String getSpecifiedDateTime(String specDate) {
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
		return specDate + " " + sd.format(new Date());
	}

	/**
	 * 获得当前日期时间
	 * 
	 * @return
	 */
	public static String getCurrentDateTime(Date date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(date);
	}

	/**
	 * 获得当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate(Date date, String formart) {
		SimpleDateFormat sd = new SimpleDateFormat(formart);
		return sd.format(date);
	}

	/**
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @param format
	 *            yyyy-MM-dd / yyyy-MM-dd hh:mm:ss
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(String specifiedDay, String format) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat(format).format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @param format
	 *            yyyy-MM-dd / yyyy-MM-dd hh:mm:ss
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(Date specifiedDay, String format) {
		Calendar c = Calendar.getInstance();
		c.setTime(specifiedDay);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat(format).format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @param format
	 *            yyyy-MM-dd / yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay, String format) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat(format).format(c.getTime());
		return dayAfter;
	}

	/**
	 * 根据日期获得所在周的日期
	 * 
	 * @param mdate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<Date> dateToWeek(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date mdate;
		List<Date> list = new ArrayList<Date>();
		try {
			mdate = sdf.parse(dateString);
			int b = mdate.getDay();
			Date fdate;
			Long fTime = mdate.getTime() - b * 24 * 3600000;
			for (int a = 0; a <= 7; a++) {
				fdate = new Date();
				fdate.setTime(fTime + (a * 24 * 3600000));
				list.add(a, fdate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 获得最近七天的日期
	 * 
	 * @return
	 */
	public static List<Date> getRecently7Days() {
		List<Date> the7Days = new ArrayList<Date>();
		try {
			String currentDate = getCurrentDate(new Date(), "yyyy-MM-dd");
			String specDay = currentDate;
			String specBeforeDay = currentDate;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < 7; i++) {
				specDay = specBeforeDay;
				specBeforeDay = getSpecifiedDayBefore(specDay, "yyyy-MM-dd");
				the7Days.add(i, sdf.parse(specBeforeDay));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return the7Days;
	}

	/**
	 * 根据一个日期获得一个起始日期和一个结束日期
	 * 
	 * @param specDay
	 *            日期
	 * @param timeStamp
	 *            时间戳
	 * @return
	 */
	public static String[] getStartAndEndTime(String specDay, String timeStamp) {
		String[] stratAndEndTime = new String[2];
		String specifiedDay = specDay + " " + timeStamp;
		stratAndEndTime[0] = getSpecifiedDayBefore(specifiedDay, "yyyy-MM-dd") + " " + timeStamp;
		stratAndEndTime[1] = specifiedDay;
		return stratAndEndTime;
	}

	/**
	 * 验证日期字符串的格式
	 * 
	 * @param validateString
	 * @return
	 */
	public static boolean validateDate(String validateString) {
		boolean flag = false;
		if (StringUtils.isEmpty(validateString)) {
			return flag;
		}
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]"
				+ "?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])"
				+ "|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579]"
				+ "[01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))"
				+ "|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(validateString);
		boolean b = m.matches();
		if (b) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

	/**
	 * 获取指定日期是星期几，参数为null时表示获取当前日期是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekOfDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}
	
	public static void main(String[] args) {
		System.out.println(getWeekOfDate(null));
	}

}
