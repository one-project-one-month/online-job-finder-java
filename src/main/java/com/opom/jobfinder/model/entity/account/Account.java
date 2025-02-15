package com.opom.jobfinder.model.entity.account;

import com.opom.jobfinder.model.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Account extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(optional = false)
    private Role role;

    private boolean completed;

    @Column(nullable = true)
    private String profilePhoto;

    @OneToMany(mappedBy = "account")
    private List<SocialMedia> socialMedias;

}
