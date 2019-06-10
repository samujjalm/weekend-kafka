package dev.samujjal.weekend.kafkademo.service;

import java.util.Map;

public interface ConsumerService {
    Map<String, String> readMessages(String topicName);
}
