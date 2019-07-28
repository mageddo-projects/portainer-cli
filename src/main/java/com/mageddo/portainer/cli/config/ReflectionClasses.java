package com.mageddo.portainer.cli.config;

import com.fasterxml.jackson.databind.ext.Java7SupportImpl;
import com.mageddo.common.graalvm.SubstrateVM;
import com.mageddo.portainer.cli.command.PortainerStackDeployCommand;
import com.mageddo.portainer.cli.command.PortainerStackRunCommand;
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
		SubstrateVM.process(ArrayList.class, Java7SupportImpl.class);
		SubstrateVM.process(
			true, true, true,
			PortainerStackDeployCommand.class, PortainerStackRunCommand.class
		);
	}
}

