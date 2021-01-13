package com.wyy.javademo.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

/*
    需要加依赖包

      <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcprov-jdk15on</artifactId>
            <version>1.67</version>
        </dependency>
 */
public class AesUtil {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String KEY = "ed2a9e4fd1bccc7f";
    private static final String CHARSET_NAME = "UTF-8";
    private static final String AES_NAME = "AES";
    // 加密模式
    public static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    public static final String IV = "ed2a9e4fd1bccc7f";

    //充电桩二维码解密
    public static String decrypt( String content) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            return new String(cipher.doFinal(Base64.decodeBase64(content)), CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("decrypt error",e);
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
