package com.example.springbootCounsellorPortalProject.controllers;

import com.example.springbootCounsellorPortalProject.dto.DashboardResponse;
import com.example.springbootCounsellorPortalProject.model.Counsellor;
import com.example.springbootCounsellorPortalProject.services.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CounsellorController {

    private final CounsellorService counsellorService;

    public CounsellorController(CounsellorService counsellorService) {
        this.counsellorService = counsellorService;
    }

    @GetMapping("/")
    public String index(Model model){

        Counsellor counsellorObj = new Counsellor();
        //sending data from controller to UI as a key and value
        model.addAttribute("counsellor",counsellorObj);

        //returning view name
        return "index";
    }

    @PostMapping("/login")
    public String login(Counsellor counsellor, HttpServletRequest httpServletRequest, Model model){

        Optional<Counsellor> loginObj = counsellorService.login(counsellor.getEmail(), counsellor.getPwd());
        if(loginObj.isPresent()){
            Counsellor counsellorObj = loginObj.get();

            //valid login, store counsellor Id in session for future purpose
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("counsellorId",counsellorObj.getCounsellorId());

            return "redirect:/dashboard";
        }
        else {
            model.addAttribute("emsg","Invalid Credentials");
            return "index";
        }
    }

    @GetMapping("/dashboard")
    public String displayDashboard(HttpServletRequest req,Model model){

        //get existing session
        HttpSession session = req.getSession(false);
        Long counsellorId = (Long) session.getAttribute("counsellorId");

        DashboardResponse dashboardObj = counsellorService.getDashboardInfo(counsellorId);
        model.addAttribute("dashboardInfo",dashboardObj);
        return "dashboard";
    }



    @PostMapping("/register")
    public String handelRegistration(Counsellor counsellor, Model model){

        Counsellor byEmail = counsellorService.findByEmail(counsellor.getEmail());
        if(byEmail!=null){
            model.addAttribute("emsg","Duplicate email found....!");
        }

        boolean register = counsellorService.register(counsellor);

        if(register){
            //success
            model.addAttribute("smsg","Successfully Registered....!");
        }else {
            model.addAttribute("emsg","Registration failed..!");
        }
        return "register";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        //get existing session and invalidate(remove) the session
        HttpSession session = httpServletRequest.getSession(false);
        session.invalidate();

        //redirect to the login page
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){

        Counsellor counsellorObj = new Counsellor();
        //sending data from controller to UI as a key and value
        model.addAttribute("counsellor",counsellorObj);
        return "register";
    }

//    @PostMapping("/register")
//    public ResponseEntity<Boolean> register(@RequestBody Counsellor counsellor) {
//        boolean result = counsellorService.register(counsellor);
//        return ResponseEntity.ok(result);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<Counsellor> login(@RequestParam String email, @RequestParam String pwd) {
//        Optional<Counsellor> counsellor = counsellorService.login(email, pwd);
//        if (counsellor.isPresent()) {
//            Counsellor counsellor1 = counsellor.get();
//            return ResponseEntity.ok(counsellor1);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Return 401 Unauthorized if not found
//        }
//    }

//    @GetMapping("/dashboard/{counsellorId}")
//    public ResponseEntity<DashboardResponse> getDashboardInfo(@PathVariable Long counsellorId) {
//        DashboardResponse response = counsellorService.getDashboardInfo(counsellorId);
//        return ResponseEntity.ok(response);
//    }
}
