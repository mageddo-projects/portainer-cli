package com.mageddo.portainer.cli.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.mageddo.common.resteasy.RestEasy;
import com.mageddo.portainer.cli.apiclient.PortainerAuthApiClient;
import com.mageddo.portainer.cli.apiclient.PortainerAuthenticationFilter;
import com.mageddo.portainer.cli.apiclient.PortainerStackApiClient;
import com.mageddo.portainer.cli.service.PortainerStackService;
import com.mageddo.portainer.cli.utils.EnvUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.client.Client;
import java.nio.file.Paths;

@Parameters(commandDescription = "Deploy the stack to docker cluster")
public class PortainerStackDeployCommand implements Command {

	@Parameter(names = {"-k", "--insecure"}, description = "Allow connections to SSL sites without certs ")
	private boolean insecure;
	
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

	@Override
	public String name() {
		return "deploy";
	}

	@Override
	public void run() {

		final PortainerStackService portainerStackService = new PortainerStackService(
			new PortainerStackApiClient(
				createClient()
					.register(new PortainerAuthenticationFilter(new PortainerAuthApiClient(
						createClient().target(EnvUtils.getPortainerApiUri())
					)))
					.target(EnvUtils.getPortainerApiUri())
			)
		);

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

	private Client createClient() {
		return RestEasy.newClient(1, this.insecure);
	}

	@Override
	public String toString() {
		return "PortainerStackDeployCommand{" +
			"authToken='" + authToken + '\'' +
			", stack='" + stack + '\'' +
			'}';
	}
}
