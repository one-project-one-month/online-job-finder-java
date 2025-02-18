package com.opom.jobfinder.feature.admin.location.controller;

import com.opom.jobfinder.feature.admin.location.service.LocationService;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.utility.BaseResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BaseResponse> addLocation(@RequestBody Location location ) {
        BaseResponse response = locationService.save(location);
        if(response.errorCode().equals("00000")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/locations")
    public ResponseEntity<BaseResponse> getAllLocations() {
        return ResponseEntity.ok(locationService.getAll());
    }

    @PutMapping("/locations")
    public ResponseEntity<BaseResponse> updateLocationById(@RequestBody Location location) {
        BaseResponse response = locationService.update(location);
        if(response.errorCode().equals("00000")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/locations")
    public ResponseEntity<BaseResponse> deleteLocationsById(@RequestParam("id") @NotNull String id) {
        BaseResponse response = locationService.delete(id);
        if(response.errorCode().equals("00000")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/locations/{id}/jobs")
    public ResponseEntity<BaseResponse> getJobsByLocations(@PathVariable("id") @NotNull String id) {
        BaseResponse response = locationService.getJobsByLocation(id);
        if(response.errorCode().equals("00000")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/locations/{id}/companies")
    public ResponseEntity<BaseResponse> getCompaniesByLocations(@PathVariable("id") @NotNull String id) {
        BaseResponse response = locationService.getCompaniesByLocation(id);
        if(response.errorCode().equals("00000")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

}
