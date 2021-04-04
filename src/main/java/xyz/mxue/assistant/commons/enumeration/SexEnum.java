package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 4/4/2021$ 4:40 PM$
 */
@Getter
public enum SexEnum implements Serializable {


    /**
     * 1男
     */
    MALE(1, "男"),

    /**
     * 2女
     */
    FEMALE(2, "女"),

    /**
     * 未知
     */
    UNKNOWN(3, "未知");

    private final Integer value;
    private final String desc;

    SexEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
