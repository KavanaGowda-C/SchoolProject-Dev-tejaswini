package com.nimblix.SchoolPEPProject.Controller;

import com.nimblix.SchoolPEPProject.Constants.SchoolConstants;
import com.nimblix.SchoolPEPProject.Request.SchoolSignupRequest;
import com.nimblix.SchoolPEPProject.Service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody SchoolSignupRequest request
    ) {
        schoolService.registerSchool(request);

        return ResponseEntity.ok("Student registered successfully");
    }
}
