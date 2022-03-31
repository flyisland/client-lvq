package com.solace.demo.lvq;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DumpMessageListener implements MessageListener {
    protected static final Logger logger = LoggerFactory.getLogger(DumpMessageListener.class);
    @Override
    public void onMessage(Message message) {
        try {
            var dest = message.getJMSDestination();
            String body = "Unknown Message Type";
            if (message instanceof TextMessage) {
                body = ((TextMessage) message).getText();
            } else if (message instanceof BytesMessage) {
                var bMsg = (BytesMessage) message;
                var bytes = new byte[(int)bMsg.getBodyLength()];
                bMsg.readBytes(bytes);
                body = new String(bytes);
            }
            logger.info("onMessage: [{}]\n{}", dest.toString(),body);
            String s = message.getJMSDestination().toString();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
