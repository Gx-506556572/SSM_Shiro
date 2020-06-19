package com.imcode.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("cms_article_type")
public class ArticleType {

    @TableId(value = "type_id",type = IdType.AUTO)
    private Integer typeId;

    @TableField("name")
    private String name;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ArticleType{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                '}';
    }
}