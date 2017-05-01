@PropertySource({"classpath:"})
@ComponentScan(basepackages={""}
@EnableTransactionManagement
@Configuration
public class Hibernate3PersistenceConfig{
  private static final String KEY_DB_DRIVER="";
  private static final String KEY_DB_URL="";
  private static final String KEY_DB_USERNAME="";
  private static final String KEY_DB_PASSWORD="";

  @Autowired
  private Environment env;

  @Bean
  public AnnotationSessionFactoryBean sessionfactory(){
    AnnotationSessionFactoryBean sessionfactory=new  AnnotationSessionFactoryBean();
    sessionfactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan(new String[]{""});
    sessionFactory.setHibernateProperties(hibernateProperties());
    return sessionFactory;
  }

  @Bean
  public DataSource dataSource(){
    BasicDataSource dataSource=new BasicDatasource();
    dataSource.setDriverClassName(env.getProperty(KEY_DB_DRIVER));
    dataSource.setUrl(env.getProperty(KEY_DB_URL));
    dataSource.setUsername(env.getProperty(KEY_DB_USERNAME));
    dataSource.setPassword(env.getProperty(KEY_DB_PASSWORD));
    return dataSource;
  }

  @Bean
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
    HibernateTransactionManager transactionManager=new HiberanteTransactionManager();
    transactionManager.setSessionFactory(sessionFactory);
    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
    return new PerisistenceExceptionTranslationProcessor();
  }

  private Properties hibernateProperties(){
    Properties Properties=new Properties();
    return new Properties(){
      {
        setProperty("hibernate.hbm2ddl.auto",env.getProperties("hibernate.hbm2ddl.auto"));
        setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
      }
    };
  }
}
