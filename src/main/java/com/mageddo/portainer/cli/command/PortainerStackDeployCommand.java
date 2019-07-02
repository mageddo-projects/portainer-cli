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
import com.mageddo.portainer.cli.vo.DockerStackDeploy;
import com.mageddo.portainer.cli.vo.StackEnv;

import javax.ws.rs.client.Client;
import java.nio.file.Paths;
import java.util.List;

@Parameters(commandDescription = "Deploy the stack to docker cluster")
public class PortainerStackDeployCommand implements Command {

	@Parameter(names = {"-s", "--stack-name"}, required = true)
	private String stackName;

	@Parameter(names = {"--prune"}, description = "If must for inexistent services pruning on the stack")
	private boolean prune;

	@Parameter(names = {"-e", "--env"}, converter = EnvConverter.class)
	private List<StackEnv> envs;

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

		if(stack.endsWith(".yml") || stack.endsWith(".yaml")){
			portainerStackService.createOrUpdateStack(
				this.stackName, Paths.get(this.stack), this.prune, this.envs
			);
		} else {
			portainerStackService.createOrUpdateStack(
				new DockerStackDeploy()
					.setName(this.stackName)
					.setStackFileContent(this.stack)
					.setPrune(this.prune)
					.setEnvs(this.envs)
			);
		}
	}

	private Client createClient() {
		return RestEasy.newClient(1, EnvUtils.insecureConnection());
	}

	@Override
	public String toString() {
		return "PortainerStackDeployCommand{" +
			", stack='" + stack + '\'' +
			'}';
	}
}
