package com.opom.jobfinder.feature.admin.service;

import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.utility.BaseResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocationService {

    // CREATE LOCATION BY ADMIN
    BaseResponse save(Location location);

    // GET ALL LOCATIONS (GENERAL PURPOSE USE)
    BaseResponse getAll();

    // UPDATE LOCATION BY ID (UPDATE BY ADMIN)
    BaseResponse update(Location locationDetails);

    // DELETE LOCATION (DELETE BY ADMIN)
    BaseResponse delete(String id);

    // GET APPLICANTS BY LOCATION ID (FOR ADMIN DASHBOARD)
//    List<Applicant> getApplicantsByLocationId(String locationId);

    // GET JOBS BY LOCATION ID (FOR APPLICANT && FOR ADMIN DASHBOARD)
    BaseResponse getJobsByLocation(String locationID);

    // GET COMPANIES BY LOCATION ID (FOR APPLICANT && ADMIN DASHBOARD)
    BaseResponse getCompaniesByLocation(String locationId);
}