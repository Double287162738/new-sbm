package com.sbm.controller;


import com.sbm.pojo.model.Suggestion;
import com.sbm.service.SuggestionService;
import com.sbm.util.ExecuteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/suggestion")
public class SuggestionController {

    @Resource
    private SuggestionService suggestionService;


    @RequestMapping("/addSuggestion.do")
    @ResponseBody
    public ExecuteResult<String> addSuggestion(Suggestion suggestion){
        ExecuteResult<String> result = new ExecuteResult<>();
        try{
            suggestionService.addSuggestion(suggestion);
        }catch (Exception e){
            e.getMessage();
        }
        return result;
    }
}