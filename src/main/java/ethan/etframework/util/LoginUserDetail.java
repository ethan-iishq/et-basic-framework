package ethan.etframework.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUserDetail {
	public static UserDetails getLoginUserDetail(){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()  
			    .getAuthentication()  
			    .getPrincipal();
		return userDetails;
	}
}
