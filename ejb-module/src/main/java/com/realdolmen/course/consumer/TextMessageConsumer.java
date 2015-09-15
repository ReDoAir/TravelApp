package com.realdolmen.course.consumer;


import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.persistence.TicketRepository;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/*@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/MyQueue"),
})*/
public class TextMessageConsumer implements MessageListener {

    private TicketRepository repository;

    @Override
    public void onMessage(Message message) {
        /*try {
            TextMessage textMessage = (TextMessage) message;
            System.out.println(textMessage.getText());

        } catch (JMSException e) {
            throw new RuntimeException("Deal with this", e);
        }*/
    }
}
