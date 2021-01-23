/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis.plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

/**
 * Mybatis插件示例-打印日志的Plugin
 *
 * @author taigai
 * @version LogPlugin.java, v 0.1 2021年01月23日 19:22 taigai Exp $
 */
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "parameterize",
                args = Statement.class
        )
})
public class LogPlugin implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogPlugin.class);

    private Properties prop;

    /**
     * 真正做事情的地方，拦截原方法调用，进行一些自定义的业务处理逻辑
     *
     * @see Interceptor#intercept(Invocation)
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        LOGGER.info("LogPlugin#intercept, target: " + invocation.getTarget());
        LOGGER.info("LogPlugin#intercept, method: " + invocation.getMethod());
        LOGGER.info("LogPlugin#intercept, args: " + Arrays.toString(invocation.getArgs()));
        Object proceed = invocation.proceed();
        return proceed;
    }

    /**
     * 注入插件，通过此方法将插入注入Mybatis，Mybatis框架会自动执行注册的插件的plugin方法进行注入。
     * 注入的内部实现原理是为目标对象target创建一个代理对象并返回。
     * 后续方法执行时，会执行代理对象的方法，而代理对象又会调用 this.intercept 方法进行方法拦截，达到扩展的目的
     * Mybatis提供了创建代理的对象的Utils对象，方便的进行注入
     *
     * @see Interceptor#plugin(Object)
     */
    @Override
    public Object plugin(Object target) {
        // 创建代理对象
        Object wrap = Plugin.wrap(target, this);
        // 不能直接调用warp.toString进行比较，ToString会调用invocationHandler.invoke()方法
        // Plugin.invoke方法会直接代理给method.invoke(target, args)，实际调用的还是目标对象的ToString方法
        // Object的toString hashCode equals方法均会转发给invocationHandler，其他Object的方法不会
        LOGGER.info("LogPlugin#plugin, targetClass={}, wrapClass={} ", target.getClass(), wrap.getClass());
        return wrap;
    }

    /**
     * xml中配置的properties，会调用此方法进行设置
     *
     * @see Interceptor#setProperties(Properties)
     */
    @Override
    public void setProperties(Properties properties) {
        prop = properties;
        LOGGER.info("LogPlugin#setProperties: " + properties);
    }
}