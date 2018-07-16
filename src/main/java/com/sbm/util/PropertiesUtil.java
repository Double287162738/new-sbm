package com.sbm.util;



import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 *压缩图片
 */
public class PropertiesUtil {

    public File   getSmallFile(File   file) throws IOException{
//创建图片文件(此处为1024×768px的图片)和处理后的图片文件
        File fromPic=new File("/Users/miller/IdeaProjects/new-sbm/src/main/resources/static/img/1.png");
        File toPic=new File("/Users/miller/IdeaProjects/new-sbm/src/main/resources/static/img/01.png");
        File waterPic=new File("/Users/miller/IdeaProjects/new-sbm/src/main/resources/static/img/3.png");//作为水印的图片
//图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
        Thumbnails.of(file).scale(1f).outputQuality(0.25f).toFile(toPic);

        Thumbnails.of(fromPic).size(400,400)
                .watermark(Positions.CENTER,ImageIO.read(waterPic),0.5f)
                .outputQuality(0.8f).toFile(toPic);

        return null;
    }


    public static byte[] fromBufferedImage2(BufferedImage img, String imagType) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", bos);

        // 得到指定Format图片的writer
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(imagType);
        ImageWriter writer = (ImageWriter) iter.next();

        // 得到指定writer的输出参数设置(ImageWriteParam )
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        // 设置可否压缩
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        // 设置压缩质量参数
        iwp.setCompressionQuality(1f);

        iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        iwp.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

        writer.setOutput(ImageIO.createImageOutputStream(bos));
        IIOImage iIamge = new IIOImage(img, null, null);
        writer.write(null, iIamge, iwp);

        return bos.toByteArray();
    }
}