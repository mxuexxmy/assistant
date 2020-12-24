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
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_work_info")
public class WorkInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String studnetId;

    private String workNumber;

    private Date createTime;

    private Date updateTime;

    private String workCourse;

    private String workTitle;

    private String workContant;

    private String workRemark;

    private Date endTime;

    private Integer workType;

    private Integer workStatus;

    private String workAnnex;

    private String workPicture;


}
