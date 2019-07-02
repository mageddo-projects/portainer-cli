package com.mageddo.portainer.cli.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.mageddo.common.resteasy.RestEasy;
import com.mageddo.portainer.cli.apiclient.PortainerAuthApiClient;
import com.mageddo.portainer.cli.apiclient.PortainerAuthenticationFilter;
import com.mageddo.portainer.cli.apiclient.PortainerStackApiClient;
import com.mageddo.portainer.cli.command.converter.EnvConverter;
import com.mageddo.portainer.cli.service.PortainerStackService;
import com.mageddo.portainer.cli.utils.EnvUtils;
import com.mageddo.portainer.cli.vo.StackEnv;

import javax.ws.rs.client.Client;
import java.util.List;

@Parameters(commandDescription = "Run a existing stack updating the specified environments")
public class PortainerStackRunCommand implements Command {

	@Parameter(names = {"--prune"}, description = "If must for inexistent services pruning on the stack")
	private boolean prune;

	@Parameter(names = {"-e", "--env"}, converter = EnvConverter.class)
	private List<StackEnv> envs;

	@Parameter(required = true)
	private String stackName;

	@Override
	public String name() {
		return "deploy";
	}

	@Override
	public void run() {
		final PortainerStackService portainerStackService = newInstance();
		portainerStackService.runStack(stackName, prune, envs);
	}

	private PortainerStackService newInstance() {
		return new PortainerStackService(
			new PortainerStackApiClient(
				createClient()
					.register(new PortainerAuthenticationFilter(new PortainerAuthApiClient(
						createClient().target(EnvUtils.getPortainerApiUri())
					)))
					.target(EnvUtils.getPortainerApiUri())
			)
		);
	}

	private Client createClient() {
		return RestEasy.newClient(1, EnvUtils.insecureConnection());
	}

	@Override
	public String toString() {
		return "PortainerStackRunCommand{" +
			", stack='" + stackName + '\'' +
			'}';
	}
}
