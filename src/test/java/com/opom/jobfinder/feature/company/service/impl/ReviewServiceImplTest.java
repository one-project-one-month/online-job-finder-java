package com.opom.jobfinder.feature.company.service.impl;

import com.opom.jobfinder.feature.company.review.service.impl.ReviewServiceImpl;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.review.ReviewRepo;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.Translator;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
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
        String reviewId = String.valueOf(sampleReview.getId());
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.save(sampleReview,String.valueOf(validId));
        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.message(),is("mocked message"));
        assertThat(result.data(), is(sampleReview));
        verify(reviewRepo, times(1)).save(sampleReview);
        verify(companyRepo, times(1)).findById(UUID.fromString(validId));
    }

    @Test
    void addReview_exceptionThrown() {
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
        System.out.println(validId);
        BaseResponse result = reviewService.save(sampleReview,validId);
        assertThat(result.data(), is("Company Not Found!"));
        assertThat(result.errorCode(), is("00400"));
        assertThat(result.message(), is("mocked message"));
        verify(reviewRepo, times(0)).save(sampleReview);
    }

    @Test
    void addReview_exceptionThrown_2() {
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
        BaseResponse result = reviewService.save(sampleReview,"23556-ee16-11ef-8daa-325096b39f47");
        assertThat(result.data(), is("Company Id is not valid!"));
        assertThat(result.errorCode(), is("00400"));
        assertThat(result.message(), is("mocked message"));
        verify(reviewRepo, times(0)).save(sampleReview);
    }

    @Test
    void updateReview() {
        when(reviewRepo.save(sampleReview)).thenReturn(sampleReview);
        when(companyRepo.findById(any())).thenReturn(Optional.ofNullable(sampleCompany));
        when(reviewRepo.findById(any())).thenReturn(Optional.ofNullable(sampleReview));
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.update(sampleReview,String.valueOf(validId));
        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.message(),is("mocked message"));
        assertThat(result.data(), is(sampleReview));
        verify(reviewRepo, times(1)).save(sampleReview);
        verify(companyRepo, times(1)).findById(UUID.fromString(validId));
    }

    @Test
    void updateReview_exceptionThrown() {
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.update(sampleReview,validId);
        assertThat(result.data(), is("Company Not Found!"));
        assertThat(result.errorCode(), is("00400"));
        assertThat(result.message(), is("mocked message"));
        verify(reviewRepo, times(0)).save(sampleReview);
    }

    @Test
    void updateReview_exceptionThrown_2() {
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.update(sampleReview,"23556-ee16-11ef-8daa-325096b39f47");
        assertThat(result.data(), is("Company Id is not valid!"));
        assertThat(result.errorCode(), is("00400"));
        assertThat(result.message(), is("mocked message"));
        verify(reviewRepo, times(0)).save(sampleReview);
    }
    @Test
    void deleteReview() {
        when(reviewRepo.findById(UUID.fromString(validId))).thenReturn(Optional.ofNullable(sampleReview));
        when(reviewRepo.save(sampleReview)).thenReturn(sampleReview);
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.delete(validId);
        assertNotNull(result);
        assertThat(result.message(), is("mocked message"));
        assertThat(result.data(), is("Deleted Review Successfully!"));
        assertThat(result.errorCode(), is("00000"));
        verify(reviewRepo, times(1)).findById(UUID.fromString(validId));
        verify(reviewRepo, times(1)).save(sampleReview);
    }

    @Test
    void deleteReview_exceptionThrown() {
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.delete(validId);
        assertNotNull(result);
        assertThat(result.message(), is("mocked message"));
        assertThat(result.data(), is("Review Not Found!"));
        assertThat(result.errorCode(), is("00400"));
        verify(reviewRepo, times(1)).findById(UUID.fromString(validId));
        verify(reviewRepo, times(0)).save(sampleReview);
    }

    @Test
    void getReviewsByCompany() {
        List<Review> reviews = Instancio.ofList(Review.class).size(5).create();
        when(companyRepo.findById(any())).thenReturn(Optional.ofNullable(sampleCompany));
        when(reviewRepo.search(any())).thenReturn(Arrays.asList(reviews.toArray()));
        UUID companyId =  sampleCompany.getId();

        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.getByCompany(companyId.toString());
        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.data(), is(reviews));
        assertThat(result.message(), is("mocked message"));
        verify(reviewRepo, times(1)).search(any());
    }

    @Test
    void getReviewsByCompany_exceptionThrown() {
        UUID companyId =  sampleCompany.getId();
        List<Review> reviews = Instancio.ofList(Review.class).size(5).create();
        when(companyRepo.search(any())).thenReturn(Arrays.asList(reviews.toArray()));
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.getByCompany(companyId.toString());
        assertNotNull(result);
        assertThat(result.errorCode(), is("00400"));
        assertThat(result.data(), is("Company Not Found!"));
        assertThat(result.message(), is("mocked message"));
        verify(reviewRepo, times(0)).search(any());
    }

    @Test
    void getAverageReviewFromCompany() {
        when(reviewRepo.search(any())).thenReturn(List.of(4.5));
        when(companyRepo.findById(any())).thenReturn(Optional.ofNullable(sampleCompany));
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");
        BaseResponse response = reviewService.getAvgFromCompany(validId);

        assertNotNull(response);
        assertThat(response.errorCode(), is("00000"));
        assertThat(response.data(),is(4.5));
        assertThat(response.message(), is("mocked message"));
        verify(reviewRepo, times(1)).search(any());
    }

    @Test
    void getAverageReviewFromCompany_exceptionThrown() {
        UUID companyId =  sampleCompany.getId();

        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.getAvgFromCompany(companyId.toString());
        assertNotNull(result);
        assertThat(result.errorCode(), is("00400"));
        assertThat(result.data(), is("Company Not Found!"));
        assertThat(result.message(), is("mocked message"));
        verify(reviewRepo, times(0)).search(any());
    }
}