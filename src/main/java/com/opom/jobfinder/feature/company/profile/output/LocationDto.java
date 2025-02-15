package com.opom.jobfinder.feature.company.profile.output;

import com.opom.jobfinder.model.entity.info.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDto {

    private int id;
    private String name;

    public static LocationDto from(Location location) {
        return new LocationDto(location.getId(), location.getName());
    }

}
