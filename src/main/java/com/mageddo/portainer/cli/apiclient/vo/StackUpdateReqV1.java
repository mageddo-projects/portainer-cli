package com.mageddo.portainer.cli.apiclient.vo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class StackUpdateReqV1 {

	private Long id;
	private String stackFileContent;

	@JsonIgnore
	public Long getId() {
		return id;
	}

	public StackUpdateReqV1 setId(Long id) {
		this.id = id;
		return this;
	}

	@JsonGetter("StackFileContent")
	public String getStackFileContent() {
		return stackFileContent;
	}

	public StackUpdateReqV1 setStackFileContent(String stackFileContent) {
		this.stackFileContent = stackFileContent;
		return this;
	}
}
