package com.opom.jobfinder.model.entity.location;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Location")
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Version
    @Column(name="version")
    private int version;
    @Column(name="created_at")
    private LocalDateTime created_at = LocalDateTime.now();
    @Column(name="updated_at")
    private LocalDateTime updated_at;

    // TO STRING METHOD
    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", version=" + version +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
