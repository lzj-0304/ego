package com.shsxt.ego.rpc.query;

import java.io.Serializable;

public class BaseQuery implements Serializable {
    private Integer page =1;
    private Integer rows=10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
