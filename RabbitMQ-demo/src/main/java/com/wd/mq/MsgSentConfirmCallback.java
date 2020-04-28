package com.wd.mq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.stereotype.Component;
@Component
public class MsgSentConfirmCallback implements ConfirmCallback{

	//当消息发送到交换机（exchange）时，该方法被调用.
    //1.如果消息没有到exchange,则 ack=false
    //2.如果消息到达exchange,则 ack=true
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("correlationData:"+correlationData);
		System.out.println("id:"+correlationData.getId());
		if(ack) {
			System.out.println("消息投递到达exchange!");
		}else {
			System.out.println("消息投递失败,开始补偿机制");
		}
		
		
	}

	
	
}
