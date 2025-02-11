package com.opom.jobfinder.model.entity.applicant;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.account.Account;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class ApplicantInfo extends AbstractEntity {

    @Id
    @Column(name = "applicant_id")
    private UUID id;
    private String phone;
    private String address;
    private String description;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Applicant applicant;
}
