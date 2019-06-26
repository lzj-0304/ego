package com.shsxt.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.manager.db.dao.TbItemDescMapper;
import com.shsxt.ego.rpc.manager.db.dao.TbItemMapper;
import com.shsxt.ego.rpc.manager.db.dao.TbItemParamItemMapper;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.pojo.TbItemDesc;
import com.shsxt.ego.rpc.pojo.TbItemParamItem;
import com.shsxt.ego.rpc.query.ItemQuery;
import com.shsxt.ego.rpc.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Override
    public PageResult<TbItem> queryItemsByParams(ItemQuery itemQuery) {
        //执行分页操作
        Page ps = PageHelper.startPage(itemQuery.getPage(), itemQuery.getRows());
        //执行数据库查询操作
        List<TbItem> list = itemMapper.itemListByParams(itemQuery);
        PageResult<TbItem> result = new PageResult<TbItem>();
        result.setRows(list);
        result.setTotal(ps.getTotal());
        return result;
    }

    @Override
    public EgoResult updateItemStatus(List<Long> itemIds, Integer status) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("ids",itemIds);
        params.put("status",status);
        itemMapper.updateItemStatus(params);
        return new EgoResult();
    }

    @Override
    public EgoResult saveItem(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem itemParamItem) {
        /**
         * 商品表
         *  tb_item
         * 商品信息描述表
         *  tb_item_desc
         *  商品规格信息
         *    tb_item_param_item
         */
        itemMapper.insertSelective(tbItem);
        itemDescMapper.insertSelective(tbItemDesc);
        itemParamItemMapper.insertSelective(itemParamItem);
        return new EgoResult();
    }

    @Override
    public EgoResult updateItem(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem itemParamItem) {
        /**
         * 商品表
         *  tb_item
         * 商品信息描述表
         *  tb_item_desc
         */
        itemMapper.updateByPrimaryKeySelective(tbItem);
        itemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
        itemParamItemMapper.updateByPrimaryKeySelective(itemParamItem);
        return new EgoResult();
    }


    /**
     * 删除商品记录
     * @param itemIds
     * @return
     */
    @Override
    public EgoResult itemDelete(List<Long> itemIds) {
        /**
         * 批量更新商品状态
         * 批量删除商品描述信息
         * 批量删除商品规格信息
         */
        itemDescMapper.deleteByItemIds(itemIds);
        itemParamItemMapper.deleteByItemIds(itemIds);
        itemMapper.itemDelete(itemIds);
        return new EgoResult();
    }


}
