package com.busyzero.config;

/** 
 * Mongodb 数据库的配置
 */
@Configuration
/**启用mongodb的repository功能*/
@EnableMongoRepositories(basePackages="com.busyzero.domain")
public class MongoConfig{

  /**
   * mongoClient bean
   * @return 
   */
  @Bean
  public MongoFactoryBean mongo(){
    MongoFactoryBean mongo=new MongoFactoryBean();
    mongo.setHost("host");
    return mongo;
  }
  /**
   * MongoTemeple bean 模板操作方法
   * @param  Mongo mongo         
   * @return       
   */
  @Bean
  public MongoOperations mongoTemplate(Mongo mongo){
    return new MongoTemplate(mongo,"dbname");
  }
}
