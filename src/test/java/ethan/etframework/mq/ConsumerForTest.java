package ethan.etframework.mq;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerForTest {  
	private static Logger logger = LoggerFactory.getLogger(ConsumerForTest.class);
    /** 
     * @param args 
     * @throws InterruptedException 
     */  
    public static void main(String[] args) throws JMSException, IOException {  
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");  
       
        Connection connection = factory.createConnection();  
        connection.setClientID("etClient1");
        connection.start();  
  
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
  
        Topic destination = session.createTopic("EXCHANGE.FILE");  
  
        //MessageConsumer consumer = session.createConsumer(destination);  
        MessageConsumer consumer = session.createDurableSubscriber(destination, "etClient1");
        
        final String SAVE_PATH = "F:/";
        
        boolean appended = false;
        long begin = 0;
        long end = 0;
        try {  
            while (true) {  
                Message message = consumer.receive(1000);  
                if (message == null) {  
                    continue;  
                }  
  
                if (message instanceof StreamMessage) {  
                    StreamMessage streamMessage = (StreamMessage) message;  
                    String command = streamMessage.getStringProperty("COMMAND");  
                      
                    if ("start".equals(command)) {
                    	begin = new Date().getTime();
                    	
                    	String file_name = message.getStringProperty("FILE_NAME");
                    	FileOutputStream fos = new FileOutputStream(SAVE_PATH + file_name);
                    	if(fos != null){
                    		fos.close();
                    	}
                        appended = false;
                        System.out.println("开始保存文件 " + file_name + " ...");
                        continue;  
                    }  
  
                    if ("sending".equals(command)) {  
                        byte[] content = new byte[4096*4];  
                        String file_name = message.getStringProperty("FILE_NAME"); 
                        
                        FileOutputStream fos = null;
                        do{
                        	try{
                        		fos = new FileOutputStream(SAVE_PATH + file_name, appended);
                        	}catch(IOException e){
                        		System.err.println(e.getMessage());
                        	}
                        	
                        }while(fos == null);
                        
                        BufferedOutputStream bos = null;
                        
                        
                        bos = new BufferedOutputStream(fos);
                        	
                        if (!appended) {  
                            appended = true;  
                        }
                        
                        while (streamMessage.readBytes(content) > 0) {  
                            bos.write(content); 
                            System.out.println("saving file " + file_name + " ...");
                        }
                        bos.flush();
                        bos.close();
                        fos.flush();
                        fos.close();
                        
                        continue;  
                    }  
  
                    if ("end".equals(command)) {
                    	end = new Date().getTime();
                        appended = false;
                        System.out.println("文件保存完毕...");
                        System.out.println("文件保存耗时：" + (end - begin) + " ms");
                        String file_name = message.getStringProperty("FILE_NAME"); 
                        logger.info("文件 " + file_name + " 保存耗时：" + (end - begin) + " ms");
                        continue;
                    }  
                }  
            }  
        } catch (JMSException e) {  
            throw e;  
        } finally {  
            if (connection != null) {  
                connection.close();  
            }  
        }  
  
    }  
  
}  