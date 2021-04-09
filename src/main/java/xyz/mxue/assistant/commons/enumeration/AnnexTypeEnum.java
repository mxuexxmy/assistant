package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

/**
 * @author mxuexxmy
 * @date 4/9/2021$ 3:00 PM$
 */
@Getter
public enum AnnexTypeEnum {

    /**
     * 0 未查看
     */
    ANNEX(0, "未查看"),

    /**
     * 2女
     */
    IMAGE(1, "已查看");

    private final Integer value;
    private final String desc;

    AnnexTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
