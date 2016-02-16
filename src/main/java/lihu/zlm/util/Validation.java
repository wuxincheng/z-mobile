package lihu.zlm.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * 基本验证
 * 
 * @author wuxincheng
 *
 */
public class Validation {

	/**
	 * 检查是否为null
	 * 
	 * @author
	 * @param str
	 * @return null返回true 否则返回false
	 */
	public static boolean isNull(String str) {
		return str == null;
	}

	/**
	 * 
	 * 检查是否为空
	 * 
	 * @author
	 * @param str
	 * @return 空返回true 非空返回false
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() <= 0;
	}

	/**
	 * 
	 * 检查是否为空/空格
	 * 
	 * @author
	 * @param str
	 * @return 空/空格返回true 否则返回false
	 */
	public static boolean isBlank(String str) {
		return isEmpty(str) || str.matches("^\\s*$");
	}

	/**
	 * 
	 * 检查是否为数字
	 * 
	 * @author
	 * @param str
	 * @return 数字返回true，否则返回false
	 */
	public static boolean isDigits(String str) {
		return !isEmpty(str) && str.matches("^\\d+$");
	}

	/**
	 * 
	 * 检查整数（可传入整数类型）
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负整数 "+":正整数 "-0":非正整数 "-":负整数 "":整数
	 * @return type整数返回true，否则返回false
	 */
	public static boolean isInt(String num, String type) {
		String pattern = "";
		if ("0+".equalsIgnoreCase(type) || "+0".equalsIgnoreCase(type))
			pattern = "^((0)|(0*[1-9]\\d*))$";// 非负整数
		else if ("+".equalsIgnoreCase(type))
			pattern = "^0*[1-9]\\d*$";// 正整数
		else if ("0-".equalsIgnoreCase(type) || "-0".equalsIgnoreCase(type))
			pattern = "^((0)|(-0*[1-9]\\d*))$";// 非正整数
		else if ("-".equalsIgnoreCase(type))
			pattern = "^-0*[1-9]\\d*$";// 负整数
		else
			pattern = "^((0)|(-?0*[1-9]\\d*))$";// 整数
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 
	 * 检查整数（包括正负整数/非负整数）
	 * 
	 * @author
	 * @param str
	 * @return 整数返回true，否则返回false
	 */
	public static boolean isInt(String str) {
		return isInt(str, "");
	}

	/**
	 * 
	 * 是否为正整数
	 * 
	 * @author
	 * @param str
	 * @return 正整数返回true,否则返回false
	 */
	public static boolean isIntPositive(String str) {
		return isInt(str, "+");
	}

	/**
	 * 检查浮点数（可传入浮点数类型）
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负浮点数 "+":正浮点数 "-0":非正浮点数 "-":负浮点数 "":浮点数
	 * @return type浮点数返回true,否则返回false
	 */
	public static boolean isFloat(String num, String type) {
		String pattern = "";
		if ("0+".equalsIgnoreCase(type) || "+0".equalsIgnoreCase(type))
			pattern = "^\\d+(\\.\\d+)?$";// 非负浮点数
		else if ("+".equalsIgnoreCase(type))
			pattern = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";// 正浮点数
		else if ("0-".equalsIgnoreCase(type) || "-0".equalsIgnoreCase(type))
			pattern = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";// 非正浮点数
		else if ("-".equalsIgnoreCase(type))
			pattern = "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";// 负浮点数
		else
			pattern = "^(-?\\d+)(\\.\\d+)?$";// 浮点数
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 
	 * 检查浮点数（包括正负浮点数/非正负浮点数/浮点数）
	 * 
	 * @author
	 * @param str
	 * @return 浮点数返回true，否则返回false
	 */
	public static boolean isFloat(String str) {
		return isFloat(str, "");
	}

	/**
	 * 
	 * 检查正浮点数
	 * 
	 * @author
	 * @param str
	 * @return 正浮点数返回true，否则返回false
	 */
	public static boolean isFloatPositive(String str) {
		return isFloat(str, "+");
	}

	/**
	 * 
	 * 检查金额
	 * 
	 * @author
	 * @param str
	 * @return 符合金额条件返回true，否则返回false
	 */
	public static boolean isMoney(String str) {
		return !isEmpty(str) && str.matches("^(([1-9]\\d*)|\\d+\\.\\d{1,2})$");
	}

	/**
	 * 
	 * 检查基金净值
	 * 
	 * @author
	 * @param str
	 * @return 符合基金净值条件返回true，否则返回false
	 */
	public static boolean isNav(String str) {
		return !isEmpty(str)
				&& str.matches("^(([1-9]\\d{0,1})|\\d{1,2}\\.\\d{1,4})$");
	}

	/**
	 * 
	 * 检查预约指数点位
	 * 
	 * @author
	 * @param str
	 * @return 预约指数点位返回true，否则返回false
	 */
	public static boolean isIndex(String str) {
		return !isEmpty(str)
				&& str.matches("^(([1-9]\\d{2,5})|\\d{3,6}\\.\\d{1,2})$");
	}

	/**
	 * 
	 * 检查日期
	 * 
	 * @author
	 * @param str
	 * @return 日期返回true，否则返回false
	 */
	public static boolean isDate(String str) {
		return checkDate(str);
	}

	/**
	 * 
	 * 检查日期
	 * 
	 * @author
	 * @param str
	 * @return 日期返回true，否则返回false
	 */
	public static boolean isDateNew(String str) {
		return checkDateNew(str);
	}

	/**
	 * 
	 * 检查是否匹配
	 * 
	 * @author
	 * @param str
	 * @param pattern
	 * @return 匹配返回true，否则返回false
	 */
	public static boolean isMatch(String str, String pattern) {
		return !isEmpty(str) && str.matches(pattern);
	}

	/**
	 * 
	 * 检查基金代码
	 * 
	 * @author
	 * @param str
	 * @return 基金代码返回true，否则返回false
	 */
	public static boolean checkFundCode(String str) {
		return !isEmpty(str) && str.matches("^\\d{6}$");
	}

	/**
	 * 
	 * 检查交易账号
	 * 
	 * @author
	 * @param str
	 * @return 交易账号返回true，否则返回false
	 */
	public static boolean checkTradeAcco(String str) {
		return !isEmpty(str) && str.length() <= 17;
	}

	/**
	 * 
	 * 检查基金账号
	 * 
	 * @author
	 * @param str
	 * @return 基金账号返回true，否则返回false
	 */
	public static boolean checkFundAcco(String str) {
		return !isEmpty(str) && str.length() <= 12;
	}

	/**
	 * 检查出生日期
	 * 
	 * @author
	 * @param str
	 * @return 有效出生日期返回true，否则返回false
	 */
	@SuppressWarnings("deprecation")
	public static boolean checkBirthday(String str) {
		if (!checkDate(str))
			return false;

		int thisyear = (new Date()).getYear() + 1900;
		int age = thisyear - Integer.parseInt(str.substring(0, 4));
		if (age < 0 || age > 150) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * 检查日期（不区分20130833这种情况）
	 * 
	 * @author
	 * @param str
	 * @return 日期返回true，否则返回false
	 */
	public static boolean checkDate(String str) {
		if (isEmpty(str) || !str.matches("^\\d{8}$"))
			return false;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.parse(str);
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * 检查日期
	 * 
	 * @author
	 * @param str
	 * @return 日期返回true，否则返回false
	 */
	public static boolean checkDateNew(String str) {
		if (isEmpty(str) || !str.matches("^\\d{8}$"))
			return false;

		int intYear = Integer.parseInt(str.substring(0, 4), 10);
		int intMonth = Integer.parseInt(str.substring(4, 6), 10);
		int intDay = Integer.parseInt(str.substring(6, 8), 10);
		if (intMonth > 12 || intMonth < 1)
			return false;

		if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7
				|| intMonth == 8 || intMonth == 10 || intMonth == 12)
				&& (intDay > 31 || intDay < 1))
			return false;

		if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11)
				&& (intDay > 30 || intDay < 1))
			return false;

		if (intMonth == 2) {
			if (intDay < 1)
				return false;

			boolean boolLeapYear = false;
			if ((intYear % 100) == 0) {
				if ((intYear % 400) == 0)
					boolLeapYear = true;
			} else {
				if ((intYear % 4) == 0)
					boolLeapYear = true;
			}

			if (boolLeapYear) {
				if (intDay > 29)
					return false;
			} else {
				if (intDay > 28)
					return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * 检查email
	 * 
	 * @author
	 * @param str
	 * @return email返回true，否则返回false
	 */
	public static boolean checkEmail(String str) {
		return !isEmpty(str)
				&& str.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
	}

	/**
	 * 
	 * 检查手机号
	 * 
	 * @author
	 * @param str
	 * @return 手机号返回true，否则返回false
	 */
	public static boolean checkMobile(String str) {
		return !isEmpty(str) && str.matches("^(1[3-8][0-9])\\d{8}$");
	}

	/**
	 * 
	 * 检查交易密码
	 * 
	 * @author
	 * @param str
	 * @return 交易密码返回true，否则返回false
	 */
	public static boolean checkTradePassword(String str) {
		return !isEmpty(str) && str.matches("^[^\\s\\u4E00-\\u9FA5]{6,20}$");
	}

	/**
	 * 
	 * 检查姓名
	 * 
	 * @author
	 * @param str
	 * @return 符合返回true，否则返回false
	 */
	public static boolean checkName(String str) {
		return !isEmpty(str) && str.matches("^\\S{2,30}$");
	}

	/**
	 * 
	 * 检查验证码
	 * 
	 * @author
	 * @param str
	 * @return 符合返回true，否则返回false
	 */
	public static boolean checkVerifyCode(String str) {
		return !isEmpty(str) && str.matches("^[0-9]{1,4}$");
	}

	/**
	 * 
	 * 检查地址
	 * 
	 * @author
	 * @param str
	 * @return 符合返回true，否则返回false
	 */
	public static boolean checkAddress(String str) {
		return !isEmpty(str) && str.matches("^[\\S ]{5,30}$");
	}

	/**
	 * 
	 * 检查邮政编码
	 * 
	 * @author
	 * @param str
	 * @return 符合返回true，否则返回false
	 */
	public static boolean checkPostCode(String str) {
		return !isEmpty(str) && str.matches("^[0-9]{6}$");
	}

	/**
	 * 
	 * 检查银行卡
	 * 
	 * @author
	 * @param str
	 * @return 符合返回true，否则返回false
	 */
	public static boolean checkBankAcctNo(String str) {
		return !isEmpty(str) && str.matches("^[0-9]{8,20}$");
	}

	/**
	 * 
	 * 检查证件类型
	 * 
	 * @author
	 * @param str
	 * @return 符合返回true，否则返回false
	 */
	public static boolean checkCertType(String str) {
		return !isEmpty(str) && str.matches("^[0123589]$");
	}

	/**
	 * 
	 * 检查证件号码
	 * 
	 * @author
	 * @param str
	 * @return 符合返回true，否则返回false
	 */
	public static boolean checkCertNo(String str) {
		return !isEmpty(str) && str.matches("^\\S{3,20}$");
	}

	/**
	 * 功能：身份证的有效验证
	 * 
	 * @param IDStr
	 *            身份证号
	 * @return 有效：返回"" 无效：返回String信息
	 * @throws ParseException
	 */
	public static String checkIdcardNo(String IDStr) {
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		Hashtable<String, String> areaCode = new Hashtable<String, String>();
		areaCode.put("11", "北京");
		areaCode.put("12", "天津");
		areaCode.put("13", "河北");
		areaCode.put("14", "山西");
		areaCode.put("15", "内蒙古");
		areaCode.put("21", "辽宁");
		areaCode.put("22", "吉林");
		areaCode.put("23", "黑龙江");
		areaCode.put("31", "上海");
		areaCode.put("32", "江苏");
		areaCode.put("33", "浙江");
		areaCode.put("34", "安徽");
		areaCode.put("35", "福建");
		areaCode.put("36", "江西");
		areaCode.put("37", "山东");
		areaCode.put("41", "河南");
		areaCode.put("42", "湖北");
		areaCode.put("43", "湖南");
		areaCode.put("44", "广东");
		areaCode.put("45", "广西");
		areaCode.put("46", "海南");
		areaCode.put("50", "重庆");
		areaCode.put("51", "四川");
		areaCode.put("52", "贵州");
		areaCode.put("53", "云南");
		areaCode.put("54", "西藏");
		areaCode.put("61", "陕西");
		areaCode.put("62", "甘肃");
		areaCode.put("63", "青海");
		areaCode.put("64", "宁夏");
		areaCode.put("65", "新疆");
		areaCode.put("71", "台湾");
		areaCode.put("81", "香港");
		areaCode.put("82", "澳门");
		areaCode.put("91", "国外");

		String Ai = "";
		// ================ 号码的长度 15位或18位 ================
		if (IDStr.length() != 15 && IDStr.length() != 18) {
			errorInfo = "身份证号码长度应该为15位或18位。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (Ai.matches("^\\d$")) {
			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		String strBirthday = strYear + strMonth + strDay;
		if (checkBirthday(strBirthday) == false) {
			errorInfo = "身份证生日无效。";
			return errorInfo;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		if (areaCode.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return errorInfo;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (Ai.equalsIgnoreCase(IDStr) == false) {
				errorInfo = "身份证无效，不是合法的身份证号码";
				return errorInfo;
			}
		} else {
			return null;
		}
		// =====================(end)=====================
		return null;
	}

	/**
	 * 检查是否成年
	 * 
	 * @author
	 * @param birthday
	 * @return 未成年返回true，否则返回false
	 */
	@SuppressWarnings("deprecation")
	public static boolean checkMinor(String birthday) {
		int thisyear = (new Date()).getYear() + 1900;
		int age = 0;
		try {
			age = thisyear - Integer.parseInt(birthday.substring(0, 4));
		} catch (Exception ex) {
			age = 0;
		}

		if (age < 18)
			return true;

		return false;
	}

	/**
	 * 
	 * 检查证件有效期
	 * 
	 * @author
	 * @param str
	 * @return 有效true，无效返回false
	 */
	public static boolean checkCertExpire(String str) {
		if (!checkDate(str))
			return false;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		if (str.compareTo(today) <= 0) {
			return false;
		}

		return true;
	}
	
	/**
	 * 验证图片类型
	 * 
	 * @param imageFile
	 * @throws IOException
	 */
	public static String checkImageType(File imageFile) throws IOException {
		// get image format in a file
		File file = imageFile;

		// create an image input stream from the specified file
		ImageInputStream iis = ImageIO.createImageInputStream(file);

		// get all currently registered readers that recognize the image format
		Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);

		if (!iter.hasNext()) {
			throw new RuntimeException("No readers found!");
		}

		// get the first reader
		ImageReader reader = iter.next();

		String formatName = reader.getFormatName();

		// close stream
		iis.close();

		return formatName;
	}

}
