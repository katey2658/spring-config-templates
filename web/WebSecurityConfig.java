@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      super.configure(auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
          .anyRequest()
          .permitAll();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
      super.configure(web);
  }
}
