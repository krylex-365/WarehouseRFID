/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.OrderDetailDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class OrderDetailDAO {
    Connect conn;

    public OrderDetailDAO() {
    }

    public ArrayList<OrderDetailDTO> getList() {
        ArrayList<OrderDetailDTO> orderDetailDTOs = new ArrayList<OrderDetailDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from Order_Detail";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                OrderDetailDTO dto = new OrderDetailDTO();
                dto.setOrderDetailId(conn.rs.getString(1));
                dto.setOrderId(conn.rs.getString(2));
                dto.setProductId(conn.rs.getString(3));
                dto.setOrderQuantity(conn.rs.getInt(4));
                orderDetailDTOs.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("OrderDetailDAO.getList.executeQuery error.");
        }
        try {
            conn.getConn().close();
        } catch (SQLException e) {
            System.out.println("OrderDetailDAO.getList.close error.");
        }
        return orderDetailDTOs;
    }
    
    public boolean insertOrderDetail(ArrayList<OrderDetailDTO> orderDetailDTOs) {
        conn = new Connect();
        conn.getConnection();
        String query = "INSERT INTO Order_Detail"
                + " VALUES ";
        int size = orderDetailDTOs.size(), count = 1;
        for (OrderDetailDTO orDetalDTO : orderDetailDTOs) {
            query += "('" + orDetalDTO.getOrderDetailId() + "'"
                + ",'" + orDetalDTO.getOrderId() + "'"
                + ",'" + orDetalDTO.getProductId()+ "'"
                + "," + orDetalDTO.getOrderQuantity() + ")";
            if (count < size) {
                query += ", ";
                count++;
            } else if (count == size) {
                query += ";";
            }
        }
//        System.out.println("query: " + query);       
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("OrderDetailDAO insert success.");
            return true;
        }
        conn.close();
        System.out.println("OrderDetailDAO insert fail.");
        return false;
    }
    
    public boolean deleteDetail(String orderId) {
        conn = new Connect();
        conn.getConnection();
        String query = "DELETE FROM Order_Detail"
                + " WHERE order_id='" + orderId + "';";
//        System.out.println("query: " + query);       
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("OrderDetailDAO delete success.");
            return true;
        }
        conn.close();
        System.out.println("OrderDetailDAO delete fail.");
        return false;
    }
}
