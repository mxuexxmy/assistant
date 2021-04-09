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
 * 作业提醒
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_work_notice")
@ApiModel(value="WorkNotice对象", description="作业提醒")
public class WorkNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学生ID")
    private Long studentId;

    @ApiModelProperty(value = "作业ID")
    private Long workId;

    @ApiModelProperty(value = "提醒标题")
    private String noticeSubject;

    @ApiModelProperty(value = "提醒内容")
    private String noticeContent;

    @ApiModelProperty(value = "0未查看，1已查看")
    private Integer isLook;

    @ApiModelProperty(value = "创建时间")
    @TableField( fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
