/**
 * 
 */
package com.xqs.mybaby.utils;


import java.io.IOException;



public class Md5Encrypt {

	private static Md5Encrypt instance;

	private Md5Encrypt() {
	}

	public static Md5Encrypt getInstance() {
		if (instance == null) {
			instance = new Md5Encrypt();
		}
		return instance;
	}

	/*
	 * encrypt是类MD5最主要的公共方法，入口参数是你想要进行MD5变换的字符串
	 */
	public String encrypt(String inbuf) {
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(inbuf.getBytes("UTF-8"));
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;

	}


	/**
	 *
	 * @param postBody 明文
	 * @param key 秘钥
	 * @return
	 * @throws IOException
     */
	public String getMd5(String postBody,String key) throws IOException {
		String rs = encrypt(postBody + key);

		return rs;
	}
}
