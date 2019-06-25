package com.shsxt.ego;

import com.shsxt.ego.rpc.service.IItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-dao.xml",
        "classpath:spring/applicationContext-service.xml",
        "classpath:spring/applicationContext-tx.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestItemService {
    @Resource
    private IItemService itemService;

    @Test
    public void test01(){
        itemService.updateItemStatus(Arrays.asList(536563L,562379L),1);
    }
}
