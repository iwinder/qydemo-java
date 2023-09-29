package cn.zhonya.BiYaDiForm.utills;


import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串String操作工具类
 * Created by wind on 2016/8/18.
 * http://windcoder.com
 */
public class StringUtilZ {
	/**
	 * 生成指定长度字符串-type固定版
	 *  (数字字母大小写)
	 * @param length 字符串长度
	 * @return
	 */
	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i ++) {
			sb.append(buffer.charAt(random.nextInt(range)));
		}
		return sb.toString();
	}

    /**
     * 生成指定长度字符串-type自定义版
     * @param
     *          type  类型，如 0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
     *         length 字符串长度
     * @return
     */
    public static String getRandomString(String type,int length) {
        StringBuffer buffer = new StringBuffer(type);
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i ++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }

    /**
     * 生成指定长度字符串-可自定义开始结尾
     * @param start 自定义开始字段，"" 时为不定义
     * @param end   自定义结束字段 ,"" 时为不定义
     * @param type  生成字符串的类型，如 0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
     * @param length 长度
     * @return
     */
    public static String getRandomOfSignString(String start,String end,String type,int length) {
        int startLength = 0, endLength=0;
        int tmpLength = length;
        StringBuffer sb = new StringBuffer();
        if(start!=null&& !StringUtils.isEmpty(start)&&!start.equals("null")){
            startLength = start.length();
            tmpLength -=startLength;
            sb.append(start);
        }
        if (end!=null&&!StringUtils.isEmpty(end)&&!end.equals("null")){
            endLength = end.length();
            tmpLength -= endLength;
        }
        sb.append(getRandomString(type,tmpLength));

        if(endLength>0){
            sb.append(end);
        }

        return sb.toString();

    }


    /**
     * 密码加密 生成32位MD5
     * @param password
     * @return
     */
   public static String getNewPassword32(String password) {
       String re_md5 = new String();
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(password.getBytes("UTF-8"));
           byte b[] = md.digest();

           int i;

           StringBuffer buf = new StringBuffer("");
           for (int offset = 0; offset < b.length; offset++) {
               i = b[offset];
               if (i < 0)
                   i += 256;
               if (i < 16)
                   buf.append("0");
               buf.append(Integer.toHexString(i));
           }

           re_md5 = buf.toString();

       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       }
       return re_md5;
   }

    /**
     *  对字符串进行MD5
     * @param sourceStr
     *          字符串
     * @param num
     *          md5位数
     * @return
     */
    public static String stringToMD5(String sourceStr,int num) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            System.out.println("MD5(" + sourceStr + ",32) = " + result);
            System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
            if(num==32){
                return result;
            }else if(num==16){
                return buf.toString().substring(8, 24);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 验证码生成
	 *	一般用于手机验证码
     * @return 
	 *	6位数字
     */
    public static String createRandomVcode(){
        //验证码
        Random rad=new Random();

        String result  = rad.nextInt(1000000) +"";

        if(result.length()!=6){
            return createRandomVcode();
        }
        return result;
    }


    /**
     * 验证是否为纯数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    /**
     * 验证是否仅含有数字和英文
     * @param str
     * @return
     */
    public static boolean checkNumericAndEnglish(String str){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    /**
     * 验证邮箱是否合法
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }


    /**
     * 验证手机号码
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber){
        boolean flag = false;
        try{
            String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5,7,9]))\\d{8}$";
            Pattern regex = Pattern.compile(regExp);
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
	
	
	/**
	 * 拼接地址与参数
	 * @param redirectUri 源地址
	 * @param parm  参数字符串
	 * @return
	 */
	public static String joinLink(String redirectUri,String parm ){

		if(redirectUri.indexOf("?")>0){
			redirectUri = redirectUri+"&"+parm;
		}else{
			redirectUri = redirectUri+"?"+parm;
		}
		return redirectUri;
	}


}
