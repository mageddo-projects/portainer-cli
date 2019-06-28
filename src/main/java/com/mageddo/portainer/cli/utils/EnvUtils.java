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

	public static void setUsername(String username) {
		System.setProperty("PTN_USERNAME", username);
	}

	public static String getUsername() {
		return Optional
			.ofNullable(System.getProperty("PTN_USERNAME"))
			.orElse(System.getenv("PTN_USERNAME"))
			;
	}

	public static void setPassword(String password) {
		System.setProperty("PTN_PASSWORD", password);
	}

	public static String getPassword() {
		return Optional
			.ofNullable(System.getProperty("PTN_PASSWORD"))
			.orElse(System.getenv("PTN_PASSWORD"))
			;
	}
}
