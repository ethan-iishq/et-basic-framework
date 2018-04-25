package ethan.etframework.mq;

import java.io.BufferedInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
  
import javax.jms.Connection;  
import javax.jms.ConnectionFactory;  
import javax.jms.Destination;  
import javax.jms.JMSException;  
import javax.jms.MessageProducer;  
import javax.jms.Session;  
import javax.jms.StreamMessage;  
  
import org.apache.activemq.ActiveMQConnectionFactory;  
  
public class PublisherForTest {  
  
    public static String FILE_NAME = "03校对女孩.MP4";  
      
    public static void main(String[] args) throws JMSException, IOException {  
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");  
        Connection connection = factory.createConnection();  
        connection.start();  
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
        Destination destination = session.createTopic("EXCHANGE.FILE");
        
        MessageProducer producer = session.createProducer(destination);  
        long time = System.currentTimeMillis();  
          
        //通知客户端开始接受文件  
        StreamMessage message = session.createStreamMessage(); 
        message.setStringProperty("FILE_NAME", FILE_NAME);
        message.setStringProperty("COMMAND", "start");  
        producer.send(message);  
        System.out.println("开始发送...");
        //开始发送文件  
        byte[] content = new byte[4096*4];  
        InputStream ins = PublisherForTest.class.getClassLoader().getResourceAsStream(FILE_NAME);
        BufferedInputStream bins = new BufferedInputStream(ins);  
        while (bins.read(content) > 0) {  
            //  
            message = session.createStreamMessage();  
            message.setStringProperty("FILE_NAME", FILE_NAME);  
            message.setStringProperty("COMMAND", "sending");  
            message.clearBody();  
            message.writeBytes(content);  
            producer.send(message);
            System.out.println("发送中...");
        }  
        bins.close();  
        ins.close();  
          
        //通知客户端发送完毕  
        message = session.createStreamMessage();
        message.setStringProperty("FILE_NAME", FILE_NAME);
        message.setStringProperty("COMMAND", "end");  
        producer.send(message);  
        System.out.println("结束发送...");
        connection.close();
          
        System.out.println("Total Time costed : " + (System.currentTimeMillis() - time) + " mili seconds");  
    }  
}  
