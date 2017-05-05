@Configuration
public class RpcExporterConfig{
  /**
   * RMI
   * @param  MyService
   * @return
   */
  @Bean
  public RmiServiceExporter RmiExporter(IMyService myService){
    RmiServiceExporter rmiExporter=new RmiServiceExporter();
    rmiExporter.setService(userService);
    rmiExporter.setServiceName("myService");
    rmiExporter.setServiceInterface(IMyService.class);
    //option
    rmiExporter.setRegistryHost("rmi.myweb.com");
    //option
    rmiExporter.setRegistryPort(1199);
    return rmiExporter;
  }

/**
 * Hessian
 * 另外需要添加mapping *.service
 */
  @Bean
  public HessianServiceExporter hessianExporter(IMyService myService){
    HessianServiceExporter hessianExporter=new HessianServiceExporter();
    hessianExporter.setService(setService);
    hessianExporter.setServiceInterface(IMyService.class);
    return hessianExporter;
  }

  /**
   * Burlap
   * @param  IMyService
   * @return
   */
  @Bean
  public BurlapServiceExporter burlapServiceExporter(IMyService myService){
    BurlapServiceExporter burlapServiceExporter=new BurlapServiceExporter();
    burlapServiceExporter.setService(myService);
    burlapServiceExporter.setServiceInterface(IMyService.class);
    return burlapServiceExporter;
  }

  /**
   * recommend config in the WebConfig.java
   * @return
   */
  @Bean
  public HandlerMapping hessianBurlapMapping(){
    SimpleUrlHandlerMapping handlerMapping=new SimpleUrlHandlerMapping();
    Properties mappings=new Properties();
    handlerMapping.setProperty("/myService.service","myService");
    handlerMapping.setMappings(mappings);
    return handlerMapping;
  }

/**
 * HttpInvoker
 * @param  IMyService
 * @return
 */
  @Bean
  public HttpInvokerServiceExporter httpInvokerServiceExporter(IMyService myService){
    HttpInvokerServiceExporter httpInvokerServiceExporter=new HttpInvokerServiceExporter();
    httpInvokerServiceExporter.setService(myService);
    httpInvokerServiceExporter.setServiceInterface(IMyService.class);
    return httpInvokerServiceExporter;
  }

  @Bean
  public HandlerMapping httpInvokerMapping(){
    SimpleUrlHandlerMapping handlerMapping=new SimpleUrlHandlerMapping();
    Properties properties=new Properties();
    properties.setProperty("/myService.service","myService");
    handlerMapping.setMappings(properties);
    return handlerMapping;
  }
}
