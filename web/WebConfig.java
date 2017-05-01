@EnableWebMvc
@Configuration
@ComponentScan(basePackages={""})
public class WebConfig extends WebMvcConfigurerAdapter implements ApplcationContextAware{

  private final static String PATTERN_RESOURCE_CSS="/css/**";
  private final static String PATTERN_RESOURCE_JS="/js/**";
  private final static String PATTERN_RESOURCE_IMAGES="/images/**";
  private final static String LOCATION_RESOURCE_CSS="/js/";
  private final static String LOCATION_RESOURCE_JS="/css/";
  private final static String LOCATION_RESOURCE_IMAGES="/images/";

  private final static String CHARACTER_ENCODING="utf-8";

  private final static String TEMPLATE_PREFIX="/WEB-INF/templates/";
  private final static String TEMPLATE_SUFFIX=".html";

  private ApplicationConetext applicationContext;

  @Override
  public void setApplicationContext(ApplicationConetext applicationContext)throws BeansException{
    this.applicationContext=applicationContext;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry){
    super.addResourceHandlers(registry);
    registry.addResourceHandler(PATTERN_RESOURCE_CSS).addResourceLocations(LOCATION_RESOURCE_CSS);
    registry.addResourceHandler(PATTERN_RESOURCE_JS).addResourceLocations(LOCATION_RESOURCE_JS);
    registry.addResourceHandler(PATTERN_RESOURCE_IMAGES).addResourceLocations(LOCATION_RESOURCE_IMAGES);
  }

  @Bean
  public ViewResolver viewResolver(){
    ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
    viewResolver.setOrder(1);
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setCharacterEncoding(CHARACTER_ENCODING);
    return viewResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine(){
    SpringTemplateEngine templateEngine=new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    templateEngine.setEnableSpringELComplier(true);
    return templateEngine;
  }

  @Bean
  public SpringResourceTemplateResolver templateResolver(){
    SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
    templateResolver.setApplicationContext(this.applicationContext);
    templateResolver.setPrefix(TEMPLATE_PREFIX);
    templateResolver.setSuffix(TEMPLATE_SUFFIX);
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setCharacterEncoding(CHARACTER_ENCODING);
    templateResolver.setCacheable(true);
    return templateResolver;
  }
}
