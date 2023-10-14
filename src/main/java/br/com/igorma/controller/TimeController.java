package br.com.igorma.controller;

import br.com.igorma.controller.request.ActivityRequest;
import br.com.igorma.controller.response.ActivityResponse;
import br.com.igorma.service.Service;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;

import java.util.List;
import java.util.UUID;

@Path("/time")
public class TimeController {

    @Inject
    Service service;

    @POST
    @APIResponseSchema(value = ActivityResponse.class,
            responseDescription = "Created", responseCode = "201")
    public Response create(ActivityRequest request) {
        ActivityResponse response = new ActivityResponse(service.create(request));
        return Response.status(201).entity(response).build();
    }

    @PUT
    @APIResponseSchema(value = ActivityResponse.class,
            responseDescription = "Finished", responseCode = "200")
    @Path("/finish/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") UUID id) {
        try {
            ActivityResponse response = new ActivityResponse(service.finish(id));
            return Response.ok(response).build();
        } catch (Exception e) {
           return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @APIResponse(
            responseCode = "200",
            description = "List",
            content = @Content(
                    schema = @Schema(type = SchemaType.ARRAY, implementation = ActivityResponse.class)
            )
    )
    @Path("/last/{count}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findLast(@PathParam("count") int count) {
        List<ActivityResponse> list = service.findLast(count)
                .stream()
                .map(ActivityResponse::new)
                .toList();
        return Response.status(200).entity(list).build();
    }

    @GET
    @Path("/not-finished")
    @APIResponse(
            responseCode = "200",
            description = "List",
            content = @Content(
                    schema = @Schema(type = SchemaType.ARRAY, implementation = ActivityResponse.class)
            )
    )
    public Response findNotFinished() {
        List<ActivityResponse> list = service.findNotFinished()
                .stream()
                .map(ActivityResponse::new)
                .toList();
        return Response.status(200).entity(list).build();
    }
}


