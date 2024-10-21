package zerobase.tablenow.domin.member.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zerobase.tablenow.domin.member.dto.LoginDto;
import zerobase.tablenow.domin.member.dto.UserDto;
import zerobase.tablenow.domin.member.service.UserService;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;

    //회원가입
    @GetMapping("register")
    public String register() {return "/user/register";}
    @PostMapping("register")
    public String registerSubmit(Model model, UserDto userDto){
        boolean result = userService.register(userDto);
        model.addAttribute("result",result);


        return "/user/login";
    }


    @GetMapping("user/email-auth")
    public String emailAuth(Model model ,HttpServletRequest request){
        String uuid = request.getParameter("id");
        log.info(uuid);

        boolean result = userService.emailAuth(uuid);
        model.addAttribute("result",result);
        return "user/email_auth";
    }

    @RequestMapping("login")
    public String login(Model model, HttpSession session) {
        // 세션에서 에러 메시지 가져오기
        String errorMessage = (String) session.getAttribute("errorMessage");
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            // 에러 메시지를 표시한 후 세션에서 제거
            session.removeAttribute("errorMessage");
        }
        return "user/login";
    }
}

