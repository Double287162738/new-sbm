package com.sbm.controller;

import com.alibaba.fastjson.JSON;
import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.User;
import com.sbm.service.SousouService;
import com.sbm.service.UserPersonCenterServcie;
import com.sbm.service.UserService;
import com.sbm.util.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/personCenter")
public class UserPersonCenterController {

    @Resource
    private UserService UserService;
    @Resource
    private SousouService sousouService;
    @Resource
    private UserPersonCenterServcie userPersonCenterServcie;

    /**
     * 获取用户详细信息
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/getUserDetail.do")
    @ResponseBody
    public ExecuteResult<User> getUserAvatarPic(HttpServletResponse response, HttpServletRequest request){
        ExecuteResult<User> result = new ExecuteResult<>();
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user==null){
            result.addErrorMessage("未登录");
            return result;
        }else {
           result.setResult((User) user);
            return result;
        }
    }


    /**
     * 用户信息
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/personInfo.do")
    @ResponseBody
    public ExecuteResult<User> getPeronInfo(HttpServletResponse response, HttpServletRequest request) {
        ExecuteResult<User> result = new ExecuteResult<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (StringUtils.isBlank(user.getUserId())) {
            result.addErrorMessage("用户登录失效，请重新登录");
        } else {
            if(StringUtils.isBlank(user.getUserAvatar())){
                user.setUserAvatar(SkssConstant.COMMON_AVATAR);
            }
            result.setResult(user);
        }
        return result;
    }


    /**
     * 用户已发布物品信息
     * @param page
     * @param response
     * @param request
     * @param yiFaBuKind
     * @param keyword
     * @return
     */
    @RequestMapping("/personFabu.do")
    @ResponseBody
    public ExecuteResult<Page> getPeronFabu(Page page, HttpServletResponse response, HttpServletRequest request, String yiFaBuKind, String keyword) {
        ExecuteResult<Page> result = new ExecuteResult<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (StringUtils.isBlank(user.getUserId()) || user == null) {
            result.addErrorMessage(SkssConstant.NOT_LOGIN);
        } else {
            page.setPageSize(3);//在此设置每页显示3个，后期可修改每页显示的数量
            result.setResult(sousouService.souPerFabuGoods(page, user.getUserId(), yiFaBuKind, keyword));
        }
        return result;
    }


    /**
     * 用户收藏物品信息
     * @param page
     * @param response
     * @param request
     * @param keyword
     * @return
     */
    @RequestMapping("/personSc.do")
    @ResponseBody
    public ExecuteResult<Page> getPeronFabu(Page page, HttpServletResponse response, HttpServletRequest request, String keyword) {
        ExecuteResult<Page> result = new ExecuteResult<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (StringUtils.isBlank(user.getUserId()) || user == null) {
            result.addErrorMessage(SkssConstant.NOT_LOGIN);
        } else {
            page.setPageSize(3);//在此设置每页显示3个，后期可修改每页显示的数量
            result.setResult(sousouService.souPerScGoods(page, user.getUserId(), keyword));
        }
        return result;
    }


    /**
     * 用户回收站物品信息
     * @param page
     * @param response
     * @param request
     * @param keyword
     * @return
     */
    @RequestMapping("/personRecycle.do")
    @ResponseBody
    public ExecuteResult<Page> getPeronRecycle(Page page, HttpServletResponse response, HttpServletRequest request, String keyword) {
        ExecuteResult<Page> result = new ExecuteResult<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (StringUtils.isBlank(user.getUserId()) || user == null) {
            result.addErrorMessage(SkssConstant.NOT_LOGIN);
        }  else {
            page.setPageSize(3);//在此设置每页显示3个，后期可修改每页显示的数量
            result.setResult(sousouService.souPerRecycleGoods(page, user.getUserId(), keyword));
        }
        return result;
    }


    /**
     * 上传用户信息修改
     * @param uploadFile
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/uploadPerInfo.do")
    @ResponseBody
    public ExecuteResult<String> uploadPerPic(@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //得到session对象
            HttpSession session = request.getSession(false);
            //取出session数据
            Object object = session.getAttribute("user");
            if (object == null) {
                //没有登录成功，返回未登录，跳转页面
                result.setResult(SkssConstant.NOT_LOGIN);
                return result;
            }
            User sessionUser = (User)session.getAttribute("user");
            //编辑之后的用户信息
            User user = JSON.parseObject(request.getParameter("user"),User.class);
            user.setUserId(sessionUser.getUserId());
            String oldAvatarPic = sessionUser.getUserAvatar();
            if(uploadFile!=null){
                // 获取图片原始文件名
                String originalFilename = uploadFile.getOriginalFilename();
                // 获取上传图片的扩展名(jpg/png/...)
                String extension = FilenameUtils.getExtension(originalFilename);
                // 文件名随机生成
                String name = NowTimeUtils.getYmd() + GetUuidUtils.getUUID() + "." + extension;
                //更新头像
                user.setUserAvatar(name);
                // 图片上传的相对路径（因为相对路径放到页面上就可以显示图片）
                String path = SkssConstant.AVATAR_UPLOAD_URL + NowTimeUtils.getYear() + "/" + NowTimeUtils.getMonth() + "/" + NowTimeUtils.getDay() + "/" + name;
                String url = path;
                File dir = new File(url);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 上传图片
                uploadFile.transferTo(new File(url));
                //删除旧的头像
                userPersonCenterServcie.deleteAvatarPic(oldAvatarPic);
                //如果头像有改动则返回新生成的名字
                oldAvatarPic=name;
            }
            //更新用户信息
            userPersonCenterServcie.updateUserInfo(user);
            //返回生成的新头像
            result.setResult(oldAvatarPic);
            user.setUserAvatar(oldAvatarPic);
            //更新session中的用户信息
            session.setAttribute("user",user);
        } catch (Exception e) {
            result.addErrorMessage("非常抱歉，修改失败，请重试或联系管理员");
        }
        return result;
    }


    /**
     * 编辑已发布物品信息
     * @param uploadFiles
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/editOtherPic.do")
    @ResponseBody
    public ExecuteResult<String> updateOtherPic(@RequestParam(value = "uploadFiles", required = false) MultipartFile[] uploadFiles, HttpServletRequest request, HttpServletResponse response) {
        ExecuteResult<String> result = new ExecuteResult<>();
        Map<String, String> otherPicName = new HashMap<>();
        try {
            //编辑之后的商品信息
            Goods changeGoods = JSON.parseObject(request.getParameter("goods"),Goods.class);
            //需要删除的图片
            String deleteOtherPicId = request.getParameter("deletePic");
            //需要替换的图片
            String changePicId = request.getParameter("changePic");
            //商品ID
            String goodsId = changeGoods.getGoodsId();
            //数据库置空删除的详情图片，并删除旧的图片
            if (StringUtils.isNotBlank(deleteOtherPicId)) {
                //这一步删除服务器旧的图片，置空需要删除的图片字段（没有数据库的删除和更新操作）
                userPersonCenterServcie.updateAndDelFilOtherPic(deleteOtherPicId, goodsId,changeGoods);
            }
            if (StringUtils.isNotBlank(changePicId)) {
                List<String> changePicIdAsc = StringToListUtils.changeToList(changePicId, ",");
                //更新变化的图片
                for (int i = 0; i < changePicIdAsc.size(); i++) {
                        String originalFilename = uploadFiles[i].getOriginalFilename();
                        // 获取上传图片的扩展名(jpg/png/...)
                        String extension = FilenameUtils.getExtension(originalFilename);
                        if (StringUtils.isNotBlank(extension)) {
                            // 文件名随机生成
                            String name = NowTimeUtils.getYmd() + GetUuidUtils.getUUID() + "." + extension;
                            //保留被替换的图片
                            otherPicName.put(changePicIdAsc.get(i).toString(), name);
                            // 图片上传的相对路径
                            String path = SkssConstant.XZ_UPLOAD_URL + NowTimeUtils.getYear() + "/" + NowTimeUtils.getMonth() + "/" + NowTimeUtils.getDay() + "/" + name;
                            String url = path;
                            File dir = new File(url);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            uploadFiles[i].transferTo(new File(url));
                        }
                }
            }
            userPersonCenterServcie.updateChangeFilOtherPic(otherPicName, goodsId,changeGoods);
            result.setResult("success");
        } catch (Exception e) {
            result.addErrorMessage("修改失败，请联系管理员");
        }
        return result;
    }


    @RequestMapping("/savePerInfoChange.do")
    @ResponseBody
    public ExecuteResult<String> savePerInfoChange(User user, HttpServletRequest request) {
        ExecuteResult<String> result = new ExecuteResult<>();
        //得到session对象
        HttpSession session = request.getSession(false);
        if (session == null) {
            result.setResult(SkssConstant.NOT_LOGIN);
            result.addErrorMessage("用户未登录，请登录");
        } else {
            Object object = session.getAttribute("user");
            if (object == null) {
                result.setResult(SkssConstant.NOT_LOGIN);
                result.addErrorMessage("用户未登录，请登录");
            } else {
                User sessionUser = (User)object;
                User user2 = UserService.getUserIdAndUserAvatar(sessionUser.getUserId());
                if (user2 == null || user2.getUserId() == null) {
                    result.setResult(SkssConstant.NOT_LOGIN);
                    result.addErrorMessage("用户未登录，请登录");
                } else {
                    if (user.getUserAvatar() != null) {
                        String picName = user2.getUserAvatar();
                        File oldUserAvatar = new File(SkssConstant.AVATAR_UPLOAD_URL + picName.substring(0, 4) + "/"
                                + picName.substring(4, 6) + "/"
                                + picName.substring(6, 8) + "/"
                                + picName);
                        oldUserAvatar.delete();
                    }
                    user.setUserId(sessionUser.getUserId());
                    UserService.UpdateUser(user);
                    result.setResult(SkssConstant.RESULT_SUCCESS);
                }
            }
        }
        return result;

    }

    //放入回收站
    @RequestMapping("/deleteToRecycle.do")
    @ResponseBody
    public ExecuteResult<Boolean> deleteToRecycle(@RequestBody String goodsId){
        return userPersonCenterServcie.deleteToRecycle(goodsId);
    }

    //恢复发布
    @RequestMapping("/recoverActive.do")
    @ResponseBody
    public ExecuteResult<Boolean> recoverActive(@RequestBody String goodsId){
        return userPersonCenterServcie.recoverActive(goodsId);
    }

    //删除收藏
    @RequestMapping("/deleteScGoods.do")
    @ResponseBody
    public ExecuteResult<Boolean> deleteScGoods(@RequestBody String goodsId, HttpServletRequest request){
        return userPersonCenterServcie.deleteScGoods(goodsId,request);
    }
}
