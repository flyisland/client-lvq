package com.solace.demo.lvq;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class LVQListener implements MessageListener {
    private static String SIGNAL = "";
    private ConcurrentHashMap<String, Message> lvqMap = new ConcurrentHashMap<>();
    private ArrayBlockingQueue<String> signalQueue =new ArrayBlockingQueue<>(1);
    private MessageListener listener;

    public LVQListener(MessageListener listener) {
        this.listener = listener;
        var exec = Executors.newSingleThreadExecutor();
        exec.execute(this::run);
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message.getJMSDeliveryMode() == DeliveryMode.PERSISTENT) {
                message.acknowledge();
            }
            // for each key (topic), store only one message
            lvqMap.put(message.getJMSDestination().toString(), message);
            // notify the message listener
            signalQueue.offer(SIGNAL);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        try {
        while (true) {
            // wait for new messages
            signalQueue.take();
            processMessages();
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processMessages() {
        System.out.println("\n---");
        while (lvqMap.size() > 0) {
            var keys = new ArrayList<>(lvqMap.keySet());
            keys.forEach(key -> {
                // take the last message out of the LVQ for each topic
                var message = lvqMap.get(key);
                lvqMap.remove(key, message);
                // handle this message
                listener.onMessage(message);
            });
        }
    }
}
