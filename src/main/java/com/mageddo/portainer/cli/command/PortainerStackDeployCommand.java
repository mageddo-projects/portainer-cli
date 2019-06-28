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

	@Parameter(names = "--username")
	private String username;

	@Parameter(names = "--password")
	private String password;

	@Parameter(names = {"--stack-name", "-s"}, required = true)
	private String stackName;

	@Parameter
	private String stackFile = "docker-compose.yml";

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
		EnvUtils.setUsername(this.username);
		EnvUtils.setPassword(this.password);

		portainerStackService.createOrUpdateStack(stackName, Paths.get(stackFile));
	}

	@Override
	public String toString() {
		return "PortainerStackDeployCommand{" +
			"authToken='" + authToken + '\'' +
			", stackFile='" + stackFile + '\'' +
			'}';
	}
}
