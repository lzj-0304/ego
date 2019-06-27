package com.shsxt.ego.rpc.query;

import java.io.Serializable;

public class ContentQuery extends BaseQuery implements Serializable {
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
