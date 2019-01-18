package com.sbm.controller;

import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.GoodsSpec;
import com.sbm.pojo.model.SouSouInparameterDTO;
import com.sbm.service.SousouService;
import com.sbm.util.ExecuteResult;
import com.sbm.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/sousou")
public class SousouController {
    @Resource
    private SousouService sousouService;


    /**
     * 搜索物品
     *
     * @param souSouInparameterDTO
     * @return
     */
    @RequestMapping("/sougoods.do")
    @ResponseBody
    public Page souGoods(SouSouInparameterDTO souSouInparameterDTO) {
        return sousouService.souGoods(souSouInparameterDTO);
    }

    /**
     * 查看闲置的详细信息
     *
     * @param goodsId
     * @return
     */
    @RequestMapping("/sougoodsDetail.do")
    @ResponseBody
    public ExecuteResult<Goods> souGoods(String goodsId, HttpServletRequest request) {
        return sousouService.souGoodsDetail(goodsId,request);
    }

    @RequestMapping("/sougoodsHover.do")
    @ResponseBody
    public List<Goods> souGoodsHover(String type){
        return sousouService.souGoodsHover(type);
    }


    /**
     * main.html页面展示的商品
     * @return
     */
    @RequestMapping("/souMainGoods.do")
    @ResponseBody
    public List<Goods> souMainGoods(){
        return sousouService.souMainGoods();
    }
}
