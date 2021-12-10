package cc.lzy.mybatis.biz.service;


/**
 * class function desc
 *
 * @author taigai
 * @date 2021/10/17 6:13 PM
 */
public interface EmployeeTxService {

    void updateNameAndAddress(String empNo, String name, String addr);
}