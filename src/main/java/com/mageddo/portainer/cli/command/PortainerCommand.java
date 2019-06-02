package com.mageddo.portainer.cli.command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;

@Parameters(commandNames = "stack-deploy")
public class PortainerCommand {

	public static JCommander buildCommandLine(){
		final JCommander commander = JCommander.newBuilder()
			.addCommand(new PortainerCommand())
			.addCommand("stack-deploy", new PortainerStackDeployCommand())
			.build()
		;
		return commander;
	}

	public static void parseAndRun(String... args){
		JCommander jCommander = buildCommandLine();
		jCommander.usage();
		jCommander.parse(args);

		for (final Object object : jCommander.getCommands().get(jCommander.getParsedCommand()).getObjects()) {
			Command.class.cast(object).run();
		}
	}
}
