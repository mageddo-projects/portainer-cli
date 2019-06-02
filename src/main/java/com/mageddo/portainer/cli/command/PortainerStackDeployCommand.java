package com.mageddo.portainer.cli.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.mageddo.common.resteasy.RestEasy;

@Parameters(commandDescription = "Deploy the stack to docker cluster")
public class PortainerStackDeployCommand implements Command {

	@Parameter(names = "--auth-token", required = true)
	private String authToken;

	@Parameter(names = {"--stack-name", "-s"})
	private String stackName;

	@Parameter
	private String stackFile = "docker-compose.yml";

	@Override
	public String name() {
		return "deploy";
	}

	@Override
	public void run() {
		System.out.println("stack deploy: " + this);
//		RestEasy.newClient()
	}

	@Override
	public String toString() {
		return "PortainerStackDeployCommand{" +
			"authToken='" + authToken + '\'' +
			", stackFile='" + stackFile + '\'' +
			'}';
	}
}
