package ca.appolizer.outreach.data.mapper;

import ca.appolizer.outreach.data.dto.UserDto;
import ca.appolizer.outreach.data.entity.UserEntity;

public class UserMapper implements ModelMapper<UserDto, UserEntity> {

    @Override
    public UserDto mapFromModel(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setEmail(userDto.getEmail());

        return userDto;
    }

    @Override
    public UserEntity mapToEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userEntity.getId());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }
}
