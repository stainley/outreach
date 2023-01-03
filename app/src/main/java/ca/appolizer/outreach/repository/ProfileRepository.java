package ca.appolizer.outreach.repository;

import ca.appolizer.outreach.data.dto.StudentDto;

public interface ProfileRepository {

    StudentDto getRepository(long id);
}
