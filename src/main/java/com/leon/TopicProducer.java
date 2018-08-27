package com.leon;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicProducer {
	
	public static void main(String[] args) throws JMSException {
			// 1.create connection factory
			ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.131:61616");
			
			// 2.create connection object
			Connection connection = factory.createConnection();
			
			// 3. start connection
			connection.start();
			
			// 4. get session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// 5. create queue object
			Topic topic = session.createTopic("test-topic");
			
			// 6. create msg provider obj
			MessageProducer producer = session.createProducer(topic);
			
			// 7. create text msg
			TextMessage textMessage = session.createTextMessage("hello world!");
			
			// 8. provider send msg
			producer.send(textMessage);
			
			// 9. close resources;
			producer.close();
			session.close();
			connection.close();
		
}
}
