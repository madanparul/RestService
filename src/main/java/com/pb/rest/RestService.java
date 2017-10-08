package com.pb.rest;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pb.analytics.MainApp;
import com.pb.analytics.dto.RecommendationsDto;
import com.pb.dps.dto.ServiceInputDto;
import com.pb.dps.dto.ServiceResponseDto;
import com.pb.dps.service.DpsService;
 
@Path("/hello")
public class RestService {

	@POST
	@Path("/validateEntity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public ServiceResponseDto getScore(@QueryParam("name") String name,
	        @QueryParam("address") String address) {
		
		DpsService ds=new DpsService();
		ServiceInputDto sid =new ServiceInputDto();
		sid.setName(name);
		sid.setAddress(address);
		System.out.println("");
		
		ServiceResponseDto srd=null;
		try {
			srd=ds.service(sid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return srd;

	}
	
	@POST
	@Path("/getRecommendation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RecommendationsDto getRecommendation(@QueryParam("name") String name,
	        @QueryParam("type") String type) {
		MainApp ma=new MainApp();
		RecommendationsDto  rd=null;
		try {
			rd=ma.generateRecommendation(name, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}	
}