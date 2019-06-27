package com.shsxt.ego.portal.service.impl;

import com.shsxt.ego.model.CatNode;
import com.shsxt.ego.model.CatResult;
import com.shsxt.ego.portal.service.IPortalItemCatService;
import com.shsxt.ego.rpc.pojo.TbItemCat;
import com.shsxt.ego.rpc.service.IItemCatService;
import com.shsxt.ego.utils.JsonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PortalItemCatServiceImpl implements IPortalItemCatService {
    @Resource
    private IItemCatService itemCatServiceProxy;
    @Override
    public String loadItemCatService() {
        List<TbItemCat> itemCatList = itemCatServiceProxy.queryItemCatsList();
        //将List转化为符合前端规范数据格式,递归遍历list
        List<?> data = getChildren(0L,itemCatList);
        //创建CatResult对象
        CatResult result=new CatResult();
        result.setData(data);
        //将result对象序列化json字符串
        return JsonUtils.objectToJson(result);
    }

    private List<?> getChildren(long parentId, List<TbItemCat> itemCats) {
        // 盛放指定分类下的所有子分类信息
        List resultList = new ArrayList();
        for (TbItemCat itemCat : itemCats) {
            if (itemCat.getParentId().equals(parentId)) {
                if (itemCat.getIsParent()) {
                    // 如果itemCat代表一级分类或者二级分类
                    CatNode catNode = new CatNode();
                    if (itemCat.getParentId().longValue() == 0) {
                        // 如果是一级分类 "<a href='/products/1.html'>图书、音像、电子书刊</a>",
                        catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                    } else {
                        // 如果是二级分类 "电子书刊",
                        catNode.setName(itemCat.getName());
                    }
                    // "/products/2.html",
                    catNode.setUrl("/products/" + itemCat.getId() + ".html");
                    catNode.setList(getChildren(itemCat.getId(), itemCats));
                    // 将节点添加到list集合中
                    resultList.add(catNode);
                } else {
                    // 如果itemCat表示三级分类 "/products/3.html|电子书",
                    resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
                }
            }
        }
        return resultList;
    }
}
