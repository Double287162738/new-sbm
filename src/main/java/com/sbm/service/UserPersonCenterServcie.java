package com.sbm.service;

import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.User;
import com.sbm.util.ExecuteResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserPersonCenterServcie {

    void updateAndDelFilOtherPic(String needDelOtherPicIds, String goodsId,Goods goods);

    void updateChangeFilOtherPic(Map<String, String> changeOtherPicIds, String goodsId,Goods goods);

    void updateGoodsById(Goods goods);

    void updateGoodsDetails(Goods goods);

    void deleteAvatarPic(String picName);

    void updateUserInfo(User user);

    ExecuteResult<Boolean> deleteToRecycle(String goodsId);

    ExecuteResult<Boolean> recoverActive(String goodsId);

    ExecuteResult<Boolean> deleteScGoods(String goodsId,HttpServletRequest request);
}
