/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package cc.lzy.mybatis.dal.dao.impl;

import cc.lzy.mybatis.dal.dao.EmployeeDAO;
import cc.lzy.mybatis.dal.entity.EmployeeDO;
import cc.lzy.mybatis.domain.model.Paginator;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 员工DAO实现
 * <p>
 * Mybatis 查询步骤
 * 1. 根据XML或接口方式创建SqlSessionFactory
 * 2. openSession, sqlSession可以认为就是一个connection，非线程安全，每次都要openSession
 * 3. 执行语句
 * 4. 关闭session
 *
 * @author taigai
 * @version EmployeeDAOImpl.java, v 0.1 2020年12月26日 13:01 taigai Exp $
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDAOImpl.class);

    /** sqlSessionFactory */
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 无参构造函数
     */
    public EmployeeDAOImpl() {
        initSqlSessionFactory();
    }

    /**
     * @see EmployeeDAO#getByEmpNo(String)
     */
    @Override
    public EmployeeDO getByEmpNo(String empNo) {
//        return getByEmpNoOldWay(empNo);
        return getByEmpNoNewWay(empNo);
    }

    /**
     * @see EmployeeDAO#getByNameAndLevel(String, String)
     */
    @Override
    public EmployeeDO getByNameAndLevel(String name, String level) {
        // 获取session
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.getByNameAndLevel(name, level);
        }
    }

    /**
     * @see EmployeeDAO#listByPage(String, Paginator)
     */
    @Override
    public List<EmployeeDO> listByPage(String level, Paginator page) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);

            int total = employeeMapper.countByLevel(level);
            page.setTotal(total);

            List<EmployeeDO> employeeDOS = employeeMapper.listByPage(level, page);
            page.setItems(employeeDOS);

            return employeeDOS;
        }
    }

    @Override
    public List<EmployeeDO> listByHireDate(Date hireDate) {
        // 获取session
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.listByHireDate(hireDate);
        }
    }

    /**
     * @see EmployeeDAO#insert(EmployeeDO)
     */
    @Override
    public Long insert(EmployeeDO employee) {
        // 获取session, 不带参数的openSession()不会自动提交事务，所以需要手动提交
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            employeeMapper.insert(employee);
            // 手动提交事务
            session.commit();

            return employee.getId();
        }
    }

    /**
     * @see EmployeeDAO#updateByEmpNo(EmployeeDO)
     */
    @Override
    public int updateByEmpNo(EmployeeDO updated) {
        // 获取session, openSession(true) 自动提交事务
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.updateByEmpNo(updated);
        }
    }

    /**
     * @see EmployeeDAO#deleteByEmpNo(String)
     */
    @Override
    public boolean deleteByEmpNo(String empNo) {
        // 获取session, openSession(true) 自动提交事务
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.deleteByEmpNo(empNo);
        }
    }

    /**
     * @see EmployeeDAO#countByLevel(String)
     */
    @Override
    public int countByLevel(String level) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.countByLevel(level);
        }
    }

    /**
     * @see EmployeeDAO#listByEmpNoList(List)
     */
    @Override
    public List<EmployeeDO> listByEmpNoList(List<String> empNos) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.listByEmpNoList(empNos);
        }
    }

    /**
     * @see EmployeeDAO#listByEmpNoListAndLevel(String, List)
     */
    @Override
    public List<EmployeeDO> listByEmpNoListAndLevel(String level, List<String> empNos) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.listByEmpNoListAndLevel(level, empNos);
        }
    }

    /**
     * @see EmployeeDAO#listByEmpNoSet(Set)
     */
    @Override
    public List<EmployeeDO> listByEmpNoSet(Set<String> empNoSet) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.listByEmpNoSet(empNoSet);
        }
    }

    /**
     * @see EmployeeDAO#listByEmpNoArray(String[])
     */
    @Override
    public List<EmployeeDO> listByEmpNoArray(String[] empNoArray) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.listByEmpNoArray(empNoArray);
        }
    }

    /**
     * @see EmployeeDAO#listByEmpNoCollection(Collection)
     */
    @Override
    public List<EmployeeDO> listByEmpNoCollection(Collection<String> collection) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.listByEmpNoCollection(collection);
        }
    }

    /**
     * @see EmployeeDAO#mapByEmpNoList(List)
     */
    @Override
    public Map<String, EmployeeDO> mapByEmpNoList(List<String> empNos) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.mapByEmpNoList(empNos);
        }
    }

    /**
     * @see EmployeeDAO#where(String, String)
     */
    @Override
    public List<EmployeeDO> where(String name, String level) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.where(name, level);
        }
    }

    /**
     * @see EmployeeDAO#choose(String, String, Integer)
     */
    @Override
    public List<EmployeeDO> choose(String name, String level, Integer age) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.choose(name, level, age);
        }
    }

    /**
     * @see EmployeeDAO#trim(String, String, Integer)
     */
    @Override
    public List<EmployeeDO> trim(String name, String level, Integer age) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.trim(name, level, age);
        }
    }

    /**
     * @see EmployeeDAO#set(String, EmployeeDO)
     */
    @Override
    public int set(String empNo, EmployeeDO updated) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.set(empNo, updated);
        }
    }

    /**
     * @see EmployeeDAO#saveEmployees(List)
     */
    @Override
    public int saveEmployees(List<EmployeeDO> employees) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.saveEmployees(employees);
        }
    }

    /**
     * @see EmployeeDAO#getByNameLike(String)
     */
    @Override
    public List<EmployeeDO> getByNameLike(String fuzzyName) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            return employeeMapper.getByNameLike(fuzzyName);
        }
    }

    /**
     * @see EmployeeDAO#getSqlSessionFactory()
     */
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    /**
     * 新方式执行sql，定义一个接口，限定入参和出参，将xml中的一条语句映射为接口的一个方法
     * 接口与配置文件动态绑定：namespace -> 接口全限定名  id -> 接口方法
     */
    private EmployeeDO getByEmpNoNewWay(String empNo) {
        // 获取session
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 执行语句 - 获取Mapper， 执行方法
            // 此处EmployeeDAO只是一个接口，我们并没有些实现类，但是却可以直接调用方法
            // 这就是Mybatis的黑科技，底层就是利用动态代理为我们自动创建了一个Dao实现类
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            // org.apache.ibatis.binding.MapperProxy@xxxxxx
            // System.out.println(employeeDAO.toString());
            return employeeMapper.getByEmpNo(empNo);
        }
    }

    /**
     * 老方式执行sql，出参是泛型，入参是Object，无法利用编译时检查功能
     * <p>
     * While this approach works, and is familiar to users of previous versions of MyBatis,
     * there is now a cleaner approach. Using an interface (e.g. BlogMapper.class) that properly
     * describes the parameter and return value for a given statement,
     * you can now execute cleaner and more type safe code,
     * without error prone string literals and casting.
     */
    private EmployeeDO getByEmpNoOldWay(String empNo) {
        // 获取session
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 执行语句
            return session.selectOne("getByEmpNo", empNo);
        }
    }

    /**
     * 构建sqlSessionFactory
     */
    private void initSqlSessionFactory() {
        if (sqlSessionFactory != null) {
            return;
        }

        synchronized (this) {
            if (sqlSessionFactory != null) {
                return;
            }
            String resource = "config/mybatis-config.xml";
            try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
                this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("init sqlSessionFactory fail");
            }
        }
    }
}