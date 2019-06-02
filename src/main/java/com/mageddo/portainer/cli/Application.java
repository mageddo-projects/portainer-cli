package com.mageddo.portainer.cli;

import com.mageddo.portainer.cli.command.PortainerCommand;

public class Application {

	public static void main(String[] args) {
		PortainerCommand.parseAndRun(args);
	}
}
