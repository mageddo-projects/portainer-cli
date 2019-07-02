package com.mageddo.portainer.cli.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

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

	public static PortainerProp loadConfigProps(){
		return Optional.ofNullable(
			Optional
			.ofNullable(loadConfigPropsFromPath())
			.orElse(loadConfigPropsFromResources())
		)
		.orElse(new PortainerProp(new Properties()))
		;
	}

	public static Path getConfigFilePath() {
		return getConfigDir().resolve( "portainer-cli.properties");
	}

	private static PortainerProp loadConfigPropsFromPath() {
		final Path configPath = getConfigFilePath();
		if(!Files.exists(configPath)){
			return null;
		}
		try (InputStream in = Files.newInputStream(configPath)){
			return new PortainerProp(in);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private static Path getConfigDir() {
		return Optional
			.ofNullable(Paths.get(System.getProperty("PTN_CONFIG_DIR")))
			.orElse(Paths.get(System.getProperty("user.home"), "/.portainer-cli/"))
		;
	}


	static PortainerProp loadConfigPropsFromResources() {
		return Optional
			.ofNullable(getConfigStreamFromResources())
			.map(PortainerProp::new)
			.orElse(null)
		;
	}

	private static InputStream getConfigStreamFromResources() {
		return EnvUtils.class.getResourceAsStream("/portainer-cli.properties");
	}

	public static Properties createProperties(InputStream it) {
		try {
			Properties p = new Properties();
			p.load(it);
			return p;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
