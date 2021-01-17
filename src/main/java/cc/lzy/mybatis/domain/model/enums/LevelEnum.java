package cc.lzy.mybatis.domain.model.enums;

/**
 * 层级Enum
 *
 * @author taigai
 * @version LevelEnum.java, v 0.1 2020年12月26日 12:48 taigai Exp $
 */
public enum LevelEnum {

    P5("P5", "初级"), P6("P5", "高级"), P7("P5", "专家"), P8("P5", "高级专家"), P9("P5", "资深专家"), P10("P5", "研究员"),
    ;

    private String code;
    private String desc;

    LevelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>desc</tt>.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }
}