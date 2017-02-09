package cn.zhonya.newyearsmailtarget.utils;



import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * Base64工具类
 * Created by wind on 2017/1/22.
 * http://windcoder.com
 */
public class Base64Util {

    public static void main(String[] args) {
        String s = "data:image/png;base64,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";
        byte[]  sBy = decoderBase64File(s);
        try {
            String u = getImageString(sBy);
            System.out.println(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 将base64字符解码-转为byte[]
     * @param base64Code
     * @param
     * @throws Exception
     */
    public static byte[] decoderBase64File(String base64Code) {
        String  picUrl = base64Code.replace("data:image/png;base64,","");
        byte[] buffer =   Base64.getDecoder().decode(picUrl);
        for (int i = 0; i < buffer.length; ++i) {
            if (buffer[i] < 0) {// 调整异常数据
                buffer[i] += 256;
            }
        }
        return buffer;
    }

    /**
     * 转化Base64文件为InputStream
     * @param base64Code
     * @return
     */
    public static InputStream decoderBase64FileToInputStream(String base64Code){
       String  picUrl = base64Code.replace("data:image/png;base64,","");
        // Base64解码
        byte[] b =   Base64.getDecoder().decode(picUrl);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }
        if(b!=null){
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            return in;
        }
        return null;
    }

    public static final InputStream byteTostream(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }


    /**
     * 二进制流转Base64字符串
     *
     * @param data 二进制流
     * @return data
     * @throws IOException 异常
     */
    public static String getImageString(byte[] data) throws IOException {
       return  Base64.getEncoder().encodeToString(data);
    }
}
