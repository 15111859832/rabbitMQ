package com.wd.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wd.ao.Order;
import com.wd.mq.Producer;

@RestController
public class ApplicationTests {
	@Autowired
	Producer producer;
	/**
	 * 主题型exchange
	 * @return
	 */
	@RequestMapping("/topic")
	public String topic() {
		Order s=new Order();
		String id=System.currentTimeMillis()+":"+UUID.randomUUID().toString();
    	s.setId(id);
    	s.setName("topic类型订单号");
		producer.produceTopic(s);
		return "hello RabbitMQ!";
	}
	
	/**
	 * 广播型exchange
	 * @return
	 */
	@RequestMapping("/fanout")
	public String fanout() {
		Order s=new Order();
		String id=System.currentTimeMillis()+":"+UUID.randomUUID().toString();
    	s.setId(id);
    	s.setName("fanout类型订单号");
		producer.produceFanout(s);
		return "hello RabbitMQ!";
	}
}
