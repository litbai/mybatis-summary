package cc.lzy.mybatis.domain.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 层级Enum
 *
 * @author taigai
 * @version LevelEnum.java, v 0.1 2020年12月26日 12:48 taigai Exp $
 */
public enum LevelEnum {

    P_4("P4", "助理"), P_5("P5", "初级"), P_6("P6", "高级"), P_7("P7", "专家"), P_8("P8", "高级专家"), P_9("P9", "资深专家"), P_10("P10", "研究员"),
    ;

    private static final Map<String, LevelEnum> CODE_MAP = new HashMap<>();
    static {
        for (LevelEnum e : LevelEnum.values()) {
            CODE_MAP.put(e.getCode(), e);
        }
    }

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

    /**
     * 根据code获取枚举类型
     */
    public static LevelEnum ofCode(String code) {
        return CODE_MAP.get(code);
    }
}