package ethan.etframework.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import ethan.etframework.entity.SecuritySysUser;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
    private CustomUserDetailsService userService;
	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();  
        logger.info(details.toString());
        String imageCode = details.getImageCode();
        String session_imageCode = details.getSession_imageCode();
        long session_imageTime = details.getSession_imageTime();
        if(imageCode == null || "".equals(imageCode)){
        	return null;
        }
        if(imageCode == null || session_imageCode == null) {
            throw new ImageCodeIllegalException("验证码错误");
        }

        if(!imageCode.equals(session_imageCode)) {
            throw new ImageCodeIllegalException("验证码错误");
        }else{
            long nowTime = System.currentTimeMillis();
            if((nowTime - session_imageTime)/1000 > 60) { //大于60s,超时
                throw new ImageCodeIllegalException("验证码已超时");
            }
        }
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        SecuritySysUser user = (SecuritySysUser) userService.loadUserByUsername(username);
        logger.info(username + ":" + password);
        if(user == null){
            throw new BadCredentialsException("Username not found.");
        }
        //加密过程在这里体现
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

class ImageCodeIllegalException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6846465073069776834L;

	public ImageCodeIllegalException(String msg){
		super(msg);
	}
}
