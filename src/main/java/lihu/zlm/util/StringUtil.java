package lihu.zlm.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class StringUtil {

	/**
	 * 截取指定长度的字符串, 如果长度不够, 直接返回
	 * 
	 * @param string
	 * @param len
	 * @return
	 */
	public static String subString(String string, Integer len) {
		string = StringUtil.trimNull(string); // 去空
        int lenStr = string.length(); // 实际字符串的长度
        if (lenStr < len) {
            return string;
        }

        return string.substring(0, len);
    }
	
	/**
     * 去掉字符串前后的空格，如果参数为null，则返回空字符串
     * 
     * @param str
     * @return
     */
    public static String trimNull(String str) {
        String result = "";
        if (str != null) {
            result = str.trim();
        }

        return result;
    }
	
	public static Map<String, String> parseNameValuePair(String str, String split, String split2) {
        Map<String, String> map = new HashMap<String, String>();
        String temp, key, value;
        StringTokenizer stk = new StringTokenizer(str, split);
        while (stk.hasMoreTokens()) {
            temp = stk.nextToken();
            key = temp.substring(0, temp.indexOf(split2));
            value = temp.substring(temp.indexOf(split2) + 1);
            map.put(key, value);
        }
        return map;
    }

    public static Map<String, String> parseNameValuePair(String str, String split) {
        return parseNameValuePair(str, split, "=");
    }

    public static Map<String, String> parseNameValuePair(String str) {
        return parseNameValuePair(str, "\\|", "=");
    }

    public static String map2Str(Map<String, Object> map) {
        if (map == null) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        Iterator<Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            Object value = entry.getValue();
            if (value == null) {
                value = "";
            }
            sb.append(entry.getKey() + "=" + value).append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 
     * 对Map进行排序后，再将Map数据转换为HTTP报文体形式的明文字符串
     * 
     */
    public static String map2SortedStr(Map<String, Object> map) {
        Comparator<String> c = new Comparator<String>() {

            @Override
            public int compare(String a, String b) {
                if (a == null && b == null) {
                    return 0;
                }
                if (a == null && b != null) {
                    return -1;
                }
                if (a != null && b == null) {
                    return 1;
                }

                return a.compareTo(b);
            }
        };

        TreeMap<String, Object> tmp = new TreeMap<String, Object>(c);
        tmp.putAll(map);
        return map2Str(tmp);
    }

    public static String escapeRegex(String pattern) {
        String[] escapeChars = new String[] { "\\", "^", "$", ".", "?", "+", "*", "[", "]", "|",
                "-", "(", ")", ":", "!", "&", "," };
        for (String c : escapeChars) {
            pattern = pattern.replace(c, "\\" + c);
        }
        return pattern;
    }

    public static List<String> readLine(String line, String linePattern) {
        Assert.hasText(linePattern);

        Pattern pattern = Pattern.compile(linePattern);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            return null;
        }

        List<String> values = new ArrayList<String>();
        for (int i = 1; i <= matcher.groupCount(); i++) {
            values.add(matcher.group(i));
        }

        return values;
    }

    public static Map<String, String> readLinePlaceHolder(String lineText, String linePattern,
            String itemPattern) {
        Assert.hasText(linePattern);

        if (StringUtils.isEmpty(lineText)) {
            return null;
        }

        // 从linePattern解析变量名称
        linePattern = escapeRegex(linePattern);
        List<String> names = new ArrayList<String>();
        Pattern pattern = Pattern.compile(itemPattern);
        Matcher matcher = pattern.matcher(linePattern);
        while (matcher.find()) {
            names.add(matcher.group(1));
        }
        if (names.size() > 0) {
            String patternTextEscaped = escapeRegex("(.+?)");
            linePattern = "^" + matcher.replaceAll(patternTextEscaped) + "$";
        }

        // 从lineText解析变量值
        List<String> values = readLine(lineText, linePattern);
        if (values == null) {
            return null;
        }

        if (names.size() != values.size()) {
            return null;
        }

        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < names.size(); i++) {
            map.put(names.get(i), values.get(i));
        }

        return map;
    }

    public static String toString(Object o) {
        return ToStringBuilder.reflectionToString(o);
    }

    public static String toStringSimple(Object o) {
        return ToStringBuilder.reflectionToString(o, ToStringStyle.SIMPLE_STYLE);
    }

    public static String toStringShort(Object o) {
        return ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static String toStringMultiLine(Object o) {
        return ToStringBuilder.reflectionToString(o, ToStringStyle.MULTI_LINE_STYLE);
    }

}
