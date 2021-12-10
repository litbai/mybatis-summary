/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis.web;

import cc.lzy.mybatis.biz.service.EmployeeTxService;
import cc.lzy.mybatis.dal.dao.EmployeeDAO;
import cc.lzy.mybatis.dal.entity.EmployeeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private EmployeeTxService employeeTxService;

    @GetMapping("/getEmp/{empNo}")
    private String getEmp(@PathVariable String empNo) {
        EmployeeDO byEmpNo = employeeDAO.getByEmpNo(empNo);
        return byEmpNo == null ? "null" : byEmpNo.toString();
    }

    @GetMapping("/updateEmp")
    private String getEmp(String empNo, String name, String addr) {
        try {
            System.out.println(employeeTxService.getClass());
            employeeTxService.updateNameAndAddress(empNo, name, addr);
        } catch (Exception e) {
            System.out.println("cated e in updateEmp: " + e.getMessage());
        }

        return "SUCCESS";
    }
}