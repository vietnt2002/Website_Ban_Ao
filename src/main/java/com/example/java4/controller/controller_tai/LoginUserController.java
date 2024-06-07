package com.example.java4.controller.controller_tai;


import com.example.java4.entities.KhachHang;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.request.req_tai.KhachHangDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dang-nhap")
public class LoginUserController {

    @Autowired
    KhachHangRepository khachHangRepository;

    // Hiển thị giao diện đăng nhâp của User
    @GetMapping("/login")
    public String view(Model model) {
        model.addAttribute("khachHangDTO", new KhachHangDTO());
        return "/view/view_tai/login/login.jsp";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("khachHangDTO") KhachHangDTO  khachHangDTO,
                        HttpSession session,
                        Model model,
                        BindingResult result,
                        RedirectAttributes redirectAttributes
                        ) {
        if (result.hasErrors()) {
            System.out.println("Có lỗi");
            model.addAttribute("khachHangDTO", khachHangDTO);
            return "/view/view_tai/login/login.jsp";
        }

        // Lấy ra khách hàng theo tên đăng nhập
        KhachHang khachHang = khachHangRepository.findByTaiKhoan(khachHangDTO.getTaiKhoan());
        if (khachHang != null) {
            // Kiểm tra mật khẩu có đúng không
            if (khachHang.getMatKhau().equals(khachHangDTO.getMatKhau())) {
                session.setAttribute("user", khachHang);
                redirectAttributes.addFlashAttribute("successMessage", "Đăng nhập thành công!");
                return "redirect:/home";
            } else {
                // Thông báo lỗi nếu mật khẩu không đúng
                model.addAttribute("errorPass", "Sai mật khẩu");
                redirectAttributes.addFlashAttribute("errorPass", "Sai mật khẩu");
                redirectAttributes.addFlashAttribute("error", "Đăng nhập thất bại!");
//                return "redirect:/dang-nhap/login";
                return "/view/view_tai/login/login.jsp";
            }
        } else {
            // Thông báo lỗi nếu tài khoản không tồn tại
            model.addAttribute("errorUser", "Tài khoản không tồn tại");
            redirectAttributes.addFlashAttribute("errorUser", "Tài khoản không tồn tại");
            redirectAttributes.addFlashAttribute("error", "Đăng nhập thất bại!");
//            return "redirect:/dang-nhap/login";
            return "/view/view_tai/login/login.jsp";
        }

    }
}
