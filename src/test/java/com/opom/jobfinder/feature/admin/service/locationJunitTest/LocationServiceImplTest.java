package com.opom.jobfinder.feature.admin.service.locationJunitTest;

import com.opom.jobfinder.feature.admin.location.dtos.GetCompanyByLocationDTO;
import com.opom.jobfinder.feature.admin.location.mapper.LocationManager;
import com.opom.jobfinder.feature.admin.location.service.impl.LocationServiceImpl;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.info.LocationRepo;
import com.opom.jobfinder.model.repo.job.JobRepo;
import com.opom.jobfinder.utility.Translator;
import com.opom.jobfinder.utility.exception.BadRequestException;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

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
    @Mock
    private LocationManager locationManager;

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

        Location result = locationService.save(sampleLocation);

        assertNotNull(result);
        Assertions.assertEquals(result.getName(), sampleLocation.getName());
        Assertions.assertEquals(result.getDescription(), sampleLocation.getDescription());
        Assertions.assertEquals(result.getCreatedAt(), sampleLocation.getCreatedAt());
        verify(locationRepo, times(1)).save(sampleLocation);
    }

    @Test
    void createLocation_exceptionThrown() {
        when(locationRepo.search(any())).thenReturn(List.of(sampleLocation));

        try {
            Location result = locationService.save(sampleLocation);
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getClass(),is("BadRequestException"));
            assertThat(e.getMessage(),is("Location already exist!"));
            verify(locationRepo, times(1)).findById(validId);
        }
    }


    @Test
    void getAllLocations() {
        List<Location> locations = Instancio.ofList(Location.class).size(3).create();
        when(locationRepo.findAll()).thenReturn(locations);

        List<Location> result = locationService.getAll();
        assertNotNull(result);
        Assertions.assertEquals(result, locations);
        Assertions.assertEquals(result.size(), locations.size());
        verify(locationRepo, times(1)).findAll();
    }

    @Test
    void updateLocation() {
        when(locationRepo.save(sampleLocation)).thenReturn(sampleLocation);
        when(locationRepo.findById(any())).thenReturn(Optional.ofNullable(sampleLocation));
        Location result = locationService.update(sampleLocation,validId);

        assertNotNull(result);
        Assertions.assertEquals(result.getName(), sampleLocation.getName());
        Assertions.assertEquals(result.getDescription(), sampleLocation.getDescription());
        Assertions.assertEquals(result.getCreatedAt(), sampleLocation.getCreatedAt());
        verify(locationRepo, times(1)).save(sampleLocation);
    }

    @Test
    void updateLocation_exceptionThrown() {
        try {
            Location result = locationService.update(sampleLocation,validId);
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(),is("Location not found!"));
            verify(locationRepo, times(1)).findById(validId);
        }
    }

    @Test
    void deleteLocation() {
        when(locationRepo.findById(validId)).thenReturn(Optional.ofNullable(sampleLocation));
        when(locationRepo.save(sampleLocation)).thenReturn(sampleLocation);
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        locationService.delete(String.valueOf(validId));
        verify(locationRepo, times(1)).findById(validId);
        verify(locationRepo, times(1)).save(sampleLocation);
    }

    @Test
    void deleteReview_exceptionThrown() {
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        try {
            locationService.delete(String.valueOf(validId));
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(),is("Location delete failed!"));
            verify(locationRepo, times(1)).findById(validId);
        }
    }

    @Test
    void getCompaniesByLocation() {
        List<Company> companies = Instancio.ofList(Company.class).size(3).create();
        when(companyRepo.search(any())).thenReturn(Arrays.asList(companies.toArray()));
        when(locationRepo.findById(any())).thenReturn(Optional.ofNullable(sampleLocation));

        List<GetCompanyByLocationDTO> result = locationService.getCompaniesByLocation(String.valueOf(validId));
        assertNotNull(result);
        Assertions.assertEquals(companies.size(), result.size());
        verify(companyRepo, times(1)).search(any());
        verify(locationRepo, times(1)).findById(any());
    }

    @Test
    void getCompaniesByLocation_exceptionThrown() {
        when(locationRepo.findById(any())).thenReturn(Optional.ofNullable(sampleLocation));

        try {
            List<GetCompanyByLocationDTO> result = locationService.getCompaniesByLocation(String.valueOf(validId));
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(),is("Location not found!"));
            verify(companyRepo, times(1)).search(any());
        }

    }

    @Test
    void getLocationById() {
        when(locationRepo.findById(validId)).thenReturn(Optional.of(sampleLocation));

        Location result = locationService.getLocationById(validId);
        assertNotNull(result);
        Assertions.assertEquals(result.getName(), sampleLocation.getName());
        Assertions.assertEquals(result.getDescription(), sampleLocation.getDescription());
        Assertions.assertEquals(result.getCreatedAt(), sampleLocation.getCreatedAt());
        verify(locationRepo, times(1)).findById(validId);
    }

    @Test
    void getLocationById_exceptionThrown() {
        try {
            Location result = locationService.getLocationById(validId);
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(),is("Location not found!"));
            verify(locationRepo, times(1)).findById(any());
        }
    }
}