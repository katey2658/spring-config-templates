@Configuraiton
@EnableTransactionManagement
public class PersistenceJPAConfig{

  private static final String KEY_DB_DRIVER="";
  private static final String KEY_DB_URL="";
  private static final String KEY_DB_USERNAME="";
  private static final String KYE_DB_PASSWORD="";

  @Autowired
  private Environment env;

  @Bean
  public LocalContainerEntityMangerFactoryBean entityManagerFactory(){
    LocalContainerEntityrManagerFactoryBean em=new LocalContainerEntityrManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setJpaVendorAdapter(vendorAdapter());
    em.setJpaProperties(additionalProperties());
    return em;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter(){
    HibernateJpaVendorAdapter jpaVendorAdapter=new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);
    vendorAdapter.setShowSql(true);
    jpaVendorAdapter.setDatabase(Database.MYSQL);
    jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
    return jpaVendorAdapter;
  }

  @Bean
  public DataSource dataSource(){
    BasicDataSource dataSource=new BasicDataSource();
    dataSource.setDriverClassName(env.getProperty(KEY_DB_DRIVER));
    dataSource.setUrl(env.getProperty(KEY_DB_URL));
    dataSource.setUsername(env.getProperty(KEY_DB_USENAME));
    dataSource.setPassword(env.getProperty(KEY_DB_PASSWORD));
    return dataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManaerFactory emf){
    JpaTransactionManager transactionManager=new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);
    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
    return new PerisistenceExceptionTranslationProcessor();
  }

  private Properties additionalProperties(){
    Properties properties=new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto","create-drop");
    properties.setProperty("hibernate.dialect","org.hibernate.dialect.MYSQL5Dialect");
    return properties;
  }
}
