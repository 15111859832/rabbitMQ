package com.wd.mq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wd.ao.Order;
/**
 * 生产者
 * @author Administrator
 *
 */
@Component
public class Producer {
	 	@Autowired
	    RabbitTemplate rabbitTemplate;
	 	@Autowired
	 	MsgSentConfirmCallback confirmCallback;
	 	@Autowired
	 	MsgSendReturnCallback returnCallback;
	 	
	 	
	 	/**
	 	 * Topic
	 	 * @param order
	 	 */
	    public void produceTopic(Order order) {
	        CorrelationData correlationData=new CorrelationData();
	        correlationData.setId(order.getId());
	        System.out.println("生产者:Topic=====" +order.getId()+"---"+ order.getName());
	        //生产者向交换机  投递
	        rabbitTemplate.setConfirmCallback(confirmCallback);
	        //启动消息失败返回，比如路由不到队列时触发回调
	        rabbitTemplate.setReturnCallback(returnCallback);
	        //参数:交换机名称,路由key,信息,消息唯一id
	        
	        //正确 示范
	        //rabbitTemplate.convertAndSend("wd-change", "wd.123", order,correlationData);
	        //rabbitTemplate.convertAndSend("lc-change", "lc.123.ddd", order,correlationData);
	        
	        //交换机不匹配
	        rabbitTemplate.convertAndSend("hehe-exchange","lc.123", order,correlationData);
	        //路由key不匹配
	        rabbitTemplate.convertAndSend("lc-change","hh.123", order,correlationData);
	    }
	    
	    /**
	     * Fanout  无路由key
	     * @param order
	     */
	    public void produceFanout(Order order) {
	        CorrelationData correlationData=new CorrelationData();
	        correlationData.setId(order.getId());
	        System.out.println("生产者:Fanout=====" +order.getId()+"---"+ order.getName());
	        //生产者向交换机  投递
	        rabbitTemplate.setConfirmCallback(confirmCallback);
	        //启动消息失败返回，比如路由不到队列时触发回调  (因为无路由key,不适用此方法)
	        //rabbitTemplate.setReturnCallback(returnCallback);
	        //参数:交换机名称,路由key,信息,消息唯一id
	        rabbitTemplate.convertAndSend("wd-exchange", null, "广播模式",correlationData);
	    }
}
