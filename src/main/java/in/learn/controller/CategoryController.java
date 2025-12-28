package in.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.learn.entity.Categories;
import in.learn.entity.Courses;
import in.learn.service.CategoryService;

@RestController

public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("addcategories")
    public String addCategory(@RequestBody Categories category) {
        service.save(category);
        return "Category added successfully";
    }

    @GetMapping("getall/categories")
    public List<Categories> getAll() {
        return service.getAll();
    }

    @GetMapping("categories/{id}")
    public Categories getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PutMapping("categories/update/{id}")
    public String update(@PathVariable long id, @RequestBody Categories category) {
        service.update(id, category);
        return "Category updated";
    }

    @DeleteMapping("categories/delete/{id}")
    public String delete(@PathVariable long id) {
        service.delete(id);
        return "Category deleted";
    }

    @GetMapping("categories/{id}/courses")
    public List<Courses> getCoursesByCategory(@PathVariable long id) {
        return service.getCoursesByCategory(id);
    }
}
