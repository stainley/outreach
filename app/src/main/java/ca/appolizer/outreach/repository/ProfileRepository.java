package ca.appolizer.outreach.repository;

import ca.appolizer.outreach.model.Student;

public interface ProfileRepository {

    Student getRepository(long id);
}
