@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer{
  private final static String REGISTRY_PATH_ENDPOINT="";

  private final static String[] REGISTRY_DESTINATION_PREFIXES={""};
  private final static String[] APPLICATION_DESTINATION_PREFIXES={""};

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry){
    registry.addEndpoint(REGISTRY_PATH_ENDPOINT).withSocketJS();
  }

  @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //启用stomp代理中继功能，根据选择的代理不同，可选的前缀也会有所限制
        registry.enableStompBrokerRelay(REGISTRY_DESTINATION_PREFIXES);
               .setRelayHost()
               .setRelayPort()
               .setClientLogin()
               .setClientPasscode();
        registry.setApplicationDestinationPrefixes("/app");

}
