package com.opom.jobfinder.feature.admin.controller;

import com.opom.jobfinder.feature.admin.service.LocationService;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.utility.BaseResponse;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "*")
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
}
