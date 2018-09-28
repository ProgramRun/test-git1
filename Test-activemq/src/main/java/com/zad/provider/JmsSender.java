package com.zad.provider;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-20 19:11
 */
public class JmsSender {
    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.220.131:61616");
        Connection connection = null;

        try {
            // 创建连接
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("first-queue");
            MessageProducer producer = session.createProducer(destination);
            /*TextMessage message = session.createTextMessage();
            message.setText("hello,zad");*/

           /* MapMessage message = (MapMessage)session.createMapMessage();
            message.setString("name","zad");*/

            /*BytesMessage message = session.createBytesMessage();
            message.writeUTF("你好");*/

            ObjectMessage message = session.createObjectMessage();

            producer.send(message);

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
