package com.upuphub.template.util.encrypt;

/**
 * 可解密接口
 * @author 王志立
 */

public interface Decipherable {

    /**
     * AES对称解密字符串
     * @param decryptText 需要解密的字符串
     * @return 解密后的字符串
     * @author: 王志立
     * @create: : 2018年12月20日 9:37
     */
    String decrypt(String decryptText);

    /**
     * AES对称加密字符串，并通过Jdk自带Base64转换为ASCII
     * @param encryptText 需要加密的字符串
     * @return 加密后的字符串
     * @author: 王志立
     * @create: : 2018年12月20日 9:37
     */
    String encrypt(String encryptText);
}
