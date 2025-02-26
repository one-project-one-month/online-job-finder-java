package com.opom.jobfinder.feature.admin.location.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.feature.admin.location.dtos.GetCompanyByLocationDTO;
import com.opom.jobfinder.feature.admin.location.dtos.GetJobByLocationDTO;

import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.entity.job.Job;
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

    public GetCompanyByLocationDTO toGetCompanyByLocation(Company company) {
        try {
            double avgReview = 0.0;
            if (company.getReviews() != null && !company.getReviews().isEmpty()) {
                avgReview = company.getReviews().stream()
                        .mapToDouble(Review::getRating)
                        .average()
                        .orElse(0.0);
            }

            return new GetCompanyByLocationDTO(
                    company.getAccount().getName(),
                    company.getAddress(),
                    company.getDescription(),
                    company.getLocation().getId(),
                    avgReview
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public GetJobByLocationDTO toGetJobByLocationDTO(Job job) {
        try {
            return new GetJobByLocationDTO(
                    job.getTitle(),
                    job.getDescription(),
                    job.getPost(),
                    job.getAddress(),
                    job.getType(), // Assuming Type is an enum or a specific type, adjust accordingly
                    job.getStatus().name()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
