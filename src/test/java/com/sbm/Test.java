package com.sbm;

import com.sbm.util.AppMD5Util;
import com.sbm.util.ExecuteResult;
import com.sbm.util.RedisUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by zhangxz on 2018/6/15
 */
public class Test {

    public static void main(String[] args) {
      //  ExecuteResult<Boolean>a=   sendPhoneCode("17854219747");

        //设置缓存及过期时间
       /* RedisUtil redisUtil= new RedisUtil();
        redisUtil.set("registered","Test");*/
       try {
           getSmallFile();
       }catch (Exception e){
           e.printStackTrace();
       }


    }


    public static void   getSmallFile() throws IOException {
//创建图片文件(此处为1024×768px的图片)和处理后的图片文件



        File fromPic=new File("/Users/miller/IdeaProjects/new-sbm/src/main/resources/static/img/1.png");
        File toPic=new File("/Users/miller/IdeaProjects/new-sbm/src/main/resources/static/img/01.png");
        File waterPic=new File("/Users/miller/IdeaProjects/new-sbm/src/main/resources/static/img/3.png");//作为水印的图片
//图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
        Thumbnails.of(fromPic).scale(1f).outputQuality(0.25f).toFile(toPic);

        Thumbnails.of(fromPic).size(900,400)
                .watermark(Positions.CENTER,ImageIO.read(waterPic),0.5f)
                .outputQuality(0.8f).toFile(toPic);


    }


    public static ExecuteResult<Boolean> sendPhoneCode(String phoneCode) {
        //云参数
        //ID
        String accountSid = "ede863a6937e46628e5efaafe437633c";
        //模版内容
        String smsContent = "您的验证码为{1}，请于{2}分钟内正确输入，如非本人操作，请忽略此短信。";
        //模版ID
        String templateid = "285592961";
        //授权信息
        String authToken = "28ac003c3a3040e9aa1c88a09f7e76d0";
        //准备url
        String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
        //验证码过期时间
        String minute="5";


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
        String mdSig=AppMD5Util.getMD5(stringBuffer.toString());


        BasicNameValuePair timestamp = new BasicNameValuePair("timestamp", dateStr);
        BasicNameValuePair sig = new BasicNameValuePair("sig",mdSig);

        //设置验证码
        Random ne=new Random();//实例化一个random的对象ne
        int  random=  ne.nextInt(8999)+1000;
        String ranStr= String.valueOf(random);
        //设置缓存及过期时间
        RedisUtil redisUtil= new RedisUtil();
        int second=Integer.parseInt(minute)*60;
        redisUtil.set("registered"+phoneCode,ranStr,second);
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
