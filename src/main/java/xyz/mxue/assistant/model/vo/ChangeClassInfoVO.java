package xyz.mxue.assistant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 4/10/2021$ 12:44 AM$
 */
@Data
public class ChangeClassInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "班级ID")
    private Long classId;

    @ApiModelProperty(value = "学生学号")
    private String studentNo;

    @ApiModelProperty(value = "班级名称")
    private String className;

}
