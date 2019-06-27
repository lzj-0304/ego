package com.shsxt.ego.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CatNode {

	//@JsonProperty 指定将java对象转化为json格式的时候，对应的key
	@JsonProperty(value="u")
	private String url;
	@JsonProperty(value="n")
	private String name;
	@JsonProperty(value="i")
	private List<?>list;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	 
	
    

}
