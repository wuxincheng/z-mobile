package lihu.zlm.util;

/**
 * Byte工具类
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月28日 下午7:24:30 
 *
 */
public class ByteUtil {
	static public byte[] hexToBytes(String hex) {
		if (hex.length() % 2 != 0)
			hex = "0" + hex;
		byte[] hexBytes = hex.toUpperCase().getBytes();
		byte[] bcdBytes = new byte[hex.length() / 2];
		for (int i = 0, t = 0; i < hexBytes.length; i += 2, t++) {
			int b = (hexBytes[i] > 0x39 ? hexBytes[i] - 0x37
					: hexBytes[i] - 0x30) * 0x10;
			b += hexBytes[i + 1] > 0x39 ? hexBytes[i + 1] - 0x37
					: hexBytes[i + 1] - 0x30;
			bcdBytes[t] = (byte) b;
		}
		return bcdBytes;
	}

	static public String bytesToHex(byte[] bytes) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int a = (bytes[i] & 0xFF) / 0x10;
			int b = (bytes[i] & 0xFF) % 0x10;
			s.append(new String(new byte[] {
					(byte) (a > 0x9 ? a + 0x37 : a + 0x30),
					(byte) (b > 0x9 ? b + 0x37 : b + 0x30) }));
		}
		return s.toString();
	}

	static public String bytesToHexLower(byte[] bytes) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int a = (bytes[i] & 0xFF) / 0x10;
			int b = (bytes[i] & 0xFF) % 0x10;
			s.append(new String(new byte[] {
					(byte) (a > 0x9 ? a + 0x57 : a + 0x30),
					(byte) (b > 0x9 ? b + 0x57 : b + 0x30) }));
		}
		return s.toString();
	}
	
	static public String[] bytesToHexArray(byte[] bytes) {
		String[] s = new String[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			int a = (bytes[i] & 0xFF) / 0x10;
			int b = (bytes[i] & 0xFF) % 0x10;
			s[i] = new String(new byte[] {
					(byte) (a > 0x9 ? a + 0x37 : a + 0x30),
					(byte) (b > 0x9 ? b + 0x37 : b + 0x30) });
		}
		return s;
	}
	
	static public String[] bytesToHexArrayLower(byte[] bytes) {
		String[] s = new String[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			int a = (bytes[i] & 0xFF) / 0x10;
			int b = (bytes[i] & 0xFF) % 0x10;
			s[i] = new String(new byte[] {
					(byte) (a > 0x9 ? a + 0x57 : a + 0x30),
					(byte) (b > 0x9 ? b + 0x57 : b + 0x30) });
		}
		return s;
	}

	static public byte[] binToBytes(String bin) {
		while (bin.length() % 8 != 0)
			bin = "0" + bin;

		byte[] bytes = new byte[bin.length() / 8];
		for (int i = 0, t = 0; i < bin.length(); i += 8, t++) {
			String s = bin.substring(i, i + 8);
			bytes[t] = (byte) Integer.parseInt(s, 2);
		}
		return bytes;
	}

	static public String bytesToBin(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes)
			sb.append(Integer.toBinaryString(b));
		return sb.toString();
	}

	static public String[] bytesToBinArray(byte[] bytes) {
		String[] s = new String[bytes.length];
		for (int i = 0; i < bytes.length; i++)
			s[i] = Integer.toBinaryString(bytes[i]);
		return s;
	}

	static public boolean equals(byte[] bytes1, byte[] bytes2) {
		if (bytes1 == null || bytes2 == null || bytes1.length != bytes2.length)
			return false;
		for (int i = 0; i < bytes1.length; i++)
			if (bytes1[i] != bytes2[i])
				return false;
		return true;
	}
	
}
