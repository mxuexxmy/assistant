package xyz.mxue.assistant.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "学号")
    private String studentId;

    @ApiModelProperty(value = "班级名")
    private String classNumber;

    @ApiModelProperty(value = "1表示学委，2表示普通学生")
    private Integer role;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
