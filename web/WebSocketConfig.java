@Configuration
@ComponentScan(basePackages={""})
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
  private final String REGISTRY_PATH_MESSAGE="";

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
    registry.addHandler(getMessageHandler(),REGISTRY_PATH_MESSAGE);
  }

  @Bean
  public WebSocketMessageHandler getMessageHandler(){
    return new WebSocketMessageHandler();
  }
}
