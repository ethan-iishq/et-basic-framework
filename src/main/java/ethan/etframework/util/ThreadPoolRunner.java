package ethan.etframework.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ThreadPoolRunner implements CommandLineRunner{

	@Override
	public void run(String... arg0) throws Exception {
		//可以从这个地方启动调度线程池的任务
		System.out.println("StartPool implements CommandLineRunner exec...");
		
	}



}
