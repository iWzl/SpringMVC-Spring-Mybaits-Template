package com.upuphub.template.config;

/**
 * @program: RegexStatic
 * @description: 正则表达式规则
 * @author: 王志立
 * @create: 2018/12/14 15:26
 **/
public class RegexStatic {
    public static final String REG_NUMBER = "^[0-9]*$";
    public static final String REG_NUMBER_DECIMAL = "^[0-9]+(\\.[0-9]{2})?$";
    public static final String REG_ENGLISG_NUMBER = "^[A-Za-z0-9]+$ 或 ^[A-Za-z0-9]{4,40}$";
    public static final String REG_ID_CARD = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
    public static final String REG_CHINESE = "[\\u4e00-\\u9fa5]+";
    public static final String REG_PHONE = "^[1][3,4,5,7,8][0-9]{9}$";
    public static final String REG_NUMBER_TIME = "[1-9]+[0-9]{5,16}";
    public static final String REG_NUMBE = "[1-9]+[0-9]{5,7}";
    public static final String REG_EX = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
    public static final String REG_URL_SUFFIX = ".+\\..+$";
    public static final String REG_EMAIL = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
}
