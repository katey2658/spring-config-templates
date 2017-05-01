
@ComponentScan(basePackages={""})
@PropertySource("classpath:")
@Configuration
public class JdbcTemplatePersistenceConfig{
  private static final String KEY_DB_DRIVER="";
  private static final String KEY_DB_URL="";
  private static final String KEY_DB_USERNAME="";
  private static final String KEY_DB_PASSWORD="";

  @Autowired
  private Environment env;

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
  public JdbcTemplate JdbcTemplate(){
    return new JdbcTemplate(dataSource());
  }
  /**
   * @Autowired
   * private JdbcOperations jdbcOperations;
   */

}
