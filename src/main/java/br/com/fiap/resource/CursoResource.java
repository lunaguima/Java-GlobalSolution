package br.com.fiap.resource;

import br.com.fiap.bo.CursoBO;
import br.com.fiap.to.CursoTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/curso")
public class CursoResource {

    private CursoBO cursoBO = new CursoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<CursoTO> resultado = cursoBO.findAll();
        Response.ResponseBuilder response;

        if (resultado != null) {
            response = Response.ok(); // 200
        } else {
            response = Response.status(404); // NOT FOUND
        }

        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{idCurso}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idCurso") Integer idCurso) {
        CursoTO resultado = cursoBO.findById(idCurso);
        Response.ResponseBuilder response;

        if (resultado != null) {
            response = Response.ok(); // 200
        } else {
            response = Response.status(404); // NOT FOUND
        }

        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid CursoTO curso) {
        CursoTO resultado = cursoBO.save(curso);
        Response.ResponseBuilder response;

        if (resultado != null) {
            response = Response.created(null); // 201
        } else {
            response = Response.status(400); // BAD REQUEST
        }

        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{idCurso}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid CursoTO curso, @PathParam("idCurso") Integer idCurso) {

        curso.setIdCurso(idCurso);
        CursoTO resultado = cursoBO.update(curso);
        Response.ResponseBuilder response;

        if (resultado != null) {
            response = Response.ok(); // 200
        } else {
            response = Response.status(400); // BAD REQUEST
        }

        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idCurso}")
    public Response delete(@PathParam("idCurso") Integer idCurso) {

        Response.ResponseBuilder response;

        if (cursoBO.delete(idCurso)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // NOT FOUND
        }

        return response.build();
    }
}
