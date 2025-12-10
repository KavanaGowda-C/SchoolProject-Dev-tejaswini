package com.nimblix.SchoolPEPProject.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SchoolSignupRequest {

    private String schoolName;
    private String schoolId;
    private String email;

    private String password;
    private String confirmPassword;

    private String mobileNumber;
    private String address;

    private String city;
    private String state;
    private String pincode;
}
