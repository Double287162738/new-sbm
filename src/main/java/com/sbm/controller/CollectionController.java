package com.sbm.controller;


import com.sbm.pojo.model.User;
import com.sbm.service.CollectionService;
import com.sbm.util.ExecuteResult;
import com.sbm.util.SkssConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Resource
    private CollectionService collectionService;


    @RequestMapping("addCollection.do")
    @ResponseBody
    public ExecuteResult<String> addCollection(String goodsId,HttpServletRequest request){
        ExecuteResult<String> result = new ExecuteResult<>();
        try{
            HttpSession session = request.getSession(false);
            //取出session数据
            Object object = session.getAttribute("user");
            if (object == null) {
                //没有登录成功，返回未登录，跳转页面
                result.setResult(SkssConstant.NOT_LOGIN);
                return result;
            }
            User user = (User)object;
            collectionService.addCollection(goodsId,user.getUserId());
            result.setSuccessMessage("收藏成功");
        }catch (Exception e){
            e.getMessage();
            result.addErrorMessage("收藏失败，请联系客服");
        }
        return result;
    }
}