package com.sbm.service.impl;

import com.sbm.mapper.CollectionMapper;
import com.sbm.pojo.model.Collection;
import com.sbm.pojo.model.CollectionExample;
import com.sbm.service.CollectionService;
import com.sbm.util.GetUuidUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {

    @Resource
    private CollectionMapper collectionMapper;

    @Override
    public void addCollection(String goodsIds,String userId) {
        CollectionExample collectionExample = new CollectionExample();
        collectionExample.createCriteria().andGoodsIdEqualTo(goodsIds).andUserIdEqualTo(userId);
        Collection ifExist = collectionMapper.selectOneByExample(collectionExample);
        //如果不存在即添加收藏，否则不添加
        if(ifExist==null || StringUtils.isBlank(ifExist.getCollectionId())){
            Collection collection = new Collection();
            collection.setCollectionId(GetUuidUtils.getUUID());
            collection.setCreateTime(new Date());
            collection.setGoodsId(goodsIds);
            collection.setUserId(userId);
            collection.setVersion(1);
            collectionMapper.insertSelective(collection);
        }
    }
}