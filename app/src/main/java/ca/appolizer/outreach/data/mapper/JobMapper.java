package ca.appolizer.outreach.data.mapper;

import ca.appolizer.outreach.data.dto.JobDto;
import ca.appolizer.outreach.data.entity.JobEntity;

public class JobMapper implements ModelMapper<JobEntity, JobDto> {

    @Override
    public JobEntity mapFromModel(JobDto jobDto) {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setId(jobEntity.getId());
        jobEntity.setUserId(jobDto.getUserId());
        jobEntity.setName(jobDto.getName());
        jobEntity.setDescription(jobEntity.getDescription());

        return jobEntity;
    }

    @Override
    public JobDto mapToEntity(JobEntity jobEntity) {
        JobDto jobDto = new JobDto();
        jobDto.setId(jobEntity.getId());
        jobDto.setUserId(jobEntity.getUserId());
        jobDto.setName(jobEntity.getName());
        jobDto.setDescription(jobEntity.getDescription());

        return jobDto;
    }
}
