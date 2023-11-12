package com.example.EcomApp.mappers;


import com.example.EcomApp.dto.SigUpDto;
import com.example.EcomApp.dto.UserDto;
import com.example.EcomApp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SigUpDto userDto);


}
