package com.upuphub.template.util.encrypt;


import com.upuphub.template.config.BaseStatic;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @description: AES加密工具
 * @author: 王志立
 * @create: : 2017年11月7日 上午10:11:02
 */

@SuppressWarnings({"ALL", "AlibabaClassNamingShouldBeCamel"})
public class AESEncryptUtil implements Decipherable {

    /**
     * 实例化密钥
     * 密钥算法
     * 加密-解密算法 / 工作模式 / 填充方式
     *  LOG4J打印控制
     */
    private Key keyInstance;
    private static String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private Logger logger = LoggerFactory.getLogger(getClass());

    public AESEncryptUtil(String key) {
        //通过传入的明文key生成keyInstance密钥
        try {
            logger.info("明文秘钥: " + key);
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            generator.init(128, random);
            keyInstance = generator.generateKey();
            logger.debug("秘钥: " + keyInstance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 二进制转换成16进制
     * @param buf 二进制数组
     * @return 16进制字符串
     */
    private static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 16进制转换为二进制
     * @param hexStr 16进制字符串
     * @return 二进制数组
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0;
                 i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    @Override
    public String encrypt(@NotNull String encryptText) {
        logger.debug("明文: "+encryptText);
        try {
            byte[] bytes = encryptText.getBytes(BaseStatic.CHARSET);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keyInstance);
            byte[] doFinal = cipher.doFinal(bytes);
            return parseByte2HexStr(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String decrypt(@NotNull String decryptText) {
        logger.debug("密文: "+decryptText);
        try {
            byte[] bytes = parseHexStr2Byte(decryptText.trim());
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keyInstance);
            assert bytes != null;
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(doFinal, BaseStatic.CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}