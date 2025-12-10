package com.nimblix.SchoolPEPProject.Controller;

import com.nimblix.SchoolPEPProject.Request.SchoolSignupRequest;
import com.nimblix.SchoolPEPProject.Service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



    @RestController
    @RequestMapping("/api/v1/school")
    @RequiredArgsConstructor
    public class SchoolController {
        private final SchoolService schoolService;

        @PostMapping("/signup")
        public ResponseEntity<?> signup(@Valid @RequestBody SchoolSignupRequest request){
            return schoolService.registerSchool(request);
        }
}
