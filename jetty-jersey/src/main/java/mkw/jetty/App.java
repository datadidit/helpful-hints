package mkw.jetty;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class App 
{
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHello(@DefaultValue("world") @QueryParam("name") String name){
		return Response.ok("Hello "+name).build();
	}
}
