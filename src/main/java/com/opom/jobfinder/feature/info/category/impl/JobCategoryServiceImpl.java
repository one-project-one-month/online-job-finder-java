package com.opom.jobfinder.feature.info.category.impl;

import com.opom.jobfinder.feature.info.category.JobCategoryService;
import com.opom.jobfinder.feature.info.category.dto.CreateJobJobCategoryDTO;
import com.opom.jobfinder.feature.info.category.dto.UpdateJobCategoryDTO;
import com.opom.jobfinder.feature.info.category.mapper.JobCategoryMapper;
import com.opom.jobfinder.model.entity.info.JobCategory;
import com.opom.jobfinder.model.repo.info.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobCategoryServiceImpl implements JobCategoryService {

    @Autowired
    private CategoryRepo jobCategoryRepo;

    @Autowired
    private JobCategoryMapper jobCategoryMapper;

    @Override
    @Transactional
    public JobCategory createCategory(CreateJobJobCategoryDTO createJobCategoryDTO) {
        JobCategory jobCategory = jobCategoryMapper.toEntity(createJobCategoryDTO);
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
    public void deleteCategory(int id) {
        jobCategoryRepo.deleteById(id);
    }

    @Override
    @Transactional
    public JobCategory updateCategory(int id, UpdateJobCategoryDTO updateCategoryDTO) {
        JobCategory jobCategory1 = jobCategoryRepo.findById(id).orElseThrow(()->new RuntimeException("Job Category with this id not found!"));
        jobCategory1.setName(updateCategoryDTO.getName());
        jobCategory1.setDescription(updateCategoryDTO.getDescription());
        return jobCategory1;
    }
}
