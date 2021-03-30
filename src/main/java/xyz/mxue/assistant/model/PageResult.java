package xyz.mxue.assistant.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author mxuexxmy
 * @date 3/30/2021$ 5:10 PM$
 */
@Data
@ApiModel(value = "分页返回数据")
public class PageResult<T> implements Serializable {

    @ApiModelProperty(value = "错误编码")
    private Integer code = 0;

    @ApiModelProperty(value = "返回信息")
    private String msg = "查询成功";

    @ApiModelProperty(value = "返回对象")
    private List data = null;

    @ApiModelProperty(value = "总条数")
    private Long count;

    @ApiModelProperty(value = "当前页")
    private Long page;

    @ApiModelProperty(value = "每页显示条数")
    private Long pageSize;

    public static <T> PageResult<T> succeed(Page<T> page) {
       return new PageResult<>(page);
    }

    public PageResult(Page<T> page) {
      this.setData(page.getRecords());
      this.setCount(page.getTotal());
      this.setPage(page.getCurrent());
      this.setPageSize(page.getSize());
    }
}
