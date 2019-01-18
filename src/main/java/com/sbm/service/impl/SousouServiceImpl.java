package com.sbm.service.impl;

import com.sbm.mapper.*;
import com.sbm.mapper.customerMapper.GoodsCustomerMapper;
import com.sbm.pojo.model.*;
import com.sbm.service.SousouService;
import com.sbm.util.ExecuteResult;
import com.sbm.util.Page;
import com.sbm.util.SkssConstant;
import com.sbm.util.SnowflakeIdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;


@Service("sousouService")
public class SousouServiceImpl implements SousouService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsCustomerMapper goodsCustomerMapper;
    @Resource
    private CollectionMapper collectionMapper;
    @Resource
    private UserOperationMapper userOperationMapper;

    SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);


    @Override
    public Page souGoods(SouSouInparameterDTO souSouInparameterDTO) {
        Page page = new Page();
        page.setCurrentPage(souSouInparameterDTO.getCurrentPage());
        Page resultPage = new Page();
        //拼接搜索关键字
        StringBuilder newKeyWord = new StringBuilder();
        if(StringUtils.isNotBlank(souSouInparameterDTO.getKeyWord())){
            newKeyWord.append("%");
            for (int i = 0; i < souSouInparameterDTO.getKeyWord().length(); i++) {
                newKeyWord.append(souSouInparameterDTO.getKeyWord().charAt(i));
                newKeyWord.append("%");
            }
        }
        //搜索结果包含字段
        List<String> stringList = asList("goodsId", "userId", "goodsName", "goodsPrice", "goodsWords", "goodsQq", "goodsWx", "goodsClickAmount",
                "goodsOther", "goodsPic1", "goodsPic2", "goodsPic3", "goodsPic4", "goodsPic5");
        if(StringUtils.isBlank(souSouInparameterDTO.getSouType()) || souSouInparameterDTO.getSouType().equals("all")){
            GoodsExample goodsExample = new GoodsExample();
            GoodsExample.Criteria criteria = goodsExample.createCriteria();
            goodsExample.addSelectiveFields(stringList);
            goodsExample.setPage(page);
            goodsExample.setOrderByClause("goods_no ASC");
            if(souSouInparameterDTO.getSouArea()==null || souSouInparameterDTO.getSouArea().size()==0 ){
                if(StringUtils.isNotBlank(newKeyWord.toString())){
                    criteria.andGoodsNameLike(newKeyWord.toString());
                }
            }else{
                if(StringUtils.isNotBlank(newKeyWord.toString())){
                    criteria.andGoodsNameLike(newKeyWord.toString()).andGoodsAreaIn(souSouInparameterDTO.getSouArea());
                }else{
                    criteria.andGoodsAreaIn(souSouInparameterDTO.getSouArea());
                }
            }
            if(StringUtils.isNotBlank(souSouInparameterDTO.getSpecialType())){
                criteria.andGoodsSpecificTypeEqualTo(souSouInparameterDTO.getSpecialType());
            }
            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
            Integer count = goodsMapper.countByExample(goodsExample);
            resultPage.setRecords(goodsList);
            resultPage.setTotalRecord(count);
        }else {
            try{
                souSouInparameterDTO.setPage(page);
                souSouInparameterDTO.setKeyWord(newKeyWord.toString());
                List<Goods> goodsList = goodsCustomerMapper.sousou(souSouInparameterDTO);
                Integer count = goodsCustomerMapper.sousouCount(souSouInparameterDTO);
                resultPage.setRecords(goodsList);
                resultPage.setTotalRecord(count);
            }catch (Exception e){
                e.getMessage();
            }
        }
        return resultPage;
    }


    @Override
    public ExecuteResult<Goods> souGoodsDetail(String goodsId,HttpServletRequest request) {
        ExecuteResult<Goods> result = new ExecuteResult<>();
        if (StringUtils.isBlank(goodsId)) {
            result.addErrorMessage("请选择查看的闲置");
            return result;
        }
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsIdEqualTo(goodsId);
        Goods goods = goodsMapper.selectOneByExample(goodsExample);
        if (goods == null) {
            result.addErrorMessage("查看的闲置不存在");
            return result;
        }
        //获取用户信息
        HttpSession session = request.getSession();
        Object object = session.getAttribute("user");
        if(object==null){
            goods.setIfCollected(false);
        }else {
            User user = (User)object;
            CollectionExample collectionExample = new CollectionExample();
            collectionExample.createCriteria().andGoodsIdEqualTo(goodsId).andUserIdEqualTo(user.getUserId());
            int count = collectionMapper.countByExample(collectionExample);
            if(count>0){
                goods.setIfCollected(true);
            }else {
                goods.setIfCollected(false);
            }
            //增加用户操作记录(用户登录浏览才有效且非浏览自己的商品)
            if(!goods.getUserId().equals(user.getUserId())){
                UserOperation userOperation = new UserOperation();
                userOperation.setOperationId(String.valueOf(snowflakeIdWorker.nextId()));
                userOperation.setCreateTime(new Date());
                userOperation.setOperationUserId(user.getUserId());
                userOperation.setOperationUserIphone(user.getUserPhone());
                userOperation.setOperationDetail("浏览商品");
                userOperation.setOperationGoodsId(goodsId);
                userOperationMapper.insert(userOperation);
                Goods newGoods = new Goods();
                newGoods.setGoodsId(goodsId);
                if(StringUtils.isBlank(goods.getGoodsClickAmount())){
                    goods.setGoodsClickAmount("0");
                }
                //增加点击查看数量
                newGoods.setGoodsClickAmount(String.valueOf(Integer.valueOf(goods.getGoodsClickAmount())+1));
                goodsMapper.updateByPrimaryKeySelective(newGoods);
            }
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
        criteria.andGoodsStatusEqualTo(SkssConstant.GOODS_ACTIVE_STATUS);
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
        List<Collection> collections = collectionMapper.selectByExample(collectionExample);
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
        Page resultPage = new Page();
        resultPage.setPageSize(3);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andGoodsStatusEqualTo(SkssConstant.GOODS_RECYCLE_STATUS);
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
    public List<Goods> souGoodsHover(String type) {
        List<Goods> result = new ArrayList<>();
        try {
            result= goodsCustomerMapper.souGoodsHover(type);
        }catch (Exception e){
            e.getMessage();
        }
        return result;
    }

    @Override
    public List<Goods> souMainGoods() {
        List<Goods> result = goodsCustomerMapper.sousouOnlyTen();
        return result;
    }


}
