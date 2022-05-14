/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.UserDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserDAO {
    Connect conn;

    public UserDAO() {
    }

    public ArrayList<UserDTO> getList() {
        ArrayList<UserDTO> userDTOs = new ArrayList<UserDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from User_App";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                UserDTO dto = new UserDTO();
                dto.setUserId(conn.rs.getString(1));
                dto.setUserName(conn.rs.getString(2));
                dto.setPassWord(conn.rs.getString(3));
                dto.setFullName(conn.rs.getString(4));
                dto.setPhoneNum(conn.rs.getString(5));
                dto.setMail(conn.rs.getString(6));
                dto.setRole(conn.rs.getInt(7));
                userDTOs.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("UserDAO.getList.executeQuery error.");
        }
        try {
            conn.getConn().close();
        } catch (SQLException e) {
            System.out.println("UserDAO.getList.close error.");
        }
        return userDTOs;
    }
    
    public ArrayList<UserDTO> getUser(String userName, String passWord) {
        ArrayList<UserDTO> userDTOs = new ArrayList<UserDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from User_App where username='" + userName + "' and password='" + passWord + "';";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                UserDTO dto = new UserDTO();
                dto.setUserId(conn.rs.getString(1));
                dto.setUserName(conn.rs.getString(2));
                dto.setPassWord(conn.rs.getString(3));
                dto.setFullName(conn.rs.getString(4));
                dto.setPhoneNum(conn.rs.getString(5));
                dto.setMail(conn.rs.getString(6));
                dto.setRole(conn.rs.getInt(7));
                userDTOs.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("UserDAO.getUser.executeQuery error.");
        }
        try {
            conn.getConn().close();
        } catch (SQLException e) {
            System.out.println("UserDAO.getUser.close error.");
        }
        return userDTOs;
    }

    public boolean insertUser(UserDTO userDTO) {
        conn = new Connect();
        conn.getConnection();
        String name = "";
        if (userDTO.getFullName().contains("'")){
            name = userDTO.getFullName().replace("'", "''");
        } else {
            name = userDTO.getFullName();
        }
        String query = "INSERT INTO User_App"
                + " VALUES ('" + userDTO.getUserId()
                + "','" + userDTO.getUserName() + "'"
                + ",'" + userDTO.getPassWord() + "'"
                + ",N'" + name + "'"
                + ",'" + userDTO.getPhoneNum() + "'"
                + ",'" + userDTO.getMail() + "'"
                + "," + userDTO.getRole() + ")";
//        System.out.println("query: " + query);
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("UserDAO insert success.");
            return true;
        }
        conn.close();
        System.out.println("UserDAO insert fail.");
        return false;
    }
    
    public boolean updateUser(UserDTO userDTO, boolean checkPw) {
        conn = new Connect();
        conn.getConnection();
        String name = "";
        if (userDTO.getFullName().contains("'")){
            name = userDTO.getFullName().replace("'", "''");
        } else {
            name = userDTO.getFullName();
        }
        String query = "UPDATE User_App"
                + " SET username='" + userDTO.getUserName()+ "'";
        if (checkPw) {
            query += ", password='" + userDTO.getPassWord()+ "'";
        }
        query += ", full_name=N'" + name + "'"
                + ", phone_num='" + userDTO.getPhoneNum() + "'"
                + ", mail='" + userDTO.getMail() + "'"
                + " WHERE user_id='" + userDTO.getUserId() + "'";
//        System.out.println("query: " + query);
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("UserDAO update success.");
            return true;
        }
        conn.close();
        System.out.println("UserDAO update fail.");
        return false;
    }
    
    public boolean updateStatusUser(String userId, int role) {
        conn = new Connect();
        conn.getConnection();
        String query = "UPDATE User_App"
                + " SET role=" + role
                + " WHERE user_id='" + userId + "'";
//        System.out.println("query: " + query);
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("UserDAO status success.");
            return true;
        }
        conn.close();
        System.out.println("UserDAO status fail.");
        return false;
    }
    
    public boolean removeUser(String userId) {
        conn = new Connect();
        conn.getConnection();
        String query = "UPDATE User_App"
                + " SET role=0"
                + " WHERE user_id='" + userId + "'";
//        System.out.println("query: " + query);
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("UserDAO remove success.");
            return true;
        }
        conn.close();
        System.out.println("UserDAO remove fail.");
        return false;
    }
}
