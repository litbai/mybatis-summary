/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis.common;

/**
 * 业务动作
 *
 * @author taigai
 * @version BizAction.java, v 0.1 2021年01月10日 11:12 taigai Exp $
 */
public interface BizAction {
    /**
     * 前置处理
     */
    void before();

    /**
     * 执行业务处理
     */
    void execute();

    /**
     * 后置处理
     *
     * @param elapse 业务耗时
     */
    void after(long elapse);
}