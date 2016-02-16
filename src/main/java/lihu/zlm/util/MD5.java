package lihu.zlm.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * MD5加密
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月28日 下午7:24:07 
 *
 */
public class MD5 {

	/**
	 * MD5加密方法
	 * 
	 * @param data
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static byte[] encryptMD5(byte[] data) throws GeneralSecurityException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(data);
		return md.digest();
	}
	
	/**
	 * 后台系统使用的加密方式
	 * 
	 * @param password 密码明文
	 * @return
	 */
	public static String encryptMD5Pwd(String password){
		if (null == password) {
			return null;
		}
		
		String enMD5PwdStr = null;
		try {
			String str = ByteUtil.bytesToHex(MD5.encryptMD5(password.getBytes()));
			String rightSub = str.substring(0, 10);
			String centerSub = str.substring(10, 20);
			String leftSub = str.substring(20, 30);
			enMD5PwdStr = leftSub + rightSub + centerSub;
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
		return enMD5PwdStr;
	}
	
	public static void main(String[] args) {
		System.out.println(encryptMD5Pwd("AAaa1234"));
	}
	
}
