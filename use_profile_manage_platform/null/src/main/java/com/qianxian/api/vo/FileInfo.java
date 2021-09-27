package com.qianxian.api.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author naixiang
 * @since 2021-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("file_info")
@ApiModel(value="FileInfo对象", description="")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "扩展名")
    private String fileExName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件系统")
    private String fileSystem;

    @ApiModelProperty(value = "文件状态 1 正常 2 弃用")
    private Long fileStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
