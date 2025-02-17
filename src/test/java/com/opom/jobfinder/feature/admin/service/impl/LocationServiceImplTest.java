package com.opom.jobfinder.feature.admin.service.impl;

import com.opom.jobfinder.feature.admin.service.LocationService;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.job.JobRepo;
import com.opom.jobfinder.model.repo.location.LocationRepo;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.Translator;
import com.opom.jobfinder.utility.exception.BadRequestException;
import com.opom.jobfinder.utility.exception.UnexpectedException;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(InstancioExtension.class)
class LocationServiceImplTest {

    @Mock
    private LocationRepo locationRepo;
    @Mock
    private JobRepo jobRepo;
    @Mock
    private CompanyRepo companyRepo;

    private static MockedStatic<Translator> mockedStatic;

    private static int validId;

    @InjectMocks
    private LocationServiceImpl locationService;
    private Location sampleLocation;

    @BeforeAll
    static void setUpAll() {
        mockedStatic = mockStatic(Translator.class);
        validId = 1;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleLocation = Instancio.create(Location.class);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void createLocation() {
        when(locationRepo.save(sampleLocation)).thenReturn(sampleLocation);

        String locationId = String.valueOf(sampleLocation.getId());
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = locationService.createLocation(sampleLocation);
        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.data(), is(sampleLocation));
        assertThat(result.message(), is("mocked message"));
        verify(locationRepo, times(1)).save(sampleLocation);
    }

    @Test
    void createLocation_fail_1() {
        when(locationRepo.save(sampleLocation)).thenThrow(new RuntimeException("Database error"));

        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("Error occurred");

        assertThrows(RuntimeException.class, () -> {locationService.createLocation(sampleLocation);});
    }


    @Test
    void getAllLocations() {
        List<Location> locations = Instancio.ofList(Location.class).size(3).create();
        when(locationRepo.search(any())).thenReturn(Arrays.asList(locations.toArray()));

        mockedStatic.when(()->Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = locationService.getAllLocations();
        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.data(), is(locations));
        assertThat(result.message(), is("mocked message"));
        verify(locationRepo, times(1)).search(any());
    }

    @Test
    void getLocationById() {
        when(locationRepo.findById(sampleLocation.getId())).thenReturn(Optional.of(sampleLocation));
        String locationId = String.valueOf(sampleLocation.getId());
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = locationService.getLocationById(locationId);

        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.data(), is(Optional.of(sampleLocation)));
        assertThat(result.message(), is("mocked message"));
        verify(locationRepo, times(1)).findById(sampleLocation.getId());
    }


    @Test
    void updateLocation() {
        when(locationRepo.save(sampleLocation)).thenReturn(sampleLocation);
        String locationId = String.valueOf(sampleLocation.getId());
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = locationService.updateLocation(sampleLocation);

        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.data(), is(sampleLocation));
        assertThat(result.message(), is("mocked message"));
        verify(locationRepo, times(1)).save(sampleLocation);
    }

    @Test
    void deleteLocation() {
        doNothing().when(locationRepo).deleteById(validId);

        locationService.deleteLocation(String.valueOf(validId));

        verify(locationRepo, times(1)).deleteById(validId);
    }

    @Test
    void getApplicantsByLocationId() {
    }

    @Test
    void getJobsByLocation() {
        List<Job> jobs = Instancio.ofList(Job.class).size(3).create();
        when(jobRepo.search(any())).thenReturn(Arrays.asList(jobs.toArray()));
        String locationId = String.valueOf(sampleLocation.getId());

        mockedStatic.when(()->Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = locationService.getJobsByLocation(locationId);
        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.data(), is(jobs));
        assertThat(result.message(), is("mocked message"));
        verify(jobRepo, times(1)).search(any());
    }

    @Test
    void getCompaniesByLocation() {
        List<Company> companies = Instancio.ofList(Company.class).size(3).create();
        when(companyRepo.search(any())).thenReturn(Arrays.asList(companies.toArray()));
        String locationId = String.valueOf(sampleLocation.getId());

        mockedStatic.when(()->Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = locationService.getCompaniesByLocation(locationId);
        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.data(), is(companies));
        assertThat(result.message(), is("mocked message"));
        verify(companyRepo, times(1)).search(any());
    }
}