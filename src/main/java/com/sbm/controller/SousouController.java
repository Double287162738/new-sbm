package com.sbm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbm.pojo.model.Goods;
import com.sbm.service.SousouService;
import com.sbm.util.ExecuteResult;
import com.sbm.util.Page;

@Controller
@RequestMapping("/sousou")
public class SousouController {
    @Resource
    private SousouService sousouService;


    /**
     * 搜索物品
     *
     * @param page
     * @param keyword
     * @return
     */
    @RequestMapping("/sougoods.do")
    @ResponseBody
    public Page souGoods(Page page, String keyword) {
        return sousouService.souGoods(page, keyword);
    }

    /**
     * 查看闲置的详细信息
     *
     * @param goodsId
     * @return
     */
    @RequestMapping("/sougoodsDetail.do")
    @ResponseBody
    public ExecuteResult<Goods> souGoods(String goodsId) {
        return sousouService.souGoodsDetail(goodsId);
    }
}
