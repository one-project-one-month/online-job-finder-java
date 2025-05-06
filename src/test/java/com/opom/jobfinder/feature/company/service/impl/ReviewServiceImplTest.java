package com.opom.jobfinder.feature.company.service.impl;

import com.opom.jobfinder.feature.company.review.dtos.ReviewByCompanyDTO;
import com.opom.jobfinder.feature.company.review.mapper.ReviewManager;
import com.opom.jobfinder.feature.company.review.service.impl.ReviewServiceImpl;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.review.ReviewRepo;
import com.opom.jobfinder.utility.Translator;
import com.opom.jobfinder.utility.exception.BadRequestException;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(InstancioExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepo reviewRepo;
    @Mock
    private CompanyRepo companyRepo;
    @Mock
    private ReviewManager  reviewManager;

    private static MockedStatic<Translator> mockedStatic;

    private static String validId;

    @InjectMocks
    private ReviewServiceImpl reviewService;
    private Review sampleReview;
    private Company sampleCompany;

    @BeforeAll
    static void setUpAll(){
        mockedStatic = mockStatic(Translator.class);
        validId = "6bb5a9c9-4d02-4704-b254-c5d7bb6d6257";
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleReview = Instancio.create(Review.class);
        sampleCompany = Instancio.create(Company.class);
    }

    @Test
    void addReview() {
        when(reviewRepo.save(sampleReview)).thenReturn(sampleReview);
        when(companyRepo.findById(UUID.fromString(validId))).thenReturn(Optional.ofNullable(sampleCompany));

        Review result = reviewService.save(sampleReview,String.valueOf(validId));
        Assertions.assertEquals(result.getComment(), sampleReview.getComment());
        Assertions.assertEquals(result.getRating(), sampleReview.getRating());
        Assertions.assertEquals(result.getCreatedAt(), sampleReview.getCreatedAt());
        verify(reviewRepo, times(1)).save(sampleReview);
        verify(companyRepo, times(1)).findById(UUID.fromString(validId));
    }

    @Test
    void addReview_exceptionThrown() {
        try {
            reviewService.save(sampleReview,validId);
        }catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(),is("Company Not Found!"));
            verify(companyRepo, times(1)).findById(any());
        }
    }

    @Test
    void addReview_exceptionThrown_2() {
        try {
            reviewService.save(sampleReview,"23556-ee16-11ef-8daa-325096b39f47");
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(), is("Company Id is not valid!"));
            verify(reviewRepo, times(0)).save(sampleReview);
        }
    }

    @Test
    void updateReview() {
        when(reviewRepo.save(sampleReview)).thenReturn(sampleReview);
        when(companyRepo.findById(any())).thenReturn(Optional.ofNullable(sampleCompany));
        when(reviewRepo.findById(any())).thenReturn(Optional.ofNullable(sampleReview));

        Review result = reviewService.update(sampleReview,String.valueOf(validId));
        assertNotNull(result);
        Assertions.assertEquals(result.getComment(), sampleReview.getComment());
        Assertions.assertEquals(result.getRating(), sampleReview.getRating());
        Assertions.assertEquals(result.getCreatedAt(), sampleReview.getCreatedAt());
        verify(reviewRepo, times(1)).save(sampleReview);
        verify(reviewRepo, times(1)).findById(any());
        verify(companyRepo, times(1)).findById(UUID.fromString(validId));
    }

    @Test
    void updateReview_exceptionThrown() {
        try {
            reviewService.update(sampleReview,validId);
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(),is("Company Not Found!"));
            verify(companyRepo, times(1)).findById(any());
        }
    }

    @Test
    void updateReview_exceptionThrown_2() {
        try {
            reviewService.update(sampleReview,"23556-ee16-11ef-8daa-325096b39f47");
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(),is("Company Id is not valid!"));
            verify(reviewRepo, times(0)).save(sampleReview);
        }
    }
    @Test
    void deleteReview() {
        when(reviewRepo.findById(UUID.fromString(validId))).thenReturn(Optional.ofNullable(sampleReview));
        when(reviewRepo.save(sampleReview)).thenReturn(sampleReview);
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        reviewService.delete(validId);

        verify(reviewRepo, times(1)).findById(UUID.fromString(validId));
        verify(reviewRepo, times(1)).save(sampleReview);
    }

    @Test
    void deleteReview_exceptionThrown() {
        try {
            reviewService.delete(validId);
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(),is("Review Not Found!"));
            verify(reviewRepo, times(1)).findById(UUID.fromString(validId));
            verify(reviewRepo, times(0)).save(sampleReview);
        }
    }

    @Test
    void getReviewsByCompany() {
        List<Review> reviews = Instancio.ofList(Review.class).size(5).create();
        when(companyRepo.findById(any())).thenReturn(Optional.ofNullable(sampleCompany));
        when(reviewRepo.findByCompanyId(any())).thenReturn(reviews);
        UUID companyId =  sampleCompany.getId();

        List<ReviewByCompanyDTO> result = reviewService.getByCompany(companyId.toString());
        assertNotNull(result);
        Assertions.assertEquals(reviews.size(), result.size());
        verify(reviewRepo, times(1)).findByCompanyId(any());
    }

    @Test
    void getReviewsByCompany_exceptionThrown() {
        UUID companyId =  sampleCompany.getId();
        List<Review> reviews = Instancio.ofList(Review.class).size(5).create();
        when(companyRepo.search(any())).thenReturn(Arrays.asList(reviews.toArray()));

        try {
            reviewService.getByCompany(companyId.toString());
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(),is("Company Not Found!"));
            verify(companyRepo, times(1)).findById(any());
            verify(reviewRepo, times(0)).findByCompanyId(any());
        }
    }

    @Test
    void getAverageReviewFromCompany() {
        when(reviewRepo.search(any())).thenReturn(List.of(4.5));
        when(companyRepo.findById(any())).thenReturn(Optional.ofNullable(sampleCompany));
        Double response = reviewService.getAvgFromCompany(validId);

        assertNotNull(response);
        Assertions.assertEquals(4.5,response);
        verify(reviewRepo, times(1)).search(any());
    }

    @Test
    void getAverageReviewFromCompany_exceptionThrown() {
        UUID companyId =  sampleCompany.getId();
        try {
            reviewService.getAvgFromCompany(companyId.toString());
        } catch (Exception e) {
            Assertions.assertEquals(BadRequestException.class, e.getClass());
            assertThat(e.getMessage(),is("Company Not Found!"));
            verify(companyRepo, times(1)).findById(any());
            verify(reviewRepo, times(0)).search(any());
        }
    }
}