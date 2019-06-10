package dev.samujjal.weekend.kafkademo.service.impl;

import dev.samujjal.weekend.kafkademo.service.ConsumerService;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;

    @Override
    public void readMessages(String topicName){
        kafkaConsumer.subscribe(Collections.singleton(topicName));
    }
}
