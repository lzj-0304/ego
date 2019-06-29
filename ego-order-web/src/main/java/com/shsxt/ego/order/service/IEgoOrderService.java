package com.shsxt.ego.order.service;

import com.shsxt.ego.order.model.CartItem;
import com.shsxt.ego.rpc.pojo.TbOrder;
import com.shsxt.ego.rpc.pojo.TbOrderShipping;

import java.util.List;
import java.util.Map;

public interface IEgoOrderService {
    Map<Long, CartItem> loadCarItemMapService(Long id);

    Map<String, String> saveOrderService(TbOrder tbOrder, Long userId, TbOrderShipping orderShipping);

    List<TbOrder> loadOrderListService(Long id);
}
