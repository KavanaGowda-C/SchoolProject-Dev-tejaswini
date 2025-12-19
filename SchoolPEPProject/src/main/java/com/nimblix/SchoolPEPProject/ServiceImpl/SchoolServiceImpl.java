package com.nimblix.SchoolPEPProject.ServiceImpl;

import com.nimblix.SchoolPEPProject.Constants.SchoolConstants;
import com.nimblix.SchoolPEPProject.Model.School;
import com.nimblix.SchoolPEPProject.Model.User;
import com.nimblix.SchoolPEPProject.Repository.SchoolRepository;
import com.nimblix.SchoolPEPProject.Repository.UserRepository;
import com.nimblix.SchoolPEPProject.Request.SchoolSignupRequest;
import com.nimblix.SchoolPEPProject.Response.SchoolSignupResponse;
import com.nimblix.SchoolPEPProject.Service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> registerSchool(SchoolSignupRequest req) {

        if (isBlank(req.getSchoolName()) || isBlank(req.getSchoolId()) ||
                isBlank(req.getEmail()) || isBlank(req.getPassword()) ||
                isBlank(req.getConfirmPassword()) || isBlank(req.getMobileNumber()) ||
                isBlank(req.getAddress()) || isBlank(req.getCity()) ||
                isBlank(req.getState()) || isBlank(req.getPincode())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("All fields are mandatory");
        }

        if (!req.getPassword().equals(req.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Password and Confirm Password do not match");
        }

        String pwdRule = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";
        if (!req.getPassword().matches(pwdRule)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Password must be at least 8 characters and contain one uppercase, one lowercase, one number and one special character");
        }

        if (!req.getMobileNumber().matches("^[0-9]{10}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Mobile number must be exactly 10 digits");
        }

        if (schoolRepository.existsBySchoolId(req.getSchoolId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("School already exists with this School ID");
        }

        if (schoolRepository.existsBySchoolEmail(req.getEmail()) ||
                userRepository.findByEmailId(req.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already registered");
        }

        School school = new School();
        school.setSchoolName(req.getSchoolName());
        school.setSchoolId(req.getSchoolId());
        school.setSchoolEmail(req.getEmail());
        school.setSchoolPhone(req.getMobileNumber());
        school.setSchoolAddress(req.getAddress());
        school.setCity(req.getCity());
        school.setState(req.getState());
        school.setPincode(req.getPincode());
        school.setStatus(SchoolConstants.ACTIVE);

        School savedSchool = schoolRepository.save(school);

        User user = new User();
        user.setFullName(req.getSchoolName());
        user.setEmailId(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword())); // encrypted
        user.setMobile(req.getMobileNumber());
        user.setStatus(SchoolConstants.ACTIVE);
        user.setIsLogin(false);

        userRepository.save(user);

        SchoolSignupResponse response = new SchoolSignupResponse(
                201,
                "School registered successfully",
                savedSchool.getSchoolId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
