
@Configuration
public class RedidClusterConfig{
  @Bean
  public JedisPoolConfig jedisPoolConfig(){
    JedisPoolConfig poolConfig=new JedisPoolConfig();
    poolConfig.setMaxIdle(100);
    poolConfig.setMaxTotal(600);
    return poolConfig;
  }
  @Bean
  public RedisClusterConfiguration clusterConfiguration(){
    RedisClusterConfiguration configuraion=new RedisClusterConfiguration();
    configuraion.setMaxRdirects(3);
    configuration.setClusterNodes(clusterNodes());
    return configuration;
  }
  @Bean
    public Set<RedisNode> clusterNodes(){
        Set<RedisNode> clusterNodes=new HashSet<>();
        clusterNodes.add(new RedisNode("192.168.30.124",7001));
        clusterNodes.add(new RedisNode("192.168.30.124",7002));
        clusterNodes.add(new RedisNode("192.168.30.124",7003));
        clusterNodes.add(new RedisNode("192.168.30.124",7004));
        clusterNodes.add(new RedisNode("192.168.30.124",7005));
        clusterNodes.add(new RedisNode("192.168.30.124",7006));
        return clusterNodes;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
      JedisConnectionFactory factory=new JedisConnectionFactory(clusterConfiguration(),clusterNodes());
      return factory;
    }

    @Bean
    public RedisTemplate redisTemplate(){
      RedisTemplate template=new RedisTemplate();
      template.setConnectionFactory(jedisConnectionFactory());
      return template;
    }

}
