/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author User
 */
public class OrderDTO {
    private String orderId;
    private String orderDate;
    private String userCreated;
    private String userCompleted;
    private int status;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String orderDate, String userCreated, String userCompleted, int status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.userCreated = userCreated;
        this.userCompleted = userCompleted;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserCompleted() {
        return userCompleted;
    }

    public void setUserCompleted(String userCompleted) {
        this.userCompleted = userCompleted;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", userCreated=" + userCreated + ", userCompleted=" + userCompleted + ", status=" + status + "}\n";
    }
}
