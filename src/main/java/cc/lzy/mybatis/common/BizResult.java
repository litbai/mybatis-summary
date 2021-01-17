/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis.common;

/**
 * @author taigai
 * @version BizResult.java, v 0.1 2021年01月10日 11:53 taigai Exp $
 */
public class BizResult extends BaseResult{

    private Object data;

    /**
     * 无参构造函数
     */
    public BizResult() {}

    /**
     * 构造函数
     *
     * @param data 数据
     */
    public BizResult(Object data) {
        this.data = data;
    }

    /**
     * Getter method for property <tt>data</tt>.
     *
     * @return property value of data
     */
    public Object getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     *
     * @param data value to be assigned to property data
     */
    public void setData(Object data) {
        this.data = data;
    }
}