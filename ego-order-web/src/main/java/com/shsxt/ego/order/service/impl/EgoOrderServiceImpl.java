package com.shsxt.ego.order.service.impl;

import com.shsxt.ego.order.model.CartItem;
import com.shsxt.ego.order.service.IEgoOrderService;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.pojo.TbOrder;
import com.shsxt.ego.rpc.pojo.TbOrderItem;
import com.shsxt.ego.rpc.pojo.TbOrderShipping;
import com.shsxt.ego.rpc.service.IOrderService;
import com.shsxt.ego.utils.IDUtils;
import com.shsxt.ego.utils.JsonUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class EgoOrderServiceImpl implements IEgoOrderService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOperations;

    @Resource
    private IOrderService orderServiceProxy;

    @Override
    public Map<Long, CartItem> loadCarItemMapService(Long id) {
        Map<Long,CartItem> carts =null;
        Map<String,String> map =  hashOperations.entries(String.valueOf(id));
        if(null !=map && map.size()>0){
            carts =new HashMap<Long,CartItem>();
            Map<Long, CartItem> finalCarts = carts;
            map.forEach((k, v)->{
                finalCarts.put(Long.parseLong(k), JsonUtils.jsonToPojo(v,CartItem.class));
            });
        }
        return carts;
    }

    @Override
    public Map<String, String> saveOrderService(TbOrder tbOrder,
                                                Long uid, TbOrderShipping orderShipping) {
        try {
            Date date = new Date();
            //产生订单号
            String orderid=String.valueOf(IDUtils.genItemId());
            tbOrder.setOrderId(orderid);
            tbOrder.setPostFee("123");
            tbOrder.setStatus(2);
            tbOrder.setCreateTime(date);
            tbOrder.setUpdateTime(date);
            tbOrder.setPaymentTime(date);
            tbOrder.setConsignTime(date);
            tbOrder.setEndTime(date);
            tbOrder.setCloseTime(date);
            tbOrder.setShippingName("EMS");
            tbOrder.setShippingCode("110110");
            tbOrder.setUserId(uid);
            tbOrder.setBuyerMessage("message");
            tbOrder.setBuyerNick("9527");
            tbOrder.setBuyerRate(0);
            //获得用户的购物车集合
            Map<Long,CartItem> carMap=this.loadCarItemMapService(uid);
            //创建List<TbOrderItem>
            List<TbOrderItem> list=new ArrayList<>();
            for(CartItem carItem:carMap.values()){
                //产生订单明细主键
                String id=String.valueOf(IDUtils.genItemId());
                //创建订单明细对象
                TbOrderItem orderItem=new TbOrderItem();
                orderItem.setId(id);
                orderItem.setOrderId(orderid);
                //获得购物车中的商品对象
                TbItem item=carItem.getItem();
                orderItem.setItemId(String.valueOf(item.getId()));
                orderItem.setNum(carItem.getNum());
                orderItem.setTitle(item.getTitle());
                orderItem.setPrice(item.getPrice());
                orderItem.setTotalFee(item.getPrice()*carItem.getNum());
                //orderItem.setPicPath(item.getImages()[0]);
                list.add(orderItem);
            }

            orderShipping.setOrderId(orderid);
            orderShipping.setReceiverPhone("1110");
            orderShipping.setCreated(date);
            orderShipping.setUpdated(date);

            //调用rpc远程服务
            orderServiceProxy.saveTbOrderService(tbOrder, list, orderShipping);

            Map<String, String> map=new HashMap<>();
            map.put("orderid",orderid);
            map.put("total",tbOrder.getPayment());

            // 清空购物车
            redisTemplate.delete(String.valueOf(uid));

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TbOrder> loadOrderListService(Long userId) {
        return orderServiceProxy.loadTbOrderListService(userId);
    }
}
