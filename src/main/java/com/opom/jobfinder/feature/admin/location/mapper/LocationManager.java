package com.opom.jobfinder.feature.admin.location.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.feature.admin.location.dtos.GetCompanyByLocationDTO;
import com.opom.jobfinder.feature.admin.location.dtos.GetJobByLocationDTO;
import com.opom.jobfinder.model.entity.info.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationManager {

    private final ObjectMapper mapper;
    public Location toLocation(Object data) {
        try {
            return mapper.convertValue(data, Location.class);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public GetCompanyByLocationDTO toGetCompanyByLocation(Object data) {
        try {
            return mapper.convertValue(data, GetCompanyByLocationDTO.class);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public GetJobByLocationDTO toGetJobByLocationDTO(Object data) {
        try {
            return mapper.convertValue(data, GetJobByLocationDTO.class);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
