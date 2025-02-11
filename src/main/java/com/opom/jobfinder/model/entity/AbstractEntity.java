package com.opom.jobfinder.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AbstractEntity {
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected int version;
}
