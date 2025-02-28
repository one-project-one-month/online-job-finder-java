package com.opom.jobfinder.feature.info.category.controller;

import com.opom.jobfinder.feature.info.category.CategoryService;
import com.opom.jobfinder.model.entity.info.JobCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public JobCategory createCategory(@RequestBody JobCategory jobCategory) {
        return categoryService.createCategory(jobCategory);
    }

    @GetMapping
    public List<JobCategory> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(new JobCategory(id));
    }

    @PutMapping("/{id}")
    public JobCategory updateCategory(@PathVariable int id, @RequestBody JobCategory jobCategory) {
        return categoryService.updateCategory(id, jobCategory);
    }
}
