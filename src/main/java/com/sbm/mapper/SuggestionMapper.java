package com.sbm.mapper;

import com.sbm.pojo.model.Suggestion;
import com.sbm.pojo.model.SuggestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionMapper {
    Suggestion selectOneByExample(SuggestionExample example);

    int countByExample(SuggestionExample example);

    int deleteByExample(SuggestionExample example);

    int deleteByPrimaryKey(String suggestionId);

    int insert(Suggestion record);

    int insertSelective(Suggestion record);

    List<Suggestion> selectByExample(SuggestionExample example);

    Suggestion selectByPrimaryKey(String suggestionId);

    int updateByExampleSelective(@Param("record") Suggestion record, @Param("example") SuggestionExample example);

    int updateByExample(@Param("record") Suggestion record, @Param("example") SuggestionExample example);

    int updateByPrimaryKeySelective(Suggestion record);

    int updateByPrimaryKey(Suggestion record);
}