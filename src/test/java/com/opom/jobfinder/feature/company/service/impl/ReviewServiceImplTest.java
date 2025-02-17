package com.opom.jobfinder.feature.company.service.impl;

import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.review.ReviewRepo;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
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
import static org.mockito.ArgumentMatchers.isA;
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
        validId = "b90a273b-870c-4533-a661-0962ab8ecdf1";
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

        BaseResponse result = reviewService.addReview(sampleReview,String.valueOf(validId));
        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.message(),is("mocked message"));
        assertThat(result.data(), is(sampleReview));
        verify(reviewRepo, times(1)).save(sampleReview);
        verify(companyRepo, times(1)).findById(UUID.fromString(validId));

    }

    @Test
    void updateReview() {
        when(reviewRepo.save(sampleReview)).thenReturn(sampleReview);
        when(companyRepo.findById(UUID.fromString(validId))).thenReturn(Optional.ofNullable(sampleCompany));
        String reviewId = String.valueOf(sampleReview.getId());
        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.addReview(sampleReview,String.valueOf(validId));
        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.message(),is("mocked message"));
        assertThat(result.data(), is(sampleReview));
        verify(reviewRepo, times(1)).save(sampleReview);
        verify(companyRepo, times(1)).findById(UUID.fromString(validId));
    }

    @Test
    void deleteReview() {
        doNothing().when(reviewRepo).deleteById(UUID.fromString(validId));

        reviewRepo.deleteById(UUID.fromString(validId));

        verify(reviewRepo, times(1)).deleteById(UUID.fromString(validId));
    }

    @Test
    void getReviewsByCompany() {
        List<Review> reviews = Instancio.ofList(Review.class).size(5).create();
        when(reviewRepo.search(any())).thenReturn(Arrays.asList(reviews.toArray()));
        UUID companyId =  sampleCompany.getId();

        mockedStatic.when(() -> Translator.toLocale(anyString())).thenReturn("mocked message");

        BaseResponse result = reviewService.getReviewsByCompany(companyId.toString());
        assertNotNull(result);
        assertThat(result.errorCode(), is("00000"));
        assertThat(result.data(), is(reviews));
        assertThat(result.message(), is("mocked message"));
        verify(reviewRepo, times(1)).search(any());
    }

    @Test
    void getAverageReviewFromCompany() {
        List<Double> mockRatings = List.of(4.5, 3.5, 5.0);
        when(reviewRepo.search(any())).thenReturn((List) mockRatings);

        BaseResponse result = reviewService.getAverageReviewFromCompany(validId);

        double expectedAverage = mockRatings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        expectedAverage = Math.round(expectedAverage * 100.0) / 100.0;

        List<?> actualData = reviewRepo.search(any());
        System.out.println("Mocked Repo Data: " + actualData);

        assertNotNull(result);
        assertThat(result.errorCode(), is("00000") );
        assertEquals(expectedAverage, (double) result.data(),0.0001);

        verify(reviewRepo, times(1)).search(any());
    }
}