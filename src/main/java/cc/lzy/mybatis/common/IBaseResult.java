/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package cc.lzy.mybatis.common;

/**
 * 所有接口通用结果基类
 *
 * @author david.dw
 * @version $Id: IBaseResult.java, v 0.1 2019年04月26日 13:47 david.dw Exp $
 */
public interface IBaseResult {

    /**
     * Getter method for property <tt>success</tt>.
     *
     * @return property value of success
     */
    boolean isSuccess();

    /**
     * Setter method for property <tt>success</tt>.
     *
     * @param success  value to be assigned to property success
     */
    void setSuccess(boolean success);

    /**
     * Getter method for property <tt>resultCode</tt>.
     *
     * @return property value of resultCode
     */
    String getResultCode();

    /**
     * Setter method for property <tt>resultCode</tt>.
     *
     * @param resultCode  value to be assigned to property resultCode
     */
    void setResultCode(String resultCode);

    /**
     * Getter method for property <tt>resultMsg</tt>.
     *
     * @return property value of resultMsg
     */
    String getResultMsg();

    /**
     * Setter method for property <tt>resultMsg</tt>.
     *
     * @param resultMsg  value to be assigned to property resultMsg
     */
    void setResultMsg(String resultMsg);

}