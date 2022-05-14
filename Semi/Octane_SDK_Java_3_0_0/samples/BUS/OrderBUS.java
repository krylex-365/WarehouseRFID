/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LastIdDAO;
import DAO.OrderDAO;
import DTO.OrderDTO;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class OrderBUS {

    OrderDAO orderDAO = new OrderDAO();
    Utils ult = new Utils();
    LastIdDAO lastIdDAO = new LastIdDAO();

    public ArrayList<OrderDTO> getList() {
        return orderDAO.getList();
    }

    public boolean insertOrder(OrderDTO orderDTO) {
        //orderDAO.insertOrder(orderDTO)
        if (orderDAO.insertOrder(orderDTO)) {
            String temp[] = orderDTO.getOrderId().split("_");
            System.out.println("temp[1] Order: " + temp[1]);
            lastIdDAO.updateOrderId(temp[1]);
            System.out.println("insert order success BUS");
            return true;
        }
        System.out.println("insert order fail BUS");
        return false;
    }

    public boolean updateOrderCompleted(String orderId, String userId) {
        if (orderDAO.updateOrderCompleted(orderId, userId)) {
            System.out.println("update order completed success BUS");
            return true;
        }
        System.out.println("update order completed fail BUS");
        return false;
    }
    
    public boolean deleteOrder(String orderId) {
        if (orderDAO.deleteOrder(orderId)) {
            System.out.println("delete order success BUS");
            return true;
        }
        System.out.println("delete order fail BUS");
        return false;
    }
}
