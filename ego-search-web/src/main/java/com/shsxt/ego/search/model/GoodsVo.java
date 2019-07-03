package com.shsxt.ego.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "ego",type = "goods")
public class GoodsVo implements Serializable {
    @Id
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private Long id;
    @Field(type = FieldType.String, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Long, searchAnalyzer = "ik", analyzer = "ik")
    private Long price;
    @Field(type = FieldType.String, searchAnalyzer = "ik", analyzer = "ik")
    private String image;

    private String titleHl;


    public String getTitleHl() {
        return titleHl;
    }

    public void setTitleHl(String titleHl) {
        this.titleHl = titleHl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image.split(",")[0];
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "GoodsVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", titleHl='" + titleHl + '\'' +
                '}';
    }
}
