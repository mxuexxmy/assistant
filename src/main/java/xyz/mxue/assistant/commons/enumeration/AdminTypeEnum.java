package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 4/4/2021$ 4:43 PM$
 */
@Getter
public enum AdminTypeEnum implements Serializable {

    /**
     * 0 超级管理员
     */
    SUPER_ADMIN(0, "超级管理员"),

    /**
     * 2非管理员
     */
    NOT_ADMIN(1, "非管理员");

    private final Integer value;
    private final String desc;

    AdminTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
