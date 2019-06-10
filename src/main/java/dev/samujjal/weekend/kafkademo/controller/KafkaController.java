package dev.samujjal.weekend.kafkademo.controller;

import dev.samujjal.weekend.kafkademo.domain.KafkaMessage;
import dev.samujjal.weekend.kafkademo.service.ConsumerService;
import dev.samujjal.weekend.kafkademo.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class KafkaController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/message/{topic}")
    public ResponseEntity<String> sendMessage(@RequestBody KafkaMessage kafkaMessage, @PathVariable("topic") String topic){
        producerService.writeMessageWithKey(topic, kafkaMessage.getKey(), kafkaMessage.getMessage());
        return ResponseEntity.ok("WrittenMessage");
    }

    @GetMapping("/messages/{topic}")
    public ResponseEntity<List<Map<String, String>>> getMessages(@PathVariable("topic") String topic){


    }
}
