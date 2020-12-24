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
 * 班级通知
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_class_notice")
public class ClassNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String classNumber;

    private String noticeSubject;

    private String noticeContent;

    private String noticeRemark;

    private Date createTime;

    private Date updateTime;


}
