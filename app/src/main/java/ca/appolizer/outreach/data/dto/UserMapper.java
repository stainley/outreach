package ca.appolizer.outreach.data.dto;

import ca.appolizer.outreach.data.entity.UserEntity;

public class UserMapper implements ModelMapper<UserDto, UserEntity> {


    @Override
    public UserDto mapFromModel(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity mapToEntity(UserDto userDto) {
        return null;
    }
}
