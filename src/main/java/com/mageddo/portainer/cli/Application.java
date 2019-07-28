package com.mageddo.portainer.cli;

import com.beust.jcommander.ParameterException;
import com.mageddo.common.net.ssl.TrustManagerUtils;
import com.mageddo.portainer.cli.command.PortainerCommand;

public class Application {

	public static void main(String[] args) {
		try {
			TrustManagerUtils.installTrustStore();
			PortainerCommand.parseAndRun(args);
		} catch (ParameterException e){
			System.err.println(e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}
