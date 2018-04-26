package ethan.etframework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import ethan.etframework.util.mq.MQTaskQueue;

@ConfigurationProperties(prefix = "et.mq") // 该注解的locations已经被启用，现在只要是在环境中，都会优先加载  
public class TaskMQConfig {  
	
	private String recvFilePath;
	private String brokerUrl;
    private String queueNames;
    private boolean status; 

	
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the queueNames
	 */
	public String getQueueNames() {
		return queueNames;
	}

	/**
	 * @param queueNames the queueNames to set
	 */
	public void setQueueNames(String queueNames) {
		this.queueNames = queueNames;
	}
	
	
  
   /**
	 * @return the recvFilePath
	 */
	public String getRecvFilePath() {
		return recvFilePath;
	}

	/**
	 * @param recvFilePath the recvFilePath to set
	 */
	public void setRecvFilePath(String recvFilePath) {
		this.recvFilePath = recvFilePath;
	}

	/**
	 * @return the brokerUrl
	 */
	public String getBrokerUrl() {
		return brokerUrl;
	}

	/**
	 * @param brokerUrl the brokerUrl to set
	 */
	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}

    
} 
