package br.com.fiap.resource;

import br.com.fiap.bo.DashboardBO;
import br.com.fiap.to.CursoTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/dashboard")
public class DashboardResource {

    private DashboardBO dashboardBO = new DashboardBO();

    @GET
    @Path("/recomendacoes/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecomendacoes(@PathParam("idUsuario") Integer idUsuario) {

        ArrayList<CursoTO> resultado = dashboardBO.gerarRecomendacoes(idUsuario);

        Response.ResponseBuilder response = null;

        if (resultado != null && !resultado.isEmpty()) {
            response = Response.ok(); // 200 OK
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }

        response.entity(resultado);

        return response.build();
    }
}