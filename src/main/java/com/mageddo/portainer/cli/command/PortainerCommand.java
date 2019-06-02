package com.mageddo.portainer.cli.command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;
import com.mageddo.common.resteasy.RestEasy;
import com.mageddo.portainer.cli.apiclient.PortainerAuthenticationFilter;
import com.mageddo.portainer.cli.apiclient.PortainerStackApiClient;
import com.mageddo.portainer.cli.service.PortainerStackService;
import com.mageddo.portainer.cli.utils.EnvUtils;

@Parameters(commandNames = "stack-deploy")
public class PortainerCommand {

	public static JCommander buildCommandLine(){
		final JCommander commander = JCommander.newBuilder()
			.addCommand(new PortainerCommand())
			.addCommand("stack-deploy", new PortainerStackDeployCommand(
				new PortainerStackService(
					new PortainerStackApiClient(
						RestEasy
							.newClient(1)
							.register(PortainerAuthenticationFilter.class)
							.target(EnvUtils.getPortainerApiUri())
					)
				)
			))
			.build()
		;
		return commander;
	}

	public static void parseAndRun(String... args){
		JCommander jCommander = buildCommandLine();
		jCommander.parse(args);

		if(jCommander.getParsedCommand() == null){
			jCommander.usage();
			return;
		}

		for (final Object object : jCommander.getCommands().get(jCommander.getParsedCommand()).getObjects()) {
			Command.class.cast(object).run();
		}
	}
}
