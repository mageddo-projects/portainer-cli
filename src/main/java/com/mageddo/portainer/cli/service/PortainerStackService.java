package com.mageddo.portainer.cli.service;

import com.mageddo.portainer.cli.apiclient.PortainerStackApiClient;
import com.mageddo.portainer.cli.apiclient.vo.StackCreateReqV1;
import com.mageddo.portainer.cli.apiclient.vo.StackUpdateReqV1;
import com.mageddo.portainer.cli.vo.DockerStack;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class PortainerStackService {

	private final PortainerStackApiClient portainerStackApiClient;
	private final Logger logger = LoggerFactory.getLogger(getClass());

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

	public void createOrUpdateStack(String name, Path stackFile){
		try {
			createOrUpdateStack(name, IOUtils.toString(Files.newInputStream(stackFile), StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public void createOrUpdateStack(String name, String stackFileContent){
		final DockerStack dockerStack = findDockerStack(name);
		if(dockerStack == null){
			logger.debug("status=creating-stack");
			portainerStackApiClient.createStack(
				new StackCreateReqV1()
				.setName(name)
				.setStackFileContent(stackFileContent)
			);
		} else {
			logger.debug("status=updating-stack");
			portainerStackApiClient.updateStack(
				new StackUpdateReqV1()
				.setStackFileContent(stackFileContent)
				.setId(dockerStack.getId())
			);
		}
	}
}
