/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis;

import cc.lzy.mybatis.dal.dao.EmployeeDAO;
import cc.lzy.mybatis.dal.dao.impl.EmployeeDAOImpl;
import cc.lzy.mybatis.dal.entity.EmployeeDO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author taigai
 * @version DynamicSqlTest.java, v 0.1 2021年01月09日 12:52 taigai Exp $
 */
public class DynamicSqlTest {
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseOpTest.class);

    /** employeeDAO */
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Test
    public void testWhere() {
        String name = "张三";
        String level = "P5";
        List<EmployeeDO> employeeDOS = employeeDAO.where(name, level);
        LOGGER.info("testWhere:{}", employeeDOS);
    }

    @Test
    public void testChoose() {
        String name = "";
        String level = "P8";
        Integer age = 0;
        List<EmployeeDO> employeeDOS = employeeDAO.choose(name, level, age);
        LOGGER.info("testChoose:{}", employeeDOS);
    }

    @Test
    public void testTrim() {
        String name = "孙七";
        String level = "P8";
        Integer age = 25;
        List<EmployeeDO> employeeDOS = employeeDAO.trim(name, level, age);
        LOGGER.info("testTrim:{}", employeeDOS);
    }

    @Test
    public void testSet() {
        EmployeeDO updated = new EmployeeDO();
        updated.setLevel("P0");
        updated.setHireDate(new Date());
        int rows = employeeDAO.set("000001", updated);
        LOGGER.info("testSet:{}", rows);
    }

    @Test
    public void testSaveEmployees() {
        List<EmployeeDO> emps = getEmployees();
        int rows = employeeDAO.saveEmployees(emps);
        LOGGER.info("testSaveEmployees:{}", rows);
    }

    @Test
    public void getByNameLike() {
        List<EmployeeDO> batch = employeeDAO.getByNameLike("BATCH-");
        LOGGER.info("getByNameLike:{}", batch);
    }

    private List<EmployeeDO> getEmployees() {
        List<EmployeeDO> emps = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            EmployeeDO emp = new EmployeeDO();
            emp.setEmpNo("00001" + i);
            emp.setName("BATCH-"+i);
            emp.setAge((int) (Math.random() * 40));
            emp.setHireDate(new Date());
            emp.setLevel("P4");
            emp.setAddress("杭州市拱墅区");
            emp.setEmail(emp.getName() + "@learing.com");
            emp.setPosition("实习工程师");
            emps.add(emp);
        }
        return emps;
    }

}