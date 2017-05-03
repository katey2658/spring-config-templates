@EnableTransactionManagement
@ComponentScan(basePackages={""})
@Configuration
public class ServiceTransactionConfig{

  @Bean
  public DataSourceTransactionManager transactionManager(DataSource dataSource){
    DataSourceTransactionManager transactionManager=new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource);
    return transactionManager;
  }
}
