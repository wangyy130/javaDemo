package com.wyy.javademo.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

/**
 * @ClassName AESUtils
 * @Description: AES对称加密和解密，有偏移量
 * @Author zh
 * @Date 2019/7/24 10:21
 * @Version V1.0
 * 配合 jdk 1.8.0 151以后版本 配置密钥无限限制 Security.setProperty("crypto.policy", "unlimited");
 *
 **/
@SuppressWarnings("restriction")
public class DosBalAESUtils {

    /**
     * 密钥
     */
    private static final String KEY = "ed2a9e4fd1bccc7f";


    /**
     * 算法
     */
    private static final String ALGORITHMSTR = "AES/CBC/pkcs7padding";

    /**
     * 初始化中执行解除密钥长度限制
     * jdk 1.8.0 151 之后可以这么干
     */
    static {
        Security.setProperty("crypto.policy", "unlimited");
    }

    /**
     * aes解密
     * @param encrypt   内容
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String encrypt) throws Exception {
        return aesDecrypt(encrypt, KEY);
    }

    /**
     * aes加密
     * @param content
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content) throws Exception {
        return aesEncrypt(content, KEY);
    }

    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : Base64.decodeBase64(base64Code);
    }

    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    private static final String CHARSET_NAME = "UTF-8";
    private static final String AES_NAME = "AES";
    // 加密模式
    public static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    public static final String IV = "ed2a9e4fd1bccc7f";

    public static String decrypt( String content) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            return new String(cipher.doFinal(Base64.decodeBase64(content)), CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public static void main(String[] args) throws Exception {
        String params = "vSi9WbP9mwYcyKnSVjfTF47OukBDNVxmaY+3R6qHLWn6OVd0xKF8FUSSt7DNE9S3ea03xWRHx3k2Rz9tvSyA1TPBVkgQsK63Oy0OL4P/TfU=";
        System.out.println(decrypt(params));

        String str = "W512031001vSi9WbP9mwYcyKnSVjfTF47OukBDNVxmaY+3R6qHLWn6OVd0xKF8FUSSt7DNE9S3ea03xWRHx3k2Rz9tvSyA1TPBVkgQsK63Oy0OL4P/TfU=";
        System.out.println(str.substring(0,7));
        System.out.println(str.substring(7,10));
        System.out.println(str.substring(10));
        System.out.println("==============   "+ decrypt(str.substring(10)));
        String string = decrypt(params);
        System.out.println(string.substring(3,13));
    }
}
