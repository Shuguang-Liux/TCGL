package com.record.tcgl.webservice;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.awt.dnd.DropTargetListener;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.webservice
 * @Description service层
 * @Date 2020/9/23 16:20
 **/
//声明webservice服务
@WebService(name = "JobListService",//暴露的服务名称
        targetNamespace = "http://webservice.tcgl.record.com") //命名空间，一般为包名倒序
@Component
public interface JobListService {
    @WebMethod
    /**
     * 暴露的service方法
     */
    String getUserInfo(@WebParam(name = "userName") String userName);


}
