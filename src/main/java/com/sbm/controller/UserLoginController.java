package com.sbm.controller;

import com.alibaba.fastjson.JSON;
import com.sbm.pojo.Information;
import com.sbm.pojo.model.User;
import com.sbm.pojo.request.RegisteredRequest;
import com.sbm.service.UserService;
import com.sbm.util.AppMD5Util;
import com.sbm.util.ExecuteResult;
import com.sbm.util.RedisUtil;
import com.sbm.util.SnowflakeIdWorker;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/login")
public class UserLoginController {

    @Resource
    private UserService userService;
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

    //生成不重复ID
    SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);


    @RequestMapping("/needLogin.do")
    @ResponseBody
    public void needLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("../html/login.html");
    }

    /**
     * 登录
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
        try{
            if (userName == null) {
                result.addErrorMessage("用户登录名不能为空");
                return result;
            }
            if (password == null) {
                result.addErrorMessage("用户登录密码不能为空");
                return result;
            }
            User user = userService.getUserByPhone(userName);
            if (user == null) {
                result.addErrorMessage("用户名与密码不匹配");
                return result;
            }
            if (!user.getUserPwd().equals(AppMD5Util.getMD5(password))) {
                result.addErrorMessage("用户名与密码不匹配");
                return result;
            }
            if (user.getUserPwd().equals(AppMD5Util.getMD5(password))) {
                User sessionUser = new User();
                sessionUser.setUserId(user.getUserId());
                sessionUser.setUserName(user.getUserName());
                sessionUser.setUserPhone(user.getUserPhone());
                sessionUser.setUserWx(user.getUserWx());
                sessionUser.setUserQq(user.getUserQq());
                sessionUser.setUserSex(user.getUserSex());
                sessionUser.setUserAvatar(user.getUserAvatar());
                result.setResult(user);
                result.setSuccessMessage(userName + "登录成功");
                //创建session对象
                HttpSession session = request.getSession();
                session.setAttribute("user", sessionUser);
                session.setMaxInactiveInterval(10*60*60);
            }
        }catch (Exception e){
            result.addErrorMessage("登录失败，请联系管理员");
        }
        return result;
    }

    /**
     * 注册
     * @param registeredRequest
     * @return
     */
    @RequestMapping("/registered.do")
    @ResponseBody
    public ExecuteResult<User> registered(RegisteredRequest registeredRequest,HttpServletRequest request) {
        ExecuteResult<User> result = new ExecuteResult<>();
        String phoneCode = registeredRequest.getPhoneCode();
        HttpSession session = request.getSession(false);
        Object object = session.getAttribute("registered"+phoneCode);
        if (object == null) {
            result.addErrorMessage("验证码失效，请获取后重试！");
            return result;
        }
        String phoneVerified = (String) object;
        if (!registeredRequest.getPhoneVerified().equals(phoneVerified)) {
            result.addErrorMessage("验证码不正确！");
            return result;
        }
        User user = userService.getUserByPhone(phoneCode);
        if(user!=null){
            result.addErrorMessage("该用户已注册！");
            return result;
        }
        User registUser = new User();
        registUser.setUserId(String.valueOf(snowflakeIdWorker.nextId()));
        registUser.setUserName(registeredRequest.getPhoneCode());
        //密码加密
        registUser.setUserPwd(AppMD5Util.getMD5(registeredRequest.getPassWord()));
        registUser.setUserPhone(registeredRequest.getPhoneCode());
        registUser.setCreateTime(new Date());
        int count = userService.registeredUSer(registUser);
        if (count < 0) {
            result.addErrorMessage("注册失败，请联系管理员！");
            return result;
        }
        result.setSuccessMessage(registeredRequest.getUserName() + "注册成功，请登录");
        return result;
    }



    /**
     * 修改密码
     *
     * @param registeredRequest
     * @return
     */
    @RequestMapping("/forgetPassword.do")
    @ResponseBody
    public ExecuteResult<User> forgetPassword(RegisteredRequest registeredRequest,HttpServletRequest request) {
        ExecuteResult<User> result = new ExecuteResult<>();
        String phoneCode = registeredRequest.getPhoneCode();
        HttpSession session = request.getSession(false);
        Object object = session.getAttribute("registered"+phoneCode);
        if (object == null) {
            result.addErrorMessage("验证码不正确！");
            return result;
        }
        String phoneVerified = (String) object;
        if (!registeredRequest.getPhoneVerified().equals(phoneVerified)) {
            result.addErrorMessage("验证码不正确！");
            return result;
        }
        User user = userService.getUserByPhone(phoneCode);
        //如果未没有查询到账号，则自动注册
        if(user==null){
            User registUser = new User();
            registUser.setCreateTime(new Date());
            registUser.setUserPhone(phoneCode);
            registUser.setUserName(phoneCode);
            registUser.setUserId(String.valueOf(snowflakeIdWorker.nextId()));
            registUser.setUserPwd(AppMD5Util.getMD5(registeredRequest.getPassWord()));
            int count = userService.registeredUSer(registUser);
            if (count < 0) {
                result.addErrorMessage("自动注册失败，请联系管理员！");
                return result;
            }else {
                result.addErrorMessage("自动注册成功，请登录！");
                return result;
            }
        }else {
            User initPwdUser = new User();
            initPwdUser.setUserPwd(AppMD5Util.getMD5(registeredRequest.getPassWord()));
            initPwdUser.setUpdateTime(new Date());
            initPwdUser.setUserId(user.getUserId());
            int count = userService.initPassword(initPwdUser);
            if(count < 0){
                result.addErrorMessage("修改密码失败，请联系管理员！");
                return result;
            }else {
                result.setSuccessMessage("修改密码成功，请登录！");
                return result;
            }
        }
    }


    /**
     * 发送验证码
     *
     * @param phoneCode
     * @return
     */
    @RequestMapping("/sendPhoneCode.do")
    @ResponseBody
    public ExecuteResult<Boolean> sendPhoneCode(String phoneCode,String type,HttpServletRequest request) {
        ExecuteResult<Boolean> result =  new ExecuteResult<>();
        try {
            if (phoneCode==null || StringUtils.isBlank(phoneCode)) {
                result.setResult(false);
                result.addErrorMessage("请输入注册手机号码");
                return result;
            }
            User user = userService.getUserByPhone(phoneCode);
            //如果是注册，则需要确保手机号不存在
            if(type.equals("regist")){
                if(user!=null){
                    result.addErrorMessage("用户已注册");
                    return result;
                }
            }
            //选择请求方式
            HttpPost httppost = new HttpPost(url);
            //准备发送验证码的参数
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
            //创建验证码session对象
            HttpSession session = request.getSession();
            session.setAttribute("registered"+phoneCode, ranStr);
            session.setMaxInactiveInterval(second);

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
            httppost.setEntity(new UrlEncodedFormEntity(para, "UTF-8"));
            //准备客户端
            org.apache.http.client.HttpClient httpclient = HttpClients.createDefault();
            //发送请求，接受响应信息
            org.apache.http.HttpResponse respones = httpclient.execute(httppost);
            //取出接口响应数据,并转成字符串
            String returnResult = EntityUtils.toString(respones.getEntity());
            System.out.println(result);
            //{"respCode":"00000","respDesc":"请求成功。","failCount":"0","failList":[],"smsId":"a7f33d0a56cd40dda80e8695044aeeeb"}
            Information information = JSON.parseObject(returnResult, Information.class);
            if (!"00000".equals(information.getRespCode())) {
                return new ExecuteResult<Boolean>(false);
            }
        } catch (Exception e) {
            result.setResult(false);
            result.addErrorMessage("获取验证码失败");
            return result;
        }
        result.setResult(true);
        result.setSuccessMessage("验证码已发送");
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
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null){
            session.removeAttribute("user");
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
