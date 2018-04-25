package ethan.etframework.mq;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.BlobMessage;
import org.apache.activemq.command.ActiveMQBlobMessage;
import org.apache.activemq.command.ActiveMQQueue;  
  
public class TestBlob {  
  
    public static void main(String[] args) {  
        try {  
  
            ActiveMQConnectionFactory factoryA = new ActiveMQConnectionFactory("tcp://localhost:61616?jms.blobTransferPolicy.defaultUploadUrl=http://localhost:8161/fileserver/");  
  
            Queue queue = new ActiveMQQueue("blob.kk");  
            ActiveMQConnection conn = (ActiveMQConnection) factoryA.createConnection();  
              
            conn.start();  
            ActiveMQSession session = (ActiveMQSession) conn.createSession(false, Session.AUTO_ACKNOWLEDGE);  
              
            MessageConsumer consumer = session.createConsumer(queue);  
  
            MessageListener listener = new MessageListener() {  
                public void onMessage(Message message) {  
                    try {  
                        System.out.println(" => receive from blob.kk: ");  
                        if (message instanceof BlobMessage) {  
                              
                            System.out.println("filename:"+message.getStringProperty("FILE.NAME"));  
                            System.out.println("filesize:"+message.getLongProperty("FILE.SIZE"));  
                              
                              BlobMessage blobMessage = (BlobMessage) message; 
                              String url = blobMessage.getURL().toString();
                              InputStream in = blobMessage.getInputStream();
                              BufferedInputStream bis = new BufferedInputStream(in);
                              byte[] buff = new byte[4096];
                              while(bis.read(buff) > 0){
                            	  System.out.println(buff);
                              }
                              in.close();
                              ((ActiveMQBlobMessage)blobMessage).deleteFile();//注意处理完后需要手工删除服务器端文件    
                        }  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            };  
            consumer.setMessageListener(listener);  
  
            File file = new File("F://ytest.txt");  
            MessageProducer producer = session.createProducer(queue);  
            BlobMessage blobMessage = session.createBlobMessage(file);  
            blobMessage.setStringProperty("FILE.NAME",file.getName());   
            blobMessage.setLongProperty("FILE.SIZE",file.length());   
            producer.send(blobMessage);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
}  