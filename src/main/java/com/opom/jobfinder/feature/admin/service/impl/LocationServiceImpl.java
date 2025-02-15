package com.opom.jobfinder.feature.admin.service.impl;

import com.opom.jobfinder.feature.admin.service.LocationService;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.entity.location.Location;
import com.opom.jobfinder.model.repo.location.LocationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    // CONSTANT VALUES
    private final LocationRepo locationRepo;

    // CONSTRUCTOR
    public LocationServiceImpl(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Override
    public Location createLocation(Location location) {
        return locationRepo.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return List.of();
    }

    @Override
    public Optional<Location> getLocationById(String id) {
        return Optional.empty();
    }

    @Override
    public Location updateLocation(Location locationDetails) {
        return null;
    }

    @Override
    public void deleteLocation(String id) {

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
