package com.shsxt.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shsxt.ego.model.EgoResult;
import com.shsxt.ego.model.PageResult;
import com.shsxt.ego.rpc.manager.db.dao.TbItemParamMapper;
import com.shsxt.ego.rpc.pojo.TbItemParam;
import com.shsxt.ego.rpc.query.ItemParamQuery;
import com.shsxt.ego.rpc.service.IItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements IItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;
    @Override
    public PageResult<TbItemParam> queryItemParamByParams(ItemParamQuery itemParamQuery) {
        //执行分页操作
        Page ps = PageHelper.startPage(itemParamQuery.getPage(), itemParamQuery.getRows());
        //执行数据库查询操作
        List<TbItemParam> list = itemParamMapper.itemParamListByParams(itemParamQuery);
        PageResult<TbItemParam> result = new PageResult<TbItemParam>();
        result.setRows(list);
        result.setTotal(ps.getTotal());
        return result;
    }





    @Override
    public EgoResult deleteItemParam(Long[] ids) {
        itemParamMapper.deleteItemParam(Arrays.asList(ids));
        return new EgoResult();
    }

    @Override
    public EgoResult loadItemParamByCid(Long cid) {
        EgoResult egoResult =new EgoResult();
        TbItemParam itemParam =  itemParamMapper.loadItemParamByCid(cid);
        if(null !=itemParam){
            egoResult.setData(itemParam);
        }
        return egoResult;
    }

    @Override
    public EgoResult saveItemParamService(Long cid, String paramData) {
        TbItemParam tbItemParam=new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        itemParamMapper.insertSelective(tbItemParam);
        return new EgoResult();
    }
}
