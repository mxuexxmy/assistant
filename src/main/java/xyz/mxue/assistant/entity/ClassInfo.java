package xyz.mxue.assistant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_class_info")
@ApiModel(value="ClassInfo对象", description="班级")
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学委学号")
    private String studentNo;

    @ApiModelProperty(value = "班名")
    private String className;

    @ApiModelProperty(value = "学校名")
    private String school;

    @ApiModelProperty(value = "学院")
    private String college;

    @ApiModelProperty(value = "年级")
    private Integer grade;

    @ApiModelProperty(value = "专业")
    private String profession;

    @ApiModelProperty(value = "班级密钥")
    private String classKey;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
