package com.mageddo.portainer.cli.vo;

public class DockerStackDeploy {

	private String name;
	private String stackFileContent;
	private boolean prune;

	public String getName() {
		return name;
	}

	public DockerStackDeploy setName(String name) {
		this.name = name;
		return this;
	}

	public String getStackFileContent() {
		return stackFileContent;
	}

	public DockerStackDeploy setStackFileContent(String stackFileContent) {
		this.stackFileContent = stackFileContent;
		return this;
	}

	public boolean isPrune() {
		return prune;
	}

	public DockerStackDeploy setPrune(boolean prune) {
		this.prune = prune;
		return this;
	}
}
