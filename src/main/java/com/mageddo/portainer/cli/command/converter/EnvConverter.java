package com.mageddo.portainer.cli.command.converter;

import com.beust.jcommander.IStringConverter;
import com.mageddo.portainer.cli.vo.StackEnv;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class EnvConverter implements IStringConverter<StackEnv> {
	@Override
	public StackEnv convert(String value) {
		Validate.isTrue(StringUtils.isNotBlank(value), "Invalid env");
		String[] values = value.split("=");
		if(values.length == 1){
			return new StackEnv(values[0]);
		}
		return new StackEnv(values[0], values[1]);
	}
}
