package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 4/4/2021$ 4:56 PM$
 */
@Getter
public enum WorkStatusEnum implements Serializable {
    /**
     * 1 未交
     */
    NOT_SUBMITTED(1, "未交"),

    /**
     * 2 已交
     */
    SUBMITTED(3, "已交");

    private final Integer value;
    private final String desc;

    WorkStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
