package common;


import cc.wangzl.config.BaseStatic;
import cc.wangzl.util.encrypt.AESEncryptUtil;
import cc.wangzl.util.encrypt.Md5EncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 王志立
 * @Project: wblog
 * @Package:test
 * @date 2018/9/20 16:30
 * @description 数据的加密
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-encrypt.xml")
public class EncrypTest {
    @Autowired
    private AESEncryptUtil aesEncryptUtil;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void md5() {
        String result = Md5EncryptUtil.encrypt("123456");
        log.info("MD5密文：" + result);
        log.info("MD5验证：" + Md5EncryptUtil.comparePass("123456", result));
    }

    @Test
    public void testEncrypt() {
        String result = aesEncryptUtil.encrypt("Wang97");
        log.info("加密结果：" + result);
        result = aesEncryptUtil.decrypt(result);
        log.info("解密结果：" + result);
    }
}
