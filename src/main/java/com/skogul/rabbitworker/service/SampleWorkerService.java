package com.skogul.rabbitworker.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.skogul.rabbitworker.domain.Job;

@Service
public class SampleWorkerService {

	private final Logger log = LoggerFactory.getLogger(SampleWorkerService.class);

	/**
	 * Simulates a long running task.
	 * 
	 * @param jobName
	 * @param hostName
	 * @throws InterruptedException
	 */
	public void doWork(Job job, String hostName) throws InterruptedException {
		log.info("Starting new job {} on host {}", job.getName(), hostName);
		for (int i = 0; i < job.getLength(); ++i) {
			log.debug("\t{}: Step {} of {} for {}", hostName, i, job.getLength(), job.getName());
			TimeUnit.SECONDS.sleep(1);
		}
		log.info("Completed job {} on host {}", job.getName(), hostName);
	}
}
