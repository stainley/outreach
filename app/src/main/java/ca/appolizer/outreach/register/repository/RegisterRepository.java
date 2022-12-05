package ca.appolizer.outreach.register.repository;

import ca.appolizer.outreach.model.response.UserResponse;
import ca.appolizer.outreach.model.request.AbstractRequest;

public interface RegisterRepository<T extends AbstractRequest> {
    UserResponse registerStudent(T request);

    UserResponse registerCompany(T request);
}
