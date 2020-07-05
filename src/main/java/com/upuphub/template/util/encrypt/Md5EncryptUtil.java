package com.upuphub.template.util.encrypt;


import com.upuphub.template.config.BaseStatic;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author 王志立
 * @create: 2018/8/15
 * @description: 基于Spring的MD5加密
 */

@SuppressWarnings({"ALL", "AlibabaClassNamingShouldBeCamel"})
public class Md5EncryptUtil {


    /**
     * 明文信息进行128位MD5加密，不加盐的MD5不安全
     * @param value 加密的参数
     * @return 返回32位小写字母的md5码
     */
    @Deprecated
    public static String parseMd5(String value) {
        return parseMd5(value, "");
    }

    /**
     * 明文信息进行128位MD5加密
     * @param salt 盐
     * @return 返回32位小写字母的md5码
     */

   public static String parseMd5(String value, String salt) {
        //确定计算方法
        try {
            value += salt;
            return DigestUtils.md5DigestAsHex(value.getBytes(BaseStatic.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String value) {
        try {
            value += BaseStatic.MD5_SALT;
            return DigestUtils.md5DigestAsHex(value.getBytes(BaseStatic.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能的描述: 将明文(密码)信息加盐后进行128位MD5加密,返回两个值：加密结果和盐
     *
     * @param password 需要加密的明文信息
     * @return 第一位返回32位小写字母的md5码, 第二位盐
     */
    public static String[] parseMd5WithSalt(String password) {
        String[] result = new String[2];
        String salt = getRandomString(8);
        result[0] = parseMd5(password, salt);
        result[1] = salt;
        return result;
    }

    /**
     * 将明文密码+盐进行Md5运算后，与在数据库中保存的md5值进行比较
     * @param password  明文密码
     * @param salt 盐
     * @param md5Pass 加密后的md5密码
     * @return 返回比较结果
     */
    public static boolean comparePass(String password, String salt, String md5Pass) {
        String md5 = parseMd5(password, salt);
        return md5 != null && md5.equals(md5Pass);
    }

    /**
     * 将明文密码+盐进行Md5运算后，与在数据库中保存的md5值进行比较
     * @param password  明文密码
     * @param md5Password 加密后的md5密码
     * @return 返回比较结果
     */
    public static boolean comparePass(String password, String md5Password) {
        String md5 = parseMd5(password, BaseStatic.MD5_SALT);
        return md5 != null && md5.equals(md5Password);
    }

    /**
     * 生成指定长度的随机字符串
     * @author: 王志立
     * @param length 随机字符串长度
     * @return 乱序后的字符串
     * @create: 2018年12月20日
     */
    private static String getRandomString(int length) {
        char[] base = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length);
            sb.append(base[number]);
        }
        return sb.toString();
    }
}