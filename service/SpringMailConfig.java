@Configuration
@PropertySource("")
public class SpringMailConfig implements ApplicationContextAware,EnvironmentAware{
  private static final String MAIL_TEMPLATE_ENCODING="utf-8";

  private static final String KEY_HOST="";
  private static final String KEY_PORT="";
  private static final String KEY_PROTOCOL="";
  private static final String KEY_USERNAME="";
  private static final String KEY_PASSWORD="";

  private Environment env;

  private ApplicationContextAware applicationConetext;

  @Override
  public void setApplicationContext(ApplicationContext applicationConetext)throws BeansException{
    this.applicationConetext=applicationConetext;
  }

  @Override
  public void setEnvironment(Environment environment){
    this.env=environment;
  }

  @Bean
  public JavaMailSender mailSender(){
    JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
    mailSender.setHost(env.getProperty(KEY_HOST));
    mailSender.setUsername(env.getProperty(KEY_USERNAME));
    mailSender.setPassword(env.getProperty(KEY_PASSWORD));
    mailSender.setDefaultEncoding(MAIL_TEMPLATE_ENCODING);
    mailSender.setJavaMailProperties(javaMailProperties());
    return mailSender;
  }

  private Properties javaMailProperties(){
    Properties properties=new Properties();
    properties.setProperty("mail.smtp.auth",env.getProperty("spring.mail.properties.mail.smtp.auth"));
    properties.setProperty("mail.smtp.starttls.enable",env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
    properties.setProperty("smtp.starttls.required",env.getProperty("spring.mail.properties.mail.smtp.starttls.required"));
    return properties;
  }

  @Bean
  public ResourceBundleMessageSource emailMessageSource(){
    final ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
    messageSource.setBasename("mail/MailMessage");
    return messageSource;
  }

  @Bean
  public TemplateEngine templateEngine(){
    final SpringTemplateEngine templateEngine=new SpringTemplateEngine();
    templateEngine.addTemplateRessolver(textTemplateResolver());
    templateEngine.addTemplateRessolver(htmlTemplateResolver());
    templateEngine.addTemplateRessolver(stringTemplateResovler());
    templateEngine.setTemplateEngineMessageSource(emailMessageSource());
    return templateEngine;
  }

  private ITemplateResolver textTemplateResolver(){
    final ClassLoaderTemplateResolver templateResolver=new ClassLoaderTemplateResolver();
    templateResolver.setOrder(Integer.valueOf(1));
    templateResolver.setResovablePatterns(Collections.singleton("text/*"));
    templateResolver.setPrefix("/mail/");
    templateResolver.setSuffix(".text");
    templateResolver.setTemplateMode(TemplateMode.TEXT);
    templateResolver.setCharacterEncoding(MAIL_TEMPLATE_ENCODING);
    templateResolver.setCacheable(false);
    return templateResolver;
  }

  private ITemplateResolver htmlTemplateResolver(){
    final ClassLoaderTemplateResolver templateResolver=new ClassLoaderTemplateResolver();
    templateResolver.setOrder(Integer.valueOf(2));
    templateResolver.setResovablePatterns(Collections.singleton("html/*"));
    templateResolver.setPrefix("/mail/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setCharacterEncoding(MAIL_TEMPLATE_ENCODING);
    templateResolver.setCacheable(false);
    return templateResolver;
  }

  private ITemplateResolver stringTemplateResolver(){
    final ClassLoaderTemplateResolver templateResolver=new ClassLoaderTemplateResolver();
    templateResolver.setOrder(Integer.valueOf(3));
    templateResolver.setTemplateMode("HTML5");
    templateResolver.setCharacterEncoding(MAIL_TEMPLATE_ENCODING);
    templateResolver.setCacheable(false);
    return templateResolver;
  }
}
