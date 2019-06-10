package dev.samujjal.weekend.kafkademo.service.impl;

import dev.samujjal.weekend.kafkademo.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import java.awt.event.KeyAdapter;
import java.time.Duration;
import java.util.*;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    private final KafkaConsumer<String, String> kafkaConsumer;

    public ConsumerServiceImpl(KafkaConsumer<String, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @Override
    public Map<String, String> readMessages(String topicName) {
        kafkaConsumer.subscribe(Collections.singleton(topicName));
        Map<String, String> messagesRead = new HashMap<>();
        boolean hasMessages = true;
        while (hasMessages) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
            if (consumerRecords.isEmpty()) {
                hasMessages = false;
            } else {
                for (ConsumerRecord<String, String> record : consumerRecords) {
                    if(record.key() != null){
                        messagesRead.put(record.key(), record.value());
                    }else {
                        messagesRead.put(String.valueOf(new Random(100).nextInt(1000)), record.value());
                    }
                    log.info("Record (key {}, value {}) Read from partition {} of topic {}", record.key(), record.value()
                            , record.partition(), record.topic());
                }
                kafkaConsumer.commitSync();
            }
        }

        kafkaConsumer.unsubscribe();
        kafkaConsumer.close();

        return messagesRead;
    }
}
