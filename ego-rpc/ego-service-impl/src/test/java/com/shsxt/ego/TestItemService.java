package com.shsxt.ego;

import com.shsxt.ego.rpc.pojo.TbContentCategory;
import com.shsxt.ego.rpc.service.IContentCategoryService;
import com.shsxt.ego.rpc.service.IItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-dao.xml",
        "classpath:spring/applicationContext-service.xml",
        "classpath:spring/applicationContext-tx.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestItemService {
    @Resource
    private IItemService itemService;

    @Resource
    private IContentCategoryService contentCategoryService;

    @Test
    public void test01(){
        itemService.updateItemStatus(Arrays.asList(536563L,562379L),1);
    }


    @Test
    public void test02(){
        TbContentCategory contentCategory =new TbContentCategory();
        //创建Date对象
        Date date=new Date();
        contentCategory.setCreated(date);
        contentCategory.setUpdated(date);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);
        contentCategory.setParentId(86L);
        contentCategory.setName("test");
        contentCategoryService.saveTbContentCateGory(contentCategory);
    }
}
