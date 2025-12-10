package com.nimblix.SchoolPEPProject.Service;

import com.nimblix.SchoolPEPProject.Request.SchoolSignupRequest;
import org.springframework.http.ResponseEntity;

public interface SchoolService {

    ResponseEntity<?> registerSchool(SchoolSignupRequest request);

}
