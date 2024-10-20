package zerobase.tablenow.domin.member.service.impl;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.tablenow.domin.member.dto.LoginDto;
import zerobase.tablenow.domin.member.dto.UserDto;
import zerobase.tablenow.domin.member.entity.Users;
import zerobase.tablenow.domin.member.repository.UserRepository;
import zerobase.tablenow.domin.member.service.UserService;
import zerobase.tablenow.domin.token.JwtTokenProvider;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDto loginDto) {
        Users user = userRepository.findByUserId(loginDto.getUserId())
                .orElseThrow(() -> new RuntimeException("아이디를 확인해 주세요."));

//        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
//            throw new RuntimeException("비밀번호를 확인해 주세요.");
//        }

        return jwtTokenProvider.generateToken(user.getUserId());
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
