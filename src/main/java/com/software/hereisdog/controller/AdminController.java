package com.software.hereisdog.controller;

import com.software.hereisdog.domain.Customer;
import com.software.hereisdog.domain.Owner;
import com.software.hereisdog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mypage/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /** 방문객, 오너 전체 조회 */
    @GetMapping
    public String showAdminPage(Model model) {
        List<Customer> customerList = adminService.findAllCustomers();
        List<Owner> ownerList = adminService.findAllOwners();
        model.addAttribute("customerList", customerList);
        model.addAttribute("ownerList", ownerList);
        return "admin";  // /WEB-INF/jsp/admin.jsp
    }

    /** 방문객 삭제 */
    @PostMapping("/customer/delete")
    public String deleteCustomer(@RequestParam("id") Long id) {
        adminService.deleteCustomer(id);
        return "redirect:/mypage/admin";
    }

    /** 오너 삭제 */
    @PostMapping("/owner/delete")
    public String deleteOwner(@RequestParam("id") Long id) {
        adminService.deleteOwner(id);
        return "redirect:/mypage/admin";
    }
}
