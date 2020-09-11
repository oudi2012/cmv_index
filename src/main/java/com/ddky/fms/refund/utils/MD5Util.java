package com.ddky.fms.refund.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * ClassName MD5Util </br>
 * 2016年8月3日 上午11:17:26 </br>
 * @author zhoudeming </br>
 * @version 1.0.0
 */
public class MD5Util {
	
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
	
	/**
	 * 按照UTF-8编码对字符串进行加密，如果字符串不是UTF-8编码会抛出异常
	 * @param s
	 * @return
	 */
	public static String getMD5String(String s) {
		try {
			return getMD5String(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("MD5加密失败，获取字符串UTF-8字节数组异常：" + ex.getLocalizedMessage());
		}
	}

	/**
	 * Returns a MessageDigest for the given <code>algorithm</code>.
	 *
	 * @return An MD5 digest instance.
	 * @throws RuntimeException
	 *             when a {@link java.security.NoSuchAlgorithmException} is
	 *             caught
	 */

	static MessageDigest getDigest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按照UTF-8编码对字符串进行加密，如果字符串不是UTF-8编码会抛出异常
	 * @param bytes
	 * @return
	 */
	public static String getMD5String(byte[] bytes) {
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			messagedigest.update(bytes);
			return bufferToHex(messagedigest.digest());
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。" + ex.getLocalizedMessage());
		}
	}
	
	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 32 character hex
	 * string.
	 *
	 * @param data
	 *            Data to digest
	 * @return MD5 digest as a hex string
	 */
	public static String md5Hex(byte[] data) {
		return HexUtil.toHexString(md5(data));
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 32 character hex
	 * string.
	 *
	 * @param data
	 *            Data to digest
	 * @return MD5 digest as a hex string
	 */
	public static String md5Hex(String data) {
		return HexUtil.toHexString(md5(data));
	}

	public static String md5Hex2(String data){
		String rst = "";
		if(StringUtils.isNotEmpty(data)){
			rst = md5Hex(data);
		}
		return rst;
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 16 element
	 * <code>byte[]</code>.
	 *
	 * @param data
	 *            Data to digest
	 * @return MD5 digest
	 */
	public static byte[] md5(byte[] data) {
		return getDigest().digest(data);
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 16 element
	 * <code>byte[]</code>.
	 *
	 * @param data
	 *            Data to digest
	 * @return MD5 digest
	 */
	public static byte[] md5(String data) {
		try {
			return md5(data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return md5(data.getBytes());
	}
	
}
