package com.paccy.spring_project.secured;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/management")
@PreAuthorize("hasRole('MEMBER')")
public class ManagementController {

    @GetMapping
    public String getMember(){
        return "Member";
    }
}
