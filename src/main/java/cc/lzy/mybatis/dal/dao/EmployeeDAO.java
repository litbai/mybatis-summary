/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package cc.lzy.mybatis.dal.dao;

import cc.lzy.mybatis.dal.entity.EmployeeDO;
import cc.lzy.mybatis.domain.model.Paginator;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.*;

/**
 * 员工DAO
 *
 * @author taigai
 * @version EmployeeDAO.java, v 0.1 2020年12月26日 13:00 taigai Exp $
 */
public interface EmployeeDAO {

    /**
     * 根据员工编号查询员工
     *
     * @param empNo 员工编号
     * @return 员工DO
     */
    EmployeeDO getByEmpNo(String empNo);

    /**
     * 根据name和level查询员工
     *
     * @param name  员工姓名
     * @param level 员工职级
     * @return 员工DO
     */
    EmployeeDO getByNameAndLevel(@Param("name") String name, @Param("level") String level);

    /**
     * 分页查询，结果存储于page
     *
     * @param level 职级
     * @param page  页面对象
     * @return 一页数据
     */
    List<EmployeeDO> listByPage(@Param("level") String level, @Param("page") Paginator page);

    /**
     * 根据入职日期查询员工
     *
     * @param hireDate 入职日期
     * @return 员工DO列表
     */
    List<EmployeeDO> listByHireDate(Date hireDate);

    /**
     * 新增员工
     *
     * @param employee 员工信息
     * @return 是否更新成功
     */
    Long insert(EmployeeDO employee);

    /**
     * 根据empNo更新员工
     *
     * @param updated 更新后的员工信息
     * @return 是否更新成功
     */
    int updateByEmpNo(EmployeeDO updated);

    /**
     * 根据empNo删除员工
     *
     * @param empNo 员工编号
     * @return 是否删除成功
     */
    boolean deleteByEmpNo(String empNo);

    /**
     * 查询某个level的员工数
     *
     * @param level 职级
     * @return 员工数
     */
    int countByLevel(String level);

    /**
     * 根据员工编号id批量查询
     *
     * @param empNos 员工编号列表
     * @return 员工列表
     */
    List<EmployeeDO> listByEmpNoList(List<String> empNos);

    /**
     * 根据员工编号id批量查询
     *
     * @param level  职级
     * @param empNos 员工编号列表
     * @return 员工列表
     */
    List<EmployeeDO> listByEmpNoListAndLevel(@Param("level") String level, @Param("empNos") List<String> empNos);

    /**
     * 根据员工编号id批量查询
     *
     * @param empNoSet 员工编号列表
     * @return 员工列表
     */
    List<EmployeeDO> listByEmpNoSet(Set<String> empNoSet);

    /**
     * 根据员工编号id批量查询
     *
     * @param empNoSet 员工编号列表
     * @return 员工列表
     */
    List<EmployeeDO> listByEmpNoArray(String[] empNoArray);

    /**
     * 根据员工编号id批量查询
     *
     * @param collection 员工编号列表
     * @return 员工列表
     */
    List<EmployeeDO> listByEmpNoCollection(Collection<String> collection);

    /**
     * 根据员工编号批量查询
     *
     * @param empNos 员工编号列表
     * @return 员工信息Map，key=empNo vlaue=EmployeeDO
     */
    @MapKey("empNo")
    Map<String, EmployeeDO> mapByEmpNoList(List<String> empNos);

    /**
     * 根据参数动态获取员工
     *
     * @param name  员工姓名
     * @param level 员工职级
     * @return 员工列表
     */
    List<EmployeeDO> where(@Param("name") String name, @Param("level") String level);

    /**
     * 根据参数动态获取员工
     *
     * @param name  员工姓名
     * @param level 员工职级
     * @param age   员工年龄
     * @return 员工列表
     */
    List<EmployeeDO> choose(@Param("name") String name, @Param("level") String level, @Param("age") Integer age);

    /**
     * 根据参数动态获取员工-trim
     *
     * @param name  员工姓名
     * @param level 员工职级
     * @param age   员工年龄
     * @return 员工列表
     */
    List<EmployeeDO> trim(@Param("name") String name, @Param("level") String level, @Param("age") Integer age);

    /**
     * 根据员工编号更新员工信息
     *
     * @param empNo   员工编号
     * @param updated 更新后的员工信息
     * @return 更新的记录条数
     */
    int set(@Param("empNo") String empNo, @Param("updated") EmployeeDO updated);

    /**
     * 批量保存
     *
     * @param employees 员工列表
     * @return 插入的记录条数
     */
    int saveEmployees(@Param("emps") List<EmployeeDO> employees);

    /**
     * 根据名称模糊查询
     *
     * @param fuzzyName 名称（模糊）
     * @return 员工列表
     */
    List<EmployeeDO> getByNameLike(@Param("fuzzyName") String fuzzyName);

    /**
     * 获取SqlSessionFactory
     *
     * @return sqlSessionFactory
     */
    SqlSessionFactory getSqlSessionFactory();
}