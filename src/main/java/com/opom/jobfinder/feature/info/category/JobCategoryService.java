package com.opom.jobfinder.feature.info.category;

import com.opom.jobfinder.feature.info.category.dto.CreateJobJobCategoryDTO;
import com.opom.jobfinder.feature.info.category.dto.UpdateJobCategoryDTO;
import com.opom.jobfinder.model.entity.info.JobCategory;

import java.util.List;

public interface JobCategoryService {
     JobCategory createCategory(CreateJobJobCategoryDTO createJobCategoryDTO);

     List<JobCategory> getAllCategories();

     void deleteCategory(int id);

     JobCategory updateCategory(int id, UpdateJobCategoryDTO updateCategoryDTO);

}
