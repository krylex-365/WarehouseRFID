/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class UserDTO {
    private String userId;
    private String userName;
    private String passWord;
    private String fullName;
    private String phoneNum;
    private String mail;
    private int role;

    public UserDTO() {
    }

    public UserDTO(String userId, String userName, String passWord, String fullName, String phoneNum, String mail, int role) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.mail = mail;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", fullName=" + fullName + ", phoneNum=" + phoneNum + ", mail=" + mail + ", role=" + role + "}\n";
    }
    
}
