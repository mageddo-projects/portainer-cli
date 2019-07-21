package com.mageddo.portainer.cli;

import com.beust.jcommander.ParameterException;
import com.mageddo.portainer.cli.command.PortainerCommand;
import com.mageddo.portainer.client.utils.EnvUtils;

public class Application {

	public static void main(String[] args) {
		try {
			EnvUtils.setupEnv();
			PortainerCommand.parseAndRun(args);
		} catch (ParameterException e){
			System.err.println(e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}
