package soa.backend.layer.web.services;


import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/message")
public class MessageRestService {

	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg){
		System.out.println("test " + msg);
		
		String result = "Restful example : " + msg;
		
		return Response.status(200).entity(result).build();
		
	}
}
