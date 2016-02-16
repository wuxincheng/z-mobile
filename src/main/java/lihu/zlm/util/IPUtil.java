package lihu.zlm.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * IP工具类
 * 
 * @author wuxincheng
 *
 */
public class IPUtil {
	
	/**
	 * 根据IP获取地域
	 * 
	 * @param IP
	 * @return
	 */
	public static Map<String, String> getAddressByIp(String IP) {
		Map<String, String> ipInfo = new HashMap<String, String>();
		try {
			String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + IP);
			System.out.println("ip.taobao接收到的数据: " + str);

			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(str);
			
			String code = rootNode.path("code").asText();
			
			if ("0".equals(code)) {
				String data = rootNode.path("data").toString();
				
				System.out.println("解析数据 data : " + data);
				
				ObjectMapper mapper2 = new ObjectMapper();
				JsonNode dataNode = mapper2.readTree(data);
				
				String country = dataNode.path("country").asText();
				String city = dataNode.path("city").asText();
				String region = dataNode.path("region").asText();
				
				String address = country + region + city;
				
				ipInfo.put("address", address);
				ipInfo.put("country", country);
				ipInfo.put("region", region);
				ipInfo.put("city", city);
			} else {
				System.out.println("查询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询IP地址出现异常");
		} finally {
			
		}
		
		return ipInfo;
	}

	private static String getJsonContent(String urlStr) {
		try {// 获取HttpURLConnection连接对象
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(3000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				return ConvertStream2Json(httpConn.getInputStream());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String ConvertStream2Json(InputStream inputStream) {
		String jsonStr = "";
		// ByteArrayOutputStream相当于内存输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		try {
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
			}
			// 将内存流转换为字符串
			jsonStr = new String(out.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

}
