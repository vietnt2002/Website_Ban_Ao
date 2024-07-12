package com.example.java4.controller.controller_tai;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/thong-ke")
public class ThongKeController {

    @GetMapping("/view")
    public String view(Model model){

        return "/view/ThongKe/view.jsp";
    }
}
