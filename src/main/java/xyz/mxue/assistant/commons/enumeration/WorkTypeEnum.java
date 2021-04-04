package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 4/4/2021$ 4:51 PM$
 */
@Getter
public enum WorkTypeEnum implements Serializable {
    /**
     * 1 电子作业
     */
    ELECTRONIC_WORK(1, "电子作业"),

    /**
     * 2 纸质作业
     */
    PAPER_WORK(2, "纸质作业"),

    /**
     * 3 个人作业
     */
    PERSON_WORK(3, "个人作业");

    private final Integer value;
    private final String desc;

    WorkTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
