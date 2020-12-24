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
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_work_file")
public class WorkFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String workNumber;

    private String studentId;

    private String workFileName;

    private String workFilePath;

    private Date createTime;

    private Date updateTime;


}
