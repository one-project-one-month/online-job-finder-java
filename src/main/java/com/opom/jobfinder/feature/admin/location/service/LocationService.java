package com.opom.jobfinder.feature.admin.location.service;

import com.opom.jobfinder.feature.admin.location.dtos.GetCompanyByLocation;
import com.opom.jobfinder.feature.admin.location.dtos.GetCompanyByLocationDTO;
import com.opom.jobfinder.feature.admin.location.dtos.GetJobByLocationDTO;
import com.opom.jobfinder.feature.admin.location.dtos.LocationDTO;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.utility.BaseResponse;

import java.util.List;

public interface LocationService {

    // CREATE LOCATION BY ADMIN
    Location save(Location location);

    // GET ALL LOCATIONS (GENERAL PURPOSE USE)
    List<Location> getAll();

    // UPDATE LOCATION BY ID (UPDATE BY ADMIN)
    Location update(Location locationDetails,int id);

    // DELETE LOCATION (DELETE BY ADMIN)
    void delete(String id);

    // GET APPLICANTS BY LOCATION ID (FOR ADMIN DASHBOARD)
//    List<Applicant> getApplicantsByLocationId(String locationId);

    // GET JOBS BY LOCATION ID (FOR APPLICANT && FOR ADMIN DASHBOARD)
    List<GetJobByLocationDTO> getJobsByLocation(String locationID);

    // GET COMPANIES BY LOCATION ID (FOR APPLICANT && ADMIN DASHBOARD)
    List<GetCompanyByLocationDTO> getCompaniesByLocation(String locationId);

    // GET LOCATION BY ID
    Location getLocationById(int id);

    Location mapLocationDTOToEntity(LocationDTO locationDTO);
}