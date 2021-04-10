package xyz.mxue.assistant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 社会化用户和系统用户关系表
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_social_user_auth")
@ApiModel(value="SocialUserAuth对象", description="社会化用户和系统用户关系表")
public class SocialUserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "系统用户ID")
    private Long userId;

    @ApiModelProperty(value = "社会化用户ID")
    private Long socialUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
