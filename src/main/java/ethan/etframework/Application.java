package ethan.etframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

import ethan.etframework.config.TaskMQConfig;
import ethan.etframework.config.TaskThreadPoolConfigProperties;




@SpringBootApplication
@EnableAsync  
@EnableConfigurationProperties({TaskThreadPoolConfigProperties.class, TaskMQConfig.class} ) // 开启配置属性支持
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}

}
