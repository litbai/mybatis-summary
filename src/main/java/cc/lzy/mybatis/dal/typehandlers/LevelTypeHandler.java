/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package cc.lzy.mybatis.dal.typehandlers;

import cc.lzy.mybatis.domain.model.enums.LevelEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 枚举自定义类型处理器
 *
 * @author taigai
 * @version LevelTypeHandler.java, v 0.1 2020年12月26日 19:27 taigai Exp $
 */
public class LevelTypeHandler extends BaseTypeHandler<LevelEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LevelEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public LevelEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String levelCode = rs.getString(columnName);
        return levelCode == null ? null : LevelEnum.ofCode(levelCode);
    }

    @Override
    public LevelEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String levelCode = rs.getString(columnIndex);
        return levelCode == null ? null : LevelEnum.ofCode(levelCode);
    }

    @Override
    public LevelEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String levelCode = cs.getString(columnIndex);
        return levelCode == null ? null : LevelEnum.ofCode(levelCode);
    }
}