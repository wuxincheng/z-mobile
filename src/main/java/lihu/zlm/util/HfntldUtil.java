package lihu.zlm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 标签工具类
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月27日 下午9:04:48
 * 
 */
public class HfntldUtil {
	
	/**
	 * 获取几秒前，几分钟前，几小时前，几天前，几月前，几年前
	 * 
	 * @param formatDate
	 * @return
	 */
	public static String getRelativeDateFormat(String formatDate){
		SimpleDateFormat format = null;
		if (formatDate.indexOf("-") > 0) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
		} else {
			format = new SimpleDateFormat("yyyyMMdd HH:m:s");
		}
		
		Date date = null;
		try {
			date = format.parse(formatDate);
		} catch (ParseException e) {
			return null;
		}
		return RelativeDateFormat.format(date);
	}

	/**
	 * 根据日期获取英语月份缩写
	 * 
	 * @param formatDate
	 * @return
	 */
	public static String getEngMonth(String formatDate) {
		Date date = fomartStringToDate(formatDate, "yyyyMMdd");

		String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.MONTH);
		if (w < 0) {
			w = 0;
		}

		return months[w];
	}

	/**
	 * 根据日期只截取日
	 * 
	 * @param formatDate
	 * @return
	 */
	public static String getSimpleDay(String formatDate) {
		Date date = fomartStringToDate(formatDate, "yyyyMMdd");

		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_MONTH);
		if (w < 0) {
			w = 0;
		}

		return w + "";
	}

	/**
	 * 根据日期截取月份日
	 * 
	 * @param formatDate
	 * @return 比如：6月26日
	 */
	public static String getMonthDay(String formatDate) {
		Date date = fomartStringToDate(formatDate, "yyyyMMdd");
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
		return sdf.format(date);
	}

	/**
	 * 根据日期获取星期
	 * 
	 * @param formatDate
	 * @return
	 */
	public static String getWeekday(String formatDate) {
		Date date = fomartStringToDate(formatDate, "yyyyMMdd");

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

	/**
	 * 把字符串转换成日期类型 比如: 把字符串20121212转换成日期类型
	 * 
	 * @param dateString
	 *            要转换的字符串
	 * @param fomart
	 *            转换成的格式
	 * @return
	 */
	private static Date fomartStringToDate(String dateString, String fomart) {
		SimpleDateFormat sdf = new SimpleDateFormat(fomart);
		Date date = null;

		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

}
