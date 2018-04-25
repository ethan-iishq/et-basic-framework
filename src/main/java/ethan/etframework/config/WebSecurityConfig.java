package ethan.etframework.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import ethan.etframework.security.CustomAuthenticationDetailsSource;
import ethan.etframework.security.CustomAuthenticationProvider;
import ethan.etframework.security.CustomUserDetailsService;
import ethan.etframework.security.LoginSuccessHandler;
import ethan.etframework.security.MyAuthenticationProvider;
import ethan.etframework.security.MyFilterSecurityInterceptor;


@Configuration 
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { 
	@Autowired 
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	CustomAuthenticationDetailsSource customAuthenticationDetailsSource;
	@Autowired()
	CustomAuthenticationProvider  customAuthenticationProvider;
//	@Autowired
//	private MyAuthenticationProvider myAuthenticationProvider;
	
	@Bean 
	public UserDetailsService customUserService(){ 
		//注册UserDetailsService 的bean 
		return new CustomUserDetailsService(); 
	}
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return null;
		
	}
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder()); 
		//user Details Service验证 
	}
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
        auth.eraseCredentials(false);         
    }  
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.successHandler(loginSuccessHandler())
			.authenticationDetailsSource(customAuthenticationDetailsSource)
			.failureUrl("/login?error")
			.permitAll() //登录页面用户任意访问 
			.and()
			.logout().permitAll()
			.and()
			.rememberMe()
			.tokenRepository(persistentTokenRepository())
			//.rememberMeServices(rememberMeServices())
			.tokenValiditySeconds(60*60*24*7); //注销行为任意访问 
		//http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class); 
	}
	
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
	
	@Bean  
    public LoginSuccessHandler loginSuccessHandler(){  
        return new LoginSuccessHandler();  
    }
	
	/*@Bean
	
	public RememberMeServices rememberMeServices() {
		
	    JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
	    rememberMeTokenRepository.setDataSource(dataSource);
	
	    PersistentTokenBasedRememberMeServices rememberMeServices =
	            new PersistentTokenBasedRememberMeServices("INTERNAL_SECRET_KEY", userDetailsService(), rememberMeTokenRepository);
	
	    rememberMeServices.setTokenValiditySeconds(60*60*24*7);
	    return rememberMeServices;
	}*/

}
