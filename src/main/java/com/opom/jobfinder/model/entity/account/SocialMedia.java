package com.opom.jobfinder.model.entity.account;

import com.opom.jobfinder.model.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class SocialMedia extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String link;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private Account account;
}
