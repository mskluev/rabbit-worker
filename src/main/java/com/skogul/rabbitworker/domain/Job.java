package com.skogul.rabbitworker.domain;

import java.io.Serializable;

public class Job implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer length;

	public Job() {
	}

	public Job(String name, Integer length) {
		this.name = name;
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

}
