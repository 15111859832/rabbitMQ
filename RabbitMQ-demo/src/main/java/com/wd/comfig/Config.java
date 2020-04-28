package com.wd.comfig;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Queue miaoShaQueue1(){
        return new Queue("wd-queue1");
    }
    
    @Bean
    public Queue miaoShaQueue2(){
        return new Queue("wd-queue2");
    }
    
    //广播模式
    @Bean
    public FanoutExchange exChange(){
        return new FanoutExchange("wd-exchange");
    }
    
    /**
     * 绑定交换机与队列(广播模式不需要routingKey)
    
     */
    @Bean
    public Binding queueBindFanout1() {
        return BindingBuilder.bind(miaoShaQueue1()).to(exChange());
    }
    @Bean
    public Binding queueBindFanout2() {
        return BindingBuilder.bind(miaoShaQueue2()).to(exChange());
    }
}
