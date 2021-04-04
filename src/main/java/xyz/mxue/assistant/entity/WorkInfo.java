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
 * 作业
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_work_info")
@ApiModel(value="WorkInfo对象", description="作业")
public class WorkInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学号")
    private String studnetId;

    @ApiModelProperty(value = "作业编号")
    private String workNumber;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "作业标题")
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
    private Integer workStatus;

    @ApiModelProperty(value = "作业附件：存储文件路径")
    private String workAnnex;

    @ApiModelProperty(value = "作业配图：存储文件路径")
    private String workPicture;


}
