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
 * 学生
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String studentName;

    private String studentId;

    private String classNumber;

    private String password;

    private Integer role;

    private String email;

    private String phone;

    private Date createTime;

    private Date updateTime;


}
