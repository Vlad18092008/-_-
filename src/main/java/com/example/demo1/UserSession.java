package com.example.demo1;

public class UserSession {
    private static String userName = "";
    private static String userEmail = "";
    private static String userPhone = "";
    private static String userPassword = "";
    private static boolean isLoggedIn = false;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserSession.userName = userName;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        UserSession.userEmail = userEmail;
    }

    public static String getUserPhone() {
        return userPhone;
    }

    public static void setUserPhone(String userPhone) {
        UserSession.userPhone = userPhone;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static void setUserPassword(String userPassword) {
        UserSession.userPassword = userPassword;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public static void clear() {
        userName = "";
        userEmail = "";
        userPhone = "";
        userPassword = "";
        isLoggedIn = false;
    }
}