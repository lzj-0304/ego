package com.shsxt.ego.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/****
 * 封装响应到前台大广告数据格式
 * @author Administrator
 *
 */
public class BigPicture {
	@JsonProperty("srcB")
	private String srcb;
	@JsonProperty("height")
	private Integer height=240;
	@JsonProperty("alt")
	private String alt;
	@JsonProperty("width")
	private Integer width=670;
	@JsonProperty("src")
	private String src;
	@JsonProperty("widthB")
	private Integer widthb=550;
	@JsonProperty("href")
	private String href;
	@JsonProperty("heightB")
	private Integer heightb=240;
	public String getSrcb() {
		return srcb;
	}
	public void setSrcb(String srcb) {
		this.srcb = srcb;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Integer getWidthb() {
		return widthb;
	}
	public void setWidthb(Integer widthb) {
		this.widthb = widthb;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Integer getHeightb() {
		return heightb;
	}
	public void setHeightb(Integer heightb) {
		this.heightb = heightb;
	}
	
	
}
