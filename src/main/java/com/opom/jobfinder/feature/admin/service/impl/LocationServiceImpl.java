package com.opom.jobfinder.feature.admin.service.impl;

import com.opom.jobfinder.feature.admin.service.LocationService;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.entity.location.Location;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.location.LocationRepo;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.BaseService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class LocationServiceImpl extends BaseService implements LocationService{

    // CONSTANT VALUES
    private final LocationRepo locationRepo;
    private final CompanyRepo companyRepo;

    // CONSTRUCTOR
    public LocationServiceImpl(LocationRepo locationRepo, CompanyRepo companyRepo) {
        this.locationRepo = locationRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public Location createLocation(Location location) {
        return locationRepo.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepo.findAll();
    }

    @Override
    public Optional<Location> getLocationById(String id) {
        return locationRepo.findById(id);
    }

    @Override
    public Location updateLocation(Location locationDetails) {

        // ADD UPDATED TIME
        locationDetails.setUpdated_at(LocalDateTime.now());

        return locationRepo.save(locationDetails);
    }

    @Override
    public void deleteLocation(String id) {
        locationRepo.deleteById(id);
    }

    public BaseResponse search(Function<CriteriaBuilder, CriteriaQuery<Location>> queryFunc) {
        try {
            // Perform the search using the provided CriteriaQuery function
            List<Location> locations = locationRepo.search(cb -> {
                CriteriaBuilder builder = cb;
                CriteriaQuery<Location> query = builder.createQuery(Location.class);
                Root<Location> root = query.from(Location.class);
                query.select(root);  // Select all records from the Location table
                return query;
            });
            return successResponse(locations);

        } catch (Exception e) {
            return errorResponse("500", "Error occurred: " + e.getMessage());
        }
    }



    @Override
    public Location updateLocationByApplicant(String locationId, String applicantId) {
        return null;
    }

    @Override
    public Location updateLocationByCompany(String locationId, String applicantId) {
        return null;
    }

    @Override
    public List<Applicant> getApplicantsByLocationId(String locationId) {
        return List.of();
    }

    @Override
    public List<Job> getJobsByLocation(String locationID) {
        return List.of();
    }

    @Override
    public List<Company> getCompaniesByLocation(String locationId) {
        return List.of();
    }

}
