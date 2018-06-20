package com.sbm.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Service;

import com.sbm.mapper.GoodsMapper;
import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.GoodsExample;
import com.sbm.service.UserPersonCenterServcie;
import com.sbm.util.StringToListUtils;

@Service("userPersonCenterServcie")
public class UserPersonCenterServcieImpl implements UserPersonCenterServcie {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public void updateAndDelFilOtherPic(String needDelOtherPicIds, String goodsId) {

        if (StringUtils.isNotBlank(needDelOtherPicIds) && !"null".equals(needDelOtherPicIds)) {
            needDelOtherPicIds = needDelOtherPicIds.substring(0, needDelOtherPicIds.length() - 1);
            List<String> idStrings = StringToListUtils.changeToList(needDelOtherPicIds, "-");
            List<String> bindIds = new ArrayList<>();
            //拼接需要置空的图片字段
            for (String s : idStrings) {
                bindIds.add("goodsPic" + s);
            }
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            Goods toNullOtherPicgoods = new Goods();
            toNullOtherPicgoods.setGoodsId(goodsId);
            //删除已经存在的图片
            for (String s : idStrings) {
                if (Integer.valueOf(s) == 2) {
                    toNullOtherPicgoods.setGoodsPic2("");
                    deleteOtherPic(goods.getGoodsPic2());
                }
                if (Integer.valueOf(s) == 3) {
                    toNullOtherPicgoods.setGoodsPic3("");
                    deleteOtherPic(goods.getGoodsPic3());
                }
                if (Integer.valueOf(s) == 4) {
                    toNullOtherPicgoods.setGoodsPic4("");
                    deleteOtherPic(goods.getGoodsPic4());
                }
                if (Integer.valueOf(s) == 5) {
                    toNullOtherPicgoods.setGoodsPic5("");
                    deleteOtherPic(goods.getGoodsPic5());
                }
                if (Integer.valueOf(s) == 6) {
                    toNullOtherPicgoods.setGoodsPic6("");
                    deleteOtherPic(goods.getGoodsPic6());
                }
                if (Integer.valueOf(s) == 7) {
                    toNullOtherPicgoods.setGoodsPic7("");
                    deleteOtherPic(goods.getGoodsPic7());
                }
                if (Integer.valueOf(s) == 8) {
                    toNullOtherPicgoods.setGoodsPic8("");
                    deleteOtherPic(goods.getGoodsPic8());
                }
                if (Integer.valueOf(s) == 9) {
                    toNullOtherPicgoods.setGoodsPic9("");
                    deleteOtherPic(goods.getGoodsPic9());
                }
            }
            //置空需要删除的图片
            goodsMapper.updateByPrimaryKeySelective(toNullOtherPicgoods);
        }
    }

    public void deleteOtherPic(String picName) {
        File oldUserAvatar = new File("F://Pic/GoodsPic/" + picName.substring(0, 4) + "/"
                + picName.substring(4, 6) + "/"
                + picName.substring(6, 8) + "/"
                + picName);
        oldUserAvatar.delete();
    }


    @Override
    public void updateChangeFilOtherPic(Map<String, String> changeOtherPicIds, String goodsId) {
        if (changeOtherPicIds.isEmpty() || StringUtils.isBlank(goodsId)) {
            return;
        }
        List<String> needDelFileOtherPicIds = new ArrayList<>();
        Goods needChangeOtherPicGood = new Goods();
        for (String string : changeOtherPicIds.keySet()) {
            if (Integer.valueOf(string) == 1) {
                needChangeOtherPicGood.setGoodsPic1(changeOtherPicIds.get(string));
                needDelFileOtherPicIds.add("goodsPic1");
            }
            if (Integer.valueOf(string) == 2) {
                needChangeOtherPicGood.setGoodsPic2(changeOtherPicIds.get(string));
                needDelFileOtherPicIds.add("goodsPic2");
            }
            if (Integer.valueOf(string) == 3) {
                needChangeOtherPicGood.setGoodsPic3(changeOtherPicIds.get(string));
                needDelFileOtherPicIds.add("goodsPic3");
            }
            if (Integer.valueOf(string) == 4) {
                needChangeOtherPicGood.setGoodsPic4(changeOtherPicIds.get(string));
                needDelFileOtherPicIds.add("goodsPic4");
            }
            if (Integer.valueOf(string) == 5) {
                needChangeOtherPicGood.setGoodsPic5(changeOtherPicIds.get(string));
                needDelFileOtherPicIds.add("goodsPic5");
            }
            if (Integer.valueOf(string) == 6) {
                needChangeOtherPicGood.setGoodsPic6(changeOtherPicIds.get(string));
                needDelFileOtherPicIds.add("goodsPic6");
            }
            if (Integer.valueOf(string) == 7) {
                needChangeOtherPicGood.setGoodsPic7(changeOtherPicIds.get(string));
                needDelFileOtherPicIds.add("goodsPic7");
            }
            if (Integer.valueOf(string) == 8) {
                needChangeOtherPicGood.setGoodsPic8(changeOtherPicIds.get(string));
                needDelFileOtherPicIds.add("goodsPic8");
            }
            if (Integer.valueOf(string) == 9) {
                needChangeOtherPicGood.setGoodsPic9(changeOtherPicIds.get(string));
                needDelFileOtherPicIds.add("goodsPic9");
            }
            needChangeOtherPicGood.setGoodsId(goodsId);

        }

        //删除改变图片的旧图片
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsIdEqualTo(goodsId);
        goodsExample.addSelectiveFields(needDelFileOtherPicIds);
        Goods goods = goodsMapper.selectOneByExample(goodsExample);
        if (StringUtils.isNotBlank(goods.getGoodsPic1())) {
            deleteOtherPic(goods.getGoodsPic1());
        }
        if (StringUtils.isNotBlank(goods.getGoodsPic2())) {
            deleteOtherPic(goods.getGoodsPic2());
        }
        if (StringUtils.isNotBlank(goods.getGoodsPic3())) {
            deleteOtherPic(goods.getGoodsPic3());
        }
        if (StringUtils.isNotBlank(goods.getGoodsPic4())) {
            deleteOtherPic(goods.getGoodsPic4());
        }
        if (StringUtils.isNotBlank(goods.getGoodsPic5())) {
            deleteOtherPic(goods.getGoodsPic5());
        }
        if (StringUtils.isNotBlank(goods.getGoodsPic6())) {
            deleteOtherPic(goods.getGoodsPic6());
        }
        if (StringUtils.isNotBlank(goods.getGoodsPic7())) {
            deleteOtherPic(goods.getGoodsPic7());
        }
        if (StringUtils.isNotBlank(goods.getGoodsPic8())) {
            deleteOtherPic(goods.getGoodsPic8());
        }
        if (StringUtils.isNotBlank(goods.getGoodsPic9())) {
            deleteOtherPic(goods.getGoodsPic9());
        }

        //更换改变图片的名称
        goodsMapper.updateByPrimaryKeySelective(needChangeOtherPicGood);

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
