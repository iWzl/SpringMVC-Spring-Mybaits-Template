package com.upuphub.template.util.type;

/**
 * @author 王志立
 * @Project: basic
 * @Package:cc.wangzl.util.type
 * @date 2018/9/18 17:08
 * @description
 **/
public class StringUtil {

      public static boolean isNotEmpty(String str) {
          return str != null && str.length() != 0;
      }


        public static boolean isEmpty(String str) {
            return str == null || str.length() == 0;
        }
    }

