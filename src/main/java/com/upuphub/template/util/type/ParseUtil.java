package com.upuphub.template.util.type;

import com.upuphub.template.config.BaseStatic;

/**
* @program: ParseUtil
* @description: 类型转换工具类
* @author: 王志立
* @create: 2018/12/14 16:07
**/
public class ParseUtil {

    public static Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String parseString(Integer value) {
        try {
            return String.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String parseString(Object value) {
        try {
            return String.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer parseInt(Object value) {
        if (value instanceof String)
        {return parseInt(((String) value));}
        else if (value instanceof Integer)
        { return (Integer) value;}
        return null;
    }

    public static Boolean parseBoolean(Object value) {
        if (value instanceof String)
        {return parseBoolean(((String) value));}
        else if (value instanceof Boolean)
        {return (Boolean) value;}
        return null;
    }

    public static Boolean parseBoolean(String value) {
        if (BaseStatic.KEY_BOOLEN_TRUE.equals(value))
        {return Boolean.TRUE;}
        else if (BaseStatic.KEY_BOOLEN_FALSE.equals(value))
        {return Boolean.FALSE;}
        return null;
    }
}
