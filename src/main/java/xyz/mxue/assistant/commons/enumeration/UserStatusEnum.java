package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 4/4/2021$ 4:44 PM$
 */
@Getter
public enum UserStatusEnum implements Serializable {
    /**
     * 0 正常
     */
    NORMAL(0, "正常"),

    /**
     * 1 冻结
     */
    FREEZE(1, "冻结"),

    /**
     * 2 删除
     */
    DELETE(2, "删除");

    private final Integer value;
    private final String desc;

    UserStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
