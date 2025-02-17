package com.opom.jobfinder.feature.info.category;

import com.opom.jobfinder.model.entity.info.JobCategory;
import java.util.List;

public interface CategoryService {
     JobCategory createCategory(JobCategory jobCategory);

     List<JobCategory> getAllCategories();

     void deleteCategory(JobCategory jobCategory);

     JobCategory updateCategory(int id,JobCategory jobCategory);
}
