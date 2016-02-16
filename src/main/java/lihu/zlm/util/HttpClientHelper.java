package lihu.zlm.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

/**
 * HTTP发送帮助类
 */
public class HttpClientHelper {
    private static final Logger logger = Logger.getLogger(HttpClientHelper.class);
    private String contentCharSet = "utf-8";

    private int timeout = 60; // 秒

    private String proxyAddress;
    private int proxyPort;
    private String proxyUser;
    private String proxyPwd;
    
    public static String doPost(String url, Map<String, Object> data) {
		BufferedInputStream in = null;
		OutputStream out = null;
		HttpURLConnection connection = null;
		String requestStr = JsonHelper.generate(data);
		try {
			URL realUrl = new URL(url);
			connection = (HttpURLConnection)realUrl.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			connection.connect();
			out = connection.getOutputStream();
			out.write(requestStr.getBytes("UTF-8"));
			out.flush();
			out.close();
			in = new BufferedInputStream(connection.getInputStream());
			byte[] bt = new byte[1024];
			StringBuffer sb = new StringBuffer();
			if(in.read(bt) != -1) {
				sb.append(new String(bt, 0, bt.length, "UTF-8"));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != in) {
				try {
					in.close();
				} catch (Exception e2) {
					in = null;				
				}
			}
			connection.disconnect();
		}
		return "";
	}

    /**
     * 取得页面内容
     * 
     * @param url
     * @return
     * @throws IOException
     * @throws Exception
     */
    public String getData(String url) throws IOException, Exception {
        if (url == null || url.equals(""))
            throw new IOException("不存在的URL地址");
        GetMethod get = null;
        try {
            get = new GetMethod(url);
            get.getParams().setContentCharset(contentCharSet);
            // get.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)");
            get.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36");
            HttpClient httpClient = getHttpClient();
            int i = httpClient.executeMethod(get);
            String result = get.getResponseBodyAsString();
            if (logger.isDebugEnabled()) {
                logger.debug("getData() - 向地址" + url + "请求数据，" + ",返回码:" + i);
            }
            if (i >= 400)
                throw new IOException("返回错误代码:" + i);
            return result;
        } catch (ConnectTimeoutException cte) {
            throw new Exception("操作超时");
        } catch (IOException ioe) {
            logger.warn("", ioe);
            throw ioe;
        } catch (Exception ex) {
            logger.warn("", ex);
            throw ex;
        } finally {
            if (get != null)
                get.releaseConnection();
        }
    }

    /**
     * 传递流数据（数据需要自己encoder）
     * 
     * @param url
     * @param content
     *            （encoder后的数据）
     * @return
     * @throws IOException
     * @throws Exception
     */
    public String sendStreamData(String url, String content) throws IOException, Exception {
        if (url == null || url.equals(""))
            throw new IOException("不存在的URL地址");
        PostMethod post = null;
        try {
            post = getPostMethod(url);
            @SuppressWarnings("deprecation")
            StringRequestEntity sr = new StringRequestEntity(content);
            post.setRequestEntity(sr);
            HttpClient httpClient = getHttpClient();
            if (logger.isDebugEnabled()) {
                logger.debug("sendStreamData() - 向地址" + url + "post数据：长度：" + content.getBytes().length + "[" + content + "]");
            }
            int i = httpClient.executeMethod(post);
            String result = post.getResponseBodyAsString();
            if (logger.isDebugEnabled()) {
                logger.debug("sendStreamData() - 返回码:" + i + " 结果:" + result + "[" + content + "]");
            }
            if (i >= 400)
                throw new IOException("返回错误代码:" + i);
            return result;
        } catch (ConnectTimeoutException cte) {
            throw new Exception("操作超时");
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (post != null)
                post.releaseConnection();
        }
    }

    /**
     * 传递数据（数据需要自己encoder）
     * 
     * @param url
     * @param content
     *            （encoder后的数据）
     * @return
     * @throws IOException
     * @throws Exception
     */
    public String sendData(String url, String content) throws IOException, Exception {
        if (url == null || url.equals(""))
            throw new IOException("不存在的URL地址");
        PostMethod post = null;
        try {
            post = getPostMethod(url);

            HttpClient httpClient = getHttpClient();

            if (logger.isDebugEnabled()) {
                logger.debug("sendData() - 向地址" + url + "post数据：长度：" + content.getBytes().length + "[" + content + "]");
            }
            int i = httpClient.executeMethod(post);
            String result = post.getResponseBodyAsString();
            if (logger.isDebugEnabled()) {
                logger.debug("sendData() - 返回码:" + i + " 结果:" + result + "[" + content + "]");
            }
            if (i >= 400)
                throw new IOException("返回错误代码:" + i);
            return result;
        } catch (ConnectTimeoutException cte) {
            throw new Exception("操作超时");
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (post != null)
                post.releaseConnection();
        }
    }

    /**
     * 传递数据（数据无需encoder）
     * 
     * @param url
     * @param data
     * @return
     * @throws IOException
     * @throws Exception
     */
    public String sendData(String url, Map<String, Object> data) throws IOException, Exception {
        if (data == null || data.size() == 0)
            return null;
        if (url == null || url.equals(""))
            throw new IOException("不存在的URL地址");
        if (logger.isDebugEnabled()) {
            logger.debug("sendData() -准备向" + url + "发送数据 " + data);
        }
        PostMethod post = null;
        try {
            post = getPostMethod(url);
            @SuppressWarnings("rawtypes")
            Iterator it = data.keySet().iterator();
            String key = null;
            String value = null;
            while (it.hasNext()) {
                key = (String) it.next();
                value = (String) data.get(key);
                value = value == null ? "" : value.trim();
                post.addParameter(key, value);
            }
            HttpClient httpClient = getHttpClient();
            int i = httpClient.executeMethod(post);
            String result = convertStreamToString(post.getResponseBodyAsStream());
            if (logger.isDebugEnabled()) {
                logger.debug("sendData() - 向地址" + url + " 发送数据" + ",返回码:" + i + " 结果:" + result);
            }
            if (i >= 400)
                throw new IOException("返回错误代码:" + i);
            return result;
        } catch (ConnectTimeoutException cte) {
            throw new Exception("操作超时");
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (post != null)
                post.releaseConnection();
        }
    }

    private String convertStreamToString(InputStream is) throws IOException {
        byte[] b = new byte[1024];
        int n = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        try {
            while ((n = is.read(b)) > 0) {
                out.write(b, 0, n);
            }
            byte[] resp = out.toByteArray();
            return new String(resp, "GBK");
        } finally {
            is.close();
            out.close();
        }
    }

    private HttpClient getHttpClient() {
        HttpClient httpClient = new HttpClient();
        if (proxyAddress != null && proxyAddress.length() > 0) {
            httpClient.getHostConfiguration().setProxy(proxyAddress, proxyPort);
            if (proxyUser != null) {
                httpClient.getState().setProxyCredentials(new AuthScope(proxyAddress, proxyPort, null), new UsernamePasswordCredentials(proxyUser, proxyPwd));
            }
        }
        // 添加超时设置
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout * 1000);

        return httpClient;
    }

    private PostMethod getPostMethod(String url) {
        PostMethod post = new PostMethod(url);
        post.getParams().setContentCharset(contentCharSet);
        post.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)");
        return post;
    }

    public void setTimeout(int i) {
        timeout = i;
    }

    public void setProxy(String proxyAddress, int proxyPort, String proxyUser, String proxyPwd) {
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
        this.proxyUser = proxyUser;
        this.proxyPwd = proxyPwd;
    }

    public void setContentCharSet(String string) {
        contentCharSet = string;
    }
    
}
