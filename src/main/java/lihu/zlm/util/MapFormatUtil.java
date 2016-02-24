package lihu.zlm.util;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Map工具类
 * 
 * @author wuxincheng(wxcking)
 * 
 * @Date 2016年2月24日 下午5:18:07
 * 
 */
public class MapFormatUtil {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

	private static SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat dtFormat = new SimpleDateFormat();

	private static DecimalFormat df = new DecimalFormat("#,##0");

	private static DecimalFormat df1 = new DecimalFormat();

	public static String getLimitRecord(String sql, int page, int pagesize) {
		if (page <= 1)
			page = 1;
		if (pagesize <= 0)
			pagesize = 0;
		int beginrow = (page - 1) * pagesize + 1;
		int endrow = beginrow + pagesize;
		sql = "select ttt.* from (select tt.*,rownum as numrow from(" + sql
				+ ") tt ) ttt where numrow>=" + beginrow + " and numrow<" + endrow;

		return sql;
	}

	public static String getFormatDate(long time) {
		return dateFormat.format(new Date(time));
	}

	public static String getFormatDateTime(long time) {
		return dateFormat1.format(new Date(time));
	}

	public static String getFormatDate(long time, String format) {
		synchronized (dtFormat) {
			dtFormat.applyPattern(format);
			return dtFormat.format(new Date(time));
		}
	}

	public static String getFormatDecimal(double db) {
		return df.format(db);
	}

	public static String getFormatDecimal(double db, String format) {
		synchronized (df1) {
			df1.applyPattern(format);
			return df1.format(db);
		}
	}

	public static String getString(Map<String, Object> map, String key) {
		return getString(map, key, true);
	}

	public static String getString(Map<String, Object> map, String key, boolean checkNull) {
		if (map == null) {
			if (checkNull) {
				return "";
			} else {
				return null;
			}
		}
		Object value = map.get(key);
		if (value == null) {
			value = map.get(key.toUpperCase());
		}
		if (value == null) {
			if (checkNull) {
				return "";
			} else {
				return null;
			}
		}

		String s = null;
		if (value instanceof String[]) {
			String[] s1 = (String[]) value;
			if (s1 != null && s1.length >= 1) {
				s = s1[0];
			}
		} else {
			s = (String) value;
		}

		if (checkNull) {
			if (s == null)
				s = "";
		}
		// if (s!=null&&s.indexOf("%")>=0){
		// s = AjaxUtils.unescape(s);
		// }
		return s;
	}

	public static double getDouble(Map<String, Object> map, String key) {
		if (map == null)
			return 0D;
		Object o = map.get(key);
		if (o == null) {
			return 0.0D;
		}
		if (o instanceof Double) {
			return ((Double) o).doubleValue();
		}
		if (o instanceof BigDecimal) {
			return ((BigDecimal) o).doubleValue();
		}
		return 0.0D;
	}

	public static int getInt(Map<String, Object> map, String key) {
		Object o = map.get(key);
		if (o == null) {
			return 0;
		}
		if (o instanceof Integer) {
			return ((Integer) o).intValue();
		}
		if (o instanceof BigDecimal) {
			return ((BigDecimal) o).intValue();
		}
		return 0;
	}

	public static int getRealInt(Map<String, Object> map, String key) {
		Integer db = (Integer) map.get(key);
		if (db == null)
			return 0;
		return db.intValue();
	}

	public static long getLong(Map<String, Object> map, String key) {
		Object o = map.get(key);
		if (o == null) {
			return 0;
		}
		if (o instanceof Long) {
			return ((Long) o).longValue();
		}
		if (o instanceof BigDecimal) {
			return ((BigDecimal) o).longValue();
		}
		return 0;
	}

	public static long getRealLong(Map<String, Object> map, String key) {
		Long db = (Long) map.get(key);
		if (db == null)
			return 0;
		return db.longValue();
	}

	public static int getPageCount(int totalitem, int pagesize) {
		int totalpage = 0;
		if ((totalitem % pagesize) == 0) {
			totalpage = totalitem / pagesize;
		} else {
			totalpage = totalitem / pagesize + 1;
		}
		return totalpage;
	}

	public static Timestamp getTimestamp(Map<String, Object> map, String key) {
		Object o = map.get(key);
		if (o == null)
			return null;

		return (Timestamp) o;
	}

	public static String[] getSplitString(String str, String split) {
		if (str == null | split == null)
			return null;
		StringTokenizer st = new StringTokenizer(str, split);
		List<String> list = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}

		if (list.size() == 0)
			return null;

		String[] arr = new String[list.size()];

		return (String[]) list.toArray(arr);

	}

	public static String getMD5(String s, String s1) {
		byte abyte0[] = null;
		byte abyte1[] = null;
		if (s == null)
			return null;
		abyte0 = s.getBytes();
		if (s1 != null)
			abyte1 = s1.getBytes();
		String s2 = "";
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			messagedigest.update(abyte0);
			if (s1 != null)
				messagedigest.update(abyte1);
			s2 = toHex(messagedigest.digest());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return s2;
	}

	private static String toHex(byte abyte0[]) {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < abyte0.length; i++) {
			String s = Integer.toHexString(abyte0[i] & 0xff);
			if (s.length() < 2) {
				stringbuffer.append("0");
			}
			stringbuffer.append(s);
		}

		return stringbuffer.toString();
	}

	public static Map<String, Object> cloneMap(Map<String, Object> m) {
		if (m == null)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> it = m.keySet().iterator();
		String key = null;
		while (it.hasNext()) {
			key = it.next();
			map.put(key, m.get(key));
		}
		return map;

	}

	public static String getBirthdayFormIdcard(String idcard) {
		if (idcard == null)
			return null;
		if (idcard.length() != 15 && idcard.length() != 18)
			return null;
		try {
			String s = "";
			if (idcard.length() == 15) {
				s = "19" + idcard.substring(6, 12);
			} else {
				s = idcard.substring(6, 14);
			}
			return s;
		} catch (Exception ex) {
			return null;
		}
	}

}
