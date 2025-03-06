package com.opom.jobfinder.feature.job.service.JunitTest;

import com.opom.jobfinder.feature.applicant.save.service.SaveJobService;
import com.opom.jobfinder.model.entity.applicant.SavedJob;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.repo.job.JobRepo;
import com.opom.jobfinder.utility.exception.BadRequestException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaveJobServiceTest {

    @InjectMocks
    private SaveJobService saveJobService;

    @Mock
    private JobRepo jobRepo;

    @Test
    public void saveJobByApplicant() {
        SavedJob savedJobMockData = Instancio.create(SavedJob.class);
        when(saveJobService.save(any())).thenReturn(savedJob);

        SavedJob savedJob = saveJobService.save(UUID.randomUUID());
        Assertions.assertEquals(savedJob.getApplicant(), savedJobMockData.getApplicant());
        Assertions.assertEquals(savedJob.getJob(), savedJobMockData.getJob());
        Assertions.assertEquals(savedJob.getId(),savedJobMockData.getId());
    }

    @Test
    public void saveJobByApplicant_fail() {
        when(saveJobService.save(any())).thenThrow(BadRequestException.class);

        try{
            saveJobService.save(UUID.randomUUID());
        } catch (BadRequestException e) {
            Assertions.assertEquals(e.getClass(), BadRequestException.class);
        }
    }

}
