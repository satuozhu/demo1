package com.users.component.security;


import com.users.modules.user.service.impl.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;


/**
 * Created by xiangchao on 2019/2/21.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                //由于是Jwt 关闭csrf拦截
                        csrf().disable()
                //基于token,无需session
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .authorizeRequests()
                //所有用户可以访问"/home"和favicon.ico，*代表一个包，**代表多个包
                .antMatchers( "/**/favicon.ico", "/user/**","/admin/**","/pres/**","/role/**").permitAll()
                //其余请求,全部需要认证
                .anyRequest().authenticated()
                .and()
                .formLogin() //定义登录时跳转的页面
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/")//登录成功跳转页面
                .permitAll()
                .failureUrl("/error")//登录失败URL
                .and()
                .logout()
                .permitAll();
        //禁止缓存
        //  http.headers().defaultsDisabled().cacheControl();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                //设置UserDetailsService
                .userDetailsService(this.userDetailService).passwordEncoder(new PasswordEncoder() {
            /**
             * 编码
             *
             * @param charSequence
             * @return
             */
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }
            /**
             * @param charSequence 明文
             * @param s            密文
             * @return
             */
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
            }
        });
    }
    /**
     * 注入AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
