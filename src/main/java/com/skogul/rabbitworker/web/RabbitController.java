package com.skogul.rabbitworker.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skogul.rabbitworker.domain.Job;
import com.skogul.rabbitworker.service.RabbitPostService;

@RestController
@RequestMapping("/api")
public class RabbitController {

	private final Logger log = LoggerFactory.getLogger(RabbitController.class);
	private final RabbitPostService postService;

	public RabbitController(RabbitPostService postService) {
		this.postService = postService;
	}

	@RequestMapping(value = "/rabbit", method = RequestMethod.POST)
	public ResponseEntity<String> doPost(@RequestBody Job job) {
		log.debug("POST Request with job: {}", job.getName());
		postService.postMessage(job);
		return ResponseEntity.ok(job.getName() + " received!");
	}
}
