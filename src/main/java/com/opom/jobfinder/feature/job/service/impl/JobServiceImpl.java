package com.opom.jobfinder.feature.job.service.impl;

import com.opom.jobfinder.feature.job.dtos.JobDTO;
import com.opom.jobfinder.feature.job.mapper.JobManager;
import com.opom.jobfinder.feature.job.service.JobService;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.repo.job.JobRepo;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepo jobRepo;
    private final JobManager jobManager;

    @Override
    public List<JobDTO> getJobs(String type, String location, String q) {
        type = (type == null || type.isEmpty()) ? "Any Type" : type;
        location = (location == null || location.isEmpty()) ? "Any Location" : location;
        q = (q == null || q.isEmpty()) ? "" : q;

        String finalType = type;
        String finalLocation = location;
        String finalQuery = q;

        List<Job> jobs = jobRepo.search(cb -> {
            CriteriaQuery<Job> query = cb.createQuery(Job.class);
            Root<Job> root = query.from(Job.class);

            List<Predicate> predicates = new ArrayList<>();

            if (!finalType.equals("Any Type")) {
                predicates.add(cb.equal(root.get("type"), Job.Type.valueOf(finalType)));
            }
            if (!finalLocation.equals("Any Location")) {
                predicates.add(cb.like(root.get("location").get("name"), "%"+ finalLocation + "%"));
            }
            if (!finalQuery.isEmpty()) {
                predicates.add(cb.like(root.get("title"), "%" + finalQuery + "%"));
            }
            if (predicates.isEmpty()) {
                query.select(root);
            } else {
                query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
            }
            return query;
        });
        return jobs.stream()
                .map(JobManager::toJobDTO)
                .collect(Collectors.toList());
    }
}
