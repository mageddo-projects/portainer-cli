package com.mageddo.portainer.cli.apiclient;

import com.mageddo.portainer.cli.apiclient.vo.StackCreateReqV1;
import com.mageddo.portainer.cli.apiclient.vo.StackGetRestV1;
import com.mageddo.portainer.cli.apiclient.vo.StackUpdateReqV1;
import org.apache.commons.lang3.Validate;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class PortainerStackApiClient {

	private final WebTarget webTarget;

	public PortainerStackApiClient(WebTarget webTarget) {
		this.webTarget = webTarget;
	}

	public List<StackGetRestV1> findStacks(){
		return webTarget
			.path("/api/stacks")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.get(new GenericType<List<StackGetRestV1>>(){})
		;
	}

	public void createStack(StackCreateReqV1 createReqV1){
		Response res = webTarget
			.path("/api/stacks")
			.queryParam("type", 1)
			.queryParam("method", "string")
			.queryParam("endpointId", 1)
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.json(createReqV1));
		Validate.isTrue(
			res.getStatusInfo().toEnum() == Response.Status.OK,
			res.readEntity(String.class)
		);
	}

	public void updateStack(StackUpdateReqV1 updateReqV1){
		webTarget
			.path("/api/stacks/")
			.path(String.valueOf(updateReqV1.getId()))
			.queryParam("endpointId", 1)
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.json(updateReqV1))
		;
	}
}
