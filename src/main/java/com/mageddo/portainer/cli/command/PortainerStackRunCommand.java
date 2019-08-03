package com.mageddo.portainer.cli.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.mageddo.portainer.cli.command.converter.EnvConverter;
import com.mageddo.portainer.client.service.PortainerStackService;
import com.mageddo.portainer.client.utils.BeansFactory;
import com.mageddo.portainer.client.vo.StackEnv;

import java.util.Collections;
import java.util.List;

@Parameters(commandDescription = "Run a existing stack updating the specified environments")
public class PortainerStackRunCommand implements Command {

	@Parameter(names = {"--prune"}, description = "If must for inexistent services pruning on the stack")
	private boolean prune;

	@Parameter(names = {"-e", "--env"}, converter = EnvConverter.class)
	private List<StackEnv> envs;

	@Parameter(
		names = "--clone-services",
		description = "Run stack cloning existent services this way you can run the same service multiple times in parallel, " +
		"useful when you are using the stack as a task runner"
	)
	private boolean cloneServices;

	@Parameter(required = true)
	private String stackName;

	public PortainerStackRunCommand() {
		this.envs = Collections.emptyList();
	}

	@Override
	public String name() {
		return "run";
	}

	@Override
	public void run() {
		final PortainerStackService service = BeansFactory.newStackService();
		if(cloneServices){
			service.runStackClonningServices(stackName, this.prune, envs);
		} else {
			service.runStack(stackName, this.prune, envs);
		}
	}

	@Override
	public String toString() {
		return "PortainerStackRunCommand{" +
			", stack='" + stackName + '\'' +
			'}';
	}
}
