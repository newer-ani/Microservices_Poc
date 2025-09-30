package com.ttn.quizservice.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QuestionListener {

    private static final Logger log = LoggerFactory.getLogger(QuestionListener.class);

    // This annotation tells Spring to call this method whenever
    // a message arrives on the "question_queue".
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        log.info(">>>> Received message from RabbitMQ: '{}'", message);
    }
}
