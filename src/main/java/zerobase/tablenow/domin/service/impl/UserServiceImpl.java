package zerobase.tablenow.domin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.tablenow.domin.member.dto.LoginDto;
import zerobase.tablenow.domin.member.dto.UserDto;
import zerobase.tablenow.domin.member.entity.Users;
import zerobase.tablenow.domin.member.repository.UserRepository;
import zerobase.tablenow.domin.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public Users login(LoginDto loginDto) {
        Users optionalUsers = userRepository.findByUserId(loginDto.getUserId())
                .orElseThrow(() -> new RuntimeException("아이디를 확인해 주세요."));
        return optionalUsers;

    }

    @Override
    public boolean register(UserDto userDto) {
        Optional<Users> optionalUsers = userRepository.findByUserId(userDto.getUserId());
        if (optionalUsers.isPresent()){
            return false;
        }
        Users users = Users.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .build();
        userRepository.save(users);
        return true;
    }

}
