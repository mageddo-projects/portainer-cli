package com.mageddo.portainer.cli.service;

import com.mageddo.portainer.cli.apiclient.PortainerStackApiClient;
import com.mageddo.portainer.cli.vo.DockerStack;
import com.mageddo.utils.InMemoryRestServer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static com.mageddo.utils.TestUtils.readAsString;
import static org.junit.Assert.assertEquals;

public class PortainerStackServiceTest {

	@ClassRule
	public static final InMemoryRestServer server = new InMemoryRestServer(Proxy.class);

	private PortainerStackService portainerStackService;

	@Before
	public void before(){
		portainerStackService = new PortainerStackService(new PortainerStackApiClient(server.target()));
	}

	@Test
	public void mustFindPortainerStack(){
		// act
		DockerStack dockerStack = portainerStackService.findDockerStack("web3");

		// assert
		assertEquals(Long.valueOf(3), dockerStack.getId());
	}

	@Path("/")
	public static class Proxy {

		@Path("/api/stacks")
		@GET
		public Response stacks() throws Exception {
			return Response.ok(readAsString("/mocks/portainer-stack-service-test/001.json")).build();
		}
	}

}
