package zerobase.tablenow.domin.service;

import zerobase.tablenow.domin.member.dto.LoginDto;
import zerobase.tablenow.domin.member.dto.UserDto;
import zerobase.tablenow.domin.member.entity.Users;

public interface UserService {

    //로그인
    Users login(LoginDto loginDto);

    boolean register(UserDto userDto);
}
