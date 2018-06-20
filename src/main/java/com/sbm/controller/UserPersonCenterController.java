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

    @RequestMapping("/uploadPerPic.do")
    @ResponseBody
    public ExecuteResult<String> uploadPerPic(@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // 获取图片原始文件名
            String originalFilename = uploadFile.getOriginalFilename();
            System.out.println(originalFilename);

            // 获取上传图片的扩展名(jpg/png/...)
            String extension = FilenameUtils.getExtension(originalFilename);

            // 文件名随机生成
            String name = NowTimeUtils.getYmd() + GetUuidUtils.getUUID() + "." + extension;

            // 图片上传的相对路径（因为相对路径放到页面上就可以显示图片）
            String path = "F://Pic/AvatarPic/" + NowTimeUtils.getYear() + "/" + NowTimeUtils.getMonth() + "/" + NowTimeUtils.getDay() + "/" + name;

            // 图片上传的绝对路径
//	            String url = request.getSession().getServletContext().getRealPath("") + path;  
            String url = path;
            File dir = new File(url);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 上传图片
            uploadFile.transferTo(new File(url));
            result.setResult(name);
            // 将相对路径写回（json格式）
            //JSONObject jsonObject = new JSONObject();
            // 将图片上传到本地
            //jsonObject.put("path", path);

            // 设置响应数据的类型json
            //response.setContentType("application/json; charset=utf-8");
            // 写回
            //response.getWriter().write(jsonObject.toString());

        } catch (Exception e) {
            result.addErrorMessage("头像上传失败，请联系管理员");
        }
        return result;
    }

    @RequestMapping("/editGoodsDetails.do")
    @ResponseBody
    public ExecuteResult<Boolean> editGoodsDetails(HttpServletRequest request, HttpServletResponse response, Goods goods) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        String goodsId = "";
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("goodsId")) {
                        goodsId = cookie.getValue();
                    }
                }
            }
            if (StringUtils.isBlank(goodsId)) {
                result.setResult(false);
                result.addErrorMessage("编辑失效，请重新打开编辑页面");
            }
            goods.setGoodsId(goodsId);
            goods.setGoodsLastMod(new Date());
            userPersonCenterServcie.updateGoodsDetails(goods);
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
            result.addErrorMessage("编辑失败，请重试或联系管理员");
        }
        return result;
    }

    @RequestMapping("/editOtherPic.do")
    @ResponseBody
    public ExecuteResult<String> updateOtherPic(@RequestParam(value = "uploadFiles", required = false) MultipartFile[] uploadFiles, HttpServletRequest request, HttpServletResponse response) {
        ExecuteResult<String> result = new ExecuteResult<>();
        Map<String, String> otherPicName = new HashMap<>();
        try {
            Cookie[] cookies = request.getCookies();
            String deleteOtherPicId = null;
            String goodsId = null;
            String changePicId = null;
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("deletePicId")) {
                        deleteOtherPicId = cookie.getValue();
                    }
                    if (cookie.getName().equals("goodsId")) {
                        goodsId = cookie.getValue();
                    }
                    if (cookie.getName().equals("changePicId")) {
                        changePicId = cookie.getValue();
                    }
                }
            }
            //数据库置空删除的详情图片，并删除旧的图片
            if (StringUtils.isNotBlank(deleteOtherPicId)) {
                userPersonCenterServcie.updateAndDelFilOtherPic(deleteOtherPicId, goodsId);
            }
            if (StringUtils.isNotBlank(changePicId)) {
                Integer index = 0;
                //将改变的图片按照顺序有小到大排序
                List<Integer> changePicIdAsc = StringToListUtils.changeToListIntAsc(changePicId.substring(0, changePicId.length() - 1), "-");
                //更新变化的图片
                for (int i = 0; i < uploadFiles.length; i++) {
                    if (uploadFiles[i] != null) {
                        // 获取图片原始文件名
                        String originalFilename = uploadFiles[i].getOriginalFilename();

                        // 获取上传图片的扩展名(jpg/png/...)
                        String extension = FilenameUtils.getExtension(originalFilename);
                        if (StringUtils.isNotBlank(extension)) {
                            if (deleteOtherPicId.indexOf(changePicIdAsc.get(index).toString()) == -1) {
                                // 文件名随机生成
                                String name = NowTimeUtils.getYmd() + GetUuidUtils.getUUID() + "." + extension;

                                //保留那些图片被替换
                                otherPicName.put(changePicIdAsc.get(index).toString(), name);

                                // 图片上传的相对路径（因为相对路径放到页面上就可以显示图片）
                                String path = "F://Pic/GoodsPic/" + NowTimeUtils.getYear() + "/" + NowTimeUtils.getMonth() + "/" + NowTimeUtils.getDay() + "/" + name;

                                String url = path;
                                File dir = new File(url);
                                if (!dir.exists()) {
                                    dir.mkdirs();
                                }
                                uploadFiles[i].transferTo(new File(url));
                            }
                            index++;
                        }

                    }
                }
            }
            userPersonCenterServcie.updateChangeFilOtherPic(otherPicName, goodsId);
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
                        File oldUserAvatar = new File("F://Pic/AvatarPic/" + picName.substring(0, 4) + "/"
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

    public String makeEditGoods(HttpServletRequest request, HttpServletResponse response, Goods goods) {
        Cookie[] cookies = request.getCookies();
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("goodsId")) {
                goods.setGoodsId(cookie.getValue());
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cookie.getName().equals("editGoodsName")) {
                goods.setGoodsName(cookie.getValue());
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cookie.getName().equals("editGoodsWords")) {
                goods.setGoodsWords(cookie.getValue());
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cookie.getName().equals("editGoodsArea")) {
                goods.setGoodsArea(cookie.getValue());
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cookie.getName().equals("editGoodsType")) {
                goods.setGoodsType(cookie.getValue());
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cookie.getName().equals("editGoodsPrice")) {
                goods.setGoodsPrice(cookie.getValue());
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cookie.getName().equals("editGoodsQq")) {
                goods.setGoodsQq(cookie.getValue());
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cookie.getName().equals("editGoodsWx")) {
                goods.setGoodsWx(cookie.getValue());
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        if (StringUtils.isBlank(goods.getGoodsId())) {
            sb.append("修改已经失效，请重新打开编辑");
        }
        if (StringUtils.isBlank(goods.getGoodsName())) {
            sb.append("名称不能为空");
        }
        if (StringUtils.isBlank(goods.getGoodsWords())) {
            sb.append("特点不能为空");
        }
        if (StringUtils.isBlank(goods.getGoodsPrice())) {
            sb.append("价格不能为空");
        }
        if (StringUtils.isBlank(goods.getGoodsPrice())) {
            sb.append("价格不能为空");
        }
        if (StringUtils.isBlank(goods.getGoodsType())) {
            goods.setGoodsType("Z");
        }
        if (StringUtils.isBlank(goods.getGoodsArea())) {
            goods.setGoodsArea("Z");
        }
        if (StringUtils.isBlank(goods.getGoodsQq()) && StringUtils.isBlank(goods.getGoodsWx())) {
            sb.append("QQ和微信至少留一个");
        }
        return sb.toString();
    }


}
