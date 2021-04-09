package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

/**
 * @author mxuexxmy
 * @date 4/9/2021$ 2:52 PM$
 */
@Getter
public enum ClassIsNowEnum {

    /**
     * 1 当前班级
     */
    IS_NOW(1, "当前账号"),

    /**
     * 2 以前的班级
     */
    PREVIOUS(2, "以前的班级"),

    /**
     * 3 删除的班级
     */
    DELETE(3, "删除的班级");

    private final Integer value;
    private final String desc;

    ClassIsNowEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
