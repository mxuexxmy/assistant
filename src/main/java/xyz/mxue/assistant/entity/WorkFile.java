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
 * 作业文件
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_work_file")
public class WorkFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 作业编号
     */
    private String workNumber;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 作业文件名
     */
    private String workFileName;

    /**
     * 作业文件路径
     */
    private String workFilePath;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
