package br.com.fiap.resource;

import br.com.fiap.bo.HabilidadeBO;
import br.com.fiap.to.HabilidadeTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/habilidade")
public class HabilidadeResource {

    private HabilidadeBO habilidadeBO = new HabilidadeBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){

        ArrayList<HabilidadeTO> resultado = habilidadeBO.findAll();
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) {

        HabilidadeTO resultado = habilidadeBO.findById(id);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid HabilidadeTO habilidade) {

        HabilidadeTO resultado = habilidadeBO.save(habilidade);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{idHabilidade}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid HabilidadeTO habilidade, @PathParam("idHabilidade") Integer idHabilidade) {

        habilidade.setIdHabilidade(idHabilidade);

        HabilidadeTO resultado = habilidadeBO.update(habilidade);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 - Ok
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idHabilidade}")
    public Response delete(@PathParam("idHabilidade") Integer idHabilidade) {
        Response.ResponseBuilder response = null;

        if (habilidadeBO.delete(idHabilidade)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }
}