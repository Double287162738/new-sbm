package com.sbm.service.impl;

import com.sbm.mapper.CollectionMapper;
import com.sbm.mapper.GoodsMapper;
import com.sbm.pojo.model.Collection;
import com.sbm.pojo.model.CollectionExample;
import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.GoodsExample;
import com.sbm.service.CollectionService;
import com.sbm.util.GetUuidUtils;
import com.sbm.util.SnowflakeIdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {

    @Resource
    private CollectionMapper collectionMapper;
    @Resource
    private GoodsMapper goodsMapper;

    SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);

    @Override
    public void addCollection(String goodsIds,String userId) {
        //增加收藏记录
        Collection collection = new Collection();
        collection.setCollectionId(String.valueOf(snowflakeIdWorker.nextId()));
        collection.setCreateTime(new Date());
        collection.setGoodsId(goodsIds);
        collection.setUserId(userId);
        collection.setVersion(1);
        collectionMapper.insertSelective(collection);
        //更新收藏次数
        GoodsExample goodsExample = new GoodsExample();
        List<String> list = Arrays.asList("goodsCollectionAmount","goodsId");
        goodsExample.addSelectiveFields(list);
        goodsExample.createCriteria().andGoodsIdEqualTo(goodsIds);
        Goods goods = goodsMapper.selectOneByExample(goodsExample);
        if(goods!=null){
            //更新收藏次数
            if(StringUtils.isBlank(goods.getGoodsCollectionAmount())){
                goods.setGoodsCollectionAmount("0");
            }
            goods.setGoodsCollectionAmount(String.valueOf(Integer.valueOf(goods.getGoodsCollectionAmount())+1));
            goodsMapper.updateByPrimaryKeySelective(goods);
        }
    }
}