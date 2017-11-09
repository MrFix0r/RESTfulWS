package com.bivgroup;

import com.bivgroup.Uni;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.*;

@Path("/uni")
@Stateless
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
@RolesAllowed({"ADMIN", "ORG1"})
public class UniServiceImpl implements UniService {

    private static Map<Integer, Uni> unies = new HashMap<>();

    @Override
    @POST
    @Path("/add")
    public Response addUni(Uni p) {
        Response response = new Response();
        if(unies.get(p.getId()) != null){
            response.setStatus(false);
            response.setMessage("Uni Already Exists");
            return response;
        }
        unies.put(p.getId(), p);
        response.setStatus(true);
        response.setMessage("Uni created successfully");
        return response;
    }

    @Override
    @GET
    @Path("/{id}/delete")
    public Response deleteUni(@PathParam("id") int id) {
        Response response = new Response();
        if(unies.get(id) == null){
            response.setStatus(false);
            response.setMessage("Uni Doesn't Exists");
            return response;
        }
        unies.remove(id);
        response.setStatus(true);
        response.setMessage("Uni deleted successfully");
        return response;
    }

    @Override
    @GET
    @Path("/{id}/get")
    public Uni getUniId(@PathParam("id") int id) {
        return unies.get(id);
    }

    @GET
    @Path("/{id}/getDummy")
    public Uni getDummyUni(@PathParam("id") int id) {
        Uni p = new Uni();
        p.setName("Dummy");
        p.setId(id);
        return p;
    }

    @GET
    @Path("/getAuth")
    public String getAuth(@HeaderParam("Authorization") String header) {
        String base64Credentials = header.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                Charset.forName("UTF-8"));
        return credentials;
    }

    @GET
    @Path("/getA")
//    @RolesAllowed("Administrator")
    @DenyAll
    public String getA (){
        return "Hello";
    }


    @Override
    @GET
    @Path("/getAll")
    public Uni[] getAllUnies() {
        Set<Integer> ids = unies.keySet();
        Uni[] unies = new Uni[ids.size()];
        int i=0;
        for(Integer id : ids){
            unies[i] = UniServiceImpl.unies.get(id);
            i++;
        }
        return unies;
    }

}