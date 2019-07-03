package com.mageddo.portainer.cli.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.mageddo.portainer.cli.command.converter.EnvConverter;
import com.mageddo.portainer.cli.utils.BeansFactory;
import com.mageddo.portainer.cli.vo.StackEnv;

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
		BeansFactory.newStackService().runStack(stackName, prune, envs);
	}

	@Override
	public String toString() {
		return "PortainerStackRunCommand{" +
			", stack='" + stackName + '\'' +
			'}';
	}
}
