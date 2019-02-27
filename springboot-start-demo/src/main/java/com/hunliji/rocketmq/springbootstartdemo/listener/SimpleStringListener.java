package com.hunliji.rocketmq.springbootstartdemo.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
    topic = "TEST",
    selectorExpression = "xyz",
    consumerGroup = "test-consumerGroup")
public class SimpleStringListener implements RocketMQListener<String> {

  @Override
  public void onMessage(String message) {
    System.out.printf("------- StringTransactionalConsumer received: %s \n", message);
  }
}
