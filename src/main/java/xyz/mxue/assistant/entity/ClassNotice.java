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
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_class_notice")
public class ClassNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 班级编号
     */
    private String classNumber;

    /**
     * 通知标题
     */
    private String noticeSubject;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 通知备注
     */
    private String noticeRemark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
