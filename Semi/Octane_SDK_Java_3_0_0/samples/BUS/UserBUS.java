/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import static BUS.HashPass.MD5Hash;
import DAO.LastIdDAO;
import DAO.UserDAO;
import DTO.UserDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserBUS {

    UserDAO userDAO = new UserDAO();
    Utils ult = new Utils();
    LastIdDAO lastIdDAO = new LastIdDAO();

    public ArrayList<UserDTO> getList() {
        return userDAO.getList();
    }

    public UserDTO checkLogin(String userName, String passWord) {
        String passHash = MD5Hash(passWord);
        ArrayList<UserDTO> user = userDAO.getUser(userName, passHash);
//        System.out.println("user: " + user);
        if (!user.isEmpty()) {
            return user.get(0);
        }
//        for (UserDTO u : list) {
//            if (u.getUserName().equals(userName) && u.getPassWord().equals(passHash)) {
//                return u;
//            }
//        }
        return null;
    }

    public boolean insertUser(UserDTO userDTO) {
        userDTO.setUserId(ult.initUserId());
        userDTO.setPassWord(MD5Hash(userDTO.getPassWord()));
//        System.out.println("user: " + userDTO);
        if (userDAO.insertUser(userDTO)) {
            String temp[] = userDTO.getUserId().split("_");
            System.out.println("temp[1] Order: " + temp[1]);
            lastIdDAO.updateOrderId(temp[1]);
            System.out.println("insert success UserBUS");
            return true;
        }
        System.out.println("insert fail UserBUS");
        return false;
    }

    public boolean updateUser(UserDTO userDTO, boolean checkPw) {
        if (checkPw) {
            userDTO.setPassWord(MD5Hash(userDTO.getPassWord()));
        }
        if (userDAO.updateUser(userDTO, checkPw)) {
            System.out.println("update success UserBUS");
            return true;
        }
        System.out.println("update fail UserBUS");
        return false;
    }

    public boolean updateStatusUser(String userId, int role) {
        if (userDAO.updateStatusUser(userId, role)) {
            System.out.println("update status success UserBUS");
            return true;
        }
        System.out.println("update status fail UserBUS");
        return false;
    }

    public boolean removeUser(String userId) {
        if (userDAO.removeUser(userId)) {
            System.out.println("remove success UserBUS");
            return true;
        }
        System.out.println("remove fail UserBUS");
        return false;
    }
}
