package com.sbm.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.sbm.pojo.model.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.sbm.service.FabuService;
import com.sbm.util.ExecuteResult;
import com.sbm.util.GetUuidUtils;
import com.sbm.util.MyMultipartResolver;
import com.sbm.util.NowTimeUtils;
import com.sbm.util.SkssConstant;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fabu")
public class FabuController extends MyMultipartResolver {

    @Resource
    private FabuService fabuService;


        /**
         * 图片提交
         *
         * @param request
         * @param response
         * @return
         * @throws IOException
         */
    @RequestMapping("/uploadPic.do")
    @ResponseBody
    public ExecuteResult<List<String>> LoadPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = "D:/home/" + NowTimeUtils.getYear() + "/" + NowTimeUtils.getMonth() + "/" + NowTimeUtils.getDay();
        ExecuteResult<List<String>> result = new ExecuteResult<>();
        //得到session对象
        HttpSession session = request.getSession(false);
        if (session == null) {
            //没有登录成功，跳转到登录页面
            List<String> newList = new ArrayList<>();
            newList.add("用户没有登录");
            result.setResult(newList);
            return result;
        }
        //取出session数据
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            //没有登录成功，返回错误结果
            List<String> newList = new ArrayList<>();
            newList.add("用户没有登录");
            result.setResult(newList);
            return result;
        }
        List<String> allImageName = this.uploadLocalMedia(path, request);
        request.getSession().setAttribute("headname", allImageName);
        result.setResult(allImageName);
        System.out.println("文件上传成功");
        return result;
    }

    public List<String> uploadLocalMedia(String path, HttpServletRequest request) {
        // 如果文件夹不存在创建
        File tempfile = new File(path);
        if (!tempfile.exists()) {
            tempfile.mkdirs();
        }
        String filename = "";
        List<String> resultList = new ArrayList<>();
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
        /**
         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，
         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
         * 然后再将其真正写到 对应目录的硬盘上
         */
        factory.setRepository(new File(path));
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
        factory.setSizeThreshold(1024 * 1024);
        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //可以上传多个文件
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                    //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
                    String value = item.getString();
                } else {
                    //对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，视频这些
                    /**
                     * 以下三步，主要获取 上传文件的名字
                     */
                    //获取路径名
                    String value = item.getName();
                    //索引到最后一个.
                    int start = value.lastIndexOf(".");
                    //截取 上传文件的 文件格式
                    filename = value.substring(start);
                    //自动生成文件名
                    filename = NowTimeUtils.getYmd() + GetUuidUtils.getUUID() + filename;
                    resultList.add(filename);
                    //真正写到磁盘上
                    //它抛出的异常 用exception 捕捉
                    //第三方提供的
                    //item.write( new File(path,filename) );
                    //手动写的
                    OutputStream out = new FileOutputStream(new File(path, filename));
                    InputStream in = item.getInputStream();
                    int length = 0;
                    byte[] buf = new byte[1024];
                    // in.read(buf) 每次读到的数据存放在  buf 数组中
                    while ((length = in.read(buf)) != -1) {
                        //在  buf 数组中 取出数据 写到 （输出流）磁盘上
                        out.write(buf, 0, length);
                    }
                    in.close();
                    out.close();
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return resultList;
    }

    @RequestMapping("/uploadDetail.do")
    @ResponseBody
    public ExecuteResult<String> uploadDetail(Goods goods, HttpServletRequest request) {
        ExecuteResult<String> result = new ExecuteResult<>();
        //得到session对象
        HttpSession session = request.getSession(false);
        if (session == null) {
            //没有登录成功，返回错误
            List<String> newList = new ArrayList<>();
            newList.add("用户没有登录");
            result.setResult(SkssConstant.NOT_LOGIN);
            return result;
        }
        //取出session数据
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            //没有登录成功，返回错误结果
            List<String> newList = new ArrayList<>();
            newList.add("用户没有登录");
            result.setResult(SkssConstant.NOT_LOGIN);
            return result;
        }
       return fabuService.fabuGoods(goods, userId);
    }


    public void writeToResponse(HttpServletResponse response, List<String> list) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        out = response.getWriter();
        Object json = JSONArray.toJSON(list);
        out.print(json);
        out.flush();
        out.close();
    }

}





