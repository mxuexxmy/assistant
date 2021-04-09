package xyz.mxue.assistant.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mxuexxmy
 * @date 4/9/2021$ 12:23 AM$
 */
@Data
public class UserAndStudentInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "微信openID")
    private String wxOpenId;

    @ApiModelProperty(value = "qqID")
    private String qqOpenId;

    @ApiModelProperty(value = "支付宝ID")
    private Integer aliPayOpenId;

    @ApiModelProperty(value = "性别(字典 1男 2女 3未知)")
    private Integer sex;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "学生类型")
    private Integer studentType;
}
