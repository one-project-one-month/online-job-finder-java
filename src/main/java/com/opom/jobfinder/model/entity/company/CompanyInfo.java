package com.opom.jobfinder.model.entity.company;

import com.opom.jobfinder.model.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class CompanyInfo extends AbstractEntity {

    @Id
    @Column(name = "company_id")
    private UUID id;

    private String phone;
    private String website;
    private String address;
    private String description;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Company company;
}
