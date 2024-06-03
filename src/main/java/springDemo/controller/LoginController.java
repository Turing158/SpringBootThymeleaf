package springDemo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springDemo.service.LoginService;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    HttpSession session;

    @RequestMapping("/toLoginPage")
    public String toLogin(@RequestParam(required = false,defaultValue = "zh_CN") String lang){
        session.setAttribute("lang",lang);
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password){
        loginService.login(username,password);
        String lang = (String) session.getAttribute("lang");
        return "redirect:/toLoginPage?lang="+lang;
    }

}
