package cn.wind.WebServiceC.controller;

import cn.wind.webservicee.webservices.HelloserServiceImplService;
import cn.wind.webservicee.webservices.IHelloSevice;

/**
 * Created by wind on 2016/11/15.
 */
public class ddd {
    public static void main(String[] args) {
        /**
         * <wsdl:service name="HelloserServiceImplService">
         */
        HelloserServiceImplService hss = new HelloserServiceImplService();
        /**
         *
         * <wsdl:port binding="tns:HelloserServiceImplServiceSoapBinding" name="HelloserServiceImplPort">
         * <wsdl:binding name="HelloserServiceImplServiceSoapBinding" type="ns1:IHelloSevice">
         */


        IHelloSevice soap = hss.getHelloserServiceImplPort();
        String str = soap.say("intsmaze",1);//这里我们看视乎在调用我们本地的方法，其实内部把发送数据组装为soap协议，
        //然后把数据发送到了服务端，服务端的线程接收到请求处理返回了数据。
        System.out.println(str);
    }
}
