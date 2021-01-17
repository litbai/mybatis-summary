/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 启动类
 *
 * @author taigai
 * @version MybatisApplication.java, v 0.1 2021年01月17日 11:30 taigai Exp $
 */
@SpringBootApplication(scanBasePackages = "cc.lzy.mybatis")
@MapperScan("cc.lzy.mybatis.dal.dao")
public class MybatisApplication {

    /**
     * 启动入口
     * @param args 启动参数
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MybatisApplication.class, args);
        DataSource ds = context.getBean(DataSource.class);
        System.out.println(ds.getClass().getName()); //默认的使用的是HikariDataSource数据源
    }
}