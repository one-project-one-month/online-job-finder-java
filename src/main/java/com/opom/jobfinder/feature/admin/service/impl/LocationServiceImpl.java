package com.opom.jobfinder.feature.admin.service.impl;

import com.opom.jobfinder.feature.admin.service.LocationService;
import com.opom.jobfinder.model.entity.applicant.Applicant;
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
import com.opom.jobfinder.utility.exception.BadRequestException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public BaseResponse createLocation(Location location) {
        return BaseResponse.of(MessageConstants.SUCCESS, locationRepo.save(location), Translator.toLocale(MessageConstants.SUCCESS));
    }

    @Override
    public BaseResponse getAllLocations() {
        try {
            List<Location> locations = locationRepo.search(cb -> {
                CriteriaQuery<Location> query = cb.createQuery(Location.class);
                Root<Location> root = query.from(Location.class);
                query.select(root);
                return query;
            });
            return successResponse(locations);

        } catch (Exception e) {
            throw new BadRequestException("Searching Location Failed!");
        }
    }

    @Override
    public BaseResponse getLocationById(String id) {
        return BaseResponse.of(MessageConstants.SUCCESS, locationRepo.findById(Integer.parseInt(id)), Translator.toLocale(MessageConstants.SUCCESS));
    }

    @Override
    public BaseResponse updateLocation(Location locationDetails) {
        return BaseResponse.of(MessageConstants.SUCCESS, locationRepo.save(locationDetails), Translator.toLocale(MessageConstants.SUCCESS));
    }

    @Override
    public BaseResponse deleteLocation(String id) {
        locationRepo.deleteById(Integer.parseInt(id));
        return BaseResponse.of(MessageConstants.SUCCESS, "Delete Location Successfully!", Translator.toLocale(MessageConstants.SUCCESS));
    }

//    @Override
//    public List<Applicant> getApplicantsByLocationId(String locationId) {
//        return List.of();
//    }

    @Override
    public BaseResponse getJobsByLocation(String locationId) {
        try {
            List<Job> jobs = jobRepo.search(cb -> {
                CriteriaQuery<Job> query = cb.createQuery(Job.class);
                Root<Job> root = query.from(Job.class);
                query.select(root).where(cb.equal(root.get("location").get("id"), locationId));
                return query;
            });

            return BaseResponse.of(MessageConstants.SUCCESS, jobs, Translator.toLocale(MessageConstants.SUCCESS));

        }catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid UUID format for location ID: " + Integer.parseInt(locationId));
        } catch (Exception e) {
            throw new BadRequestException("Searching Jobs by Location Failed!"+e.getMessage()+e.getClass());
        }
    }

    @Override
    public BaseResponse getCompaniesByLocation(String locationId) {
        try {
            List<Company> companies = companyRepo.search(cb -> {
                CriteriaQuery<Company> query = cb.createQuery(Company.class);
                Root<Company> root = query.from(Company.class);
                query.select(root).where(cb.equal(root.get("location").get("id"), Integer.parseInt(locationId)));
                return query;
            });

            return BaseResponse.of(MessageConstants.SUCCESS, companies, Translator.toLocale(MessageConstants.SUCCESS));

        } catch (Exception e) {
            throw new BadRequestException("Searching Companies by Location Failed!");
        }
    }
}
