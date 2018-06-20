package com.sbm.service;

import java.util.Map;

import com.sbm.pojo.model.Goods;

public interface UserPersonCenterServcie {

    void updateAndDelFilOtherPic(String needDelOtherPicIds, String goodsId);

    void updateChangeFilOtherPic(Map<String, String> changeOtherPicIds, String goodsId);

    void updateGoodsById(Goods goods);

    void updateGoodsDetails(Goods goods);
}
