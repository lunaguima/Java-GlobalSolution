package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioHabilidadeBO;
import br.com.fiap.to.UsuarioHabilidadeTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/usuariohabilidade")
public class UsuarioHabilidadeResource {

    private UsuarioHabilidadeBO usuarioHabilidadeBO = new UsuarioHabilidadeBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid UsuarioHabilidadeTO novaRelacao) {

        UsuarioHabilidadeTO resultado = usuarioHabilidadeBO.save(novaRelacao);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByUsuario(@PathParam("idUsuario") Integer idUsuario) {

        ArrayList<UsuarioHabilidadeTO> resultado = usuarioHabilidadeBO.findByUsuario(idUsuario);
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
        UsuarioHabilidadeTO resultado = usuarioHabilidadeBO.findById(id);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{idRelacao}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid UsuarioHabilidadeTO relacaoAtualizada, @PathParam("idRelacao") Integer idRelacao) {

        relacaoAtualizada.setIdRelacao(idRelacao);

        UsuarioHabilidadeTO resultado = usuarioHabilidadeBO.update(relacaoAtualizada);
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
    @Path("/{idRelacao}")
    public Response delete(@PathParam("idRelacao") Integer idRelacao) {
        Response.ResponseBuilder response = null;

        if (usuarioHabilidadeBO.delete(idRelacao)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }
}