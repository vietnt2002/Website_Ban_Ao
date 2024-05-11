package com.example.java4.restcontrollers;

import com.example.java4.entities.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("login")
public class LoginController {
    //@RequestMapping(name="login", method = RequestMethod.POST)
    Account accRem = new Account();

    public void handleSubmit(Account acc){
        System.out.println("handle submit data:"+acc);
    }
    @GetMapping("/create")
    public String getLoginForm(Model model)
    {
        String name = "SD18203";
        model.addAttribute("data",accRem);
        return "admin/login/Login";
    }
//    @PostMapping("enter")
//    public String login(
////        @RequestParam("email") Optional<String> username,
////        @RequestParam("password") String pwd,
//            LoginRequest req,
//            Model model
//    ) {
//        System.out.println("LoginController@login");
//        System.out.println(req.getEmail());
//        System.out.println(req.getPassword());
//        Account acc = new Account(req.getEmail(), req.getPassword());
//        handleSubmit(acc);
//        accRem=acc;
//        model.addAttribute("data",acc);
//        return "admin/login/Login";
//    }
}