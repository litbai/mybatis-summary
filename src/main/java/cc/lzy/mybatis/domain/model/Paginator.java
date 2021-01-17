/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis.domain.model;

import cc.lzy.mybatis.common.ToString;

import java.util.List;

/**
 * 分页器
 *
 * @author taigai
 * @version Paginator.java, v 0.1 2021年01月04日 17:48 taigai Exp $
 */
public class Paginator<T> extends ToString {

    /** 页码 */
    private int     pageNum;
    /** 页面大小 */
    private int     pageSize;
    /** 偏移量 */
    private int     offset;
    /** 总数量 */
    private int     total;
    /** item列表 */
    private List<T> items;

    /**
     * 根据pageNum和pageIndex构造分页器
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     */
    public Paginator(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.offset = (pageNum - 1) * pageSize;
    }

    /**
     * 构造分页器
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @param total    总数量
     */
    public Paginator(int pageNum, int pageSize, int total) {
        this(pageNum, pageSize);
        this.total = total;
    }

    /**
     * Getter method for property <tt>pageNum</tt>.
     *
     * @return property value of pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Setter method for property <tt>pageNum</tt>.
     *
     * @param pageNum value to be assigned to property pageNum
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * Getter method for property <tt>pageSize</tt>.
     *
     * @return property value of pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Setter method for property <tt>pageSize</tt>.
     *
     * @param pageSize value to be assigned to property pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Getter method for property <tt>offset</tt>.
     *
     * @return property value of offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Setter method for property <tt>offset</tt>.
     *
     * @param offset value to be assigned to property offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Getter method for property <tt>total</tt>.
     *
     * @return property value of total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Setter method for property <tt>total</tt>.
     *
     * @param total value to be assigned to property total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Getter method for property <tt>items</tt>.
     *
     * @return property value of items
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * Setter method for property <tt>items</tt>.
     *
     * @param items value to be assigned to property items
     */
    public void setItems(List<T> items) {
        this.items = items;
    }
}