/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package cc.lzy.mybatis.biz.service;


import cc.lzy.mybatis.domain.model.Employee;
import cc.lzy.mybatis.domain.model.Paginator;

import java.util.*;

/**
 * 员工服务
 *
 * @author taigai
 * @version EmployeeService.java, v 0.1 2020年12月26日 11:44 taigai Exp $
 */
public interface EmployeeService {

    /**
     * 根据员工编号查询员工
     *
     * @param empNo 员工编号
     * @return 员工信息
     */
    Employee getByEmpNo(String empNo);

    /**
     * 根据name和level查询员工
     *
     * @param name  员工姓名
     * @param level 员工职级
     * @return 员工信息
     */
    Employee getByNameAndLevel(String name, String level);

    /**
     * 分页查询
     *
     * @param page 分页入参
     * @return 一页数据
     */
    void listByPage(String level, Paginator page);

    /**
     * 根据入职日期查询员工列表
     *
     * @param hireDate 入职日期
     * @return 员工列表
     */
    List<Employee> getByHireDate(Date hireDate);

    /**
     * 新增员工
     *
     * @param employee 员工信息
     * @return 是否更新成功
     */
    Long addEmployee(Employee employee);

    /**
     * 根据empNo更新员工
     *
     * @param empNo   员工编号
     * @param updated 更新后的员工
     * @return 更新的记录条数
     */
    int updateByEmpNo(String empNo, Employee updated);

    /**
     * 根据empNo删除员工
     *
     * @param empNo 员工编号
     * @return 是否删除成功
     */
    boolean deleteByEmpNo(String empNo);

    /**
     * 根据员工编号批量查询
     *
     * @param level  职级，如果为空则不限制
     * @param empNos 员工编号列表
     * @return 员工列表
     */
    List<Employee> listByEmpNos(String level, List<String> empNos);

    /**
     * 根据员工编号批量查询
     *
     * @param empNos 员工编号集合
     * @return 员工列表
     */
    List<Employee> listByEmpNoSet(Set<String> empNos);

    /**
     * 根据员工编号id批量查询
     *
     * @param empNoArray 员工编号数组
     * @return 员工列表
     */
    List<Employee> listByEmpNoArray(String[] empNoArray);

    /**
     * 根据员工编号id批量查询
     *
     * @param collection 员工编号列表
     * @return 员工列表
     */
    List<Employee> listByEmpNoCollection(Collection<String> collection);

    /**
     * 根据员工编号列表查询员工信息
     *
     * @param empNos 员工编号列表
     * @return 员工Map，key=empNo value=Employee
     */
    Map<String, Employee> mapByEpNos(List<String> empNos);

}