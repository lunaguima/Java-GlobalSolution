package br.com.fiap.resource;

import br.com.fiap.bo.DemandaBO;
import br.com.fiap.to.DemandaTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/demanda")
public class DemandaResource {

    private DemandaBO demandaBO = new DemandaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        ArrayList<DemandaTO> resultado = demandaBO.findAll();
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();  // 200 OK
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }

        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{idDemanda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idDemanda") Integer idDemanda) {

        DemandaTO resultado = demandaBO.findById(idDemanda);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }

        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/empresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmpresa(@PathParam("idEmpresa") Integer idEmpresa) {

        ArrayList<DemandaTO> resultado = demandaBO.findByEmpresa(idEmpresa);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }

        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid DemandaTO demanda) {

        DemandaTO resultado = demandaBO.save(demanda);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null); // 201 CREATED
        } else {
            response = Response.status(400); // 400 BAD REQUEST
        }

        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{idDemanda}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @Valid DemandaTO demanda,
            @PathParam("idDemanda") Integer idDemanda
    ) {

        demanda.setIdDemanda(idDemanda);

        DemandaTO resultado = demandaBO.update(demanda);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(400);
        }

        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idDemanda}")
    public Response delete(@PathParam("idDemanda") Integer idDemanda) {

        Response.ResponseBuilder response = null;

        if (demandaBO.delete(idDemanda)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }

        return response.build();
    }
}