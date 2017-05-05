@Configuration
public class RpcProxyServiceConfig{
  /**
   * rmi Service proxy factoryBean
   */
  @Bean
  public RmiProxyFactoryBean  rmiProxy(){
    RmiProxyFactoryBean rmiProxy=new RmiProxyFactoryBean();
    rmiProxy.setServiceUrl("rmi://ip:port/myService");
    rmiProxy.setServiceInterface(IMyService.class);
    return rmiProxy;
  }

  @Bean
  public HessianProxyFactoryBean hessianProxy(){
    HessianProxyFactoryBean hessianProxy=new HessianProxyFactoryBean();
    hessianProxy.setServiceUrl("http://web:port/myService.servcie");
    hessianProxy.setServiceInterface(IMyService.class);
    return hessianProxy;
  }

  @Bean
  public BurlapProxyFactoryBean burlapProxy(){
    BurlapProxyFactoryBean burlapProxy=new BurlapProxyFactoryBean();
    burlapProxy.setServiceUrl("http://web:port/myService.service");
    burlapProxy.setServiceInterface(IMyService.class);
    return burlapProxy;
  }

  @Bean
  public HttpInvokerProxyFactoryBean httpInvokerProxy(){
    HttpInvokerProxyFactoryBean httpInvokerProxy=new HttpInvokerProxyFactoryBean();
    httpInvokerProxy.setServiceUrl("http://web:port/myService.service")
    httpInvokerProxy.setServiceInterface(IMyService.class);
    return httpInvokerProxy;
  }




}
