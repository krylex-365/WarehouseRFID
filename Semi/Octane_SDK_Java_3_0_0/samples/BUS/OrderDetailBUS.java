/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LastIdDAO;
import DAO.OrderDetailDAO;
import DTO.OrderDetailDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class OrderDetailBUS {

    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
    Utils ult = new Utils();
    LastIdDAO lastIdDAO = new LastIdDAO();

    public ArrayList<OrderDetailDTO> getList() {
        return orderDetailDAO.getList();
    }

    public boolean insertOrderDetail(ArrayList<OrderDetailDTO> orderDetailDTOs) {
        ult.initOrderDetailId(orderDetailDTOs);
        //orderDetailDAO.insertOrderDetail(orderDetailDTOs)
        if (orderDetailDAO.insertOrderDetail(orderDetailDTOs)) {
            String temp[] = orderDetailDTOs.get(orderDetailDTOs.size() - 1).getOrderDetailId().split("_");
            System.out.println("temp[1] OrderDetail: " + temp[1]);
            lastIdDAO.updateOrderDetailId(temp[1]);
            return true;
        }
        return false;
    }

    public boolean addOrderDetail(HashMap<String, Integer> detailNew, String orderId) {
        ArrayList<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
        OrderDetailDTO orderDetailDTO;
        for (Map.Entry<String, Integer> entry : detailNew.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            orderDetailDTO = new OrderDetailDTO("", orderId, key, value);
            orderDetailDTOs.add(orderDetailDTO);
        }
        ult.initOrderDetailId(orderDetailDTOs);
        if (orderDetailDAO.insertOrderDetail(orderDetailDTOs)) {
            String temp[] = orderDetailDTOs.get(orderDetailDTOs.size() - 1).getOrderDetailId().split("_");
            System.out.println("temp[1] OrderDetail: " + temp[1]);
            lastIdDAO.updateOrderDetailId(temp[1]);
            return true;
        }
        return false;
    }

    public boolean deleteDetail(String orderId) {
        if (orderDetailDAO.deleteDetail(orderId)) {
            System.out.println("delete detail success BUS");
            return true;
        }
        System.out.println("delete detail fail BUS");
        return false;
    }
}
