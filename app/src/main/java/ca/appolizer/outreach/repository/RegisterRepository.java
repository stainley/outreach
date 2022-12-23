package ca.appolizer.outreach.repository;

import ca.appolizer.outreach.data.network.requests.AbstractUserRequest;
import ca.appolizer.outreach.data.network.responses.UserResponse;

public interface RegisterRepository<T extends AbstractUserRequest> {
    UserResponse register(T request);
}
