package com.opom.jobfinder.feature.info.category.impl;

import com.opom.jobfinder.feature.info.category.CategoryService;
import com.opom.jobfinder.model.entity.info.JobCategory;
import com.opom.jobfinder.model.repo.info.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo jobCategoryRepo;

    @Override
    @Transactional
    public JobCategory createCategory(JobCategory jobCategory) {
        jobCategoryRepo.save(jobCategory);
        return jobCategory;
    }

    @Override
    @Transactional
    public List<JobCategory> getAllCategories() {
        return jobCategoryRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteCategory(JobCategory jobCategory) {
        jobCategoryRepo.delete(jobCategory);
    }

    @Override
    @Transactional
    public JobCategory updateCategory(int id,JobCategory jobCategory) {
        JobCategory jobCategory1 = jobCategoryRepo.findById(id).orElseThrow(()->new RuntimeException("Job Category with this id not found!"));
        jobCategory1.setName(jobCategory.getName());
        jobCategory1.setVersion(jobCategory.getVersion());
        jobCategory1.setDescription(jobCategory.getDescription());
        jobCategory1.setUpdatedAt(jobCategory.getUpdatedAt());
        return jobCategory1;
    }
}
