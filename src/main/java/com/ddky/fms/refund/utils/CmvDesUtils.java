package com.ddky.fms.refund.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;


/**
 * cmv DES加密解密工具类
 * @author a
 */
public class CmvDesUtils {

	private final static String DES_KEY="cf0=5^%Njd3984KJJHf$";

    /*public static void main(String[] args) throws Exception{
        String str = "wanghaikuo";
        System.out.println(str);
        String enStr = encrypt(str);
        System.out.println(enStr);
        String deStr = decrypt(enStr);
        System.out.println(deStr);
    }*/

    /**
     * 对字符串进行加密，并转换为base64格式
     * @param data data
     * @return String
     * @throws Exception Exception
     */
    public static String encrypt(String data)  {
        try {
            byte[] bt = encrypt(data.getBytes(), DES_KEY.getBytes());
            return new Base64().encodeAsString(bt);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将base64编码格式的字符串解密
     * @param data data
     * @return String
     * @throws Exception Exception
     */
    public static String decrypt(String data) {
        try {
            if (data == null) {
                return null;
            }
            Base64 decoder = new Base64();
            byte[] buf = decoder.decode(data);
            byte[] bt = decrypt(buf,DES_KEY.getBytes());
            return new String(bt);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 根据键值进行加密
     * @param data data
     * @param key key
     * @return byte
     * @throws Exception Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secureKey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secureKey, sr);
        return cipher.doFinal(data);
    }


    /**
     * 根据键值进行加密
     * @param data data
     * @param key key
     * @return byte
     * @throws Exception Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secureKey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secureKey, sr);
        return cipher.doFinal(data);
    }
    
}
