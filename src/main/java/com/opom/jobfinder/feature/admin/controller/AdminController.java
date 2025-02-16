package com.opom.jobfinder.feature.admin.controller;

import com.opom.jobfinder.feature.admin.service.LocationService;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.utility.BaseResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    // CONSTANT VALUES
    private final LocationService locationService;

    // CONSTRUCTOR
    public AdminController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/locations")
    public ResponseEntity<BaseResponse> addLocation(@RequestBody Location location ) {
        return ResponseEntity.ok(locationService.createLocation(location));
    }

    @GetMapping("/locations")
    public ResponseEntity<BaseResponse> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @PutMapping("/locations")
    public ResponseEntity<BaseResponse> updateLocationById(@RequestBody Location location) {
        return ResponseEntity.ok(locationService.updateLocation(location));
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<BaseResponse> getLocationsById(@PathVariable("id") @NotNull String id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @DeleteMapping("/locations")
    public ResponseEntity<BaseResponse> deleteLocationsById(@RequestParam("id") @NotNull String id) {
        return ResponseEntity.ok(locationService.deleteLocation(id));
    }

    @GetMapping("/locations/{id}/jobs")
    public ResponseEntity<BaseResponse> getJobsByLocations(@PathVariable("id") @NotNull String id) {
        return ResponseEntity.ok(locationService.getJobsByLocation(id));
    }

    @GetMapping("/locations/{id}/companies")
    public ResponseEntity<BaseResponse> getCompaniesByLocations(@PathVariable("id") @NotNull String id) {
        return ResponseEntity.ok(locationService.getCompaniesByLocation(id));
    }

}
