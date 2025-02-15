package com.opom.jobfinder.feature.admin.service;

import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.entity.location.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    // CREATE LOCATION BY ADMIN
    Location createLocation(Location location);

    // GET ALL LOCATIONS (GENERAL PURPOSE USE)
    List<Location> getAllLocations();

    // GET LOCATION BY ID (WATCH DETAIL)
    Optional<Location> getLocationById(String id);

    // UPDATE LOCATION BY ID (UPDATE BY ADMIN)
    Location updateLocation(Location locationDetails);

    // DELETE LOCATION (DELETE BY ADMIN)
    void deleteLocation(String id);

    // UPDATE LOCATION BY APPLICANT (UPDATE LOCATION APPLICANT BY THEMSELVES)
    Location updateLocationByApplicant(String locationId,String applicantId);

    // UPDATE LOCATION BY COMPANY (UPDATE LOCATION COMPANY BY THEMSELVES)
    Location updateLocationByCompany(String locationId,String applicantId);

    // GET APPLICANTS BY LOCATION ID (FOR ADMIN DASHBOARD)
    List<Applicant> getApplicantsByLocationId(String locationId);

    // GET JOBS BY LOCATION ID (FOR APPLICANT && FOR ADMIN DASHBOARD)
    List<Job> getJobsByLocation(String locationID);

    // GET COMPANIES BY LOCATION ID (FOR APPLICANT && ADMIN DASHBOARD)
    List<Company> getCompaniesByLocation(String locationId);
}
