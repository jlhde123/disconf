package com.baidu.disconf.client;

import com.baidu.disconf.client.addons.properties.ReloadableProperties;
import com.baidu.disconf.client.addons.properties.ReloadablePropertiesFactoryBean;
import com.baidu.disconf.client.scan.ScanMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

/**
 * 适配springboot 模式的disconf 管理类
 * @author mymx.jlh
 * @version 1.0.0 2018/5/6 13:24
 */
public class DisconfSpringBootMgr {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisconfSpringBootMgr.class);

    // 本实例不能初始化两次
    private boolean isFirstInit = false;
    private ScanMgr firstScanMgr;
    private boolean isSecondInit = false;
    //spring 配置环境
    private ConfigurableEnvironment environment;
    //配置文件下载
    private static ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean;

    public DisconfSpringBootMgr(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    //disconf初始化 先下载配置文件 第一步
    public synchronized void firstStep(List<String> files) {

        reloadablePropertiesFactoryBean = new ReloadablePropertiesFactoryBean(environment);
        reloadablePropertiesFactoryBean.downloadFiles(files);
    }
}
