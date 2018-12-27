package com.skogul.rabbitworker.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.skogul.rabbitworker.config.RabbitConfiguration;
import com.skogul.rabbitworker.domain.Job;

@Service
public class RabbitListenerService {

	private final Logger log = LoggerFactory.getLogger(RabbitListenerService.class);
	private final String hostname;
	private final SampleWorkerService worker;
	
	public RabbitListenerService(SampleWorkerService worker) {
		this.hostname = getHostName();
		this.worker = worker;
	}

	@RabbitListener(containerFactory = "fetchOneContainerFactory", 
		bindings = @QueueBinding(
			value = @Queue(value = RabbitConfiguration.QUEUE_NAME), 
			exchange = @Exchange(
					value = RabbitConfiguration.EXCHANGE_NAME, 
					type = ExchangeTypes.DIRECT), 
			key = RabbitConfiguration.ROUTING_KEY))
	public void receiveJob(Job job) throws InterruptedException {
		log.info(hostname + " received job: " + job.getName());
		worker.doWork(job, hostname);
	}

	private String getHostName() {
		// Docker sets this environment variable
		String hostname = System.getenv("HOSTNAME");
		// Otherwise fallback on default method
		if (hostname == null) {
			try {
				hostname = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				hostname = "UNKNOWN";
			}
		}
		return hostname;
	}
}
