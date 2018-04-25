package ethan.etframework.util.mq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MQTaskQueue {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private BlockingQueue<String> taskQueue = new LinkedBlockingQueue<String> (128);
	
	public MQTaskQueue(String names){
		String[] nameArr = names.split(",");
		for(String name : nameArr){
			boolean ret = false;
			while(true){
				ret = taskQueue.offer(name);
				if(ret){
					break;
				}
			}
		}
		
		logger.info("contruct MQTaskQueue over with:" +names);
	}
	
	public String getTask() throws InterruptedException{
		String queueName = null;
		while((queueName = taskQueue.poll()) == null){
			Thread.sleep(3000);
		}
		
		return queueName;
	}
	
	
	public boolean putTask(String queueName) throws InterruptedException{
		boolean ret = false;
		while(true){
			ret = taskQueue.offer(queueName);
			if(ret){
				break;
			}else{
				Thread.sleep(3000);
			}
		}
		return ret;
	}
	
	public boolean isMQTaskQueueEmpty(){
		return taskQueue.isEmpty();
	}
}
