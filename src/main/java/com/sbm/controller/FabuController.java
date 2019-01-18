package com.sbm.controller;

import com.alibaba.fastjson.JSONArray;
import com.sbm.pojo.model.Goods;
import com.sbm.pojo.model.User;
import com.sbm.service.FabuService;
import com.sbm.util.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/fabu")
public class FabuController extends MyMultipartResolver {

        @Resource
        private FabuService fabuService;
        /**
         * 图片提交
         * @param request
         * @param response
         * @return
         * @throws IOException
         */
        @RequestMapping("/uploadPic.do")
        @ResponseBody
        public ExecuteResult<String> LoadPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String path = SkssConstant.XZ_UPLOAD_URL + NowTimeUtils.getYear() + "/" + NowTimeUtils.getMonth() + "/" + NowTimeUtils.getDay();
            ExecuteResult<String> result = new ExecuteResult<>();
            //得到session对象
            HttpSession session = request.getSession(false);
            //取出session数据
            User user = (User) session.getAttribute("user");
            if (StringUtils.isBlank(user.getUserId())) {
                //没有登录成功，返回未登录，跳转页面
                result.setResult(SkssConstant.NOT_LOGIN);
                return result;
            }
            //保存闲置的图片之后，返回保存之后的随机文件名
            Goods goods = this.uploadLocalMedia(path, request);
            fabuService.fabuGoods(goods, user.getUserId());
            return result;
        }

        public Goods uploadLocalMedia(String path, HttpServletRequest request) {
            Goods goods = new Goods();
            // 如果文件夹不存在创建
            File tempfile = new File(path);
            if (!tempfile.exists()) {
                tempfile.mkdirs();
            }
            String filename = "";
            //获得磁盘文件条目工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //如果没以下两行设置的话，上传大的文件会占用很多内存，
            //设置暂时存放的存储室，这个存储室，可以和最终存储文件的目录不同
            //原理：它是先存到暂时存储室，然后在真正写到对应目录的硬盘上，
            //按理来说当上传一个文件时，其实是上传了两份，第一个是以.tem 格式的
            // 然后再将其真正写到对应目录的硬盘上
            factory.setRepository(new File(path));
            //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
            factory.setSizeThreshold(1024 * 1024);
            //高水平的API文件上传处理
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                //可以上传多个文件
                List<FileItem> list = upload.parseRequest(request);
                //图片个数
                int picNum=0;
                for(int fileNum=0;fileNum<list.size();fileNum++){
                    FileItem item = list.get(fileNum);
                    if (item.isFormField()) {
                        //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
                        String name = item.getFieldName();
                        //赋值除图片之外的闲置信息
                        if(name.equals("goodsType")){
                            goods.setGoodsType(item.getString("utf-8"));
                        }
                        if(name.equals("goodsArea")){
                            goods.setGoodsArea(item.getString("utf-8"));
                        }
                        if(name.equals("goodsSpecificType")){
                            goods.setGoodsSpecificType(item.getString("utf-8"));
                        }
                        if(name.equals("goodsName")){
                            goods.setGoodsName(item.getString("utf-8"));
                        }
                        if(name.equals("goodsPrice")){
                            goods.setGoodsPrice(item.getString("utf-8"));
                        }
                        if(name.equals("goodsQq")){
                            goods.setGoodsQq(item.getString("utf-8"));
                        }
                        if(name.equals("goodsWx")){
                            goods.setGoodsWx(item.getString("utf-8"));
                        }
                        if(name.equals("goodsWords")){
                            goods.setGoodsWords(item.getString("utf-8"));
                        }
                        if(name.equals("goodsOther")){
                            goods.setGoodsOther(item.getString("utf-8"));
                        }
                    } else {
                        //else里对传入的非字符串进行处理（图片，视频）
                        picNum++;
                        //获取路径名
                        String value = item.getName();
                        //索引到最后一个.
                        int start = value.lastIndexOf(".");
                        //截取 上传文件的 文件格式
                        filename = value.substring(start);
                        //自动生成文件名
                        filename = NowTimeUtils.getYmd() + GetUuidUtils.getUUID() + filename;
                        if(picNum==1){
                            goods.setGoodsPic1(filename);
                        }
                        if(picNum==2){
                            goods.setGoodsPic2(filename);
                        }
                        if(picNum==3){
                            goods.setGoodsPic3(filename);
                        }
                        if(picNum==4){
                            goods.setGoodsPic4(filename);
                        }
                        if(picNum==5){
                            goods.setGoodsPic5(filename);
                        }
                        //第三方提供的
                        //item.write( new File(path,filename) );
                        //手动写的
                        OutputStream out = new FileOutputStream(new File(path, filename));
                        InputStream in = item.getInputStream();
                        int length = 0;
                        byte[] buf = new byte[1024];
                        // in.read(buf)每次读到的数据存放在buf 数组中
                        while ((length = in.read(buf)) != -1) {
                            //在buf数组中取出数据写到(输出流)磁盘上
                            out.write(buf, 0, length);
                        }
                        in.close();
                        out.close();
                       // 压缩图片
                       // Thumbnails.of(item.getInputStream()).scale(1f).outputQuality(0.25f).toFile(path+filename);
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }
            return goods;
        }

        @RequestMapping("/uploadDetail.do")
        @ResponseBody
        public ExecuteResult<String> uploadDetail(Goods goods, HttpServletRequest request) {
            ExecuteResult<String> result = new ExecuteResult<>();
            //得到session对象
            HttpSession session = request.getSession(false);
            //取出session数据
            User user = (User) session.getAttribute("user");
            if (StringUtils.isBlank(user.getUserId())) {
                //没有登录成功，返回错误结果
                result.addErrorMessage("用户未登录");
                result.setResult(SkssConstant.NOT_LOGIN);
                return result;
            }
           return fabuService.fabuGoods(goods, user.getUserId());
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





