package zerobase.tablenow.domin.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zerobase.tablenow.domin.member.dto.LoginDto;
import zerobase.tablenow.domin.member.dto.UserDto;
import zerobase.tablenow.domin.service.UserService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;

    //회원가입
    @GetMapping("register")
    public String register(){

        return "/user/register";
    }

    @PostMapping("register")
    public String registerSubmit(Model model, UserDto userDto){
        boolean result = userService.register(userDto);
        model.addAttribute("result",result);
        return "/user/login";
    }

    //로그인
    @GetMapping("login")
    public String login(Model model){
        return "/user/login";
    }
    @PostMapping("login")
    public String loginSubmit(Model model, LoginDto loginDto){
        userService.login(loginDto);

        return "/user/login";
    }

}

