package com.shsxt.ego.rpc.dto;

import java.io.Serializable;

public class TreeDto implements Serializable {
    private Integer id;
    private Integer pId;
    private String name;
    private Boolean checked=false;// false-未选中  true-选中

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
