package com.opom.jobfinder.feature.info.category.mapper;

import com.opom.jobfinder.feature.info.category.dto.JobJobCategoryDTO;
import com.opom.jobfinder.feature.info.category.dto.CreateJobJobCategoryDTO;
import com.opom.jobfinder.model.entity.info.JobCategory;


public interface JobCategoryMapper {
    JobCategory toEntity (CreateJobJobCategoryDTO createJobCategoryDTO);

    JobJobCategoryDTO toDTO (JobCategory jobCategory);

}
