package com.nimblix.SchoolPEPProject.Response;

public class SchoolSignupResponse {

    private int status;
    private String message;
    private String schoolId;

    public SchoolSignupResponse() {
    }

    public SchoolSignupResponse(int status, String message, String schoolId) {
        this.status = status;
        this.message = message;
        this.schoolId = schoolId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}

