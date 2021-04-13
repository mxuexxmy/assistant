package xyz.mxue.assistant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mxuexxmy
 * @date 3/30/2021$ 5:51 PM$
 */
@Data
public class StudentInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "学号")
    private String studentNo;

    @ApiModelProperty(value = "0表示系统管理员，1表示学委，2表示学委助理，3表示普通学生")
    private Integer studentType;

}
