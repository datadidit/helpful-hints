package datadidit.helpful.hints.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import datadidit.helpful.hints.App;

@Path("redhawk")
public class SampleResource {
	@GET
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Path("domain")
	public Response sayHello() throws NotFound, CannotProceed, InvalidName, org.omg.CORBA.ORBPackage.InvalidName{
		App app = new App(); 
		app.init();
		return Response.ok(app.getDomainName()).build();
	}
}
