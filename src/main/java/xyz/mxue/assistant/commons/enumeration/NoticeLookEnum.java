package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

/**
 * @author mxuexxmy
 * @date 4/9/2021$ 2:56 PM$
 */
@Getter
public enum NoticeLookEnum {

    /**
     * 0 未查看
     */
    NOT_VIEWED(0, "未查看"),

    /**
     * 1 已查看
     */
    VIEWED(1, "已查看");

    private final Integer value;
    private final String desc;

    NoticeLookEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
