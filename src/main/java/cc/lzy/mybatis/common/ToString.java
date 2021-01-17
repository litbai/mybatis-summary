package cc.lzy.mybatis.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 通用ToString
 *
 * @author taigai
 * @version ToString.java, v 0.1 2020年12月26日 11:37 taigai Exp $
 */
public class ToString implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -6674138541636269343L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}