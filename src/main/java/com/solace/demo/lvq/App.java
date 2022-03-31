package com.solace.demo.lvq;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import javax.jms.ConnectionFactory;

import org.apache.qpid.jms.JmsConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    protected static final Logger logger = LoggerFactory.getLogger(App.class);
    private Conf conf = new Conf();
    private CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        App app = new App();
        app.run(args);
    }

    private void run(String[] args) {
        conf.parse(args);

        ConnectionFactory connectionFactory = new JmsConnectionFactory(conf.user, conf.pwd, conf.host);
        try (var context = connectionFactory.createContext()) {
            logger.info("Connected to {} with username {}", conf.host, conf.user);
            var listener = new DumpMessageListener();
            if (Objects.nonNull(conf.topicName)){
                logger.info("Subscribe to TOPIC [{}]", conf.topicName);
                var topic = context.createTopic(conf.topicName);
                var consumer = context.createConsumer(topic);
                consumer.setMessageListener(listener);
            }
            if (Objects.nonNull(conf.queueName)) {
                logger.info("Subscribe to QUEUE [{}]", conf.queueName);
                var queue = context.createQueue(conf.queueName);
                var consumer = context.createConsumer(queue);
                consumer.setMessageListener(listener);
            }
            logger.info("Waiting for messages ...");
            context.start();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
