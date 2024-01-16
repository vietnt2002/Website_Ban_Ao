package com.example.java4.controllers;
import com.example.java4.dto.login.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class IndexController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    @GetMapping("view/admin")
    public String getHomeForm(Model model)
    {
        String name = "SD18203";
        model.addAttribute("name", name);
        return "index";
    }

    @PostMapping("view/admin")
    public String index(
//        @RequestParam("email") Optional<String> username,
//        @RequestParam("password") String pwd,
            LoginRequest req,
            Model model
    ) {
        System.out.println("LoginController@login");
        System.out.println(req.getEmail());
        System.out.println(req.getPassword());
        model.addAttribute("name", req.getEmail());
        return "index";
    }
}