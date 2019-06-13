package com.users.component.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 地区级联响应参数类
 */
@ApiModel(value = "AreaCascader响应参数类", description = "地区级联返回参数类")
public class AreaCascader implements Serializable {

    private static final long serialVersionUID = -378070903787291672L;

    @ApiModelProperty(value = "地区编号", example = "地区编号", required = true)
    private Integer value;
    @ApiModelProperty(value = "地区名称", example = "地区名称", required = true)
    private String label;
    @ApiModelProperty(value = "地区状态：true启用、false禁用", example = "地区状态：true启用、false禁用", required = true)
    private Boolean disabled;
//    @ApiModelProperty(value = "地区子级", example = "地区子级", required = true)//会把list下的提示覆盖
    @ApiModelProperty(value = "地区子级", required = true)
    private List<AreaCascader> children;

    public AreaCascader() {
        this.disabled=true;
    }

    public AreaCascader(Integer value, String label, List<AreaCascader> children, boolean disabled) {
        this.value = value;
        this.label = label;
        this.children = children;
        this.disabled=disabled;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<AreaCascader> getChildren() {
        return children;
    }

    public void setChildren(List<AreaCascader> children) {
        this.children = children;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

}
