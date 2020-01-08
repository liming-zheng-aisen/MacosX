package com.macos.framework.starter;

import com.macos.framework.annotation.MacosXApplicationStarter;
import com.macos.framework.core.bean.manage.BeanManager;
import com.macos.framework.core.listener.manager.MacosXListerManager;
import com.macos.start.web.jetty.listener.JettyServerStarterListener;
import com.macos.start.web.jetty.filter.init.FilterRegisterServer;
import com.macos.start.web.jetty.servlet.ServletBeanInitManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Properties;
import java.util.Set;

/**
 * @Desc DyBootStarterWeb
 * @Author Zheng.LiMing
 * @Date 2019/9/4
 */
@MacosXApplicationStarter(scannerPath = {},order =Integer.MAX_VALUE)
public class JettyWebStarter implements DefaultStarter {

    private final static Logger log= LoggerFactory.getLogger(JettyWebStarter.class);

    @Override
    public void doStart(Properties evn, Class cl) throws Exception {

        Set<Class> classSet = BeanManager.getClassContainer();

        FilterRegisterServer filterRegisterServer=new FilterRegisterServer();
        filterRegisterServer.autoRegisterFilter(classSet);

        ServletBeanInitManager servletBeanInitManager= ServletBeanInitManager.Builder.getServletBeanInitManager();
        servletBeanInitManager.init(classSet);

        JettyServerStarterListener serverStarterListener= new JettyServerStarterListener();
        log.debug("注册TomcatStarterListener监听器");
        MacosXListerManager.registerLister(serverStarterListener);

    }

}
