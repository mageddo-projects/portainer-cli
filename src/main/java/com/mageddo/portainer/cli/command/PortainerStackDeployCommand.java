package com.mageddo.portainer.cli.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.mageddo.portainer.cli.service.PortainerStackService;
import com.mageddo.portainer.cli.utils.EnvUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Paths;

@Parameters(commandDescription = "Deploy the stack to docker cluster")
public class PortainerStackDeployCommand implements Command {

	private final PortainerStackService portainerStackService;

	@Parameter(names = "--auth-token")
	private String authToken;

	@Parameter(names = {"-u", "--username"})
	private String username;

	@Parameter(names = {"-p", "--password"})
	private String password;

	@Parameter(names = {"-s", "--stack-name"}, required = true)
	private String stackName;

	@Parameter(description = "stack file or stack file content")
	private String stack = "docker-compose.yml";

	public PortainerStackDeployCommand(PortainerStackService portainerStackService) {
		this.portainerStackService = portainerStackService;
	}

	@Override
	public String name() {
		return "deploy";
	}

	@Override
	public void run() {

		if(StringUtils.isNotBlank(this.authToken)){
			EnvUtils.setAuthToken(this.authToken);
		}

		if(StringUtils.isNoneBlank(username, password)){
			EnvUtils.setUsername(this.username);
			EnvUtils.setPassword(this.password);
		}

		if(stack.endsWith(".yml") || stack.endsWith(".yaml")){
			portainerStackService.createOrUpdateStack(stackName, Paths.get(stack));
		} else {
			portainerStackService.createOrUpdateStack(stackName, stack);
		}
	}

	@Override
	public String toString() {
		return "PortainerStackDeployCommand{" +
			"authToken='" + authToken + '\'' +
			", stack='" + stack + '\'' +
			'}';
	}
}
