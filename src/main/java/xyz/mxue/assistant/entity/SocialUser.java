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
 * 社会化用户表
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_social_user")
@ApiModel(value="SocialUser对象", description="社会化用户表")
public class SocialUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "第三方系统的唯一ID")
    private String uuid;

    @ApiModelProperty(value = "第三方用户来源,如GITHUB、GITEE、QQ")
    private String source;

    @ApiModelProperty(value = "用户的授权令牌")
    private String accessToken;

    @ApiModelProperty(value = "第三方用户的授权令牌的有效期")
    private Long expireIn;

    @ApiModelProperty(value = "刷新令牌")
    private String refreshToken;

    @ApiModelProperty(value = "第三方用户的 open id")
    private String openId;

    @ApiModelProperty(value = "第三方用户的 ID")
    private String uid;

    @ApiModelProperty(value = "个别平台的授权信息")
    private String accessCode;

    @ApiModelProperty(value = "第三方用户的 union id")
    private String unionId;

    @ApiModelProperty(value = "第三方用户授予的权限")
    private String scope;

    @ApiModelProperty(value = "个别平台的授权信息")
    private String tokenType;

    @ApiModelProperty(value = "id token")
    private String idToken;

    @ApiModelProperty(value = "小米平台用户的附带属性")
    private String macAlgorithm;

    @ApiModelProperty(value = "小米平台用户的附带属性")
    private String macKey;

    @ApiModelProperty(value = "用户的授权code")
    private String code;

    @ApiModelProperty(value = "Twitter平台用户的附带属性")
    private String oauthToken;

    @ApiModelProperty(value = "Twitter平台用户的附带属性")
    private String oauthTokenSecret;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
