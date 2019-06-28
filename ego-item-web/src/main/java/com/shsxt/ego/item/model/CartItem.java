package com.shsxt.ego.item.model;

import com.shsxt.ego.rpc.pojo.TbItem;

import java.io.Serializable;

/****
 * 购物车类
 * @author Administrator
 *
 */
public class CartItem implements Serializable{

	private TbItem item; //购买商品对象
	private Integer num; //购买商品的数量
	public TbItem getItem() {
		return item;
	}
	public void setItem(TbItem item) {
		this.item = item;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	
}
