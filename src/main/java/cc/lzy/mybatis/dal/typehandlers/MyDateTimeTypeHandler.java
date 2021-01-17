/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package cc.lzy.mybatis.dal.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

/**
 * 自定义类型处理器
 *
 * You can override the type handlers or create your own to deal with unsupported or non-standard types.
 * To do so, implement the interface org.apache.ibatis.type.TypeHandler or
 * extend the convenience class org.apache.ibatis.type.BaseTypeHandler
 * and optionally map it to a JDBC type.
 *
 * @author taigai
 * @version MyDateTimeTypeHandler.java, v 0.1 2020年12月26日 19:27 taigai Exp $
 */
@MappedJdbcTypes(value = {JdbcType.VARCHAR}, includeNullJdbcType=true)
@MappedTypes({String.class})
public class MyDateTimeTypeHandler extends BaseTypeHandler<String> {

    /**
     * @see BaseTypeHandler#setNonNullParameter(PreparedStatement, int, Object, JdbcType)
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("setNonNullParameter index: " + i + ", parameter: " + parameter + ", jdbcType: " + jdbcType);
        ps.setString(i, parameter);
    }

    /**
     * @see BaseTypeHandler#getNullableResult(ResultSet, String)
     */
    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        System.out.println("getNullableResult columnName: " + columnName);
        if (!"level".equals(columnName)) {
            return "mocked!!";
        } else {
            return rs.getString(columnName);
        }
    }

    /**
     * @see BaseTypeHandler#getNullableResult(ResultSet, int)
     */
    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        System.out.println("getNullableResult columnIndex: " + columnIndex);
        return rs.getString(columnIndex);
    }

    /**
     * @see BaseTypeHandler#getNullableResult(CallableStatement, int)
     */
    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        System.out.println("CallableStatement columnIndex: " + columnIndex);
        return cs.getString(columnIndex);
    }
}