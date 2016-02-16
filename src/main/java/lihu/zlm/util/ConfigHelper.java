package lihu.zlm.util;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigHelper {
	private static final Log logger = LogFactory.getLog(ConfigHelper.class);
	private static ConfigHelper config;

	private Properties p;

	private Map<String, Properties> configs = new Hashtable<String, Properties>();

	private ConfigHelper() {
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			p = new Properties();
			p.load(in);
			in.close();
			logger.info(p);
		} catch (Exception ex) {

		}
	}

	public static ConfigHelper getInstance() {
		if (config == null) {
			config = new ConfigHelper();
		}
		return config;
	}

	public String getConfig(String key) {
		return getGbk(p.getProperty(key));
	}

	public String getConfig(String key, String filename) {
		try {
			Properties p1 = (Properties) configs.get(filename);
			if (p1 == null) {
				InputStream in = null;
				try {
					in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
					logger.info("成功加载" + filename);
					p1 = new Properties();
					p1.load(in);
					configs.put(filename, p1);
				} catch (Exception ex) {
					logger.warn("", ex);
				} finally {
					if (in != null)
						try {
							in.close();
						} catch (Exception ex) {
						}
				}
				configs.put(filename, p1);
			}
			return getGbk(p1.getProperty(key));
		} catch (Exception ex) {
			logger.warn("", ex);
			return null;
		}

	}

	public Properties getProperties(String filename) {
		if (filename == null)
			return p;
		return (Properties) configs.get(filename);
	}

	private String getGbk(String msg) {
		if (msg == null)
			return null;
		try {
			return new String(msg.getBytes("ISO8859_1"), "GBK");
		} catch (Exception ex) {
			logger.warn("", ex);
			return msg;
		}
	}

	public static void main(String args[]) {
		System.out.println(ConfigHelper.getInstance().getConfig("mail.host"));
	}

}
