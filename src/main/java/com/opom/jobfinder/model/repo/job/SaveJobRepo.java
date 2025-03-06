package com.opom.jobfinder.model.repo.job;

import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.applicant.SavedJob;
import com.opom.jobfinder.model.entity.applicant.pk.SavedJobPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SaveJobRepo extends JpaRepository<SavedJob, SavedJobPk> {
    List<SavedJob> findBySavedJobPk(SavedJobPk savedJobPk);
    List<SavedJob> findByApplicantOrderByCreatedAtDesc(Applicant applicant);
}
