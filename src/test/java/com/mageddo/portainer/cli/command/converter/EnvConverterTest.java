package com.mageddo.portainer.cli.command.converter;

import com.mageddo.portainer.client.vo.StackEnv;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnvConverterTest {

	private final EnvConverter converter = new EnvConverter();

	@Test
	public void shouldSplitVariable() {

		// arrange

		// act
		final StackEnv env = converter.convert("KEY=VALUE");

		// assert
		assertEquals("KEY", env.getName());
		assertEquals("VALUE", env.getValue());

	}
	@Test
	public void shouldSplitVariableAndKeepDockerConstraint() {

		// arrange

		// act
		final StackEnv env = converter.convert("KEY=node.hostname == manager-01");

		// assert
		assertEquals("KEY", env.getName());
		assertEquals("node.hostname == manager-01", env.getValue());

	}
}
