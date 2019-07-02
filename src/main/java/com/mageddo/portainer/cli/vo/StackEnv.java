package com.mageddo.portainer.cli.vo;

public class StackEnv {

	private final String name;
	private final String value;

	public StackEnv(String name) {
		this.name = name;
		this.value = null;
	}

	public StackEnv(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
