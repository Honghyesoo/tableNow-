package zerobase.tablenow.domin.member.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import zerobase.tablenow.domin.member.dto.LoginDto;
import zerobase.tablenow.domin.member.dto.UserDto;
import zerobase.tablenow.domin.member.entity.Users;

public interface UserService extends UserDetailsService {

    boolean register(UserDto userDto);

    boolean emailAuth(String uuid);

//    LoginDto login(LoginDto loginDto);

}

