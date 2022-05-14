/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BaoCaoDAO;
import DTO.BaoCaoDTO;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;
import DTO.ProductDTO;
import DTO.TagDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BaoCaoBUS {
    BaoCaoDAO baoCaoDAO = new BaoCaoDAO();

    public ArrayList<BaoCaoDTO> initBaoCao(ArrayList<OrderDTO> orders) {
        ArrayList<BaoCaoDTO> baoCaoDTOs = new ArrayList<>();
        for (OrderDTO order : orders) {
            baoCaoDTOs.addAll(baoCaoDTOs.size(), baoCaoDAO.getBaoCaosByOrderId(order.getOrderId()));
        }
        System.out.println("baoCaos: " + baoCaoDTOs);
//        BaoCaoDTO baoCao;
//        for (OrderDTO order : orders) {
//
//            for (OrderDetailDTO orderDetail : details) {
//                if (orderDetail.getOrderId().equals(order.getOrderId())) {
//                    baoCao = new BaoCaoDTO();
//                    baoCao.setOrder_id(order.getOrderId());
//                    baoCao.setOrder_date(order.getOrderDate());
//                    baoCao.setStatus(order.getStatus());
//                    baoCao.setProduct_id(orderDetail.getProductId());
//                    baoCao.setOrder_quantity(orderDetail.getOrderQuantity());
//                    for (ProductDTO product : products) {
//                        
//                    }
//                }
//            }
//        }

        return baoCaoDTOs;
    }
}
