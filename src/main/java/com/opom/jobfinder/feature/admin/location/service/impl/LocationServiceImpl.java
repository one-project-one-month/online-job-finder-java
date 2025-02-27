package com.opom.jobfinder.feature.admin.location.service.impl;

import com.opom.jobfinder.feature.admin.location.dtos.GetCompanyByLocationDTO;
import com.opom.jobfinder.feature.admin.location.dtos.GetJobByLocationDTO;
import com.opom.jobfinder.feature.admin.location.dtos.LocationDTO;
import com.opom.jobfinder.feature.admin.location.mapper.LocationManager;
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
import com.opom.jobfinder.utility.exception.BadRequestException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LocationServiceImpl extends BaseService implements LocationService {

    // CONSTANT VALUES
    private final LocationRepo locationRepo;
    private final CompanyRepo companyRepo;
    private final JobRepo jobRepo;
    private final LocationManager locationManager;

    @Override
    public Location save(Location location) {

        locationRepo.findByName(location.getName())
                        .ifPresent(existingLocation -> {
                            throw new BadRequestException("Location already exist!");
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
            locationDetails.setCreatedAt(originLocation.get().getCreatedAt());
            return locationRepo.save(locationDetails);
        } else {
            throw new BadRequestException("Location not found!");
        }
    }

    @Override
    public void delete(String id) {
        Optional<Location> originLocation = locationRepo.findById(Integer.valueOf(id));
        if(originLocation.isPresent()) {
            originLocation.get().setStatus(false);
            locationRepo.save(originLocation.get());
        } else {
            throw new BadRequestException("Location delete failed!");
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
            List<Job> jobs = jobRepo.search(cb -> {
                CriteriaQuery<Job> query = cb.createQuery(Job.class);
                Root<Job> root = query.from(Job.class);
                query.select(root).where(cb.equal(root.get("location").get("id"), locationId));
                return query;
            });
            List<GetJobByLocationDTO> jobByLocationDTOS = new ArrayList<>();
            for (Job job : jobs) {
                jobByLocationDTOS.add(locationManager.toGetJobByLocationDTO(job));
            }

            return jobByLocationDTOS;
        } else {
            throw new BadRequestException("Location not found!");
        }
    }

    @Override
    public List<GetCompanyByLocationDTO> getCompaniesByLocation(String locationId) {
        Optional<Location> location = locationRepo.findById(Integer.valueOf(locationId));
        if(location.isPresent()) {
            List<Company> companies = companyRepo.search(cb -> {
                CriteriaQuery<Company> query = cb.createQuery(Company.class);
                Root<Company> root = query.from(Company.class);
                query.select(root).where(cb.equal(root.get("location").get("id"), Integer.parseInt(locationId)));
                return query;
            });
            List<GetCompanyByLocationDTO> companyByLocationDTOS = new ArrayList<>();
            for (Company company : companies) {
                companyByLocationDTOS.add(locationManager.toGetCompanyByLocation(company));
            }

            return companyByLocationDTOS;
        } else {
            throw new BadRequestException("Location not found!");
        }
    }

    @Override
    public Location getLocationById(int id) {
        Optional<Location> optionalLocation = locationRepo.findById(id);
        if(optionalLocation.isPresent()) {
            return optionalLocation.get();
        }else {
            throw new BadRequestException("Location not found!");
        }
    }

    public Location mapLocationDTOToEntity(LocationDTO locationDTO) {
        Location location = new Location();

        location.setName(locationDTO.name());
        location.setDescription(locationDTO.description());
        location.setStatus(true);
        return location;
    }
}
