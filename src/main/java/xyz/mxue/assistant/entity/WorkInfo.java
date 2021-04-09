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
 * 作业
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_work_info")
@ApiModel(value="WorkInfo对象", description="作业")
public class WorkInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学生ID")
    private Long studentId;

    @ApiModelProperty(value = "作业课程")
    private String workCourse;

    @ApiModelProperty(value = "作业标题")
    private String workTitle;

    @ApiModelProperty(value = "作业内容")
    private String workContant;

    @ApiModelProperty(value = "作业备注")
    private String workRemark;

    @ApiModelProperty(value = "截止时间")
    private Date endTime;

    @ApiModelProperty(value = "作业类型：1-电子作业，2-纸质作业，3-个人作业")
    private Integer workType;

    @ApiModelProperty(value = "作业状态：1-未交，2-已交")
    private String workStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField( fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
