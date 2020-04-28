package com.wd.mq;

import java.io.IOException;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.wd.ao.Order;


/**
 * 消费者
 * @author Administrator
 *
 */
@Component
public class Consumer {
	
    @RabbitListener(bindings = @QueueBinding(
    		exchange = @Exchange(value="wd-change",durable="true", type=ExchangeTypes.TOPIC),
    		value = @Queue(value="wd-queue",durable="true"),
    		key = "wd.*"  ))
    @RabbitHandler
    public void process(@Payload Order order ) throws IOException {
        System.out.println("消费者消费消息1=====" + order.getName());
        System.out.println("订单ID:"+order.getId());
        /*//ACK 如果消费者配置文件的签收为手动签收,则需要此操作
        channel.basicAck(111, false);*/
    }
    
    
    @RabbitListener(bindings = @QueueBinding(
    		exchange = @Exchange(value="lc-change",durable="true", type=ExchangeTypes.TOPIC),
    		value = @Queue(value="lc-queue",durable="true"),
    		key = "lc.#"  ))
    @RabbitHandler
    public void process2(@Payload Order order ) throws IOException {
        System.out.println("消费者消费消息3=====" + order.getName());
        System.out.println("订单ID:"+order.getId());
        
    }
    
    
    
    @RabbitHandler
    @RabbitListener(queues= "wd-queue1")
    public void processFanout(String msg ) throws IOException {
        System.out.println("消费者wd-queue1=====" + msg);
        
    }
    
    @RabbitHandler
    @RabbitListener(queues= "wd-queue2")
    public void processFanout1(String msg ) throws IOException {
        System.out.println("消费者wd-queue2=====" + msg);
        
    }
    
}