/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis.plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Properties;

/**
 * 篡改参数插件
 * 
 * @author taigai
 * @version TamperingParamPlugin.java, v 0.1 2021年01月23日 19:22 taigai Exp $
 */
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "parameterize",
                args = Statement.class
        )
})
public class TamperingParamPlugin implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TamperingParamPlugin.class);

    /**
     * @see Interceptor#intercept(Invocation) 
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        LOGGER.info("TamperingParamPlugin#intercept， target: " + invocation.getTarget());
        // 获取属性值
        MetaObject metaObject = SystemMetaObject.forObject(invocation.getTarget());
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        LOGGER.info("parameter: " + value);
        // 修改属性值
        metaObject.setValue("parameterHandler.parameterObject", "000002");
        LOGGER.info("set value = 000002");
        return invocation.proceed();
    }

    /**
     * @see Interceptor#plugin(Object)
     */
    @Override
    public Object plugin(Object target) {
        // 创建代理对象
        Object wrap = Plugin.wrap(target, this);
        LOGGER.info("TamperingParamPlugin#plugin, targetClass={}, wrapClass={} ", target.getClass(), wrap.getClass());
        return wrap;
    }

    /**
     * @see Interceptor#setProperties(Properties) 
     */
    @Override
    public void setProperties(Properties properties) {
        // ignore
    }
}