package cc.lzy.mybatis.biz.service.impl;

import cc.lzy.mybatis.biz.service.EmployeeService;
import cc.lzy.mybatis.biz.service.EmployeeTxService;
import cc.lzy.mybatis.dal.dao.EmployeeDAO;
import cc.lzy.mybatis.dal.entity.EmployeeDO;
import cc.lzy.mybatis.domain.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * class function desc
 *
 * @author taigai
 * @date 2021/10/17 6:13 PM
 */
@Service
public class EmployeeTxServiceImpl implements EmployeeTxService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public void updateNameAndAddress(String empNo, String name, String addr) {

        EmployeeDO emp = employeeDAO.getByEmpNo(empNo);
        emp.setName(name);
        int rows = employeeDAO.updateByEmpNo(emp);
        System.out.println("updated: " + rows);

        Employee empModel = employeeService.getByEmpNo(empNo);
        empModel.setAddress(addr);
        int i = employeeService.updateByEmpNo(empNo, empModel);
        System.out.println(i);
    }
}