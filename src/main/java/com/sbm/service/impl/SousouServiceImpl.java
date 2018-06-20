package com.sbm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sbm.mapper.CollectionMapper;
import com.sbm.mapper.GoodsMapper;
import com.sbm.mapper.RecycleMapper;
import com.sbm.pojo.model.Collection;
import com.sbm.pojo.model.CollectionExample;
import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.GoodsExample;
import com.sbm.pojo.model.Recycle;
import com.sbm.pojo.model.RecycleExample;
import com.sbm.service.SousouService;
import com.sbm.util.ExecuteResult;
import com.sbm.util.Page;

import static java.util.Arrays.asList;

import java.util.ArrayList;


@Service("sousouService")
public class SousouServiceImpl implements SousouService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private CollectionMapper CollectionMapper;
    @Resource
    private RecycleMapper recycleMapper;

    @Override
    public Page souGoods(Page page, String keyword) {
        Page resultPage = new Page();
        //拼接搜索关键字
        StringBuilder newKeyWord = new StringBuilder();
        newKeyWord.append("%");
        for (int i = 0; i < keyword.length(); i++) {
            newKeyWord.append(keyword.charAt(i));
            newKeyWord.append("%");
        }
        //搜索结果包含字段
        List<String> stringList = asList("goodsId", "userId", "goodsName", "goodsPrice", "goodsWords", "goodsQq", "goodsWx", "goodsClickAmount",
                "goodsOther", "goodsPic1", "goodsPic2", "goodsPic3", "goodsPic4", "goodsPic5", "goodsPic6", "goodsPic7",
                "goodsPic8", "goodsPic9");
        //查询
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.addSelectiveFields(stringList);
        goodsExample.setPage(page);
        goodsExample.setOrderByClause("goods_no ASC");
        goodsExample.createCriteria().andGoodsNameLike(newKeyWord.toString());
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        Integer count = goodsMapper.countByExample(goodsExample);
        resultPage.setRecords(goodsList);
        resultPage.setTotalRecord(count);
        return resultPage;
    }


    @Override
    public ExecuteResult<Goods> souGoodsDetail(String goodsId) {
        ExecuteResult<Goods> result = new ExecuteResult<>();
        if (goodsId == null) {
            result.addErrorMessage("请选择查看的闲置");
            return result;
        }
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsIdEqualTo(goodsId);
        Goods goods = goodsMapper.selectOneByExample(goodsExample);
        if (goods == null) {
            result.addErrorMessage("查看的闲置不存在，请联系管理员");
            return result;
        }
        result.setResult(goods);
        return result;
    }


    @Override
    public Page souPerFabuGoods(Page page, String userId, String yiFaBuKind, String keyword) {
        Page resultPage = new Page();
        resultPage.setPageSize(3);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        if (!yiFaBuKind.equals("ALL")) {
            criteria.andGoodsTypeEqualTo(yiFaBuKind);
        }
        if (StringUtils.isNotEmpty(keyword)) {
            criteria.andGoodsNameLike("%" + keyword + "%");
        }
        //根据创建时间降序查询
        goodsExample.setOrderByClause("goods_last_mod DESC");
        goodsExample.setPage(page);
        List<Goods> resultGoodsList = goodsMapper.selectByExample(goodsExample);
        Integer count = goodsMapper.countByExample(goodsExample);
        resultPage.setRecords(resultGoodsList);
        resultPage.setTotalRecord(count);
        return resultPage;
    }


    @Override
    public Page souPerScGoods(Page page, String userId, String keyword) {
        CollectionExample collectionExample = new CollectionExample();
        collectionExample.createCriteria().andUserIdEqualTo(userId);
        collectionExample.addSelectiveField("goodsId");
        collectionExample.setOrderByClause("create_time DESC");
        List<Collection> collections = CollectionMapper.selectByExample(collectionExample);
        List<String> goodsIdList = new ArrayList<>();
        if (collections == null || collections.size() < 1) {
            return null;
        } else {
            for (Collection c : collections) {
                goodsIdList.add(c.getGoodsId());
            }
        }
        Page resultPage = new Page();
        resultPage.setPageSize(3);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andGoodsIdIn(goodsIdList);
        if (StringUtils.isNotEmpty(keyword.trim())) {
            criteria.andGoodsNameLike("%" + keyword.trim() + "%");
        }
        goodsExample.setPage(page);
        List<Goods> resultGoodsList = goodsMapper.selectByExample(goodsExample);
        Integer count = goodsMapper.countByExample(goodsExample);
        resultPage.setRecords(resultGoodsList);
        resultPage.setTotalRecord(count);
        return resultPage;
    }


    @Override
    public Page souPerRecycleGoods(Page page, String userId, String keyword) {
        RecycleExample recycleExample = new RecycleExample();
        recycleExample.createCriteria().andUserIdEqualTo(userId);
        recycleExample.addSelectiveField("goodsId");
        recycleExample.setOrderByClause("create_time DESC");
        List<Recycle> recycles = recycleMapper.selectByExample(recycleExample);
        if (recycles == null || recycles.size() < 1) {
            return null;
        }
        List<String> goodsIdList = new ArrayList<>();
        for (Recycle r : recycles) {
            goodsIdList.add(r.getGoodsId());
        }
        Page resultPage = new Page();
        resultPage.setPageSize(3);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andGoodsIdIn(goodsIdList);
        if (StringUtils.isNotEmpty(keyword.trim())) {
            criteria.andGoodsNameLike("%" + keyword.trim() + "%");
        }
        goodsExample.setPage(page);
        List<Goods> resultGoodsList = goodsMapper.selectByExample(goodsExample);
        Integer count = goodsMapper.countByExample(goodsExample);
        resultPage.setRecords(resultGoodsList);
        resultPage.setTotalRecord(count);
        return resultPage;
    }


}
