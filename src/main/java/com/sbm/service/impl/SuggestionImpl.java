package com.sbm.service.impl;

import com.sbm.mapper.SuggestionMapper;
import com.sbm.pojo.model.Suggestion;
import com.sbm.service.SuggestionService;
import com.sbm.util.GetUuidUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service("suggestionService")
public class SuggestionImpl implements SuggestionService{

    @Resource
    private SuggestionMapper suggestionMapper;


    @Override
    public void addSuggestion(Suggestion suggestion) {
        //赋值ID
        suggestion.setSuggestionId(GetUuidUtils.getUUID());
        //0：未审核
        suggestion.setIfAudit("0");
        //版本号
        suggestion.setVersion(1);
        //创建时间
        suggestion.setCreateTime(new Date());
        suggestionMapper.insertSelective(suggestion);
    }
}