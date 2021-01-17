/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis;

import cc.lzy.mybatis.dal.dao.EmployeeDAO;
import cc.lzy.mybatis.dal.dao.impl.EmployeeDAOImpl;
import cc.lzy.mybatis.dal.entity.EmployeeDO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * @author taigai
 * @version CacheTest.java, v 0.1 2021年01月10日 18:49 taigai Exp $
 */
public class CacheTest {
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseOpTest.class);

    /** employeeDAO */
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    /**
     * 执行日志，总共执行了3次相同的sql，只向DB发起了一次sql命令，后续2次均命中缓存，返回的为同一个对象
     * 注意：因为默认开启了一级缓存，没法关闭，且Mybatis并没有做缓存对象复制，所以对于查询返回的DO，不要作改动，否则会影响后续相同的sql查询。
     *
     * 19:18:07.985 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Opening JDBC Connection
     * 19:18:07.985 [main] DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Checked out connection 1636050357 from pool.
     * 19:18:07.988 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - ==>  Preparing: select id, emp_no, name, age, position, level, address, email, hire_date from Employee where emp_no = ?
     * 19:18:08.016 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - ==> Parameters: 000001(String)
     * 19:18:08.037 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - <==      Total: 1
     * 19:18:08.038 [main] INFO  cc.lzy.mybatis.BaseOpTest - emp == cache1: true
     * 19:18:08.038 [main] INFO  cc.lzy.mybatis.BaseOpTest - cache1 == cache2: true
     * 19:18:08.038 [main] INFO  cc.lzy.mybatis.BaseOpTest - testCache:363023858:363023858:363023858
     * 19:18:08.048 [main] INFO  cc.lzy.mybatis.BaseOpTest - EmployeeDO[id=9,empNo=000001,name=被篡改的DO，注意防范此种风险！！！,age=25,position=高级开发工程师,level=P6,address=杭州市西湖区,email=zhangsan@learing.com,hireDate=Sun Jan 10 12:44:30 CST 2021]
     * 19:18:08.049 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@618425b5]
     * 19:18:08.049 [main] DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Returned connection 1636050357 to pool.
     *
     */
    @Test
    public void testCacheHit() {
        String empNo = "000001";
        final SqlSessionFactory sqlSessionFactory = employeeDAO.getSqlSessionFactory();
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            EmployeeDO emp = employeeMapper.getByEmpNo(empNo);
            emp.setName("被篡改的DO，注意防范此种风险！！！");
            EmployeeDO cache1 = employeeMapper.getByEmpNo(empNo);
            EmployeeDO cache2 = employeeMapper.getByEmpNo(empNo);
            LOGGER.info("emp == cache1: " + (emp == cache1));
            LOGGER.info("cache1 == cache2: " + (cache1 == cache2));
            LOGGER.info("testCache:" + emp.hashCode() + ":" + cache1.hashCode() + ":" + cache2.hashCode());
            LOGGER.info("" + emp);
        }
    }

    /**
     * 对于一级缓存，只有两次相同的查询sql之间有更新（update、delete、insert）操作，缓存自动失效
     *
     * 如下，在两次查询之间有一个更新操作，则一共向DB发起了3条sql命令
     *
     * 19:23:27.839 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Opening JDBC Connection
     * 19:23:27.839 [main] DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Checked out connection 452121674 from pool.
     * 19:23:27.840 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - ==>  Preparing: select id, emp_no, name, age, position, level, address, email, hire_date from Employee where emp_no = ?
     * 19:23:27.864 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - ==> Parameters: 000001(String)
     * 19:23:27.881 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - <==      Total: 1
     * 19:23:27.893 [main] INFO  cc.lzy.mybatis.BaseOpTest - EmployeeDO[id=9,empNo=000001,name=被篡改的DO，注意防范此种风险！！！,age=33,position=高级开发工程师,level=P6,address=杭州市西湖区,email=zhangsan@learing.com,hireDate=Sun Jan 10 12:44:30 CST 2021]
     * 19:23:27.916 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.set - ==>  Preparing: update employee SET age = ? where emp_no = ? limit 1
     * 19:23:27.916 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.set - ==> Parameters: 33(Integer), 000003(String)
     * 19:23:27.918 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.set - <==    Updates: 1
     * 19:23:27.918 [main] INFO  cc.lzy.mybatis.BaseOpTest - rows: 1
     * 19:23:27.918 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - ==>  Preparing: select id, emp_no, name, age, position, level, address, email, hire_date from Employee where emp_no = ?
     * 19:23:27.918 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - ==> Parameters: 000001(String)
     * 19:23:27.920 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - <==      Total: 1
     * 19:23:27.921 [main] INFO  cc.lzy.mybatis.BaseOpTest - emp == cache1: false
     * 19:23:27.921 [main] INFO  cc.lzy.mybatis.BaseOpTest - EmployeeDO[id=9,empNo=000001,name=张三,age=33,position=高级开发工程师,level=P6,address=杭州市西湖区,email=zhangsan@learing.com,hireDate=Sun Jan 10 12:44:30 CST 2021]
     * 19:23:27.921 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@1af2d44a]
     * 19:23:27.921 [main] DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Returned connection 452121674 to pool.
     *
     */
    @Test
    public void testCacheInvalidate() {
        String empNo = "000001";
        final SqlSessionFactory sqlSessionFactory = employeeDAO.getSqlSessionFactory();
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            EmployeeDO emp = employeeMapper.getByEmpNo(empNo);
            emp.setName("被篡改的DO，注意防范此种风险！！！");
            LOGGER.info("" + emp);

            EmployeeDO updated = new EmployeeDO();
            updated.setEmpNo(empNo);
            updated.setAge(33);
            int rows = employeeMapper.set("000003", updated);
            LOGGER.info("rows: " + rows);

            EmployeeDO cache1 = employeeMapper.getByEmpNo(empNo);
            LOGGER.info("emp == cache1: " + (emp == cache1));

            LOGGER.info("" + cache1);
        }
    }

    /**
     * <cache size="1000" readOnly="true" flushInterval="120000"/>
     *
     * readOnly=true，不会做缓存拷贝，返回的为同一个对象的引用
     *
     * 20:47:09.422 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO - Cache Hit Ratio [cc.lzy.mybatis.dal.dao.EmployeeDAO]: 0.0
     * 20:47:09.428 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Opening JDBC Connection
     * 20:47:09.428 [main] DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Checked out connection 452121674 from pool.
     * 20:47:09.430 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - ==>  Preparing: select id, emp_no, name, age, position, level, address, email, hire_date from Employee where emp_no = ?
     * 20:47:09.462 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - ==> Parameters: 000001(String)
     * 20:47:09.485 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO.getByEmpNo - <==      Total: 1
     * 20:47:09.498 [main] INFO  cc.lzy.mybatis.BaseOpTest - EmployeeDO[id=9,empNo=000001,name=张三,age=33,position=高级开发工程师,level=P6,address=杭州市西湖区,email=zhangsan@learing.com,hireDate=Sun Jan 10 12:44:30 CST 2021]
     * 20:47:09.498 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@1af2d44a]
     * 20:47:09.498 [main] DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Returned connection 452121674 to pool.
     * 20:47:09.498 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO - Cache Hit Ratio [cc.lzy.mybatis.dal.dao.EmployeeDAO]: 0.5
     * 20:47:09.498 [main] INFO  cc.lzy.mybatis.BaseOpTest - emp == namespaceCache1: true
     * 20:47:09.498 [main] INFO  cc.lzy.mybatis.BaseOpTest - testCache:441001942:441001942:
     * 20:47:09.499 [main] INFO  cc.lzy.mybatis.BaseOpTest - EmployeeDO[id=9,empNo=000001,name=张三,age=33,position=高级开发工程师,level=P6,address=杭州市西湖区,email=zhangsan@learing.com,hireDate=Sun Jan 10 12:44:30 CST 2021]
     * 20:47:09.499 [main] DEBUG cc.lzy.mybatis.dal.dao.EmployeeDAO - Cache Hit Ratio [cc.lzy.mybatis.dal.dao.EmployeeDAO]: 0.6666666666666666
     * 20:47:09.499 [main] INFO  cc.lzy.mybatis.BaseOpTest - emp == namespaceCache1: true
     * 20:47:09.499 [main] INFO  cc.lzy.mybatis.BaseOpTest - testCache:441001942:441001942:
     * 20:47:09.499 [main] INFO  cc.lzy.mybatis.BaseOpTest - EmployeeDO[id=9,empNo=000001,name=张三,age=33,position=高级开发工程师,level=P6,address=杭州市西湖区,email=zhangsan@learing.com,hireDate=Sun Jan 10 12:44:30 CST 2021]
     *
     */
    @Test
    public void testCacheNamespaceLevel() {
        String empNo = "000001";
        final SqlSessionFactory sqlSessionFactory = employeeDAO.getSqlSessionFactory();
        EmployeeDO emp;
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            emp = employeeMapper.getByEmpNo(empNo);
            LOGGER.info("" + emp);
        }

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            EmployeeDO namespaceCache1 = employeeMapper.getByEmpNo(empNo);
            LOGGER.info("emp == namespaceCache1: " + (emp == namespaceCache1));
            LOGGER.info("testCache:" + emp.hashCode() + ":" + namespaceCache1.hashCode() + ":");
            LOGGER.info("" + namespaceCache1);
        }

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            EmployeeDO namespaceCache1 = employeeMapper.getByEmpNo(empNo);
            LOGGER.info("emp == namespaceCache1: " + (emp == namespaceCache1));
            LOGGER.info("testCache:" + emp.hashCode() + ":" + namespaceCache1.hashCode() + ":");
            LOGGER.info("" + namespaceCache1);
        }
    }

    @Test
    public void testCacheNamespaceLevelInvalidate() {
        String empNo = "000001";
        final SqlSessionFactory sqlSessionFactory = employeeDAO.getSqlSessionFactory();
        EmployeeDO emp;
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            emp = employeeMapper.getByEmpNo(empNo);
            emp.setHireDate(new Date());
            employeeMapper.updateByEmpNo(emp);
            LOGGER.info("" + emp);
        }

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            EmployeeDO namespaceCache1 = employeeMapper.getByEmpNo(empNo);
            LOGGER.info("emp == namespaceCache1: " + (emp == namespaceCache1));
            LOGGER.info("testCache:" + emp.hashCode() + ":" + namespaceCache1.hashCode() + ":");
            LOGGER.info("" + namespaceCache1);
        }

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 获取Mapper，执行方法
            EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
            EmployeeDO namespaceCache1 = employeeMapper.getByEmpNo(empNo);
            LOGGER.info("emp == namespaceCache1: " + (emp == namespaceCache1));
            LOGGER.info("testCache:" + emp.hashCode() + ":" + namespaceCache1.hashCode() + ":");
            LOGGER.info("" + namespaceCache1);
        }
    }
}