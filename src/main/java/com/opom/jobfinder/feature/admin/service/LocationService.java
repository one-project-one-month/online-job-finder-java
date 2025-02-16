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
    BaseResponse createLocation(Location location);

    // GET ALL LOCATIONS (GENERAL PURPOSE USE)
    BaseResponse getAllLocations();

    // GET LOCATION BY ID (WATCH DETAIL)
    BaseResponse getLocationById(String id);

    // UPDATE LOCATION BY ID (UPDATE BY ADMIN)
    BaseResponse updateLocation(Location locationDetails);

    // DELETE LOCATION (DELETE BY ADMIN)
    BaseResponse deleteLocation(String id);

    // UPDATE LOCATION BY APPLICANT (UPDATE LOCATION APPLICANT BY THEMSELVES)
    BaseResponse updateLocationByApplicant(String locationId,UUID applicantId);

    // UPDATE LOCATION BY COMPANY (UPDATE LOCATION COMPANY BY THEMSELVES)
    BaseResponse updateLocationByCompany(String locationId,UUID companyId);

    // GET APPLICANTS BY LOCATION ID (FOR ADMIN DASHBOARD)
    List<Applicant> getApplicantsByLocationId(String locationId);

    // GET JOBS BY LOCATION ID (FOR APPLICANT && FOR ADMIN DASHBOARD)
    BaseResponse getJobsByLocation(String locationID);

    // GET COMPANIES BY LOCATION ID (FOR APPLICANT && ADMIN DASHBOARD)
    BaseResponse getCompaniesByLocation(String locationId);
}