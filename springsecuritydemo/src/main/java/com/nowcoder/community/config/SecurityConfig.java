package com.nowcoder.community.config;

import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //注入该类的原因时因为该类实现了UserDetailService
    @Autowired
    private UserService userService;

    //忽略静态资源的访问
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    //认证
    //AuthenticationManager:  认证的核心接口
    //AuthenticationManagerBuilder： 用于构建AuthenticationManager的工具
    //ProviderManager:  AuthenticationManager接口的默认实现类
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内置的认证规则                       密码加密(加密方式(盐))
        //auth.userDetailsService(userService).passwordEncoder(new Pbkdf2PasswordEncoder("12345"));由于该项目中采用md5加密已经加密所以不采用内置规则

        //自定义认证规则
        //AuthenticationProvider : ProviderManager持有一组AuthenticationProvider
        //每个AuthenticationProvider负责一种认证
        //委托模式: ProviderManager将认证委托给了AuthenticationProvider
        auth.authenticationProvider(new AuthenticationProvider() {
            //Authentication: 用于分装认证认证信息的接口,不同的实现类,代表不同类型的认证信息
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String username = authentication.getName();
                String password = (String) authentication.getCredentials();

                User user = userService.findUserByName(username);
                if (user == null){
                    throw new UsernameNotFoundException("账号不存在");
                }
                password = CommunityUtil.md5(password + user.getSalt());
                if (!user.getPassword().equals(password)){
                    throw new BadCredentialsException("密码不正确");
                }

                //UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities)
                // principal:主要信息; credentials:证书;authorities:权限
                return new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
            }

            //返回当前AuthenticationProvider接口支持的哪种认证类型
            @Override
            public boolean supports(Class<?> aClass) {
                //UsernamePasswordAuthenticationToken : Authentication接口的常用实现类
                return UsernamePasswordAuthenticationToken.class.equals(aClass);
            }
        });
    }


    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录相关的配置
        http.formLogin()
                .loginPage("/loginpage")  //RequestMapping路径
                .loginProcessingUrl("/login")  //登录提交表单的action路径
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect(request.getContextPath() + "/index");

                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        request.setAttribute("error",e.getMessage());
                        request.getRequestDispatcher("/loginpage").forward(request,response);
                    }
                });

        //退出相关配置
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                       response.sendRedirect(request.getContextPath() + "/index");
                    }
                });

        //授权设置
        http.authorizeRequests()
                .antMatchers("/letter").hasAnyAuthority("USER","ADMIN")//letter页面访问的权限
                .antMatchers("/admin").hasAnyAuthority("ADMIN")//admin页面访问的权限
                .and().exceptionHandling().accessDeniedPage("/denied");//访问失败跳转的页面


        //自定义Filter,处理验证码,在用户名密码校验前执行
        http.addFilterBefore(new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) servletRequest;
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                //当前访问路径时login时
                if (request.getServletPath().equals("/login")){
                    String verifyCode = request.getParameter("verifyCode");
                    //这里为了演示前台没有生成验证码,并将验证码固定为1234
                    if (verifyCode == null || !verifyCode.equalsIgnoreCase("1234")){
                        request.setAttribute("error","验证码错误");
                        request.getRequestDispatcher("/loginpage").forward(request,response);
                        return;
                    }

                }
                //让请求继续向下执行
                filterChain.doFilter(request,response);
            }
        },UsernamePasswordAuthenticationFilter.class);



        //记住我
        http.rememberMe()
                .tokenRepository(new InMemoryTokenRepositoryImpl())//将用户存到内存中
                .tokenValiditySeconds(3600 * 24)//过期事件
                .userDetailsService(userService);//指定userDetailsService
    }
}
