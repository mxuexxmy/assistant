package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 4/4/2021$ 4:48 PM$
 */
@Getter
public enum StudentTypeEnum implements Serializable {
    /**
     * 1 学委
     */
    ACADEMIC_COMMITTEE(1, "学委"),

    /**
     * 2 学委助理
     */
    ACADEMIC_COMMITTEE_ASSISTANT(2, "学委助理"),

    /**
     * 3 普通学生
     */
    GENERAL_STUDENT(3, "普通学生");

    private final Integer value;
    private final String desc;

    StudentTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
