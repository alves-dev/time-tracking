package br.com.igorma.controller;

import br.com.igorma.model.TimeTracking;
import br.com.igorma.service.Service;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/hello")
public class GreetingResource {

    @Inject
    Service service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        service.save();
        return Response.status(200).entity(service.getAll()).build();
        //return service.getAll();
    }
}


