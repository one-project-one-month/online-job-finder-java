package com.opom.jobfinder.feature.admin.location.service;

import com.opom.jobfinder.feature.admin.location.dtos.LocationDTO;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.utility.BaseResponse;

public interface LocationService {

    // CREATE LOCATION BY ADMIN
    BaseResponse save(Location location);

    // GET ALL LOCATIONS (GENERAL PURPOSE USE)
    BaseResponse getAll();

    // UPDATE LOCATION BY ID (UPDATE BY ADMIN)
    BaseResponse update(Location locationDetails,int id);

    // DELETE LOCATION (DELETE BY ADMIN)
    BaseResponse delete(String id);

    // GET APPLICANTS BY LOCATION ID (FOR ADMIN DASHBOARD)
//    List<Applicant> getApplicantsByLocationId(String locationId);

    // GET JOBS BY LOCATION ID (FOR APPLICANT && FOR ADMIN DASHBOARD)
    BaseResponse getJobsByLocation(String locationID);

    // GET COMPANIES BY LOCATION ID (FOR APPLICANT && ADMIN DASHBOARD)
    BaseResponse getCompaniesByLocation(String locationId);

    // GET LOCATION BY ID
    BaseResponse getLocationById(int id);

    Location mapLocationDTOToEntity(LocationDTO locationDTO);
}