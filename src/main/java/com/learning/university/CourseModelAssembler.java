package com.learning.university;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CourseModelAssembler implements RepresentationModelAssembler<Course, EntityModel<Course>> {


    @Override
    public EntityModel<Course> toModel(Course entity) {
        try {
            return EntityModel.of(entity, //
                    linkTo(methodOn(UniversityController.class).getCourse(entity.getId())).withSelfRel(),
                    linkTo(methodOn(UniversityController.class).get()).withRel("course"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CollectionModel<EntityModel<Course>> toCollectionModel(Iterable<? extends Course> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
