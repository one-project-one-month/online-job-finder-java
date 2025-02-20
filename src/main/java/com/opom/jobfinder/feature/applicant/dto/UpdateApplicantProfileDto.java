package com.opom.jobfinder.feature.applicant.dto;


import java.time.LocalDate;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;


public record UpdateApplicantProfileDto(
        @NotBlank(message = "isInformationCompleted is required") boolean isInformationCompleted ,
        @NotBlank(message = "full name is required") String fullname,
        @NotBlank(message = "phone is required") String phone,
        @NotBlank(message = "locationId is required") String locationId,
        @NotBlank(message = "job categoryIds is required") String jobCategoryIds,
        @Nullable String description,
        @Nullable String resumeId,
        @Nullable EducationDto education,
        @Nullable ExperienceDto experience
) {
        private record EducationDto (
                  @NotBlank(message = "degree is required")              String degree,
                  @NotBlank(message = "field is required")               String field,
                  @NotBlank(message = "description is required")         String description,
                  @NotBlank(message = "attending is required")           boolean attending
        ){}

        private record ExperienceDto (
                         @NotBlank(message = "title is required")             String title,
                         @NotBlank(message = "comapany name is reqyired")     String companyName,
                         @NotBlank(message = "description is required")       String description,
                         @NotBlank(message = "address is required")           String address,
                         @NotBlank(message = "location id is required")       String location,
                         @NotBlank(message = "jobType is required")           String jobType,
                         @NotBlank(message = "start At is required")          LocalDate startAt,
                         @Nullable                                            LocalDate endAt,
                         @NotBlank(message = "working is required")           boolean working
        ){}
}
