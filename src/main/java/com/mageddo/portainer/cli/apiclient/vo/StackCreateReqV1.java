package com.mageddo.portainer.cli.apiclient.vo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.mageddo.portainer.cli.vo.DockerStackDeploy;

public class StackCreateReqV1 {

	private String name;
	private String stackFileContent;

	public static StackCreateReqV1 valueOf(DockerStackDeploy dockerStackDeploy) {
		return new StackCreateReqV1()
			.setName(dockerStackDeploy.getName())
			.setStackFileContent(dockerStackDeploy.getStackFileContent())
		;
	}

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
