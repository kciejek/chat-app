package com.shubh.kafkachat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Message {

  private String sender;
  private String content;
  private String timestamp;

}
