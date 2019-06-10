package dev.samujjal.weekend.kafkademo.domain;

import lombok.Data;

@Data
public class KafkaMessage {
    private String key;
    private String message;
}
