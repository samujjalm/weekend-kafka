package dev.samujjal.weekend.kafkademo.service.impl;

import dev.samujjal.weekend.kafkademo.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private KafkaProducer<String, String> kafkaProducer;

    @Override
    public void writeMessageWithKey(String topic, String key, String message){
        this.kafkaProducer.send(new ProducerRecord<>("topic-new-york", key, message), (recordMetadata, e) -> {
            log.info("Message written to {} at {} has offset {}", recordMetadata.topic(), recordMetadata.timestamp(), recordMetadata.offset());
        });
    }
}
