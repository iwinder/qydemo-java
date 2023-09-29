package cn.zhonya.newyearsmailtarget.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 *
 * Created by 63586 on 2016-9-11.
 * http://windcoder.com
 */
public class OOSUtil {

    private static final String ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "个人 ACCESS_KEY_ID";
    private static final String ACCESS_KEY_SECRET = "个人 ACCESS_KEY_SECRET";
    private static final String BUCKET_NAME = "个人 BUCKET_NAME";

    /**
     *上传文件至阿里云-MultipartFile形式
     * @param file
     *          文件
     * @param tmppath
     *      深层目录名称-可有可无
     * @return
     */
    public static String downloadResource(MultipartFile file, String tmppath) {
        String path = "zhonyaCase/upload/image/" +DateUtilZ.getTodayString()+"/"+tmppath + "/" + UUIDUtil.newUUID()+".";
// 文件路径
        try {
            if (file!=null) {// 判断上传的文件是否为空

                String type = null;// 文件类型
                String fileName = file.getOriginalFilename();// 文件原名称
                System.out.println("上传的文件原名称:" + fileName);
                // 判断文件类型
                type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                if(type!=null){
                    path +=type;
                    InputStream is =file.getInputStream();
                    // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
                PutObjectResult por = ossClient.putObject(BUCKET_NAME,
                        path, is);
                    return path;
                }
            }else{
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "";
    }


    /**
     * 上传文件至阿里云-InputStream形式
     * @param is
     * @return
     */
    public static String downloadResourceOfInputStream(InputStream is) {
        String path = "newyearsmailtarget/upload/image/" +DateUtilZ.getTodayString()+"/" + UUIDUtil.newUUID()+".png";
// 文件路径
        try {
            if (is!=null) {// 判断上传的文件是否为空
                // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
                PutObjectResult por = ossClient.putObject(BUCKET_NAME,
                        path, is);
                return path;

            }else{
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
