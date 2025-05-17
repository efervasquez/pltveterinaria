
package com.citas.messaging;
import com.citas.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    private final com.citas.service.EmailService emailService;

    public MessageConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveEmailNotification(CitaEmailNotification notification) {
        logger.info("Received email notification: {}", notification);
        try {
            emailService.sendEmail(notification);
            logger.info("Email sent successfully to: {}", notification.getTo());
        } catch (Exception e) {
            logger.error("Failed to process email notification: {}", e.getMessage(), e);
        }
    }
}