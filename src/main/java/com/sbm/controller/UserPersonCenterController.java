package com.sbm.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.User;
import com.sbm.service.SousouService;
import com.sbm.service.UserPersonCenterServcie;
import com.sbm.service.UserService;
import com.sbm.util.ExecuteResult;
import com.sbm.util.GetUuidUtils;
import com.sbm.util.NowTimeUtils;
import com.sbm.util.Page;
import com.sbm.util.SkssConstant;
import com.sbm.util.StringToListUtils;

@Controller
@RequestMapping("/personCenter")
public class UserPersonCenterController {

    @Resource
    private UserService UserService;
    @Resource
    private SousouService sousouService;
    @Resource
    private UserPersonCenterServcie userPersonCenterServcie;

    @RequestMapping("/avatarPic.do")
    @ResponseBody
    public String getUserAvatarPic(HttpServletResponse response, HttpServletRequest request){
        String result;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if(userId==null){
            return null;
        }else {
           result= UserService.getUserByUserId(userId).getUserAvatar();
        }
        return result;
    }


    @RequestMapping("/personInfo.do")
    @ResponseBody
    public ExecuteResult<User> getPeronInfo(HttpServletResponse response, HttpServletRequest request) {
        ExecuteResult<User> result = new ExecuteResult<>();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            result.addErrorMessage("用户登录失效，请重新登录");
        } else {
            User nowUserInfo = UserService.getUserByUserId(userId);
            if (nowUserInfo == null) {
                result.addErrorMessage("查无此人，请重新登录");
            } else {
                result.setResult(nowUserInfo);
            }
        }
        return result;
    }

    @RequestMapping("/personFabu.do")
    @ResponseBody
    public ExecuteResult<Page> getPeronFabu(Page page, HttpServletResponse response, HttpServletRequest request, String yiFaBuKind, String keyword) {
        ExecuteResult<Page> result = new ExecuteResult<>();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            result.addErrorMessage(SkssConstant.NOT_LOGIN);
        } else {
            page.setPageSize(3);//在此设置每页显示3个，后期可修改每页显示的数量
            result.setResult(sousouService.souPerFabuGoods(page, userId, yiFaBuKind, keyword));
        }
        return result;
    }

    @RequestMapping("/personSc.do")
    @ResponseBody
    public ExecuteResult<Page> getPeronFabu(Page page, HttpServletResponse response, HttpServletRequest request, String keyword) {
        ExecuteResult<Page> result = new ExecuteResult<>();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            result.addErrorMessage(SkssConstant.NOT_LOGIN);
        } else {
            page.setPageSize(3);//在此设置每页显示3个，后期可修改每页显示的数量
            result.setResult(sousouService.souPerScGoods(page, userId, keyword));
        }
        return result;
    }

    @RequestMapping("/personRecycle.do")
    @ResponseBody
    public ExecuteResult<Page> getPeronRecycle(Page page, HttpServletResponse response, HttpServletRequest request, String keyword) {
        ExecuteResult<Page> result = new ExecuteResult<>();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            result.addErrorMessage(SkssConstant.NOT_LOGIN);
        } else {
            page.setPageSize(3);//在此设置每页显示3个，后期可修改每页显示的数量
            result.setResult(sousouService.souPerRecycleGoods(page, userId, keyword));
        }
        return result;
    }

    @RequestMapping("/uploadPerInfo.do")
    @ResponseBody
    public ExecuteResult<String> uploadPerPic(@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //得到session对象
            HttpSession session = request.getSession(false);
            //取出session数据
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                //没有登录成功，返回未登录，跳转页面
                result.setResult(SkssConstant.NOT_LOGIN);
                return result;
            }
            //编辑之后的用户信息
            User user = JSON.parseObject(request.getParameter("user"),User.class);
            user.setUserId(userId);
            //查询数据库拿到旧的头像
            User oldUser = UserService.getUserByUserId(userId);
            String oldAvatarPic = oldUser.getUserAvatar();
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
            //返回用户名
            result.setResult(oldAvatarPic);
        } catch (Exception e) {
            result.addErrorMessage("非常抱歉，修改失败，请重试或联系管理员");
        }
        return result;
    }

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
                Integer index = 0;
                //将需要改变的图片按照ID由小到大排序
                List<Integer> changePicIdAsc = StringToListUtils.changeToListIntAsc(changePicId.substring(0, changePicId.length() - 1), "-");
                //更新变化的图片
                for (int i = 0; i < changePicIdAsc.size(); i++) {
                        if (uploadFiles[changePicIdAsc.get(i)] != null) {
                            // 获取图片原始文件名,uploadFiles坐标从0开始，但是传入的改动的图片下标最小是1，所以需要默认-1
                            String originalFilename = uploadFiles[changePicIdAsc.get(i)-1].getOriginalFilename();
                            // 获取上传图片的扩展名(jpg/png/...)
                            String extension = FilenameUtils.getExtension(originalFilename);
                            if (StringUtils.isNotBlank(extension)) {
                                //删除优先级大于替换（场景：用户先更换后删除，那么只需要直接删除即可，无需更新）
                                if (deleteOtherPicId.indexOf(changePicIdAsc.get(i).toString()) == -1) {
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
                                    //同上注释，下标默认-1
                                    uploadFiles[changePicIdAsc.get(i)-1].transferTo(new File(url));
                                }
                            }
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
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                result.setResult(SkssConstant.NOT_LOGIN);
                result.addErrorMessage("用户未登录，请登录");
            } else {
                User user2 = UserService.getUserIdAndUserAvatar(userId);
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
                    user.setUserId(userId);
                    UserService.UpdateUser(user);
                    result.setResult(SkssConstant.RESULT_SUCCESS);
                }
            }
        }
        return result;

    }


}
