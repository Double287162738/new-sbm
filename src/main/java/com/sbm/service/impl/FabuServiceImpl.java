package com.sbm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sbm.mapper.GoodsMapper;
import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.User;
import com.sbm.service.FabuService;
import com.sbm.service.UserService;
import com.sbm.util.ExecuteResult;
import com.sbm.util.GetUuidUtils;
import com.sbm.util.SkssConstant;
import com.sbm.util.StringToListUtils;


@Service("fabuService")
public class FabuServiceImpl implements FabuService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private UserService UserService;

    @Override
    public ExecuteResult<String> fabuGoods(Goods goods, String userId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        if (userId == null) {
            result.setResult(SkssConstant.RESULT_FAIL);
            result.addErrorMessage("用户未登录，请登录后再发布");
            return result;
        }
        User user = UserService.getUserByUserId(userId);
        if (user == null) {
            result.setResult(SkssConstant.RESULT_FAIL);
            result.addErrorMessage("该用户不存在，请确认后再发布");
            return result;
        }
        if (StringUtils.isNotBlank(checkGoods(goods))) {
            result.setResult(SkssConstant.RESULT_FAIL);
            result.addErrorMessage(checkGoods(goods));
            return result;
        }
        //给物品一个ID
        goods.setUserName(user.getUserName());
        goods.setGoodsId(GetUuidUtils.getUUID());
        goods.setUserId(userId);
        goods.setGoodsNo(Long.toString(System.currentTimeMillis()));
        makeNewGoodsPic(goods);
        goods.setGoodsGmtCreate(new Date());
        goodsMapper.insertSelective(goods);
        result.setResult(SkssConstant.RESULT_SUCCESS);
        result.setSuccessMessage("发布成功");
        return result;
    }


    //校验发布的物品
    public String checkGoods(Goods goods) {
        StringBuilder errorMsg = new StringBuilder();
        StringBuilder s = new StringBuilder();
        s.append("为必填项。");
        if (StringUtils.isBlank(goods.getGoodsType())) {
            errorMsg.append("发布类型、");
        }
        if (StringUtils.isBlank(goods.getGoodsArea())) {
            errorMsg.append("区域、");
        }
        if (StringUtils.isBlank(goods.getGoodsName())) {
            errorMsg.append("名字、");
        }
        if (StringUtils.isBlank(goods.getGoodsPrice())) {
            errorMsg.append("价格、");
        }
        if (StringUtils.isBlank(goods.getGoodsWords())) {
            errorMsg.append("特点、");
        }
        if (StringUtils.isBlank(goods.getGoodsQq()) && StringUtils.isBlank(goods.getGoodsWx())) {
            errorMsg.append("QQ或微信、");
        }
        if (StringUtils.isBlank(goods.getGoodsOther())) {
            errorMsg.append("介绍、");
        }
        if (StringUtils.isBlank(goods.getGoodsPic1())) {
            errorMsg.append("封面图片、");
        }
        if (StringUtils.isBlank(goods.getGoodsPic2())) {
            errorMsg.append("详情图片、");
        }
        if (errorMsg.length() > 0) {
            String newErrorMsg = errorMsg.substring(0, errorMsg.length() - 1);
            return newErrorMsg + s;
        } else {
            return null;
        }
    }


    public void makeNewGoodsPic(Goods goods) {
        List<String> pic2List = new ArrayList<>();
        pic2List = StringToListUtils.changeToList(goods.getGoodsPic2(), ",");
        goods.setGoodsPic2(pic2List.get(0));
        if (2 <= pic2List.size()) {
            goods.setGoodsPic3(pic2List.get(1));
        }
        if (3 <= pic2List.size()) {
            goods.setGoodsPic4(pic2List.get(2));
        }
        if (4 <= pic2List.size()) {
            goods.setGoodsPic5(pic2List.get(3));
        }
        if (5 <= pic2List.size()) {
            goods.setGoodsPic6(pic2List.get(4));
        }
        if (6 <= pic2List.size()) {
            goods.setGoodsPic7(pic2List.get(5));
        }
        if (7 <= pic2List.size()) {
            goods.setGoodsPic8(pic2List.get(6));
        }
        if (8 <= pic2List.size()) {
            goods.setGoodsPic9(pic2List.get(7));
        }
    }

}
