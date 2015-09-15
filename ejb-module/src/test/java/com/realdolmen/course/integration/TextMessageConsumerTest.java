package com.realdolmen.course.integration;
import org.junit.Test;

public class TextMessageConsumerTest extends RemoteJmsTest {
    @Test
    public void produceTextMessage() throws Exception {
        producer.send(session.createTextMessage("1001,200"));
    }
}
