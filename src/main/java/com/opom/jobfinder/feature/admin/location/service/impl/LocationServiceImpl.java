package com.opom.jobfinder.feature.admin.location.service.impl;

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
    public BaseResponse save(Location location) {
        try {
            List<Location> locations = locationRepo.search(cb -> {
                CriteriaQuery<Location> query = cb.createQuery(Location.class);
                Root<Location> root = query.from(Location.class);
                query.select(root).where(cb.equal(root.get("name"), location.getName()));
                return query;
            });
            if(!locations.isEmpty()) {
                return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, "Location Already Exist!", Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
            }
            return successResponse(locationRepo.save(location));
        } catch (Exception e) {
            return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, "Save Location Failed "+e,Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        }
    }

    @Override
    public BaseResponse getAll() {
        return successResponse(locationRepo.findAll());
    }

    @Override
    public BaseResponse update(Location locationDetails,int id) {
        Optional<Location> originLocation = locationRepo.findById(id);
        if(originLocation.isPresent()) {
            locationDetails.setId(id);
            return successResponse(locationRepo.save(locationDetails));
        } else {
            return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, "Location Not Found!",Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        }
    }

    @Override
    public BaseResponse delete(String id) {
        Optional<Location> originLocation = locationRepo.findById(Integer.valueOf(id));
        if(originLocation.isPresent()) {
            originLocation.get().setStatus(false);
            locationRepo.save(originLocation.get());
            return successResponse("Delete Location Successfully!");
        } else {
            return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, "Location Not Found!",Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        }
    }

//    @Override
//    public List<Applicant> getApplicantsByLocationId(String locationId) {
//        return List.of();
//    }

    @Override
    public BaseResponse getJobsByLocation(String locationId) {
        Optional<Location> location = locationRepo.findById(Integer.valueOf(locationId));
        if(location.isPresent()) {
            List<Job> jobs = jobRepo.search(cb -> {
                CriteriaQuery<Job> query = cb.createQuery(Job.class);
                Root<Job> root = query.from(Job.class);
                query.select(root).where(cb.equal(root.get("location").get("id"), locationId));
                return query;
            });
            return successResponse(jobs);
        } else {
            return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR,"Location Not Found!", Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
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
