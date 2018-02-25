package de.niklasmehner;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;


@Stateless
@Path("hello")
public class HelloResource {
	@javax.ws.rs.core.Context
	private HttpHeaders headers;

	@GET
	public String hello(@QueryParam("name") @NotNull String name) {
		return "Hello " + name + " -> " + headers.getAcceptableLanguages();
	}
	
}
