package ethan.etframework.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ethan.etframework.entity.SecuritySysUser;

//@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private CustomUserDetailsService userService;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        SecuritySysUser user = (SecuritySysUser) userService.loadUserByUsername(username);
        logger.info(username + ":" + password);
        if(user == null){
            throw new BadCredentialsException("Username not found.");
        }
        //加密过程在这里体现
        
        
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
