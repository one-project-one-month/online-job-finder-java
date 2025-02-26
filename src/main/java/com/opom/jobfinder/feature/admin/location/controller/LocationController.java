package com.opom.jobfinder.feature.admin.location.controller;

import com.opom.jobfinder.feature.admin.location.dtos.GetCompanyByLocationDTO;
import com.opom.jobfinder.feature.admin.location.dtos.GetJobByLocationDTO;
import com.opom.jobfinder.feature.admin.location.dtos.LocationDTO;
import com.opom.jobfinder.feature.admin.location.service.LocationService;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class LocationController {

    // CONSTANT VALUES
    private final LocationService locationService;

    // CONSTRUCTOR
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/locations")
    public ResponseEntity<BaseResponse> addLocation(@RequestBody LocationDTO locationDTO ) {
        Location location = locationService.mapLocationDTOToEntity(locationDTO);
        Location response = locationService.save(location);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, response, Translator.toLocale(MessageConstants.SUCCESS)));
    }

    @GetMapping("/locations")
    public ResponseEntity<BaseResponse> getAllLocations() {
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, locationService.getAll(), Translator.toLocale(MessageConstants.SUCCESS)));
    }

    @PutMapping("/locations")
    public ResponseEntity<BaseResponse> updateLocationById(@RequestBody LocationDTO locationDTO,@RequestParam("id") @NotNull int id) {
        Location location = locationService.mapLocationDTOToEntity(locationDTO);
        Location response = locationService.update(location,id);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, response, Translator.toLocale(MessageConstants.SUCCESS)));

    }

    @DeleteMapping("/locations")
    public ResponseEntity<BaseResponse> deleteLocationsById(@RequestParam("id") @NotNull String id) {
         locationService.delete(id);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, "Delete Location Successfully!", Translator.toLocale(MessageConstants.SUCCESS)));
    }

    @GetMapping("/locations/{id}/jobs")
    public ResponseEntity<BaseResponse> getJobsByLocations(@PathVariable("id") @NotNull String id) {
        List<GetJobByLocationDTO> response = locationService.getJobsByLocation(id);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, response, Translator.toLocale(MessageConstants.SUCCESS)));
    }

    @GetMapping("/locations/{id}/companies")
    public ResponseEntity<BaseResponse> getCompaniesByLocations(@PathVariable("id") @NotNull String id) {
        List<GetCompanyByLocationDTO> response = locationService.getCompaniesByLocation(id);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, response, Translator.toLocale(MessageConstants.SUCCESS)));

    }

}
