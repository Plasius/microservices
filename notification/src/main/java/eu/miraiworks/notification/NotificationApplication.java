package eu.miraiworks.notification;

import eu.miraiworks.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "eu.miraiworks.notification",
                "eu.miraiworks.amqp",
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
           RabbitMQMessageProducer producer,
           NotificationConfig notificationConfig
            ) {
        return args -> {
            producer.publish(
                    new Person("ali", 44),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey());
        };
    }

    record Person(String name, int age){

    }
}