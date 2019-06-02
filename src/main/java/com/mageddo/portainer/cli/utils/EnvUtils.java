package com.mageddo.portainer.cli.utils;

import java.util.Optional;

public class EnvUtils {
	public static String getPortainerApiUri(){
		return Optional
			.ofNullable(System.getenv("PTN_URI"))
			.orElse("http://localhost:9000");
	}
}
