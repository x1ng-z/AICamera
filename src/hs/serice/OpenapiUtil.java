package hs.serice;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.imageio.ImageIO;

/**
 * Openapi接口调用过程中会用到的一些工具方法
 * @author shengyiling
 *
 */
public class OpenapiUtil {

	/**
	 * BASE64加密网络图片，返回加密之后的字符串
	 * @param imageUrl 图片的绝对地址
	 * @param extensioName 图片的扩展名，例如 jpg、bmp等
	 * @return 加密之后的字符串
	 */
	public static String encodeImage2Base64(URL imageUrl, String extensioName){
		ByteArrayOutputStream outputStream = null;  
        try {  
            BufferedImage bufferedImage = ImageIO.read(imageUrl);  //读取网络图片
            outputStream = new ByteArrayOutputStream();  //文件输出流
            ImageIO.write(bufferedImage, extensioName, outputStream);  
        } catch (MalformedURLException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        Encoder encoder = Base64.getEncoder();
        
        return encoder.encodeToString(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串  
	}
	
	/** 
     * 将本地图片进行Base64位编码 
     *  
     * @param imgUrl 
     *            图片的url路径，如http://.....xx.jpg 
     * @param extensioName 图片的扩展名
     * @return 加密之后的字符串
     */  
    public static String encodeImgageToBase64(File imageFile, String extensioName) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        ByteArrayOutputStream outputStream = null;  
        try {  
            BufferedImage bufferedImage = ImageIO.read(imageFile);  
            outputStream = new ByteArrayOutputStream();  
            ImageIO.write(bufferedImage, extensioName, outputStream);  
        } catch (MalformedURLException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        // 对字节数组Base64编码  
        Encoder encoder = Base64.getEncoder(); 
        
        return encoder.encodeToString(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串  
    }
    
    
    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     * @param base64Str 利用base64加密之后的字符串
     * @param path 文件解密之后存放的地址 例如：D：//
     * @param imgName 文件解密之后命名的名称 例如： test.jpg
     */
    public static void decodeBase64ToImage(String base64Str, String path,  
            String imgName) {  
        Decoder decoder = Base64.getDecoder();  
        try {  
            FileOutputStream write = new FileOutputStream(new File(path  
                    + imgName));  
            byte[] decoderBytes = decoder.decode(base64Str);  
            write.write(decoderBytes);  
            write.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
