package xu.gateway.config;


import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: security config 的 笔记代码
 * @Author: xuyujun
 * @Date: 2022/5/17
 */
//@Configuration
//@EnableWebSecurity
public class SecurityTestConfig extends WebSecurityConfigurerAdapter {
/*
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //访问"/"和"/home"路径的请求都允许
                .antMatchers("/", "/home").permitAll()
                //而其他的请求都需要认证
                .anyRequest().authenticated()
                .and()
                //修改Spring Security默认的登陆界面
                .formLogin()
                .loginPage("/login")
                .permitAll()
//                可指定 登录成功、登录失败 后的页面
//                .successForwardUrl("/success")
//                .failureForwardUrl("/failure")
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //基于内存来存储用户信息
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER").and()
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("456")).roles("USER", "ADMIN");

        // 设置自定义的userDetailsService
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();// 使用不使用加密算法保持密码
//        return new BCryptPasswordEncoder();
    }


*/
}
