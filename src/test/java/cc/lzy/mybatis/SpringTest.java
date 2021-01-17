/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis;

import cc.lzy.mybatis.biz.service.EmployeeService;
import cc.lzy.mybatis.bootstrap.MybatisApplication;
import cc.lzy.mybatis.domain.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring Test
 *
 * @author taigai
 * @version SpringTest.java, v 0.1 2021年01月17日 15:48 taigai Exp $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public class SpringTest {

    private static final Logger LOGGER  = LoggerFactory.getLogger(SpringTest.class);
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testEmp() {
        Employee byEmpNo = employeeService.getByEmpNo("000001");
        LOGGER.info("SpringTest->testEmp: " + byEmpNo);
    }
}