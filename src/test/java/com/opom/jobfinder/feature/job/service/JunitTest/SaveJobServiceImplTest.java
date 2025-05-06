package com.opom.jobfinder.feature.job.service.JunitTest;

import com.opom.jobfinder.feature.auth.service.AuthService;
import com.opom.jobfinder.feature.job.save.service.impl.SaveJobServiceImpl;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.applicant.SavedJob;
import com.opom.jobfinder.model.repo.applicant.ApplicantRepo;
import com.opom.jobfinder.model.repo.job.JobRepo;
import com.opom.jobfinder.model.repo.job.SaveJobRepo;
import com.opom.jobfinder.utility.exception.BadRequestException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveJobServiceImplTest {

    @InjectMocks
    private SaveJobServiceImpl saveJobServiceImpl;

    @Mock
    private JobRepo jobRepo;
    @Mock
    private SaveJobRepo saveJobRepo;
    @Mock
    private AuthService authService;
    @Mock
    private ApplicantRepo applicantRepo;

    @Test
    public void saveJobByApplicant() {
        SavedJob savedJobMockData = Instancio.create(SavedJob.class);
        when(saveJobRepo.save(any())).thenReturn(savedJobMockData);
        when(jobRepo.findById(any())).thenReturn(Optional.ofNullable(savedJobMockData.getJob()));
        when(authService.getLoginUserId()).thenReturn(savedJobMockData.getApplicant().getId());
        when(applicantRepo.findById(any())).thenReturn(Optional.ofNullable(savedJobMockData.getApplicant()));

        String response = saveJobServiceImpl.save(UUID.randomUUID());
        assertThat("Saved Job Successfully!", is(response));
    }

    @Test
    public void saveJobByApplicant_fail() {
        SavedJob savedJobMockData = Instancio.create(SavedJob.class);
        when(saveJobRepo.save(any())).thenReturn(savedJobMockData);
        when(jobRepo.findById(any())).thenReturn(Optional.ofNullable(savedJobMockData.getJob()));
        when(authService.getLoginUserId()).thenReturn(savedJobMockData.getApplicant().getId());
        when(applicantRepo.findById(any())).thenReturn(Optional.ofNullable(savedJobMockData.getApplicant()));

        try{
            saveJobServiceImpl.save(UUID.randomUUID());
        } catch (BadRequestException e) {
            Assertions.assertEquals(e.getClass(), BadRequestException.class);
            assertThat("Applicant Not Found!" ,is(e.getMessage()));
        }
    }

    @Test
    public void getJobsByApplicant() {
        List<SavedJob> savedJobs = Instancio.ofList(SavedJob.class).size(5).create();
        when(authService.getLoginUserId()).thenReturn(savedJobs.getFirst().getId().getApplicantId());
        when(applicantRepo.findById(any())).thenReturn(Optional.of(savedJobs.getFirst().getApplicant()));
        when(saveJobRepo.findByApplicantOrderByCreatedAtDesc(any())).thenReturn(savedJobs);

        List<SavedJob> savedJobsByApplicant = saveJobServiceImpl.getSaveJobsByApplicant();
        Assertions.assertEquals(savedJobs.getFirst().getApplicant(), savedJobsByApplicant.getFirst().getApplicant());
        Assertions.assertEquals(savedJobs.getClass(), ArrayList.class);
    }

    @Test
    public void getJobByApplicant_fail() {
        List<SavedJob> savedJobs = Instancio.createList(SavedJob.class);
        try {
            List<SavedJob> savedJobsByApplicant = saveJobRepo.findByApplicantOrderByCreatedAtDesc(new Applicant());
        }catch (BadRequestException e) {
            Assertions.assertEquals(e.getClass(), BadRequestException.class);
            assertThat("Applicant Not Found!", is(e.getMessage()));
        }
    }

    @Test
    public void un_saveJob() {
        SavedJob savedJobMockData = Instancio.create(SavedJob.class);
        List<SavedJob> savedJobsMockData = Instancio.ofList(SavedJob.class).size(5).create();

        when(jobRepo.findById(any())).thenReturn(Optional.of(savedJobMockData.getJob()));
        when(applicantRepo.findById(any())).thenReturn(Optional.of(savedJobsMockData.getFirst().getApplicant()));
        when(authService.getLoginUserId()).thenReturn(savedJobsMockData.getFirst().getApplicant().getId());
        when(saveJobRepo.findByApplicantOrderByCreatedAtDesc(any())).thenReturn(savedJobsMockData);
        when(saveJobRepo.findById(any())).thenReturn(Optional.of(savedJobMockData));

        List<SavedJob> savedJobsOfResult = saveJobServiceImpl.un_save(UUID.randomUUID());
        Assertions.assertEquals(ArrayList.class, savedJobsOfResult.getClass());
        Assertions.assertEquals(savedJobsMockData.getFirst().getApplicant(), savedJobsOfResult.getFirst().getApplicant());
        verify(applicantRepo, times(2)).findById(any());
        verify(saveJobRepo, times(1)).findByApplicantOrderByCreatedAtDesc(any());
        verify(saveJobRepo, times(1)).deleteById(any());
    }

    @Test
    public void un_saveJob_fail() {
        try {
            List<SavedJob> savedJobsByApplicant = saveJobRepo.findByApplicantOrderByCreatedAtDesc(new Applicant());
        }catch (BadRequestException e) {
            Assertions.assertEquals(e.getClass(), BadRequestException.class);
            assertThat("Job Not Found!", is(e.getMessage()));
        }
    }
}
