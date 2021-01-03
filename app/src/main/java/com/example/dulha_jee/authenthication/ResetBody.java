package com.example.dulha_jee.authenthication;

public class ResetBody {
    private String reset_password_token;
    private String password;
    private String email;

    public ResetBody(String reset_password_token, String password, String email) {
        this.reset_password_token = reset_password_token;
        this.password = password;
        this.email = email;
    }

    public String getReset_password_token() {
        return reset_password_token;
    }

    public void setReset_password_token(String reset_password_token) {
        this.reset_password_token = reset_password_token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
