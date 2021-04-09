package xyz.mxue.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 班级
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_class_info")
@ApiModel(value="ClassInfo对象", description="班级")
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "学生ID")
    private Long studentId;

    @ApiModelProperty(value = "班名")
    private String className;

    @ApiModelProperty(value = "学校名")
    private String school;

    @ApiModelProperty(value = "学院")
    private String college;

    @ApiModelProperty(value = "年级")
    private String grade;

    @ApiModelProperty(value = "专业")
    private String profession;

    @ApiModelProperty(value = "班级密钥")
    private String classKey;

    @ApiModelProperty(value = "1是当前账号，2是以前账号，3被删除账号 ")
    private Integer isNow;

    @ApiModelProperty(value = "创建时间")
    @TableField( fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
