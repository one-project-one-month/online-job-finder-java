package com.opom.jobfinder.feature.info.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobJobCategoryDTO extends MasterJobCategoryDTO {
    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int version;

}
