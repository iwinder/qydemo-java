package cn.wind.WebServiceE.webServices;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by wind on 2016/11/15.
 */
@WebService(targetNamespace = "webServices.WebServiceE.wind.cn")
public interface IHelloSevice {
    @WebResult(name="Resultstr")
    public  String say(@WebParam(name="str" )String str,@WebParam(name="AA" )int a);
}
