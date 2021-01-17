/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis;

import cc.lzy.mybatis.biz.service.EmployeeService;
import cc.lzy.mybatis.biz.service.impl.EmployeeServiceImpl;
import cc.lzy.mybatis.dal.dao.impl.EmployeeDAOImpl;
import cc.lzy.mybatis.domain.model.Employee;
import cc.lzy.mybatis.domain.model.Paginator;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author taigai
 * @version ParamHandleTest.java, v 0.1 2021年01月04日 18:58 taigai Exp $
 */
public class ParamHandleTest {
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseOpTest.class);

    /** employeeService */
    EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOImpl());

    @Test
    public void testNamedParam() {
        Employee employee = employeeService.getByNameAndLevel("张三", "P6");
        LOGGER.info("getByNameAndLevel: {}", employee);
    }

    @Test
    public void testNamedParam2() {
        Paginator<Employee> page = new Paginator<>(1, 2);
        employeeService.listByPage("P6", page);
        LOGGER.info("listByPage: {}", page);
    }

    @Test
    public void testCollectionParam() {
        // 仅测试目的，实际中不会使用Queue这种类型
        Collection<String> collection = new ArrayDeque<>();
        collection.add("000001");
        collection.add("000002");
        collection.add("000003");
        List<Employee> employees = employeeService.listByEmpNoCollection(collection);
        LOGGER.info("listByEmpNoCollection");
        for (Employee emp : employees) {
            LOGGER.info(emp.getEmpNo() + "-->" + emp + "\n");
        }
    }

    @Test
    public void testListParam() {
        List<String> empNoList = Lists.newArrayList("000001", "000002", "000003");
        List<Employee> employees = employeeService.listByEmpNos("P5", empNoList);
        LOGGER.info("listByEmpNoList");
        for (Employee emp : employees) {
            LOGGER.info(emp.getEmpNo() + "-->" + emp + "\n");
        }

        employees = employeeService.listByEmpNos(null, empNoList);
        LOGGER.info("listByEmpNoList");
        for (Employee emp : employees) {
            LOGGER.info(emp.getEmpNo() + "-->" + emp + "\n");
        }
    }

    @Test
    public void testSetParam() {
        Set<String> empNoSet = Sets.newHashSet("000001", "000002", "000003");
        List<Employee> employees = employeeService.listByEmpNoSet(empNoSet);
        LOGGER.info("listByEmpNoSet");
        for (Employee emp : employees) {
            LOGGER.info(emp.getEmpNo() + "-->" + emp + "\n");
        }
    }

    @Test
    public void testArrayParam() {
        // 单参数，类型为Array且未指定命名参数，则默认命名参数为array，而非param1
        String[] empNoArray = {"000001", "000002", "000003"};
        List<Employee> employees = employeeService.listByEmpNoArray(empNoArray);
        LOGGER.info("listByEmpNoArray");
        for (Employee emp : employees) {
            LOGGER.info(emp.getEmpNo() + "-->" + emp + "\n");
        }
    }

    @Test
    public void testMap() {
        List<String> empNoList = Lists.newArrayList("000001", "000002", "000003");
        Map<String, Employee> employeeMap = employeeService.mapByEpNos(empNoList);
        LOGGER.info("employeeMap:{}", employeeMap);
    }
}