package com.realdolmen.course.consumer;


import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.persistence.FlightRepository;
import com.realdolmen.course.persistence.PassengerRepository;
import com.realdolmen.course.persistence.TicketRepository;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.print.DocFlavor;
import java.util.Objects;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/MyQueue"),
})
public class TicketMessageConsumer implements MessageListener {

    @EJB
    private TicketRepository ticketRepository;
    @EJB
    private FlightRepository flightRepository;
    @EJB
    private PassengerRepository passengerRepository;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String[] result = textMessage.getText().split(",");
            if(!Objects.equals(result[0], "")) {
                update(result);
            }else {
                create(result);
            }
        } catch (JMSException e) {
            throw new RuntimeException("Deal with this", e);
        }
    }

    private void update(String[] result) {
        Ticket t = ticketRepository.getById(Integer.parseInt(result[0]));
        t.setPrice(Double.parseDouble(result[1]));
        ticketRepository.update(t);
        System.out.println("*******************");
        System.out.printf("TICKET %d - updated", t.getId());
    }

    private void create(String[] result){

        Passenger p = passengerRepository.findPassengerById(Integer.parseInt(result[3]));
        Flight f = flightRepository.findFlightById(Integer.parseInt(result[2]));

        Ticket t = new Ticket(Double.parseDouble(result[1]),p,f);
        ticketRepository.create(t);

        System.out.println("*******************");
        System.out.println("TICKET CREATED");
    }

}
