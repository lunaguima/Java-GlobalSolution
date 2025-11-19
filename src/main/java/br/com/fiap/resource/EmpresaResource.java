package br.com.fiap.resource;

import br.com.fiap.bo.EmpresaBO;
import br.com.fiap.to.EmpresaTO;
import br.com.fiap.to.LoginTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/empresa")
public class EmpresaResource {

    private EmpresaBO empresaBO = new EmpresaBO();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginTO loginData) {

        EmpresaTO resultado = empresaBO.login(loginData.getLogin(), loginData.getSenha());
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
            response.entity(resultado);
        } else {
            response = Response.status(Response.Status.UNAUTHORIZED); // 401 (Igual ao seu exemplo)
            response.entity("Login ou senha inválidos.");
        }

        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid EmpresaTO empresa) {

        EmpresaTO resultado = empresaBO.save(empresa);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null); //  (201 CREATED)
        } else {
            response = Response.status(400); // (400 BAD REQUEST)
        }

        response.entity(resultado);
        return response.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        ArrayList<EmpresaTO> resultado = empresaBO.findAll();
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); //  (404 NOT FOUND)
        }

        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idEmpresa") Integer idEmpresa) {

        EmpresaTO resultado = empresaBO.findById(idEmpresa);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); //  (404 NOT FOUND)
        }

        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idEmpresa}")
    public Response delete(@PathParam("idEmpresa") Integer idEmpresa) {

        Response.ResponseBuilder response = null; //

        if (empresaBO.delete(idEmpresa)) {
            response = Response.status(204); // (204 NO CONTENT)
        } else {
            response = Response.status(404); // (404 NOT FOUND)
        }

        return response.build();
    }

    @PUT
    @Path("/{idEmpresa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @Valid EmpresaTO empresa,
            @PathParam("idEmpresa") Integer idEmpresa) {

        empresa.setIdEmpresa(idEmpresa);

        EmpresaTO resultado = empresaBO.update(empresa);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 - Ok
        } else {
            response = Response.status(400); //  (400 BAD REQUEST)
        }

        response.entity(resultado);
        return response.build();
    }
}