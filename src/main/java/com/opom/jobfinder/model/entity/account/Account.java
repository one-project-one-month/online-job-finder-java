package com.opom.jobfinder.model.entity.account;

import com.opom.jobfinder.model.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractEntity implements UserDetails {

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
    @JoinColumn(name = "role_id")
    private Role role;

    private boolean completed;

    @Column(nullable = true)
    private String profilePhoto;

    @OneToMany(mappedBy = "account")
    private List<SocialMedia> socialMedias;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
<<<<<<< Updated upstream
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getName()));
=======
        return List.of(new SimpleGrantedAuthority(role.getName()));
>>>>>>> Stashed changes
    }

    @Override
    public String getUsername() {
        return email;
    }
}
