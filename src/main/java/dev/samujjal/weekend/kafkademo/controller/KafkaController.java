package dev.samujjal.weekend.kafkademo.controller;

import dev.samujjal.weekend.kafkademo.domain.KafkaMessage;
import dev.samujjal.weekend.kafkademo.service.ConsumerService;
import dev.samujjal.weekend.kafkademo.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class KafkaController {

    private final ProducerService producerService;

    private final ConsumerService consumerService;

    public KafkaController(ProducerService producerService, ConsumerService consumerService) {
        this.producerService = producerService;
        this.consumerService = consumerService;
    }

    @PostMapping("/message/{topic}")
    public ResponseEntity<String> sendMessage(@RequestBody KafkaMessage kafkaMessage, @PathVariable("topic") String topic) {
        producerService.writeMessageWithKey(topic, kafkaMessage.getKey(), kafkaMessage.getMessage());
        return ResponseEntity.ok("WrittenMessage");
    }

    @GetMapping("/messages/{topic}")
    public ResponseEntity<Map<String, String>> getMessages(@PathVariable("topic") String topic) {
        return ResponseEntity.ok(consumerService.readMessages(topic));

    }
}
