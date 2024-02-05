package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepasitory userRepasitory;
    private final HttpSession session;
    @PostMapping("/login")
    public  String login(UserRequest.LoginDTO requestDTO, HttpServletRequest request){

        if(requestDTO.getUsername().length()<3){
            return "error/400"; // ViewResolver 설정이 되어 있음. (앞 경로, 뒤 경로)
        }

        User user = userRepasitory.findByUsernameAndPassword(requestDTO);

        if(user==null){
            return "error/401";
        }else{
            session.setAttribute("sessionUser", user);// 락카에 담음 (StateFul)
        }
        return "redirect:/"; // 컨트롤러가 존재하면 무조건 redirect 외우기
    }
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO){
        System.out.println(requestDTO);
        userRepasitory.save(requestDTO);
        return "redirect:/loginForm";
    }
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
