package cn.wind.WebServiceE.webServices.impl;

import cn.wind.WebServiceE.webServices.IHelloSevice;
import org.springframework.stereotype.Component;


import javax.jws.WebService;

/**
 * Created by wind on 2016/11/15.
 */
@Component("HelloWord")
@WebService(targetNamespace = "webServices.WebServiceE.wind.cn")
public class HelloserServiceImpl  implements IHelloSevice{
    @Override
    public String say(String str,int a ) {
        System.out.print("This is "+str);
        return "Hello "+str;
    }
}
