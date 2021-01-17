/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package cc.lzy.mybatis.domain.mapper;

import cc.lzy.mybatis.dal.entity.EmployeeDO;
import cc.lzy.mybatis.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 员工实体Mapper，MapStruct steps：
 * <p>
 * 1. 创建一个Mapper interface
 * <p>
 * 2. 添加@Mapper注解；
 * The @Mapper annotation marks the interface as mapping interface and lets the MapStruct processor kick in during compilation.
 * <p>
 * 3. 获取Mapper INSTANCE
 * An instance of the interface implementation can be retrieved from the Mappers class. By convention, the interface declares a member INSTANCE , providing clients access to the mapper implementation.
 * <p>
 * 4. 创建转换方法；
 * The actual mapping method expects the source object as parameter and returns the target object. Its name can be freely chosen.
 * For attributes with different names in source and target object, the @Mapping annotation can be used to configure the names.
 * <p>
 * 5. 调用：Employee employee = EmployeeMapper.INSTANCE.toEmployee(do);
 *
 * @author taigai
 * @version EmployeeMapper.java, v 0.1 2020年12月26日 12:21 taigai Exp $
 */
@Mapper
public interface EmployeeMapper {

    /** Mapper INSTANCE */
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    /**
     * DO->Model
     *
     * @param employeeDO employee DO
     * @return Employee Model
     */
    @Mapping(source = "hireDate", target = "hireTime")
    Employee toEmployee(EmployeeDO employeeDO);

    /**
     * Model->DO
     *
     * @param Employee employee Model
     * @return Employee DO
     */
    @Mapping(source = "hireTime", target = "hireDate")
    EmployeeDO toEmployeeDO(Employee employeeDO);

}