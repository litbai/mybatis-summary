package cc.lzy.mybatis.dal.entity;

import cc.lzy.mybatis.common.ToString;
import cc.lzy.mybatis.domain.model.enums.LevelEnum;

import java.util.Date;

/**
 * 员工DO
 *
 * @author taigai
 * @version Employee.java, v 0.1 2020年12月26日 11:12 taigai Exp $
 */
public class EmployeeDO extends ToString {

    private Long      id;
    /** 员工编号 */
    private String    empNo;
    /** 姓名 */
    private String    name;
    /** 年龄 */
    private Integer   age;
    /** 职位 */
    private String    position;
    /** 层级 */
    private LevelEnum level;
    /** 住址 */
    private String    address;
    /** 电邮 */
    private String    email;
    /** 入职日期 */
    private Date      hireDate;
    /** 离职日期 */
    private Date      quitTime;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>empNo</tt>.
     *
     * @return property value of empNo
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * Setter method for property <tt>empNo</tt>.
     *
     * @param empNo value to be assigned to property empNo
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>age</tt>.
     *
     * @return property value of age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Getter method for property <tt>position</tt>.
     *
     * @return property value of position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Setter method for property <tt>position</tt>.
     *
     * @param position value to be assigned to property position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Getter method for property <tt>level</tt>.
     *
     * @return property value of level
     */
    public LevelEnum getLevel() {
        return level;
    }

    /**
     * Setter method for property <tt>level</tt>.
     *
     * @param level value to be assigned to property level
     */
    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    /**
     * Getter method for property <tt>address</tt>.
     *
     * @return property value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for property <tt>address</tt>.
     *
     * @param address value to be assigned to property address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter method for property <tt>email</tt>.
     *
     * @return property value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for property <tt>email</tt>.
     *
     * @param email value to be assigned to property email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for property <tt>hireDate</tt>.
     *
     * @return property value of hireDate
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * Setter method for property <tt>hireDate</tt>.
     *
     * @param hireDate value to be assigned to property hireDate
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Getter method for property <tt>quitTime</tt>.
     *
     * @return property value of quitTime
     */
    public Date getQuitTime() {
        return quitTime;
    }

    /**
     * Setter method for property <tt>quitTime</tt>.
     *
     * @param quitTime value to be assigned to property quitTime
     */
    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }
}