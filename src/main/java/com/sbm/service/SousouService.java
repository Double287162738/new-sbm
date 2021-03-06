package com.sbm.service;

import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.GoodsSpec;
import com.sbm.pojo.model.SouSouInparameterDTO;
import com.sbm.util.ExecuteResult;
import com.sbm.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface SousouService {

    /**
     * 关键字搜索物品
     *
     * @param souSouInparameterDTO
     * @return
     */
    Page souGoods(SouSouInparameterDTO souSouInparameterDTO);


    /**
     * 查看物品详情
     *
     * @param goodsId
     * @return
     */
    ExecuteResult<Goods> souGoodsDetail(String goodsId,HttpServletRequest request);


    /**
     * 搜索个人发布的物品
     *
     * @param page
     * @param userId
     * @return
     */
    Page souPerFabuGoods(Page page, String userId, String yiFaBuKind, String keyword);


    /**
     * 搜索个人收藏物品
     *
     * @param page
     * @param userId
     * @return
     */
    Page souPerScGoods(Page page, String userId, String keyword);


    /**
     * 搜索个人回收站
     *
     * @param page
     * @param userId
     * @param keyword
     * @return
     */
    Page souPerRecycleGoods(Page page, String userId, String keyword);


    /**
     * 搜索导航悬浮的闲置
     * @param type
     * @return
     */
    List<Goods> souGoodsHover(String type);

    List<Goods> souMainGoods();
}
