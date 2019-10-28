package com.fedsea.app.mapper;

import org.mapstruct.Mapper;

import com.fedsea.app.dto.ConnectDto;
import com.fedsea.app.dto.UnConnectDto;
import com.fedsea.app.dto.UserDto;
import com.fedsea.app.model.Friend;
import com.fedsea.app.model.User;

@Mapper(componentModel = "spring")
public interface CustomMapper {

	UserDto userToUserDto(User user);
	
	User userDtoTouser(UserDto userDto);
	
	Friend connectDtoToFriend(ConnectDto connectDto);
	
	Friend unConnectDtoToFriend(UnConnectDto unConnectDto);
}