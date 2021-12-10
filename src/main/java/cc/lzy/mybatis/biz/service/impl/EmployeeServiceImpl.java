/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package cc.lzy.mybatis.biz.service.impl;

import cc.lzy.mybatis.biz.service.EmployeeService;
import cc.lzy.mybatis.dal.dao.EmployeeDAO;
import cc.lzy.mybatis.dal.dao.impl.EmployeeDAOImpl;
import cc.lzy.mybatis.dal.entity.EmployeeDO;
import cc.lzy.mybatis.domain.mapper.EmployeeMapper;
import cc.lzy.mybatis.domain.model.Employee;
import cc.lzy.mybatis.domain.model.Paginator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 员工服务实现
 *
 * @author taigai
 * @version EmployeeServiceImpl.java, v 0.1 2020年12月26日 11:46 taigai Exp $
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    /** employeeDAO */
    @Autowired
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl() {}

    /**
     * 无参构造函数
     */
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    /**
     * @see EmployeeService#getByEmpNo(String) 
     */
    @Override
    public Employee getByEmpNo(String empNo) {
        EmployeeDO employeeDO = employeeDAO.getByEmpNo(empNo);
        return EmployeeMapper.INSTANCE.toEmployee(employeeDO);
    }

    /**
     * @see EmployeeService#getByNameAndLevel(String, String) 
     */
    @Override
    public Employee getByNameAndLevel(String name, String level) {
        EmployeeDO employeeDO = employeeDAO.getByNameAndLevel(name, level);
        return EmployeeMapper.INSTANCE.toEmployee(employeeDO);
    }

    /**
     * @see EmployeeService#listByPage(String, Paginator)
     */
    @Override
    public void listByPage(String level, Paginator page) {
        employeeDAO.listByPage(level, page);
        List<EmployeeDO> items = page.getItems();
        page.setItems(items.stream().map(EmployeeMapper.INSTANCE::toEmployee).collect(Collectors.toList()));
    }

    /**
     * @see EmployeeService#getByHireDate(Date)
     */
    @Override
    public List<Employee> getByHireDate(Date hireDate) {
        List<EmployeeDO> employeeDOs = employeeDAO.listByHireDate(hireDate);
        return employeeDOs.stream().map(EmployeeMapper.INSTANCE::toEmployee).collect(Collectors.toList());
    }

    /**
     * @see EmployeeService#addEmployee(Employee) 
     */
    @Override
    public Long addEmployee(Employee employee) {
        return employeeDAO.insert(EmployeeMapper.INSTANCE.toEmployeeDO(employee));
    }

    /**
     * @see EmployeeService#updateByEmpNo(String, Employee) 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateByEmpNo(String empNo, Employee updated) {
        if ("000012".equals(empNo)) {
            throw new IllegalArgumentException("empNo can not be 000012");
        }
        updated.setEmpNo(empNo);
        return employeeDAO.updateByEmpNo(EmployeeMapper.INSTANCE.toEmployeeDO(updated));
    }

    /**
     * @see EmployeeService#deleteByEmpNo(String) 
     */
    @Override
    public boolean deleteByEmpNo(String empNo) {
        return employeeDAO.deleteByEmpNo(empNo);
    }

    /**
     * @see EmployeeService#listByEmpNos(String, List)
     */
    @Override
    public List<Employee> listByEmpNos(String level, List<String> empNos) {
        List<EmployeeDO> employeeDOS;
        if (StringUtils.isBlank(level)) {
            employeeDOS = employeeDAO.listByEmpNoList(empNos);
        } else {
            employeeDOS = employeeDAO.listByEmpNoListAndLevel(level, empNos);
        }
        return employeeDOS.stream().map(EmployeeMapper.INSTANCE::toEmployee).collect(Collectors.toList());
    }

    /**
     * @see EmployeeService#listByEmpNoSet(Set) 
     */
    @Override
    public List<Employee> listByEmpNoSet(Set<String> empNos) {
        List<EmployeeDO> employeeDOS = employeeDAO.listByEmpNoSet(empNos);
        return employeeDOS.stream().map(EmployeeMapper.INSTANCE::toEmployee).collect(Collectors.toList());
    }

    /**
     * @see EmployeeService#listByEmpNoArray(String[])
     */
    @Override
    public List<Employee> listByEmpNoArray(String[] empNoArray) {
        List<EmployeeDO> employeeDOS = employeeDAO.listByEmpNoArray(empNoArray);
        return employeeDOS.stream().map(EmployeeMapper.INSTANCE::toEmployee).collect(Collectors.toList());
    }

    /**
     * @see EmployeeService#listByEmpNoCollection(Collection)
     */
    @Override
    public List<Employee> listByEmpNoCollection(Collection<String> collection) {
        List<EmployeeDO> employeeDOS = employeeDAO.listByEmpNoCollection(collection);
        return employeeDOS.stream().map(EmployeeMapper.INSTANCE::toEmployee).collect(Collectors.toList());
    }

    /**
     * @see EmployeeService#mapByEpNos(List)
     */
    @Override
    public Map<String, Employee> mapByEpNos(List<String> empNos) {
        Map<String, EmployeeDO> employeeMap = employeeDAO.mapByEmpNoList(empNos);
        Map<String, Employee> retMap = new HashMap<>();
        for (Map.Entry<String, EmployeeDO> entry : employeeMap.entrySet()) {
            Employee emp = EmployeeMapper.INSTANCE.toEmployee(entry.getValue());
            retMap.put(entry.getKey(), emp);
        }
        return retMap;
    }

}