package com.mageddo.portainer.cli.config;

import com.beust.jcommander.converters.CommaParameterSplitter;
import com.beust.jcommander.validators.NoValidator;
import com.beust.jcommander.validators.NoValueValidator;
import com.beust.jcommander.validators.PositiveInteger;
import com.fasterxml.jackson.databind.ext.Java7SupportImpl;
import com.mageddo.common.graalvm.SubstrateVM;
import com.mageddo.portainer.cli.command.PortainerStackDeployCommand;
import com.mageddo.portainer.cli.command.PortainerStackRunCommand;
import com.mageddo.portainer.cli.command.converter.EnvConverter;
import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.hosted.Feature;

import java.util.ArrayList;

@AutomaticFeature
class ReflectionClasses implements Feature {

	@Override
	public void duringSetup(DuringSetupAccess access) {
		System.loadLibrary("sunec");
	}

	@Override
	public void beforeAnalysis(BeforeAnalysisAccess access) {
		SubstrateVM.supressWarnings();
		SubstrateVM.process(ArrayList.class, Java7SupportImpl.class);

		SubstrateVM
			.builder()
			.constructors()
			.methods()
			.fields()
			.clazz(PortainerStackDeployCommand.class)
			.clazz(PortainerStackRunCommand.class)
			.build()
		;

		SubstrateVM
			.builder()
			.clazz(NoValueValidator.class)
			.clazz(NoValidator.class)
			.clazz(PositiveInteger.class)
			.clazz(CommaParameterSplitter.class)
			.clazz(EnvConverter.class)
			.constructors()
			.build()
		;
	}
}

