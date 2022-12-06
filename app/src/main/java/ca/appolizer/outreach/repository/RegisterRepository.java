package ca.appolizer.outreach.repository;

import ca.appolizer.outreach.model.request.AbstractUserRequest;
import ca.appolizer.outreach.model.response.UserResponse;

public interface RegisterRepository<T extends AbstractUserRequest> {
    UserResponse registerStudent(T request);

    UserResponse registerCompany(T request);
}
