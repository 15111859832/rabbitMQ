package com.wd.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.stereotype.Component;
@Component
public class MsgSendReturnCallback implements ReturnCallback {
	//当消息从交换机到队列失败时，该方法被调用。（若成功，则不调用）
    // 需要注意的是：该方法调用后，MsgSendConfirmCallBack中的confirm方法也会被调用，且ack = true
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("消息从交换机到队列失败!");
		System.out.println("message:"+message);
		System.out.println("message:"+replyCode);
		System.out.println("描述:"+replyText);
		System.out.println("交换机:"+exchange);
		System.out.println("routingKey:"+routingKey);
		
	}

}
