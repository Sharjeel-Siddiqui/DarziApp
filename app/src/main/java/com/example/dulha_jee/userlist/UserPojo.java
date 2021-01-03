package com.example.dulha_jee.userlist;

public class UserPojo {
    private int userImage;
    private String userName;
    private String userOrderNumber;

    public UserPojo(int userImage, String userName, String userOrderNumber) {
        this.userImage = userImage;
        this.userName = userName;
        this.userOrderNumber = userOrderNumber;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserOrderNumber() {
        return userOrderNumber;
    }

    public void setUserOrderNumber(String userOrderNumber) {
        this.userOrderNumber = userOrderNumber;
    }
}
