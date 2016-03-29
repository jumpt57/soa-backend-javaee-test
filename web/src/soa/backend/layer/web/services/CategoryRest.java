package soa.backend.layer.web.services;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import soa.backend.layer.entreprise.ejb.SOAEjb;
import soa.backend.layer.entreprise.jpa.Category;

@Path("/categ")
public class CategoryRest {
	
	@Inject
	private SOAEjb soa;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Category categ(@QueryParam("name") String name){		
		return soa.createCateg(name);		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> categ(){		
		return soa.getCategs();	
	}
}
