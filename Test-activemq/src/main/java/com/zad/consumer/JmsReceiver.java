package com.zad.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 描述:
 * aa
 *
 * @author zad
 * @create 2018-09-20 19:20
 */
public class JmsReceiver {
    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.220.131:61616");
        Connection connection = null;

        try {
            // 创建连接
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("first-queue");
            MessageConsumer consumer = session.createConsumer(destination);
            //TextMessage tm = (TextMessage) consumer.receive();
            //MapMessage message = (MapMessage)consumer.receive();
            BytesMessage message = (BytesMessage)consumer.receive();
            System.out.println(message.readUTF());
            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
