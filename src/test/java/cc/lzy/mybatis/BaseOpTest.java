/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis;

import cc.lzy.mybatis.biz.service.EmployeeService;
import cc.lzy.mybatis.biz.service.impl.EmployeeServiceImpl;
import cc.lzy.mybatis.dal.dao.impl.EmployeeDAOImpl;
import cc.lzy.mybatis.domain.model.Employee;
import cc.lzy.mybatis.domain.model.enums.LevelEnum;
import cc.lzy.mybatis.util.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.List;

/**
 * 基本增删改查Test
 *
 * @author taigai
 * @version BaseOpTest.java, v 0.1 2021年01月04日 18:59 taigai Exp $
 */
public class BaseOpTest {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger("TEST-DIGEST");

    /** employeeService */
    EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOImpl());

    @Test
    public void testParam() throws NoSuchMethodException {
        Parameter[] getByEmpNos = EmployeeService.class.getMethod("getByEmpNo", String.class).getParameters();
        System.out.println(getByEmpNos[0].getName());
    }

    @Test
    public void testOneQuery() {
        Employee employee = employeeService.getByEmpNo("000052");
        // 无论是datetime 还是 timestamp 如果不指定长度，默认到秒级，如果需要毫秒级，需要指定长度为3
        // `hire_date` datetime(3) DEFAULT NULL,
        // `quit_time` timestamp(3) NOT NULL
        LOGGER.info("getByEmpNo 001 hire date: {}", employee.getHireTime().getTime());
        Class clazz = LOGGER.getClass();
        LOGGER.info("getByEmpNo 001 quit time: {}", employee.getQuitTime().getTime());
        LOGGER.info("getByEmpNo 001: {}", employee);
    }

    @Test
    public void testQuery() {
        // 查询不到
        Employee employee = employeeService.getByEmpNo("001");
        LOGGER.info("getByEmpNo 001: {}", employee);

        // 正常查询
        employee = employeeService.getByEmpNo("000001");
        LOGGER.info("getByEmpNo 000001: {}", employee);

        List<Employee> byHireDate = employeeService.getByHireDate(DateUtils.parseDate("2020-06-01 00:30:00"));
        LOGGER.info("getByHireDate 2020-06-01 00:30:00: {}", byHireDate);

        byHireDate = employeeService.getByHireDate(DateUtils.parseDate("2020-06-01 09:30:00"));
        LOGGER.info("getByHireDate 2020-06-01 09:30:00: {}", byHireDate);
    }


    @Test
    public void testUpdated() {
        Employee emp = newEmp();
        Long id = employeeService.addEmployee(emp);
        LOGGER.info("useGeneratedKeys id: {}", id);

        Employee byEmpNo = employeeService.getByEmpNo("000006");
        LOGGER.info("getByEmpNo 000006: {}", byEmpNo);

        emp.setPosition("技术专家");
        emp.setLevel(LevelEnum.P_7);
        int rows = employeeService.updateByEmpNo("000006", emp);
        LOGGER.info("updateByEmpNo 000006, rows: {}", rows);

        boolean success = employeeService.deleteByEmpNo("000006");
        LOGGER.info("deleteByEmpNo 000006, success: {}", success);
    }


    @Test
    public void testUpdatedTx() {


        Employee emp = employeeService.getByEmpNo("000020");
        LOGGER.info("getByEmpNo 000020: {}", emp);

        emp.setAddress("maojiaqiao 6# 603");

        int rows = employeeService.updateByEmpNo("000020", emp);
        LOGGER.info("updateByEmpNo 000020, rows: {}", rows);

        boolean success = employeeService.deleteByEmpNo("000006");
        LOGGER.info("deleteByEmpNo 000006, success: {}", success);
    }


    private static Employee newEmp() {
        Employee newEmp = new Employee();
        newEmp.setEmpNo("000052");
        newEmp.setName("宋八");
        newEmp.setAge(29);
        newEmp.setAddress("杭州市西湖区");
        newEmp.setEmail("songba@learning.com");
        newEmp.setPosition("高级开发工程师");
        newEmp.setLevel(LevelEnum.P_6);
        newEmp.setHireTime(new Date());
        newEmp.setQuitTime(new Date(System.currentTimeMillis() + 123456));
        return newEmp;
    }

}