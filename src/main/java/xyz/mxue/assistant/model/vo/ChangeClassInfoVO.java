package xyz.mxue.assistant.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mxuexxmy
 * @date 4/10/2021$ 12:44 AM$
 */
@Data
public class ChangeClassInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "学生班级关联ID")
    private Long id;

    @ApiModelProperty(value = "学校名")
    private String school;

    @ApiModelProperty(value = "学院")
    private String college;

    @ApiModelProperty(value = "年级")
    private String grade;

    @ApiModelProperty(value = "班名")
    private String className;


}
