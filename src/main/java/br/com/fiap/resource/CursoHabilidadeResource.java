package br.com.fiap.resource;

import br.com.fiap.bo.CursoHabilidadeBO;
import br.com.fiap.to.CursoHabilidadeTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/curso-habilidade")
public class CursoHabilidadeResource {

    private CursoHabilidadeBO cursoHabilidadeBO = new CursoHabilidadeBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        ArrayList<CursoHabilidadeTO> resultado = cursoHabilidadeBO.findAll();
        Response.ResponseBuilder response;

        if (resultado != null) {
            response = Response.ok();   // 200 OK
        } else {
            response = Response.status(Response.Status.NOT_FOUND);
        }

        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{idCursoHabil}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idCursoHabil") Integer idCursoHabil) {

        CursoHabilidadeTO resultado = cursoHabilidadeBO.findById(idCursoHabil);
        Response.ResponseBuilder response;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(Response.Status.NOT_FOUND);
        }

        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid CursoHabilidadeTO cursoHabilidade) {

        CursoHabilidadeTO resultado = cursoHabilidadeBO.save(cursoHabilidade);
        Response.ResponseBuilder response;

        if (resultado != null) {
            response = Response.status(Response.Status.CREATED);
        } else {
            response = Response.status(Response.Status.BAD_REQUEST);
        }

        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{idCursoHabil}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @Valid CursoHabilidadeTO cursoHabilidade,
            @PathParam("idCursoHabil") Integer idCursoHabil
    ) {

        cursoHabilidade.setIdCursoHabilidade(idCursoHabil);

        CursoHabilidadeTO resultado = cursoHabilidadeBO.update(cursoHabilidade);
        Response.ResponseBuilder response;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(Response.Status.BAD_REQUEST);
        }

        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idCursoHabil}")
    public Response delete(@PathParam("idCursoHabil") Integer idCursoHabil) {

        Response.ResponseBuilder response;

        if (cursoHabilidadeBO.delete(idCursoHabil)) {
            response = Response.status(Response.Status.NO_CONTENT);
        } else {
            response = Response.status(Response.Status.NOT_FOUND);
        }

        return response.build();
    }
}
