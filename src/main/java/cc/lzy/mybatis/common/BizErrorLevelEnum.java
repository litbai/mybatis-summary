package cc.lzy.mybatis.common;

/**
 * 业务异常等级枚举
 *
 * @author taigai
 * @version BizErrorLevelEnum.java, v 0.1 2021年01月10日 11:10 taigai Exp $
 */
public enum BizErrorLevelEnum {

    /** 非紧急异常，仅记录日志到文件，不产生告警，用于正常业务状态不符合预期，自主抛出的异常 */
    LEVEL_WARN,
    /** 系统级异常 适用于系统处理异常，数据源异常、外部依赖异常等 */
    LEVEL_ERROR,
    ;

}