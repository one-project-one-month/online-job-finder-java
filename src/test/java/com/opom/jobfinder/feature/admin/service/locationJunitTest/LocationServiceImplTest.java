package com.opom.jobfinder.feature.admin.service.locationJunitTest;

import com.opom.jobfinder.feature.admin.location.service.impl.LocationServiceImpl;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.job.JobRepo;
import com.opom.jobfinder.model.repo.location.LocationRepo;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.Translator;
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
import org.mockito.verification.VerificationMode;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(InstancioExtension.class)
class LocationServiceImplTest {
//
//    @Mock
//    private LocationRepo locationRepo;
//    @Mock
//    private JobRepo jobRepo;
//    @Mock
//    private CompanyRepo companyRepo;
//
//    private static MockedStatic<Translator> mockedStatic;
//
//    private static int validId;
//
//    @InjectMocks
//    private LocationServiceImpl locationService;
//    private Location sampleLocation;
//
//    @BeforeAll
//    static void setUpAll() {
//        mockedStatic = mockStatic(Translator.class);
//        validId = 1;
//    }
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        sampleLocation = Instancio.create(Location.class);
//    }
//
//    @AfterEach
//    void tearDown() {
//
//    }
//
//    @Test
//    void createLocation() {
//        when(locationRepo.save(sampleLocation)).thenReturn(sampleLocation);
//        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.save(sampleLocation);
//        assertNotNull(result);
//        assertThat(result.errorCode(), is("00000"));
//        assertThat(result.data(), is(sampleLocation));
//        assertThat(result.message(), is("mocked message"));
//        verify(locationRepo, times(1)).save(sampleLocation);
//    }
//
//    @Test
//    void createLocation_exceptionThrown() {
//        when(locationRepo.search(any())).thenReturn(List.of(sampleLocation));
//        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.save(sampleLocation);
//        assertNotNull(result);
//        assertThat(result.errorCode(), is("00400"));
//        assertThat(result.data(), is("Location Already Exist!"));
//        assertThat(result.message(), is("mocked message"));
//        verify(locationRepo, times(0)).save(sampleLocation);
//    }
//
//
//    @Test
//    void getAllLocations() {
//        List<Location> locations = Instancio.ofList(Location.class).size(3).create();
//        when(locationRepo.findAll()).thenReturn(locations);
//
//        mockedStatic.when(()->Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.getAll();
//        assertNotNull(result);
//        assertThat(result.errorCode(), is("00000"));
//        assertThat(result.data(), is(locations));
//        assertThat(result.message(), is("mocked message"));
//        verify(locationRepo, times(1)).findAll();
//    }
//
//    @Test
//    void updateLocation() {
//        when(locationRepo.save(sampleLocation)).thenReturn(sampleLocation);
//        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
//        when(locationRepo.findById(any())).thenReturn(Optional.ofNullable(sampleLocation));
//        BaseResponse result = locationService.update(sampleLocation,validId);
//
//        assertNotNull(result);
//        assertThat(result.errorCode(), is("00000"));
//        assertThat(result.data(), is(sampleLocation));
//        assertThat(result.message(), is("mocked message"));
//        verify(locationRepo, times(1)).save(sampleLocation);
//    }
//
//
//    @Test
//    void updateLocation_exceptionThrown() {
//        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.update(sampleLocation,validId);
//        assertThat(result.data(), is("Location Not Found!"));
//        assertThat(result.errorCode(), is("00400"));
//        assertThat(result.message(), is("mocked message"));
//        verify(locationRepo, times(0)).save(sampleLocation);
//    }
//
//    @Test
//    void deleteLocation() {
//        when(locationRepo.findById(validId)).thenReturn(Optional.ofNullable(sampleLocation));
//        when(locationRepo.save(sampleLocation)).thenReturn(sampleLocation);
//        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.delete(String.valueOf(validId));
//        assertNotNull(result);
//        assertThat(result.message(), is("mocked message"));
//        assertThat(result.data(), is("Delete Location Successfully!"));
//        assertThat(result.errorCode(), is("00000"));
//        verify(locationRepo, times(1)).findById(validId);
//        verify(locationRepo, times(1)).save(sampleLocation);
//    }
//
//    @Test
//    void deleteReview_exceptionThrown() {
//        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.delete(String.valueOf(validId));
//        assertNotNull(result);
//        assertThat(result.message(), is("mocked message"));
//        assertThat(result.data(), is("Location Not Found!"));
//        assertThat(result.errorCode(), is("00400"));
//        verify(locationRepo, times(1)).findById(validId);
//        verify(locationRepo, times(0)).save(sampleLocation);
//    }
//
//    @Test
//    void getApplicantsByLocationId() {
//    }
//
//    @Test
//    void getJobsByLocation() {
//        List<Job> jobs = Instancio.ofList(Job.class).size(3).create();
//        when(locationRepo.findById(validId)).thenReturn(Optional.ofNullable(sampleLocation));
//        when(jobRepo.search(any())).thenReturn(Arrays.asList(jobs.toArray()));
//
//        mockedStatic.when(()->Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.getJobsByLocation(String.valueOf(validId));
//        assertNotNull(result);
//        assertThat(result.errorCode(), is("00000"));
//        assertThat(result.data(), is(jobs));
//        assertThat(result.message(), is("mocked message"));
//        verify(jobRepo, times(1)).search(any());
//    }
//
//    @Test
//    void getJobsByLocation_exceptionThrown() {
//        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.getJobsByLocation(String.valueOf(validId));
//        assertNotNull(result);
//        assertThat(result.errorCode(), is("00400"));
//        assertThat(result.data(), is("Location Not Found!"));
//        assertThat(result.message(), is("mocked message"));
//        verify(locationRepo, times(0)).search(any());
//    }
//
//    @Test
//    void getCompaniesByLocation() {
//        List<Company> companies = Instancio.ofList(Company.class).size(3).create();
//        when(companyRepo.search(any())).thenReturn(Arrays.asList(companies.toArray()));
//        when(locationRepo.findById(any())).thenReturn(Optional.ofNullable(sampleLocation));
//        mockedStatic.when(()->Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.getCompaniesByLocation(String.valueOf(validId));
//        assertNotNull(result);
//        assertThat(result.errorCode(), is("00000"));
//        assertThat(result.data(), is(companies));
//        assertThat(result.message(), is("mocked message"));
//        verify(companyRepo, times(1)).search(any());
//    }
//
//    @Test
//    void getCompaniesByLocation_exceptionThrown() {
//        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.getCompaniesByLocation(String.valueOf(validId));
//        assertNotNull(result);
//        assertThat(result.errorCode(), is("00400"));
//        assertThat(result.data(), is("Location Not Found!"));
//        assertThat(result.message(), is("mocked message"));
//        verify(locationRepo, times(0)).search(any());
//    }
//
//    @Test
//    void getLocationById() {
//        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
//        when(locationRepo.findById(validId)).thenReturn(Optional.of(sampleLocation));
//
//        BaseResponse result = locationService.getLocationById(validId);
//        assertNotNull(result);
//        assertThat(result.errorCode(), is("00000"));
//        assertThat(result.data(), is(sampleLocation));
//        assertThat(result.message(), is("mocked message"));
//        verify(locationRepo, times(1)).findById(validId);
//    }
//
//    @Test
//    void getLocationById_exceptionThrown() {
//        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
//
//        BaseResponse result = locationService.getLocationById(validId);
//        assertNotNull(result);
//        assertThat(result.errorCode(), is("00400"));
//        assertThat(result.data(), is("Location Not Found!"));
//        assertThat(result.message(), is("mocked message"));
//        verify(locationRepo, times(1)).findById(validId);
//    }
}