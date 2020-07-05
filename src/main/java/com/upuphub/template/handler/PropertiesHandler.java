package com.upuphub.template.handler;

import com.upuphub.template.config.BaseStatic;
import com.upuphub.template.util.encrypt.Decipherable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * @author 王志立
 * @Project: basic
 * @Package:cc.wangzl.handler
 * @date 2018/9/20 16:41
 * @description
 **/
public class PropertiesHandler extends PropertyPlaceholderConfigurer {
    private Decipherable decrypt;

    public PropertiesHandler(Decipherable decrypt) {
        this.decrypt = decrypt;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {

        for (Object key : props.keySet()) {
            String keys = key.toString();
            if (keys.contains(BaseStatic.KEY_PASSWORD) && decrypt != null) {
                //对密码进行解密
                String value = props.getProperty(keys);
                logger.info("解密= " + keys + "  " + value);
                props.setProperty(keys, decrypt.decrypt(value));
            }
        }
        logger.info(props);
        super.processProperties(beanFactoryToProcess, props);
    }

}
