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
 * 作业提交记录
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_work_submit")
public class WorkSubmit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 作业编号
     */
    private String workNumber;

    /**
     * 1为提交，2为未提交
     */
    private Integer submitStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
