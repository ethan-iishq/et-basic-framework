package ethan.etframework.controller;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller
public class CaptchaController {
	
	@Autowired
	DefaultKaptcha captchaProducer;
	
	@RequestMapping(value = "/captcha-image")  
    public ModelAndView getKaptchaImage(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control",  
                "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
  
        
        
        
        String capText = captchaProducer.createText();  
        System.out.println("capText: " + capText);
        
        HttpSession session = request.getSession();
        session.setAttribute("session_imageCode", capText);
        session.setAttribute("session_imageTime", String.valueOf(new Date().getTime()));
        
        /*try {  
            String uuid=UUIDUtils.getUUID32().trim().toString();              
            redisTemplate.opsForValue().set(uuid, capText,60*5,TimeUnit.SECONDS);  
            Cookie cookie = new Cookie("captchaCode",uuid);  
            response.addCookie(cookie);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } */ 
  
          
  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    } 
}
