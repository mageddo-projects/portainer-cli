package com.mageddo.portainer.cli;

import com.mageddo.portainercli.config.ApplicationContextUtils;
import io.micronaut.runtime.Micronaut;

public class Application {

	public static void main(String[] args) {
		ApplicationContextUtils.context(Micronaut.run(Application.class));
	}
}
