package com.shsxt.ego.manager.service.impl;

import com.shsxt.ego.manager.service.IManagerItemService;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.pojo.TbItemDesc;
import com.shsxt.ego.rpc.pojo.TbItemParamItem;
import com.shsxt.ego.rpc.query.ItemQuery;
import com.shsxt.ego.rpc.service.IItemDescService;
import com.shsxt.ego.rpc.service.IItemParamItemService;
import com.shsxt.ego.rpc.service.IItemService;
import com.shsxt.ego.utils.IDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class ManagerItemServiceImpl implements IManagerItemService {
    @Resource
    private IItemService itemServiceProxy;

    @Resource
    private IItemDescService itemDescServiceProxy;

    @Resource
    private IItemParamItemService itemParamItemServiceProxy;

    @Override
    public PageResult<TbItem> queryItemsByParams(ItemQuery itemQuery) {
        return itemServiceProxy.queryItemsByParams(itemQuery);
    }

    @Override
    public EgoResult shelfItem(Long[] ids) {
        //将ids数组转化为List集合
        List<Long> itemIds = Arrays.asList(ids);
        //调用远程服务
        return itemServiceProxy.updateItemStatus(itemIds, 1);
    }

    @Override
    public EgoResult instockItem(Long[] ids) {
        //将ids数组转化为List集合
        List<Long> itemIds = Arrays.asList(ids);
        return itemServiceProxy.updateItemStatus(itemIds,2);
    }

    @Override
    public EgoResult itemDelete(Long[] ids) {
        List<Long> itemIds = Arrays.asList(ids);
        //调用远程删除商品信息的服务
        return itemServiceProxy.itemDelete(itemIds);
    }

    @Override
    public EgoResult saveItem(TbItem item) {
        /**
         *
         */
        Date date = new Date();

        //自己产生商品的id，满足后期的分开分表的需求
        Long id= IDUtils.genItemId();

        //给item对象封装数据
        item.setId(id);
        item.setStatus((byte) 1);
        item.setCreated(date);
        item.setUpdated(date);

        //创建TbItemDesc对象
        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemDesc(item.getDesc());
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);


        /**
         * 创建TbItemParamItem 对象 添加商品规格信息
         */
        TbItemParamItem tbItemParamItem =new TbItemParamItem();
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setParamData(item.getItemParams());


        return itemServiceProxy.saveItem(item,tbItemDesc,tbItemParamItem);
    }

    @Override
    public TbItemDesc queryItemDescByItemId(Long itemId) {
        return itemDescServiceProxy.queryItemDescByItemId(itemId);
    }

    @Override
    public EgoResult updateItem(TbItem tbItem) {
        tbItem.setUpdated(new Date());
        TbItemDesc itemDesc =itemDescServiceProxy.queryItemDescByItemId(tbItem.getId());
        itemDesc.setItemDesc(tbItem.getDesc());
        itemDesc.setUpdated(new Date());


        TbItemParamItem itemParamItem = itemParamItemServiceProxy.queryItemParamItemByItemId(tbItem.getId());
        itemParamItem.setParamData(tbItem.getItemParams());
        itemParamItem.setUpdated(new Date());

        return itemServiceProxy.updateItem(tbItem,itemDesc,itemParamItem);
    }


}
