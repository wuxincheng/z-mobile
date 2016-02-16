package lihu.zlm.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

public class RedirectUtil {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map<String, Object> beanToMap(Object model) {
        try {
            Map map = PropertyUtils.describe(model);
            map.remove("class");
            return map;
        } catch (Exception e) {
            return null;
        }
    }

    public static String redirectModel(Object model) {
        return redirectModel("", model);
    }

    @SuppressWarnings("unchecked")
    public static String redirectModel(String url, Object model) {
        Map<String, Object> map = null;
        if (model instanceof Map) {
            map = (Map<String, Object>) model;
        } else {
            map = beanToMap(model);
        }

        return redirectModelMap(url, map);
    }

    public static String redirectModelMap(Map<String, Object> model) {
        return redirectModelMap("", model);
    }

    public static String redirectModelMap(String url, Map<String, Object> model) {
        try {
            StringBuilder sb = new StringBuilder(url);
            appendQueryProperties(sb, model, "UTF-8");

            // BASE64Encoder encoder = new BASE64Encoder();
            // return url + "?m=" +
            // encoder.encode(sb.toString().getBytes("UTF-8"));
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected static void appendQueryProperties(StringBuilder targetUrl, Map<String, Object> model,
            String encodingScheme) throws UnsupportedEncodingException {

        // Extract anchor fragment, if any.
        String fragment = null;
        int anchorIndex = targetUrl.indexOf("#");
        if (anchorIndex > -1) {
            fragment = targetUrl.substring(anchorIndex);
            targetUrl.delete(anchorIndex, targetUrl.length());
        }

        // If there aren't already some parameters, we need a "?".
        boolean first = (targetUrl.toString().indexOf('?') < 0);
        for (Map.Entry<String, Object> entry : queryProperties(model).entrySet()) {
            Object rawValue = entry.getValue();
            Iterator<Object> valueIter;
            if (rawValue != null && rawValue.getClass().isArray()) {
                valueIter = Arrays.asList(ObjectUtils.toObjectArray(rawValue)).iterator();
            } else if (rawValue instanceof Collection) {
                valueIter = ((Collection) rawValue).iterator();
            } else {
                valueIter = Collections.singleton(rawValue).iterator();
            }
            while (valueIter.hasNext()) {
                Object value = valueIter.next();
                if (first) {
                    targetUrl.append('?');
                    first = false;
                } else {
                    targetUrl.append('&');
                }
                String encodedKey = urlEncode(entry.getKey(), encodingScheme);
                String encodedValue = (value != null ? urlEncode(value.toString(), encodingScheme)
                        : "");
                targetUrl.append(encodedKey).append('=').append(encodedValue);
            }
        }

        // Append anchor fragment, if any, to end of URL.
        if (fragment != null) {
            targetUrl.append(fragment);
        }
    }

    protected static Map<String, Object> queryProperties(Map<String, Object> model) {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            if (isEligibleProperty(entry.getKey(), entry.getValue())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    protected static boolean isEligibleProperty(String key, Object value) {
        if (value == null) {
            return false;
        }
        if (isEligibleValue(value)) {
            return true;
        }
        if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            if (length == 0) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                Object element = Array.get(value, i);
                if (!isEligibleValue(element)) {
                    return false;
                }
            }
            return true;
        }
        if (value instanceof Collection) {
            Collection coll = (Collection) value;
            if (coll.isEmpty()) {
                return false;
            }
            for (Object element : coll) {
                if (!isEligibleValue(element)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    protected static boolean isEligibleValue(Object value) {
        return (value != null && BeanUtils.isSimpleValueType(value.getClass()));
    }

    protected static String urlEncode(String input, String encodingScheme)
            throws UnsupportedEncodingException {
        return (input != null ? URLEncoder.encode(input, encodingScheme) : null);
    }

}
