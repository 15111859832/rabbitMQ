package com.wd.comfig;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 *处理序列化问题(message实体是个地址)
 */
@Configuration
public class MyMqpMessageConverter {
    @Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	


}
