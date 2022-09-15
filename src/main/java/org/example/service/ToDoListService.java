package org.example.service;

import org.example.model.ToDoList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Path("/to-do-list")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToDoListService {

    private Set<ToDoList> toDoLists = new HashSet<>();

    public ToDoListService() {
        toDoLists.add(new ToDoList("Test1", "desc test1"));
        toDoLists.add(new ToDoList("Test2", "desc test2"));
        toDoLists.add(new ToDoList("Test3", "desc test3"));
    }

    @GET
    public Set<ToDoList> list() {
        return toDoLists;
    }

    @POST
    public Set<ToDoList> add(ToDoList element) {
        toDoLists.add(element);
        return toDoLists;
    }

    @DELETE
    public Set<ToDoList> delete(ToDoList element) {
        toDoLists.removeIf(value -> value.getTitle().contentEquals(element.getTitle()));
        return toDoLists;
    }

    @PUT
    public Set<ToDoList> update(ToDoList element) {
        toDoLists.forEach(value -> {
            if (value.getTitle().equals(element.getTitle())) {
                value.setDescription(element.getDescription());
            }
        });
        return toDoLists;
    }

    @PATCH
    @Path("/state")
    public ToDoList toggleStatus(ToDoList element) {
        return findByTitle(element.getTitle()).toggleStatus();
    }

    @GET
    @Path("/{title}")
    public ToDoList findByTitle(@PathParam("title") String title) {
        System.out.println(title);
        return toDoLists.stream()
        .filter(value -> value.getTitle().equals(title))
        .findFirst().get();
    }

}