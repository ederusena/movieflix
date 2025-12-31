package com.movieflix.mapper;

import com.movieflix.dto.UserRequest;
import com.movieflix.dto.UserResponse;
import com.movieflix.model.User;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static User toUser(UserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }
}
