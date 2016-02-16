package lihu.zlm.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class HttpRequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static String getCookieAttribute(HttpServletRequest request, String key) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies == null || cookies.length == 0)
                return null;
            Cookie cookie;
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
            return null;
        } catch (Exception ex) {
            System.out.println("读取cookie错误" + ex.getMessage());
            return null;
        }
    }

    public static void setCookieAttribute(HttpServletResponse response, String key, String value,
            int maxAge) {
        try {

            Cookie cookie = new Cookie(key, value);
            if (maxAge > 0)
                cookie.setMaxAge(maxAge);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception ex) {
            System.out.println("写cookie错误" + ex.getMessage());
        }
    }

    public static void setCookieAttribute(HttpServletResponse response, String key, String value) {
        setCookieAttribute(response, key, value, 1728000);
    }

    public static Map<String, Object> getRequestParameters(HttpServletRequest request)
            throws Exception {
        Enumeration<String> parameterNames = request.getParameterNames();
        if (parameterNames == null) {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] parameterValues = request.getParameterValues(paramName);
            if (parameterValues != null && parameterValues.length == 1) {
                map.put(paramName, parameterValues[0]);
            } else {
                map.put(paramName, parameterValues);
            }
        }
        return map;
    }

    public static String getContextUrl(HttpServletRequest request) {
        String contextUrl = request.getScheme().toLowerCase() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        return contextUrl;
    }

    public static String getRequestFullUrl(HttpServletRequest request) {
        if (!StringUtils.isEmpty(request.getQueryString()))
            return request.getRequestURL() + "?" + request.getQueryString();

        return request.getRequestURL().toString();
    }

    public static boolean isAjax(HttpServletRequest request) {
        String header1 = request.getHeader("X-Requested-With");
        // String header2 = request.getHeader("accept");

        // if (header1 != null && "XMLHttpRequest".equalsIgnoreCase(header1) &&
        // header2.indexOf("application/json") > -1) {
        if (header1 != null && "XMLHttpRequest".equalsIgnoreCase(header1)) {
            return true;
        }

        return false;
    }

    public static boolean isGet(HttpServletRequest request) {
        return "GET".equalsIgnoreCase(request.getMethod());
    }

    public static boolean isPost(HttpServletRequest request) {
        return "POST".equalsIgnoreCase(request.getMethod());
    }

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static boolean isClientLocalhost(HttpServletRequest request) {
        String ip = getClientIpAddr(request);
        if ("127.0.0.1".equals(ip))
            return true;

        if ("localhost".equals(ip))
            return true;

        return false;
    }

    public static void writeData(HttpServletResponse httpResponse, String data, String charset)
            throws Exception {
        logger.info("输出Http报文数据:\r\n[{}]", data);
        int length = 0;
        if (charset == null) {
            length = data.getBytes().length;
        } else {
            length = data.getBytes(charset).length;
        }
        httpResponse.setContentType("text/xml; charset=" + charset);
        httpResponse.setContentLength(length);
        PrintWriter out = httpResponse.getWriter();
        out.write(data);
        out.flush();
        out.close();
    }

    public static String readData(HttpServletRequest httpRequest, String charset) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpRequest.getInputStream(), charset));
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line).append("\n");
        }
        String data = buffer.toString();
        logger.info("读取Http报文数据:\r\n[{}]", data);
        return data;
    }
}
