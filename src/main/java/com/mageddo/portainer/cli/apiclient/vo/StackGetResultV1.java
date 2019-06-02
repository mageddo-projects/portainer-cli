package com.mageddo.portainer.cli.apiclient.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StackGetResultV1 {

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	@JsonSetter("Id")
	public StackGetResultV1 setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	@JsonSetter("Name")
	public StackGetResultV1 setName(String name) {
		this.name = name;
		return this;
	}
}
