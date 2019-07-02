package com.mageddo.portainer.cli.command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;

@Parameters(commandNames = "stack-deploy")
public class PortainerCommand {

	public static JCommander buildCommandLine(){
		final JCommander commander = new JCommander();
		commander.addCommand(new PortainerCommand());
		commander.addCommand("stack-deploy", new PortainerStackDeployCommand());
		commander.addCommand("stack-run", new PortainerStackRunCommand());
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
