package com.opom.jobfinder.feature.admin.location.service.impl;

import com.opom.jobfinder.feature.admin.location.dtos.GetJobByLocationDTO;
import com.opom.jobfinder.feature.admin.location.dtos.LocationDTO;
import com.opom.jobfinder.feature.admin.location.service.LocationService;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.job.JobRepo;
import com.opom.jobfinder.model.repo.location.LocationRepo;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.BaseService;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl extends BaseService implements LocationService {

    // CONSTANT VALUES
    private final LocationRepo locationRepo;
    private final CompanyRepo companyRepo;
    private final JobRepo jobRepo;

    // CONSTRUCTOR
    public LocationServiceImpl(LocationRepo locationRepo, CompanyRepo companyRepo, JobRepo jobRepo) {
        this.locationRepo = locationRepo;
        this.companyRepo = companyRepo;
        this.jobRepo = jobRepo;
    }

    @Override
    public Location save(Location location) {

        locationRepo.findByName(location.getName())
                        .ifPresent(existingLocation -> {
                            throw new IllegalArgumentException("Location already exist!");
                        });

        return locationRepo.save(location);
    }

    @Override
    public List<Location> getAll() {
        return locationRepo.findAll();
    }

    @Override
    public Location update(Location locationDetails,int id) {
        Optional<Location> originLocation = locationRepo.findById(id);
        if(originLocation.isPresent()) {
            locationDetails.setId(id);
            return locationRepo.save(locationDetails);
        } else {
            throw new IllegalArgumentException("Location not found!");
        }
    }

    @Override
    public void delete(String id) {
        Optional<Location> originLocation = locationRepo.findById(Integer.valueOf(id));
        if(originLocation.isPresent()) {
            originLocation.get().setStatus(false);
            locationRepo.save(originLocation.get());
        } else {
            throw new IllegalArgumentException("Location delete failed!");
        }
    }

//    @Override
//    public List<Applicant> getApplicantsByLocationId(String locationId) {
//        return List.of();
//    }

    @Override
    public List<GetJobByLocationDTO> getJobsByLocation(String locationId) {
        Optional<Location> location = locationRepo.findById(Integer.valueOf(locationId));
        if(location.isPresent()) {

        } else {
            throw new IllegalArgumentException("Location not found!");
        }
    }

    @Override
    public BaseResponse getCompaniesByLocation(String locationId) {
        Optional<Location> location = locationRepo.findById(Integer.valueOf(locationId));
        if(location.isPresent()) {
            List<Company> companies = companyRepo.search(cb -> {
                CriteriaQuery<Company> query = cb.createQuery(Company.class);
                Root<Company> root = query.from(Company.class);
                query.select(root).where(cb.equal(root.get("location").get("id"), Integer.parseInt(locationId)));
                return query;
            });
            return successResponse(companies);
        } else {
            return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR,"Location Not Found!", Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        }
    }

    @Override
    public BaseResponse getLocationById(int id) {
        Optional<Location> optionalLocation = locationRepo.findById(id);
        if(optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            return successResponse(location);
        }else {
            return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR,"Location Not Found!", Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        }
    }

    @Override
    public Location mapLocationDTOToEntity(LocationDTO locationDTO) {
        Location location = new Location();

        location.setName(locationDTO.name());
        location.setDescription(locationDTO.description());
        location.setStatus(true);
        return location;
    }
}
