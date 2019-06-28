package com.mageddo.portainer.cli.apiclient.vo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mageddo.portainer.cli.vo.DockerStackDeploy;

public class StackUpdateReqV1 {

	private Long id;
	private String stackFileContent;
	private boolean prune;

	public static StackUpdateReqV1 valueOf(DockerStackDeploy dockerStackDeploy, Long stackId) {
		return new StackUpdateReqV1()
			.setStackFileContent(dockerStackDeploy.getStackFileContent())
			.setId(stackId)
		;
	}

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

	@JsonGetter("Prune")
	public boolean isPrune() {
		return prune;
	}

	public StackUpdateReqV1 setPrune(boolean prune) {
		this.prune = prune;
		return this;
	}
}
