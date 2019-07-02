package com.mageddo.portainer.cli.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public class PortainerProp {

	private Properties properties;

	public PortainerProp(InputStream in) {
		this.properties = EnvUtils.createProperties(in);
	}

	public PortainerProp(Properties properties) {
		this.properties = properties;
	}

	public String asText(String key, String actual) {
		return asText(key, actual, null);
	}

	public String asText(String key, String actual, String defaultValue){
		if(StringUtils.isNotBlank(actual)){
			return actual;
		}
		if(StringUtils.isBlank(properties.getProperty(key))){
			return defaultValue;
		}
		return properties.getProperty(key);
	}

	public boolean asBoolean(String key, Boolean actual, boolean defaultValue){
		if(actual != null){
			return actual;
		}
		final String propValue = properties.getProperty(key).toLowerCase();
		if(StringUtils.isBlank(propValue)){
			return defaultValue;
		}
		return Objects.equals(propValue, "1") || Objects.equals(propValue, "true");
	}

	public PortainerProp put(String k, String v){
		this.properties.put(k, v);
		return this;
	}

	public PortainerProp store(Path path){
		try {
			Files.createDirectories(path.getParent());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		try(OutputStream out = Files.newOutputStream(path)){
			this.properties.store(out, "");
			return this;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
