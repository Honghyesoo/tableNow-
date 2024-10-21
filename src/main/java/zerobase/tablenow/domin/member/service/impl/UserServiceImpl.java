package zerobase.tablenow.domin.member.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.tablenow.components.MailComponents;
import zerobase.tablenow.domin.member.dto.UserDto;
import zerobase.tablenow.domin.member.entity.UserCode;
import zerobase.tablenow.domin.member.entity.Users;
import zerobase.tablenow.domin.member.repository.UserRepository;
import zerobase.tablenow.domin.member.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MailComponents mailComponents;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean register(UserDto userDto) {
        Optional<Users> optionalUsers = userRepository.findByUserId(userDto.getUserId());
        if (optionalUsers.isPresent()){
            return false;
        }

        String hashPassword = passwordEncoder.encode(userDto.getPassword());

        String uuid = UUID.randomUUID().toString();

        Users users = Users.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .password(hashPassword)
                .phone(userDto.getPhone())
                .regDt(LocalDateTime.now())
                .emailAuthYn(false)
                .emailAuthKey(uuid)
                .userStatus(Users.USER_STATUS_REQ)
                .build();
        userRepository.save(users);

        String email = userDto.getUserId();
        String subject = "TableNow 사이트 가입을 축하드립니다.";
        String text = "<p>TableNow 사이트 가입을 축하드립니다.</p> <p>아래 링크를 클릭하셔서 가입 완료 하세요</p>" +
                "<div><a target='_blank' href='http://localhost:8080/user/email-auth?id=" + uuid + "'>가입완료</a></div>";
        mailComponents.sendMail(email,subject,text);
        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {
        Optional<Users> optionalMember = userRepository.findByEmailAuthKey(uuid);

        if (optionalMember.isEmpty()){ //존재하지 않은경우
            return false;
        }
        Users users = optionalMember.get();

        if (users.isEmailAuthYn()) {
            return false;
        }
        users.setUserStatus(UserCode.USER_STATUS_ING);
        users.setEmailAuthYn(true);
        users.setEmailAuthDt(LocalDateTime.now());
        userRepository.save(users);

        return true;

    }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
      Optional<Users> optionalMember = userRepository.findByUserId(userName);
      if (optionalMember.isEmpty()) {
          throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
      }

      Users users = optionalMember.get();

      if (Users.USER_STATUS_REQ.equals(users.getUserStatus())){
          throw  new UsernameNotFoundException("이메일 활성화 이후에 로그인을 해주세요.");
      }

      if (Users.USER_STATUS_STOP.equals(users.getUserStatus())){
          throw  new UsernameNotFoundException("정지된 회원 입니다.");
      }

      List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
      grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

      //관리자일때
      if (users.isAdminYn()){
          grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
      }

      return new User(users.getUserId(), users.getPassword(), grantedAuthorities);
  }





}
