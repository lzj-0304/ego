package com.shsxt.ego.rpc.service.impl;

import com.shsxt.ego.rpc.manager.db.dao.TbOrderItemMapper;
import com.shsxt.ego.rpc.manager.db.dao.TbOrderMapper;
import com.shsxt.ego.rpc.manager.db.dao.TbOrderShippingMapper;
import com.shsxt.ego.rpc.pojo.TbOrder;
import com.shsxt.ego.rpc.pojo.TbOrderItem;
import com.shsxt.ego.rpc.pojo.TbOrderShipping;
import com.shsxt.ego.rpc.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;
    @Override
    public void saveTbOrderService(TbOrder tbOrder, List<TbOrderItem> orderItems, TbOrderShipping tbOrderShipping) {
        try {
            tbOrderMapper.insert(tbOrder);
            for(TbOrderItem orderItem:orderItems){

                tbOrderItemMapper.insert(orderItem);
            }
            tbOrderShippingMapper.insert(tbOrderShipping);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TbOrder> loadTbOrderListService(Long userId) {
        return tbOrderMapper.loadTbOrderListService(userId);
    }
}
