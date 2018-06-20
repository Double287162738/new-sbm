package com.sbm.service;

import com.sbm.pojo.model.Goods;
import com.sbm.util.ExecuteResult;

public interface FabuService {

    ExecuteResult<String> fabuGoods(Goods goods, String userId);
}
