package com.sbm.service.impl;

import com.sbm.mapper.CollectionMapper;
import com.sbm.mapper.GoodsMapper;
import com.sbm.mapper.UserMapper;
import com.sbm.pojo.model.*;
import com.sbm.service.UserPersonCenterServcie;
import com.sbm.util.ExecuteResult;
import com.sbm.util.SkssConstant;
import com.sbm.util.StringToListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("userPersonCenterServcie")
public class UserPersonCenterServcieImpl implements UserPersonCenterServcie {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CollectionMapper collectionMapper;


    @Override
    public void updateAndDelFilOtherPic(String needDelOtherPicIds, String goodsId,Goods goods) {
        if (StringUtils.isNotBlank(needDelOtherPicIds) && !"null".equals(needDelOtherPicIds)) {
            //拆分需要删除的图片ID
            List<String> idStrings = StringToListUtils.changeToList(needDelOtherPicIds, ",");
            //查询出该商品在数据库的当前信息
            Goods oldGood = goodsMapper.selectByPrimaryKey(goodsId);
            //删除已经存在的图片,置空数据库中对应的字段
            for (String s : idStrings) {
                if (Integer.valueOf(s) == 2) {
                    //置空需要删除的字段
                    goods.setGoodsPic2("");
                    //删除服务器的旧的图片
                    deleteOtherPic(oldGood.getGoodsPic2());
                }
                if (Integer.valueOf(s) == 3) {
                    //置空需要删除的字段
                    goods.setGoodsPic3("");
                    //删除服务器的旧的图片
                    deleteOtherPic(oldGood.getGoodsPic3());
                }
                if (Integer.valueOf(s) == 4) {
                    //置空需要删除的字段
                    goods.setGoodsPic4("");
                    //删除服务器的旧的图片
                    deleteOtherPic(oldGood.getGoodsPic4());
                }
                if (Integer.valueOf(s) == 5) {
                    //置空需要删除的字段
                    goods.setGoodsPic5("");
                    //删除服务器的旧的图片
                    deleteOtherPic(oldGood.getGoodsPic5());
                }
            }
        }
    }

    public void deleteOtherPic(String picName) {
        File oldUserAvatar = new File(SkssConstant.XZ_UPLOAD_URL + picName.substring(0, 4) + "/"
                + picName.substring(4, 6) + "/"
                + picName.substring(6, 8) + "/"
                + picName);
        oldUserAvatar.delete();
    }

    public void deleteAvatarPic(String picName) {
        File oldUserAvatar = new File(SkssConstant.AVATAR_UPLOAD_URL + picName.substring(0, 4) + "/"
                + picName.substring(4, 6) + "/"
                + picName.substring(6, 8) + "/"
                + picName);
        oldUserAvatar.delete();
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public ExecuteResult<Boolean> deleteToRecycle(String goodsId) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        try{
            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setGoodsLastMod(new Date());
            //更新当前状态为回收站状态
            goods.setGoodsStatus(SkssConstant.GOODS_RECYCLE_STATUS);
            int count = goodsMapper.updateByPrimaryKeySelective(goods);
            if(count>0){
                result.setResult(true);
            }else {
                result.setResult(false);
                result.addErrorMessage("该物品不存在");
            }
        }catch (Exception e){
            result.setResult(false);
            result.addErrorMessage("放入回收站失败，请重试或联系客服");
        }
        return result;
    }

    @Override
    public ExecuteResult<Boolean> recoverActive(String goodsId) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        try{
            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setGoodsLastMod(new Date());
            //更新当前状态为回收站状态
            goods.setGoodsStatus(SkssConstant.GOODS_ACTIVE_STATUS);
            int count = goodsMapper.updateByPrimaryKeySelective(goods);
            if(count>0){
                result.setResult(true);
            }else {
                result.setResult(false);
                result.addErrorMessage("该物品不存在");
            }
        }catch (Exception e){
            result.setResult(false);
            result.addErrorMessage("恢复发布失败，请重试或联系客服");
        }
        return result;
    }

    @Override
    public ExecuteResult<Boolean> deleteScGoods(String goodsId,HttpServletRequest request) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        try {
            HttpSession session = request.getSession(false);
            Object object = session.getAttribute("user");
            if(object==null){
                result.setResult(false);
                result.addErrorMessage("未检测到用户，请登录后再进行操作");
                return  result;
            }else {
                User user = (User) object;
                CollectionExample collectionExample = new CollectionExample();
                collectionExample.createCriteria().andUserIdEqualTo(user.getUserId()).andGoodsIdEqualTo(goodsId);
                int count = collectionMapper.deleteByExample(collectionExample);
                if(count>0){
                    result.setResult(true);
                    result.setSuccessMessage("删除收藏成功");
                    //更新收藏次数

                }else {
                    result.setResult(false);
                    result.addErrorMessage("删除收藏失败：物品不存在");
                }
            }
        }catch (Exception e){
            result.setResult(false);
            result.addErrorMessage("删除收藏，请重试或联系客服");
        }
        return result;
    }

    @Override
    public void updateChangeFilOtherPic(Map<String, String> changeOtherPicIds, String goodsId,Goods goods) {
        List<String> needDelFileOtherPicIds = new ArrayList<>();
        if(!changeOtherPicIds.isEmpty()){
            for (String string : changeOtherPicIds.keySet()) {
                if (Integer.valueOf(string) == 1) {
                    //赋值更新之后重新生成的图片名称
                    goods.setGoodsPic1(changeOtherPicIds.get(string));
                    //需要删除的图片
                    needDelFileOtherPicIds.add("goodsPic1");
                }
                if (Integer.valueOf(string) == 2) {
                    goods.setGoodsPic2(changeOtherPicIds.get(string));
                    needDelFileOtherPicIds.add("goodsPic2");
                }
                if (Integer.valueOf(string) == 3) {
                    goods.setGoodsPic3(changeOtherPicIds.get(string));
                    needDelFileOtherPicIds.add("goodsPic3");
                }
                if (Integer.valueOf(string) == 4) {
                    goods.setGoodsPic4(changeOtherPicIds.get(string));
                    needDelFileOtherPicIds.add("goodsPic4");
                }
                if (Integer.valueOf(string) == 5) {
                    goods.setGoodsPic5(changeOtherPicIds.get(string));
                    needDelFileOtherPicIds.add("goodsPic5");
                }
            }
        }

        if(needDelFileOtherPicIds.size()>0){
            //删除替换图片的旧图片
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andGoodsIdEqualTo(goodsId);
            //只查询需要删除的图片
            goodsExample.addSelectiveFields(needDelFileOtherPicIds);
            Goods oldGood = goodsMapper.selectOneByExample(goodsExample);
            //if判断如果不为空，即需要删除服务器上的图片
            if (StringUtils.isNotBlank(goods.getGoodsPic1())) {
                deleteOtherPic(oldGood.getGoodsPic1());
            }
            if (StringUtils.isNotBlank(goods.getGoodsPic2())) {
                deleteOtherPic(oldGood.getGoodsPic2());
            }
            if (StringUtils.isNotBlank(goods.getGoodsPic3())) {
                deleteOtherPic(oldGood.getGoodsPic3());
            }
            if (StringUtils.isNotBlank(goods.getGoodsPic4())) {
                deleteOtherPic(oldGood.getGoodsPic4());
            }
            if (StringUtils.isNotBlank(goods.getGoodsPic5())) {
                deleteOtherPic(oldGood.getGoodsPic5());
            }
        }
        goods.setGoodsLastMod(new Date());
        //整体更新该商品的信息
        goodsMapper.updateByPrimaryKeySelective(goods);

    }

    @Override
    public void updateGoodsById(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);

    }

    @Override
    public void updateGoodsDetails(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

}
