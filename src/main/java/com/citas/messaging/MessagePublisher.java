package com.citas.messaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    private static final Logger logger = LoggerFactory.getLogger(MessagePublisher.class);

    private final RabbitTemplate rabbitTemplate;
    //private boolean rabbitMQAvailable = true;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    public MessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void publishEmailNotification(CitaEmailNotification notification) {
        try {
            logger.info("Sending email notification to queue: {}", notification.getTo());
            rabbitTemplate.convertAndSend(exchange, routingKey, notification);
            logger.info("Email notification sent successfully to queue");
        } catch (Exception e) {
            logger.error("Error sending email notification: {}", e.getMessage(), e);
            logger.info("Email would have been sent to: {}", notification.getTo());
            logger.info("Subject: {}", notification.getSubject());
            logger.info("Body: {}", notification.getBody());
        }
    }


}