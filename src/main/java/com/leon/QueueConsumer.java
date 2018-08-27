package com.leon;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueConsumer {
	public static void main(String[] args) throws JMSException, IOException {
		// 1.create connection factory
				ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.131:61616");
				
				// 2.create connection object
				Connection connection = factory.createConnection();
				
				// 3. start connection
				connection.start();
				
				// 4. get session
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				
				// 5. create queue object
				Queue queue = session.createQueue("test-queue");
				
				// 6. create consumer
				MessageConsumer consumer = session.createConsumer(queue);
				
				// 7. set listener
				consumer.setMessageListener(new MessageListener() {
					
					public void onMessage(Message message) {
						TextMessage textMessage = (TextMessage) message;
						try {
							System.out.println("got the msg :"+textMessage.getText());
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				
				// 8.pause for a while to listener
				System.in.read();
				
				
				// 9. close resources;
				consumer.close();
				session.close();
				connection.close();
				
				
	}
}
