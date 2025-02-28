package com.opom.jobfinder.feature.info.category.controller;

import com.opom.jobfinder.feature.info.category.JobCategoryService;
import com.opom.jobfinder.feature.info.category.dto.CreateJobJobCategoryDTO;
import com.opom.jobfinder.feature.info.category.dto.UpdateJobCategoryDTO;
import com.opom.jobfinder.model.entity.info.JobCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class JobCategoryController {
    @Autowired
    private JobCategoryService jobCategoryService;


    @PostMapping
    public JobCategory createCategory(@RequestBody CreateJobJobCategoryDTO createJobCategoryDTO) {
        return jobCategoryService.createCategory(createJobCategoryDTO);
    }

    @GetMapping("/getAll")
    public List<JobCategory> getAllCategories() {
        return jobCategoryService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        jobCategoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public JobCategory updateCategory(@PathVariable int id, @RequestBody UpdateJobCategoryDTO updateCategoryDTO) {
        return jobCategoryService.updateCategory(id, updateCategoryDTO);
    }
}
