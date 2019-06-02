package com.mageddo.portainer.cli.apiclient;

import com.mageddo.portainer.cli.apiclient.vo.StackGetResultV1;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class PortainerStackApiClient {

	private final WebTarget webTarget;

	public PortainerStackApiClient(WebTarget webTarget) {
		this.webTarget = webTarget;
	}

	public List<StackGetResultV1> findStacks(){
		return webTarget
			.path("/api/stacks")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.get(new GenericType<List<StackGetResultV1>>(){})
		;
	}
}
