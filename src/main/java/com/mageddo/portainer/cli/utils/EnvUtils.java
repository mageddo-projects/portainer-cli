package com.mageddo.portainer.cli.utils;

import java.util.Optional;

public class EnvUtils {
	public static String getPortainerApiUri(){
		return Optional
			.ofNullable(System.getenv("PTN_URI"))
			.orElse("http://localhost:9000")
		;
	}

	public static String getAuthToken() {
		return Optional
			.ofNullable(System.getProperty("PTN_AUTH_ENV"))
			.orElse(System.getenv("PTN_AUTH_TOKEN"))
		;
	}

	public static void setAuthToken(String authToken) {
		System.setProperty("PTN_AUTH_ENV", authToken);
	}
}
