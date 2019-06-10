package dev.samujjal.weekend.kafkademo.service;

public interface ProducerService {
    void writeMessageWithKey(String topic, String key, String message);
}
