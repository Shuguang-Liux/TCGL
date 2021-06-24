package com.tcgl.webservice.webservice;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.webservice
 * @Description ToDo
 * @Date 2020/9/23 16:37
 **/

@Configuration
public class WebServiceConfig {

    @Autowired
    private JobListService jobListService;

    /**
     * Apache CXF 核心架构是以BUS为核心，整合其他组件。
     * Bus是CXF的主干, 为共享资源提供一个可配置的场所，作用类似于Spring的ApplicationContext，这些共享资源包括
     * WSDl管理器、绑定工厂等。通过对BUS进行扩展，可以方便地容纳自己的资源，或者替换现有的资源。默认Bus实现基于Spring架构，
     * 通过依赖注入，在运行时将组件串联起来。BusFactory负责Bus的创建。默认的BusFactory是SpringBusFactory，对应于默认
     * 的Bus实现。在构造过程中，SpringBusFactory会搜索META-INF/cxf（包含在 CXF 的jar中）下的所有bean配置文件。
     * 根据这些配置文件构建一个ApplicationContext。开发者也可以提供自己的配置文件来定制Bus。
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }


    /**
     * 此方法作用是改变项目中服务名的前缀名，此处127.0.0.1或者localhost不能访问时，请使用ipconfig查看本机ip来访问
     * 此方法被注释后, 即不改变前缀名(默认是services), wsdl访问地址为 http://127.0.0.1:8080/services/JobListService?wsdl
     * 去掉注释后wsdl访问地址为：http://127.0.0.1:8080/jobList/JobListService?wsdl
     * http://127.0.0.1:8080/jobList/列出服务列表 或 http://127.0.0.1:8080/jobList/JobListService?wsdl 查看实际的服务
     * 新建Servlet记得需要在启动类添加注解：@ServletComponentScan
     *
     * 如果启动时出现错误：not loaded because DispatcherServlet Registration found non dispatcher servlet dispatcherServlet
     * 可能是springboot与cfx版本不兼容。
     * 同时在spring boot2.0.6之后的版本与xcf集成，不需要在定义以下方法，直接在application.properties配置文件中添加：
     * cxf.path=/service（默认是services）application设置相同，会有提示信息  注意斜杠
     */
//    @SuppressWarnings("all")
//    @Bean
//    public ServletRegistrationBean disServlet() {
//        // 此处配置的是webservice接口的访问地址，类似 http://127.0.0.1:8001/emr
//        return new ServletRegistrationBean(new CXFServlet(), "/jobList/*");
//    }


    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), jobListService);
        endpoint.publish("/JobListService");
        return endpoint;
    }




}
