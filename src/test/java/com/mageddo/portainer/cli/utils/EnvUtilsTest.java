package com.mageddo.portainer.cli.utils;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EnvUtilsTest {

	@ClassRule
	public static TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void mustLoadPropsFromResources(){
		
		// act
		PortainerProp props = EnvUtils.loadConfigProps();

		// assert
		assertNotNull(props);
		assertEquals("admin", props.asText("stack.deploy.auth.username", " "));
	}

	@Test
	public void mustLoadPropsFromConfigPath() throws Exception {

		// arrange
		System.setProperty("PTN_CONFIG_DIR", temporaryFolder.getRoot().toPath().toString());
		final Path configPath = EnvUtils.getConfigFilePath();
		EnvUtils.loadConfigPropsFromResources()
			.put("stack.deploy.auth.username", "elvis")
			.store(configPath)
		;

		// act
		final PortainerProp props = EnvUtils.loadConfigProps();

		// assert
		assertNotNull(props);
		assertEquals("elvis", props.asText("stack.deploy.auth.username", " "));
		System.out.println(configPath);
	}

}
