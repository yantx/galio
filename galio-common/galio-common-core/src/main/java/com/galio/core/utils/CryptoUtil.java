package com.galio.core.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: galio
 * @Date: 2023-12-04 22:18:58
 * @Description: 非对称加密工具类
 */
public class CryptoUtil {

    /**
     * Base64编码
     */
    private static Base64.Encoder encoder = Base64.getEncoder();

    /**
     * Base64解码
     */
    private static Base64.Decoder decoder = Base64.getDecoder();

    public static final String CHARSET = "UTF-8";
    public static final int RSA_KEY_SIZE_512 = 512;
    public static final int RSA_KEY_SIZE_1024 = 1024;
    public static final int RSA_KEY_SIZE_2048 = 2048;
    public static final String RSA_ALGORITHM = "RSA"; // ALGORITHM 算法的意思
    public static final String RSA_PUBLIC_KEY = "publicKey";
    public static final String RSA_PRIVATE_KEY = "privateKey";

    public static Map<String, String> rsaGenerateKeyPair(int keySize) {
        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        // 初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        // 生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        // 得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        // 得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // map装载公钥和私钥
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put(RSA_PUBLIC_KEY, publicKeyStr);
        keyPairMap.put(RSA_PRIVATE_KEY, privateKeyStr);
        // 返回map
        return keyPairMap;
    }

    /**
     * 得到公钥
     *
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     *
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKeyString
     * @return
     */
    public static String rsaPublicEncrypt(String data, String publicKeyString) {
        try {
            RSAPublicKey publicKey = getPublicKey(publicKeyString);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     *
     * @param data
     * @param privateKeyString
     * @return
     */
    public static String rsaPrivateDecrypt(String data, String privateKeyString) {
        try {
            RSAPrivateKey privateKey = getPrivateKey(privateKeyString);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getDecoder().decode(data), privateKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("RSA解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     *
     * @param data
     * @param privateKeyString
     * @return
     */

    public static String rsaPrivateEncrypt(String data, String privateKeyString) {
        try {
            RSAPrivateKey privateKey = getPrivateKey(privateKeyString);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            //每个Cipher初始化方法使用一个模式参数opmod，并用此模式初始化Cipher对象。此外还有其他参数，包括密钥key、包含密钥的证书certificate、算法参数params和随机源random。
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.getEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     *
     * @param data
     * @param publicKeyString
     * @return
     */

    public static String rsaPublicDecrypt(String data, String publicKeyString) {
        try {
            RSAPublicKey publicKey = getPublicKey(publicKeyString);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getDecoder().decode(data), publicKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("RSA解密字符串[" + data + "]时遇到异常", e);
        }
    }

    //rsa切割解码  , ENCRYPT_MODE,加密数据   ,DECRYPT_MODE,解密数据
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;  //最大块
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    //可以调用以下的doFinal（）方法完成加密或解密数据：
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }


    // ----------------------- 对称加密 AES -----------------------
    /**
     * 加密算法
     */
    private static String ALGORITHM = "AES";
    /**
     * 偏移量
     *
     * 说明：偏移量字符串必须是16位 当模式是CBC的时候必须设置偏移量
     * 此处值与前端偏移量值保持一致
     */
    private static String IV = "ABCDEF1234123412";
    /**
     * 默认密码算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * AES加密
     *
     * @param key 加密的密钥
     * @param text 需要加密的字符串
     * @return 返回Base64转码后的加密数据
     */
    public static String aesEncrypt(String key, String text) {
        try {
            byte[] keyBytes = key.getBytes();
            SecretKey secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            byte[] cipherBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return byteToHexString(cipherBytes);
        } catch (Exception e) {
            throw new RuntimeException("AES加密字符串[" + text + "]时遇到异常", e);
        }
    }

    /**
     * AES解密
     * @param key 加密的密钥
     * @param text 已加密的密文
     * @return 返回解密后的数据
     */
    public static String aesDecrypt(String key, String text) {
        try {
            byte[] keyBytes = key.getBytes();
            SecretKey secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] hexBytes = hexStringToBytes(text);
            byte[] plainBytes = cipher.doFinal(hexBytes);
            return new String(plainBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("AES解密字符串[" + text + "]时遇到异常", e);
        }
    }

    /**
     * 生成加密秘钥
     * @param password 秘钥
     * @return SecretKeySpec
     * @throws NoSuchAlgorithmException
     */
    private static SecretKeySpec getSecretKey(final String password) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        kg.init(128, random);
        SecretKey secretKey = kg.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), "AES");
    }

    /**
     * 将byte数组转换为16进制字符串
     *
     * @param src
     * @return
     */
    private static String byteToHexString(byte[] src) {
        return Hex.encodeHexString(src);
    }

    /**
     * 将16进制字符串转换为byte数组
     *
     * @param hexString
     * @return
     */
    private static byte[] hexStringToBytes(String hexString) throws DecoderException {
        return Hex.decodeHex(hexString);
    }


}


