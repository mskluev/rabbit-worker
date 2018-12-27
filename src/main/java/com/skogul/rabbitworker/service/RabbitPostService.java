package com.skogul.rabbitworker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.skogul.rabbitworker.config.RabbitConfiguration;
import com.skogul.rabbitworker.domain.Job;

@Service
public class RabbitPostService {

	private final Logger log = LoggerFactory.getLogger(RabbitPostService.class);
	private final RabbitTemplate template;

	public RabbitPostService(RabbitTemplate template) {
		this.template = template;
	}

	public void postMessage(Job job) {
		log.debug("Request to post the following Job to RabbitMQ: {}", job.getName());
		template.convertAndSend(RabbitConfiguration.EXCHANGE_NAME, RabbitConfiguration.ROUTING_KEY, job);
	}
}
