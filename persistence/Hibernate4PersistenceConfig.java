
@EnableTransactionManagement
@PropertySource({""})
@ComponentScan(basepackages="")
@Configuration
public class Hibernate4PersistenceConfig{
  private static final String KEY_DB_DRIVER="";
  private static final String KEY_DB_URL="";
  private static final String KEY_DB_USENAME="";
  private static final String KEY_DB_PASSWORD="";

  @Autowired
  private Environment env;

  @Bean
  public LocalSessionFactoryBean sessionFactory(){
    LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan(new String[]{});
    sessionFactory.setHibernateProperties(hibernatePropertes());
    return sessionFactory;
  }

  @Bean
  public DataSource dataSource(){
    BasicDataSource dataSource=new BasicDataSource();
    dataSource.setDriverClassName(env.getProperty(KEY_DB_DRIVER));
    dataSource.setUrl(env.getProperty(KEY_DB_URL));
    dataSource.setUsername(env.getProperty(KEY_DB_USERNAME));
    dataSource.setPassword(env.getProperty(KEY_DB_PASSWORD));
    return dataSource;
  }

  @Bean
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
    HibrenateTransactionManager transactionManager=new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory);
    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
    return new PerisistenceExceptionTranslationProcessor();
  }

  private Properties hibernateProperties(){
    return new Properties(){
      {
        setProperty("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
        setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
        setProperty("hibernate.globally_quoted_identifiers","true");
      }
    };
  }

}
