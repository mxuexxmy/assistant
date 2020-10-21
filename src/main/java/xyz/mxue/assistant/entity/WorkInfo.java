package xyz.mxue.assistant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 作业
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_work_info")
public class WorkInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学号
     */
    private String studnetId;

    /**
     * 作业编号
     */
    private String workNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 作业标题
     */
    private String workCourse;

    /**
     * 作业标题
     */
    private String workTitle;

    /**
     * 作业内容
     */
    private String workContant;

    /**
     * 作业备注
     */
    private String workRemark;

    /**
     * 截止时间
     */
    private Date endTime;

    /**
     * 作业类型：1-电子作业，2-纸质作业，3-个人作业
     */
    private Integer workType;

    /**
     * 作业状态：1-未交，2-已交
     */
    private Integer workStatus;

    /**
     * 作业附件：存储文件路径
     */
    private String workAnnex;

    /**
     * 作业配图：存储文件路径
     */
    private String workPicture;


}
