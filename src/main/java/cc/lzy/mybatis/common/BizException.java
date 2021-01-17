/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package cc.lzy.mybatis.common;

/**
 * 生活号业务异常
 *
 * @notice 一般情况不需要新增异常，否则顶层业务模版无法感知
 *
 * @author wufeng
 * @version $Id: BizException.java, v 0.1 2019年04月18日 17:23 wufeng Exp $
 */
public class BizException extends RuntimeException {

    /** 错误码 */
    private IBizResultCodeEnum resultCode;

    /** 外部用户展示文案 */
    private String resultView;

    /**
     * 非运行时异常
     *
     * @param resultCode 错误码
     */
    public BizException(IBizResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 2C的非运行时异常
     *
     * @param resultCode 错误码
     */
    public BizException(IBizResultCodeEnum resultCode, String resultView) {
        this.resultCode = resultCode;
        this.resultView = resultView;
    }

    /**
     * 生活通用异常
     *
     * @param resultCode    错误码
     * @param cause             异常堆栈
     */
    public BizException(IBizResultCodeEnum resultCode, Throwable cause) {
        super(resultCode.getDesc(), cause);
        this.resultCode = resultCode;
    }

    /**
     * 生活号2C异常
     *
     * @param resultCode    错误码
     * @param resultView    C端文案
     * @param cause             异常堆栈
     */
    public BizException(IBizResultCodeEnum resultCode, String resultView, Throwable cause) {
        super(resultCode.getDesc(), cause);
        this.resultCode = resultCode;
        this.resultView = resultView;
    }

    /**
     * Getter method for property <tt>resultCode</tt>.
     *
     * @return property value of resultCode
     */
    public IBizResultCodeEnum getResultCode() {
        return resultCode;
    }

    /**
     * Setter method for property <tt>resultCode</tt>.
     *
     * @param resultCode  value to be assigned to property resultCode
     */
    public void setResultCode(IBizResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Getter method for property <tt>resultView</tt>.
     *
     * @return property value of resultView
     */
    public String getResultView() {
        return resultView;
    }

    /**
     * Setter method for property <tt>resultView</tt>.
     *
     * @param resultView  value to be assigned to property resultView
     */
    public void setResultView(String resultView) {
        this.resultView = resultView;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "(" + resultCode.getCode() + "," + resultCode.getDesc() + "," + resultView + ")";
    }
}