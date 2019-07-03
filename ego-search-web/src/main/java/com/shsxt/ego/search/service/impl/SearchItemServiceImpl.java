package com.shsxt.ego.search.service.impl;

import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.service.IItemService;
import com.shsxt.ego.search.entity.SearchResult;
import com.shsxt.ego.search.model.GoodsVo;
import com.shsxt.ego.search.service.ISearchItemService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchItemServiceImpl implements ISearchItemService {
	//调用远程服务的代理对象
	@Autowired
	private IItemService itemServiceProxy;

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public TbItem loadItemService(Long id) {
		return itemServiceProxy.loadItemService(id);
	}

	@Override
	public SearchResult queryItemByKey(String key, Integer page) {
		SearchQuery searchQuery =new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.multiMatchQuery(key,"title"))
				.withHighlightFields(new HighlightBuilder.Field("title").postTags("</font>").preTags("<font color='red'>"))
				.withPageable(new PageRequest(page-1,10))
				.build();
		Page<GoodsVo> pg = elasticsearchTemplate.queryForPage(searchQuery, GoodsVo.class, new SearchResultMapper() {
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
		SearchResult result = new SearchResult();
		result.setList(pg.getContent());
		result.setMaxpage(Long.parseLong(pg.getTotalPages()+""));
		result.setTotal(Long.parseLong(pg.getTotalElements()+""));
		return result;
	}

}
