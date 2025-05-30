package com.rd.userservice.mapper;

import com.rd.userservice.dto.UserDto;
import com.rd.userservice.model.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<UserDto> dtos);

    default Optional<UserDto> toDtoOptional(Optional<User> userOpt) {
        return userOpt.map(this::toDto);
    }

    default Optional<User> toEntityOptional(Optional<UserDto> dtoOpt) {
        return dtoOpt.map(this::toEntity);
    }
}

