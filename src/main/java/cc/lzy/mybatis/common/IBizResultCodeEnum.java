/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis.common;

/**
 * 业务结果码枚举接口
 *
 * @author taigai
 * @version IBizResultCodeEnum.java, v 0.1 2021年01月10日 11:08 taigai Exp $
 */
public interface IBizResultCodeEnum {
    /**
     * 错误码
     * @return code
     */
    String getCode();

    /**
     * 错误描述
     * @return desc
     */
    String getDesc();

    /**
     * 错误等级
     *
     * @return errorLevel
     */
    BizErrorLevelEnum getErrorLevel();
}