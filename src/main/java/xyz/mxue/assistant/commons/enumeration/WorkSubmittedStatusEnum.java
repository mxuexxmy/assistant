package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 4/4/2021$ 4:59 PM$
 */
@Getter
public enum WorkSubmittedStatusEnum implements Serializable {

    /**
     * 1 提交
     */
    SUBMITTED(1, "提交"),
    /**
     * 2 未提交
     */
    NOT_SUBMITTED(2, "未提交");

    private final Integer value;
    private final String desc;

    WorkSubmittedStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
