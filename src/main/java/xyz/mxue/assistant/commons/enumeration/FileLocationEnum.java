package xyz.mxue.assistant.commons.enumeration;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 4/4/2021$ 5:02 PM$
 */
@Getter
public enum FileLocationEnum implements Serializable {

    /**
     * 1 阿里云
     */
    ALI_CLOUD(1, "提交"),

    /**
     * 2 腾讯云
     */
    TENCENT_CLOUD(2, "未提交"),
    /**
     * 3 minio
     */
    MINIO(3, "提交"),

    /**
     * 4 本地
     */
    LOCATION(4, "未提交");

    private final Integer value;
    private final String desc;

    FileLocationEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
