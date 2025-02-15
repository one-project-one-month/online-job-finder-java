package com.opom.jobfinder.feature.admin.service;

import com.opom.jobfinder.model.entity.location.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    Location createLocation(Location location);
    List<Location> getAllLocations();
    Optional<Location> getLocationById(String id);
    Location updateLocation(String id, Location locationDetails);
    void deleteLocation(String id);
    Location updateLocationByApplicatn(String id);
}
