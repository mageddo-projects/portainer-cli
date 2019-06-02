package com.mageddo.portainer.cli.service;

import com.mageddo.portainer.cli.apiclient.PortainerStackApiClient;
import com.mageddo.portainer.cli.vo.DockerStack;

import java.util.Objects;

public class PortainerStackService {

	private final PortainerStackApiClient portainerStackApiClient;

	public PortainerStackService(PortainerStackApiClient portainerStackApiClient) {
		this.portainerStackApiClient = portainerStackApiClient;
	}

	public DockerStack findDockerStack(String name){
		return portainerStackApiClient.findStacks()
		.stream()
		.filter(it -> Objects.equals(it.getName(), name))
		.findFirst()
		.map(
			it -> new DockerStack()
			.setId(it.getId())
		)
		.orElse(null)
		;
	}
}
