package com.sbm.service;

import java.util.Map;

import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.User;

public interface UserPersonCenterServcie {

    void updateAndDelFilOtherPic(String needDelOtherPicIds, String goodsId,Goods goods);

    void updateChangeFilOtherPic(Map<String, String> changeOtherPicIds, String goodsId,Goods goods);

    void updateGoodsById(Goods goods);

    void updateGoodsDetails(Goods goods);

    void deleteAvatarPic(String picName);

    void updateUserInfo(User user);

}
