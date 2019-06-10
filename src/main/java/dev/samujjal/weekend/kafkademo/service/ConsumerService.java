package dev.samujjal.weekend.kafkademo.service;

public interface ConsumerService {
    void readMessages(String topicName);
}
