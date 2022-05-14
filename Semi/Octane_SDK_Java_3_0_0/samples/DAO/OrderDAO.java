/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.OrderDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class OrderDAO {
    Connect conn;

    public OrderDAO() {
    }

    public ArrayList<OrderDTO> getList() {
        ArrayList<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from Order_Product";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                OrderDTO dto = new OrderDTO();
                dto.setOrderId(conn.rs.getString(1));
                dto.setOrderDate(conn.rs.getString(2));
                dto.setUserCreated(conn.rs.getString(3));
                dto.setUserCompleted(conn.rs.getString(4));
                dto.setStatus(conn.rs.getInt(5));
                orderDTOs.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("OrderDAO.getList.executeQuery error.");
        }
        try {
            conn.getConn().close();
        } catch (SQLException e) {
            System.out.println("OrderDAO.getList.close error.");
        }
        return orderDTOs;
    }
    
    public boolean insertOrder(OrderDTO orderDTO) {
        conn = new Connect();
        conn.getConnection();
        String query = "INSERT INTO Order_Product"
                + " VALUES ('" + orderDTO.getOrderId() + "'"
                + ",'" + orderDTO.getOrderDate() + "'"
                + ",'" + orderDTO.getUserCreated() + "'"
                + ",'" + orderDTO.getUserCompleted() + "'"
                + "," + orderDTO.getStatus() + ")";
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("OrderDAO insert success.");
            return true;
        }
        conn.close();
        System.out.println("OrderDAO insert fail.");
        return false;
    }
    
    public boolean updateOrderCompleted(String orderId, String userId) {
        conn = new Connect();
        conn.getConnection();
        String query = "UPDATE Order_Product SET"
                + " status=3, completed_by='" + userId + "'"
                + " WHERE order_id='" + orderId + "';";
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("OrderDAO update success.");
            return true;
        }
        conn.close();
        System.out.println("OrderDAO update fail.");
        return false;
    }
    
    public boolean deleteOrder(String orderId) {
        conn = new Connect();
        conn.getConnection();
        String query = "DELETE FROM Order_Product"
                + " WHERE order_id='" + orderId + "';";
//        System.out.println("query: " + query);       
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("OrderDAO delete success.");
            return true;
        }
        conn.close();
        System.out.println("OrderDAO delete fail.");
        return false;
    }
}
