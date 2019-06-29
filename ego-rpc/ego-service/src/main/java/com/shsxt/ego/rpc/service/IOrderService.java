package com.shsxt.ego.rpc.service;

import com.shsxt.ego.rpc.pojo.TbOrder;
import com.shsxt.ego.rpc.pojo.TbOrderItem;
import com.shsxt.ego.rpc.pojo.TbOrderShipping;

import java.util.List;

public interface IOrderService {
    void saveTbOrderService(TbOrder tbOrder, List<TbOrderItem> list, TbOrderShipping orderShipping);

    List<TbOrder> loadTbOrderListService(Long userId);
}
