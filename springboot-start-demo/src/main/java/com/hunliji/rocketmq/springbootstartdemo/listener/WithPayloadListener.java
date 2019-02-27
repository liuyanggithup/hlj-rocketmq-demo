package com.hunliji.rocketmq.springbootstartdemo.listener;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
    topic = "TEST",
    selectorExpression = "withPayload",
    consumerGroup = "withPayload-consumerGroup")
public class WithPayloadListener
    implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
  @Override
  public void onMessage(MessageExt message) {
    System.out.printf("------- MessageExtConsumer received message, %s \n", message.toString());
    // int i = 1/0; 失败重试需要抛异常，
    // 需要调研业务判断失败重试的方案，返回状态值
    // public enum ConsumeConcurrentlyStatus {
    //    /**
    //     * Success consumption
    //     */
    //    CONSUME_SUCCESS,
    //    /**
    //     * Failure consumption,later try to consume
    //     */
    //    RECONSUME_LATER;
    // }
    System.out.println(new String(message.getBody()));
  }

  @Override
  public void prepareStart(DefaultMQPushConsumer consumer) {
    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
  }
}
