/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis.web;

import cc.lzy.mybatis.dal.dao.EmployeeDAO;
import cc.lzy.mybatis.dal.entity.EmployeeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;

/**
 * 员工管理Controller
 *
 * @author taigai
 * @version EmpController.java, v 0.1 2021年01月17日 11:32 taigai Exp $
 */
@RestController
public class EmpController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping("/getEmp/{empNo}")
    private String getEmp(@PathVariable String empNo) {
        EmployeeDO byEmpNo = employeeDAO.getByEmpNo(empNo);
        return byEmpNo == null ? "null" : byEmpNo.toString();
    }
}