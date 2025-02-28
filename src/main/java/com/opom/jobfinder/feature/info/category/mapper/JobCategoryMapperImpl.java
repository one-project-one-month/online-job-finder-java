package com.opom.jobfinder.feature.info.category.mapper;

import com.opom.jobfinder.feature.info.category.dto.JobJobCategoryDTO;
import com.opom.jobfinder.feature.info.category.dto.CreateJobJobCategoryDTO;
import com.opom.jobfinder.model.entity.info.JobCategory;

public class JobCategoryMapperImpl implements JobCategoryMapper {
    @Override
    public JobCategory toEntity(CreateJobJobCategoryDTO createJobCategoryDTO) {
        JobCategory jobCategory = new JobCategory();
        jobCategory.setName(createJobCategoryDTO.getName());
        jobCategory.setDescription(createJobCategoryDTO.getDescription());
        return jobCategory;
    }

    @Override
    public JobJobCategoryDTO toDTO(JobCategory jobCategory) {
        JobJobCategoryDTO jobCategoryDTO = new JobJobCategoryDTO();
        jobCategoryDTO.setId(jobCategory.getId());
        jobCategoryDTO.setName(jobCategory.getName());
        jobCategoryDTO.setDescription(jobCategory.getDescription());
        jobCategoryDTO.setCreatedAt(jobCategory.getCreatedAt());
        jobCategoryDTO.setUpdatedAt(jobCategory.getUpdatedAt());
        return jobCategoryDTO;
    }
}
