@Configuration
@ComponentScan(basePackages={""})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="")
public class PersistenceConfig{


}

/**
 * IN XML Configuration
 * <jpa:repositories base-package=""/>
 */
