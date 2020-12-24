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
 * 班级
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_class_info")
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String studentId;

    private String classNumber;

    private String className;

    private String school;

    private String college;

    private Integer grade;

    private String profession;

    private String classKey;

    private Date createTime;

    private Date updateTime;


}
