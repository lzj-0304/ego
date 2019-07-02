package com.shsxt.ego.search;


import com.shsxt.ego.search.model.GoodsVo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-elasticsearch.xml")
public class TestEs02 {
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void test01(){

        System.out.println(elasticsearchTemplate);
        SearchQuery searchQuery =new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("手机","title"))
                .withHighlightFields(new HighlightBuilder.Field("title").postTags("</font>").preTags("<font style='red'>"))
                .withPageable(new PageRequest(0,10))
                .build();
        Page<GoodsVo> result = elasticsearchTemplate.queryForPage(searchQuery, GoodsVo.class, new SearchResultMapper() {
            @Override
            public <T> Page<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                List<GoodsVo> goodsVoList =new ArrayList<GoodsVo>();
                SearchHits hits= searchResponse.getHits();
                System.out.println("总记录-->"+searchResponse.getHits().getTotalHits());
                for(SearchHit hit:hits){
                    GoodsVo goodsVo =new GoodsVo();
                    goodsVo.setId(Long.parseLong(hit.getSource().get("id").toString()));
                    goodsVo.setImage((String) hit.getSource().get("image"));
                    goodsVo.setTitle((String) hit.getSource().get("title"));
                    goodsVo.setPrice(Long.parseLong(hit.getSource().get("price").toString()));
                    goodsVo.setTitleHl(Arrays.toString(hit.getHighlightFields().get("title").fragments()));
                    goodsVoList.add(goodsVo);
                }
                return (Page<T>) new PageImpl<GoodsVo>(goodsVoList,pageable,searchResponse.getHits().getTotalHits());
            }
        });
        System.out.println(result.getTotalPages());
        result.getContent().forEach((g)->{
            System.out.println(g);
        });
    }





}
