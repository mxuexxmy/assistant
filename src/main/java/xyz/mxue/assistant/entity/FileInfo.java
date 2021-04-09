package xyz.mxue.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文件信息
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_file_info")
@ApiModel(value="FileInfo对象", description="文件信息")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "存储位置（1:阿里云，2:腾讯云，3:minio，4:本地）")
    private Integer fileLocation;

    @ApiModelProperty(value = "文件仓库")
    private String fileBucket;

    @ApiModelProperty(value = "文件名称（上传时候的文件名)")
    private String fileOriginName;

    @ApiModelProperty(value = "文件后缀")
    private String fileSuffix;

    @ApiModelProperty(value = "文件大小kb")
    private Long fileSizeKb;

    @ApiModelProperty(value = "文件大小信息，计算后的")
    private String fileSizeInfo;

    @ApiModelProperty(value = "存储到bucket的名称（文件唯一标识id)")
    private String fileObjectName;

    @ApiModelProperty(value = "存储路径")
    private String filePath;

    @ApiModelProperty(value = "创建用户")
    private Long createUser;

    @ApiModelProperty(value = "修改用户")
    private Long updateUser;

    @ApiModelProperty(value = "创建时间")
    @TableField( fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
