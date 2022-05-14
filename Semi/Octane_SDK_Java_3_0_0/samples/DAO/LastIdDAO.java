/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.SQLException;

/**
 *
 * @author User
 */
public class LastIdDAO {

    private String userId;
    private String productId;
    private String orderId;
    private String orderDetailId;
    Connect conn;

    public LastIdDAO() {
        getLastId();
        System.out.println("LastId: " + this.toString());
    }

    public LastIdDAO(String userId, String productId, String orderId, String orderDetailId) {
        this.userId = userId;
        this.productId = productId;
        this.orderId = orderId;
        this.orderDetailId = orderDetailId;
    }

    public void getLastId() {
        conn = new Connect();
        conn.getConnection();
        String query = "select * from LastId";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                setUserId(conn.rs.getString(1));
                setProductId(conn.rs.getString(2));
                setOrderId(conn.rs.getString(3));
                setOrderDetailId(conn.rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("DuLieuLast.executeQuery error.");
        }
        try {
            conn.getConn().close();
        } catch (SQLException e) {
            System.out.println("DuLieuLast.close error.");
        }
    }
    
    public boolean updateUserId(String newUserId) {
        conn = new Connect();
        conn.getConnection();
        String query = "UPDATE LastId SET"
                + " user_id='" + newUserId + "'"
                + " WHERE user_id='" + getUserId()+ "'";
        if (conn.executeUpdate(query)) {
            System.out.println("Update last UserId success.");
            setOrderId(newUserId);
            conn.close();
            return true;
        }
        System.out.println("Update last UserId fail.");
        conn.close();
        return false;
    }
    
    public boolean updateProductId(String newProductId) {
        conn = new Connect();
        conn.getConnection();
        String query = "UPDATE LastId SET"
                + " product_id='" + newProductId + "'"
                + " WHERE product_id='" + getProductId()+ "'";
        if (conn.executeUpdate(query)) {
            System.out.println("Update last ProductId success.");
            setProductId(newProductId);
            conn.close();
            return true;
        }
        System.out.println("Update last ProductId fail.");
        conn.close();
        return false;
    }

    public boolean updateOrderId(String newOrderId) {
        conn = new Connect();
        conn.getConnection();
        String query = "UPDATE LastId SET"
                + " order_id='" + newOrderId + "'"
                + " WHERE order_id='" + getOrderId() + "'";
        if (conn.executeUpdate(query)) {
            System.out.println("Update last OrderId success.");
            setOrderId(newOrderId);
            conn.close();
            return true;
        }
        System.out.println("Update last OrderId fail.");
        conn.close();
        return false;
    }
    
    public boolean updateOrderDetailId(String newOrderDetailId) {
        conn = new Connect();
        conn.getConnection();
        String query = "UPDATE LastId SET"
                + " order_detail_id='" + newOrderDetailId + "'"
                + " WHERE order_detail_id='" + getOrderDetailId() + "'";
        if (conn.executeUpdate(query)) {
            System.out.println("Update last OrderDetailId success.");
            setOrderDetailId(newOrderDetailId);
            conn.close();
            return true;
        }
        System.out.println("Update last OrderDetailId fail.");
        conn.close();
        return false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Connect getConn() {
        return conn;
    }

    public void setConn(Connect conn) {
        this.conn = conn;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    @Override
    public String toString() {
        return "LastIdDAO{" + "productId=" + productId + ", orderId=" + orderId + ", orderDetailId=" + orderDetailId + "}\n";
    }
}
