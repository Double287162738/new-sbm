package com.sbm.mapper.customerMapper;

import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.SouSouInparameterDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsCustomerMapper {

    List<Goods> sousou(SouSouInparameterDTO souSouInparameterDTO);

    Integer sousouCount(SouSouInparameterDTO souSouInparameterDTO);

    List<Goods> souGoodsHover(@Param("type") String type);


    /**
     * 搜索前十条
     * @return
     */
    List<Goods> sousouOnlyTen();

}