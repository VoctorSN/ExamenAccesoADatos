package edu.badpals.ExamenAccesoADatos;

import edu.badpals.ExamenAccesoADatos.domain.MagicalItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;

@Path("/")
public class Resource {

    public ServiceItem service = new ServiceItem();

    public Resource(){}

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/itemcrudos")
    public String itemCrudos() {
        return "CRUD de Items!";
    }

    @GET
    @Path("/item/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("name") String nombre) {
        Optional<MagicalItem> item = service.repo.loadItem(nombre);
        return item.isEmpty()?
                Response.status(Response.Status.NOT_FOUND).build():
                Response.status(Response.Status.OK).entity(item).build();
    }

    @POST
    @Path("/item")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response post(@Valid MagicalItem itemPar) {
        service.repo.createItem(itemPar.getName(),itemPar.getQuality(),itemPar.getType());
        Optional<MagicalItem> item = service.repo.loadItem(itemPar);
        return item.isPresent()?
                Response.status(Response.Status.CREATED).entity(item.get()).build():
                Response.status(Response.Status.BAD_REQUEST).build();
    }
}
