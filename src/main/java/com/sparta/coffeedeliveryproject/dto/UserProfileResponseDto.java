package com.sparta.coffeedeliveryproject.dto;

import com.sparta.coffeedeliveryproject.entity.User;
import com.sparta.coffeedeliveryproject.entity.UserRole;
import com.sparta.coffeedeliveryproject.enums.UserStatusEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class UserProfileResponseDto {

    private String userName;

    private String nickName;

    private UserStatusEnum userStatus;

    private long cafeLikesCount;

    private long commentlikesCount;

    public UserProfileResponseDto(User user, long cafeLikesCount, long commentlikesCount) {
        this.userName = user.getUserName();
        this.nickName = user.getNickName();
        this.userStatus = user.getUserStatus();
        this.cafeLikesCount = cafeLikesCount;
        this.commentlikesCount = commentlikesCount;
    }
}