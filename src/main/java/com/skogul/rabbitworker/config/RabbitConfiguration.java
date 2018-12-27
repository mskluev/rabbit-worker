package com.skogul.rabbitworker.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

	public static final String EXCHANGE_NAME = "myExchange";
	public static final String ROUTING_KEY = "myKey";
	public static final String QUEUE_NAME = "myQueue";

	@Bean
	public DirectExchange myExchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	/**
	 * Configure our connection factory to RabbitMQ. Here we set the prefetch to 1
	 * so each worker only takes 1 job and completes it before taking another.
	 * 
	 * @param rabbitConnectionFactory
	 * @return
	 */
	@Bean
	public RabbitListenerContainerFactory<SimpleMessageListenerContainer> fetchOneContainerFactory(
			ConnectionFactory rabbitConnectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(rabbitConnectionFactory);
		factory.setPrefetchCount(1);
		return factory;
	}

}
