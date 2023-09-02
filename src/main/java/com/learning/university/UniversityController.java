package com.learning.university;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class UniversityController {

    UniversityService universityService;

    CourseModelAssembler courseModelAssembler;

    UniversityController(UniversityService universityService, CourseModelAssembler courseModelAssembler)
    {
        this.universityService = universityService;
        this.courseModelAssembler = courseModelAssembler;
    }

    @GetMapping("/Course")
    public CollectionModel<EntityModel<Course>> get()
    {
        List<EntityModel<Course>> list  =   universityService.findAll().stream().map(courseModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(list, linkTo(methodOn(UniversityController.class).get()).withSelfRel());
    }

    @PostMapping("/Course")
    public ResponseEntity<EntityModel<Course>> addCourse(@RequestBody Course newCourse)
    {
         EntityModel<Course> course = courseModelAssembler.toModel(this.universityService.save(newCourse));
         return ResponseEntity
                 .created(course.getRequiredLink(IanaLinkRelations.SELF).toUri())
                 .body(course);
    }

    @GetMapping("/Course/{id}")
    public EntityModel<Course> getCourse(@PathVariable int id) throws Exception {
        Course c = this.universityService.find(id);
        return this.courseModelAssembler.toModel(c);
    }

    @PutMapping("/Course/{id}")
    public ResponseEntity<EntityModel<Course>> updateCourse(@PathVariable int id, @RequestBody Course updatedCourse)
    {
       EntityModel<Course> course = courseModelAssembler.toModel(this.universityService.update(updatedCourse, id));
        return ResponseEntity //
                .created(course.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(course);
    }

    @DeleteMapping("/Course/{id}")
    public void deleteCourse(@PathVariable int id)
    {
        universityService.deleteById(id);
    }
}
