package com.shsxt.ego.rpc.dto;

import java.io.Serializable;

public class TreeDto implements Serializable {
    private Integer id;
    private String text;
    private String state ;// false-未选中  true-选中

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
