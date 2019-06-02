package com.mageddo.portainer.cli.apiclient.vo;

import com.fasterxml.jackson.annotation.JsonGetter;

public class StackCreateReqV1 {

	private String name;
	private String stackFileContent;

	@JsonGetter("Name")
	public String getName() {
		return name;
	}

	public StackCreateReqV1 setName(String name) {
		this.name = name;
		return this;
	}

	@JsonGetter("SwarmID")
	public String getSwarmId() {
		return "null";
	}

	@JsonGetter("StackFileContent")
	public String getStackFileContent() {
		return stackFileContent;
	}

	public StackCreateReqV1 setStackFileContent(String stackFileContent) {
		this.stackFileContent = stackFileContent;
		return this;
	}
}
