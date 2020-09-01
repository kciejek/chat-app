package com.shubh.kafkachat.controller;

import com.shubh.kafkachat.constants.KafkaConstants;
import com.shubh.kafkachat.model.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
public class ChatController {

  private final KafkaTemplate<String, Message> kafkaTemplate;

  public ChatController(KafkaTemplate<String, Message> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
  public void sendMessage(@RequestBody Message message) {
    message.setTimestamp(LocalDateTime.now().toString());
    try {
      //Sending the message to kafka topic queue
      kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

}
