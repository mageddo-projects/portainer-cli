package com.mageddo.portainer.cli.apiclient;

import com.mageddo.portainer.cli.utils.EnvUtils;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class PortainerAuthenticationFilter implements ClientRequestFilter {
	@Override
	public void filter(ClientRequestContext requestContext) {
		requestContext
			.getHeaders()
			.add("Authorization", "Bearer " + EnvUtils.getAuthToken())
		;
	}
}
