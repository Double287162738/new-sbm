package com.sbm.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.sbm.pojo.Information;
import com.sbm.pojo.request.RegisteredRequest;
import com.sbm.util.AppMD5Util;
import com.sbm.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbm.pojo.model.User;
import com.sbm.service.UserService;
import com.sbm.util.ExecuteResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

@Controller
@RequestMapping("/login")
public class UserLoginController {

    @Resource
    private UserService UserService;
    @Resource
    private RedisUtil redisUtil;
    //云参数
    //ID
    @Value("${login.accountSid}")
    private String accountSid;
    //模版内容
    @Value("${login.smsContent}")
    private String smsContent;
    //模版ID
    @Value("${login.templateid}")
    private String templateid;
    //授权信息
    @Value("${login.authToken}")
    private String authToken;
    //准备url
    @Value("${login.url}")
    private String url;
    @Value("${login.minute}")
    private String minute;



    @RequestMapping("/needLogin.do")
    @ResponseBody
    public void needLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("../html/login.html");
    }


    //======================注册  手机注册==========


    /**
     * 注册
     *`1
     * @param registeredRequest
     * @return
     */
    @RequestMapping("/registered")
    @ResponseBody
    public ExecuteResult<User> registered(RegisteredRequest registeredRequest) {
        ExecuteResult<User> result = new ExecuteResult<>();

        String phoneCode = registeredRequest.getPhoneCode();
        Object object = redisUtil.get("registered" + phoneCode);
        if (object == null) {
            result.addErrorMessage("验证码不正确！");
            return result;
        }
        String phoneVerified = (String) object;

        if (!registeredRequest.getPhoneVerified().equals(phoneVerified)) {
            result.addErrorMessage("验证码不正确！");
            return result;
        }
        User user = new User();
        user.setUserName(registeredRequest.getPhoneCode());
        user.setUserPwd(registeredRequest.getPassWord());
        user.setUserPhone(registeredRequest.getPhoneCode());
        int count = UserService.registeredUSer(user);
        if (count < 0) {
            result.addErrorMessage("注册失败，请联系管理员！");
            return result;
        }
        result.setResult(user);
        result.setSuccessMessage(registeredRequest.getUserName() + "登录成功");
        return result;
    }



    /**
     * 修改密码
     *
     * @param registeredRequest
     * @return
     */
    @RequestMapping("/forgetPassword")
    @ResponseBody
    public ExecuteResult<User> forgetPassword(RegisteredRequest registeredRequest) {
        ExecuteResult<User> result = new ExecuteResult<>();

        String phoneCode = registeredRequest.getPhoneCode();
        Object object = redisUtil.get("registered" + phoneCode);
        if (object == null) {
            result.addErrorMessage("验证码不正确！");
            return result;
        }
        String phoneVerified = (String) object;

        if (!registeredRequest.getPhoneVerified().equals(phoneVerified)) {
            result.addErrorMessage("验证码不正确！");
            return result;
        }
        User user = new User();
        user.setUserPwd(registeredRequest.getPassWord());
        user.setUserPhone(registeredRequest.getPhoneCode());
        int count = UserService.registeredUSer(user);
        if (count < 0) {
            result.addErrorMessage("密码失败失败，请联系管理员！");
            return result;
        }
        result.setResult(user);
        result.setSuccessMessage(registeredRequest.getUserName() + "修改成功");
        return result;
    }


    /**
     * 发送验证码
     *
     * @param phoneCode
     * @return
     */
    @RequestMapping("/sendPhoneCode")
    @ResponseBody
    public ExecuteResult<Boolean> sendPhoneCode(String phoneCode) {
        if (StringUtils.isBlank(phoneCode)) {
            return new ExecuteResult<Boolean>(false);
        }
//        //云参数
//        //ID
//        String accountSid = "ede863a6937e46628e5efaafe437633c";
//        //模版内容
//        String smsContent = "您的验证码为{1}，请于{2}分钟内正确输入，如非本人操作，请忽略此短信。";
//        //模版ID
//        String templateid = "285592961";
//        //授权信息
//        String authToken = "28ac003c3a3040e9aa1c88a09f7e76d0";
//        //准备url
//        String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
        //验证码过期时间
        //  String minute="50";


        //选择请求方式
        HttpPost httppost = new HttpPost(url);
        //准备参数
        List<BasicNameValuePair> para = new ArrayList<BasicNameValuePair>();
        BasicNameValuePair accountSid1 = new BasicNameValuePair("accountSid", accountSid);
        BasicNameValuePair smsContent1 = new BasicNameValuePair("smsContent", smsContent);
        BasicNameValuePair templateid1 = new BasicNameValuePair("templateid", templateid);
        BasicNameValuePair to = new BasicNameValuePair("to", phoneCode);
        //时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());

        //MD5(ACCOUNT SID + AUTH TOKEN + timestamp);加密
        StringBuffer stringBuffer = new StringBuffer(accountSid);
        stringBuffer.append(authToken);
        stringBuffer.append(dateStr);
        String mdSig = AppMD5Util.getMD5(stringBuffer.toString());


        BasicNameValuePair timestamp = new BasicNameValuePair("timestamp", dateStr);
        BasicNameValuePair sig = new BasicNameValuePair("sig", mdSig);

        //设置验证码
        Random ne = new Random();//实例化一个random的对象ne
        int random = ne.nextInt(8999) + 1000;
        String ranStr = String.valueOf(random);

        //设置缓存及过期时间
        int second = Integer.parseInt(minute) * 60;
        //设置缓存及过期时间
        redisUtil.set("registered" + phoneCode, ranStr, second);
        //提示时间
        StringBuffer randomStr = new StringBuffer();
        randomStr.append(ranStr);
        randomStr.append(",");
        randomStr.append(minute);
        BasicNameValuePair param = new BasicNameValuePair("param", randomStr.toString());

        para.add(accountSid1);
        para.add(smsContent1);
        para.add(templateid1);
        para.add(to);
        para.add(timestamp);
        para.add(sig);
        para.add(param);
        try {//把参数封装到请求体里面
            httppost.setEntity(new UrlEncodedFormEntity(para, "UTF-8"));
            //准备客户端
            org.apache.http.client.HttpClient httpclient = HttpClients.createDefault();
            //发送请求，接受响应信息
            org.apache.http.HttpResponse respones = httpclient.execute(httppost);
            //取出接口响应数据,并转成字符串
            String result = EntityUtils.toString(respones.getEntity());
            System.out.println(result);
            //{"respCode":"00000","respDesc":"请求成功。","failCount":"0","failList":[],"smsId":"a7f33d0a56cd40dda80e8695044aeeeb"}
            Information information = JSON.parseObject(result, Information.class);
            if (!"00000".equals(information.getRespCode())) {
                return new ExecuteResult<Boolean>(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ExecuteResult<Boolean>(true);
    }


    //=================================登陆 待改造(后期支持微信登陆) =====================

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/userLogin.do")
    @ResponseBody
    public ExecuteResult<User> login(String userName, String password, HttpServletResponse response, HttpServletRequest request) {
        ExecuteResult<User> result = new ExecuteResult<>();
        Cookie[] cookies = request.getCookies();
        String verCodeIfPass = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("verCode")) {
                    verCodeIfPass = cookie.getValue();
                }
            }
        }
        if (verCodeIfPass == null) {
            result.addErrorMessage("请拖动验证码验证登录");
            cleanCookie("verCode", response, request);
            return result;
        }
        if (userName == null) {
            result.addErrorMessage("用户登录名不能为空");
            cleanCookie("verCode", response, request);
            return result;
        }
        if (password == null) {
            result.addErrorMessage("用户登录密码不能为空");
            cleanCookie("verCode", response, request);
            return result;
        }

        if (verCodeIfPass.equals("Y")) {
            User user = UserService.getUserByName(userName);
            if (user == null) {
                result.addErrorMessage("用户名与密码不匹配");
                cleanCookie("verCode", response, request);
                return result;
            }
            if (!user.getUserPwd().equals(password)) {
                result.addErrorMessage("用户名与密码不匹配");
                cleanCookie("verCode", response, request);
                return result;
            }
            if (user.getUserPwd().equals(password)) {
                result.setResult(user);
                result.setSuccessMessage(userName + "登录成功");
                cleanCookie("verCode", response, request);
                //用户头像放入cookie
                Cookie userAvatarCookie = new Cookie("userAvatar",user.getUserAvatar());
                userAvatarCookie.setMaxAge(24*60*60);
                userAvatarCookie.setPath("/");
                response.addCookie(userAvatarCookie);
                //用户ID放入cookie
                Cookie userIdCookie = new Cookie("userId",user.getUserId());
                userIdCookie.setMaxAge(24*60*60);
                userIdCookie.setPath("/");
                response.addCookie(userIdCookie);
                //创建session对象
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getUserId());
                return result;
            }
        } else if (verCodeIfPass.equals("N")) {
            result.addErrorMessage("请拖动验证码验证登录");
            cleanCookie("verCode", response, request);
            return result;
        } else {
            result.addErrorMessage("登录异常");
            cleanCookie("verCode", response, request);
            return result;
        }
        return result;
    }


    /**
     * 用户注销登录
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/userLoginOut.do")
    @ResponseBody
    public ExecuteResult<Boolean> loginOut(HttpServletResponse response, HttpServletRequest request) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        result.setResult(Boolean.TRUE);
        Cookie[] cookies = request.getCookies();
        cleanCookie("userId",response,request);
        cleanCookie("userAvatar",response,request);
        HttpSession session = request.getSession();
        if(session.getAttribute("userId")!=null){
            session.removeAttribute("userId");
        }
        return result;
    }


    /**
     * 清空指定cookie
     *
     * @param cookieName
     * @param response
     * @param request
     */
    public void cleanCookie(String cookieName, HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookie.setValue("null");
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }


}
