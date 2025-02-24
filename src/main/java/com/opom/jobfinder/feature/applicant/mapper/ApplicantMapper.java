package com.opom.jobfinder.feature.applicant.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.feature.applicant.dto.UpdateApplicantProfileDto;
import com.opom.jobfinder.model.entity.applicant.Applicant;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ApplicantMapper {
        
        private final ObjectMapper mapper;
        
        public Applicant toApplicant(Object data){
                try {
                        return mapper.convertValue(data, Applicant.class);
                } catch (Exception e) {
                       throw new RuntimeException(e.getMessage());
                }
        }
}
