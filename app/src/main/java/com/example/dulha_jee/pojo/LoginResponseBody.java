package com.example.dulha_jee.pojo;

public class LoginResponseBody {

    private String api_token;

    public LoginResponseBody(String api_token) {
        this.api_token = api_token;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
