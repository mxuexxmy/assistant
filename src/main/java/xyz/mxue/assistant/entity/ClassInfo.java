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
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_class_info")
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学委学号
     */
    private String studentId;

    /**
     * 班级编号
     */
    private String classNumber;

    /**
     * 班名
     */
    private String className;

    /**
     * 学校名
     */
    private String school;

    /**
     * 学院
     */
    private String college;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 专业
     */
    private String profession;

    /**
     * 班级密钥
     */
    private String classKey;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
