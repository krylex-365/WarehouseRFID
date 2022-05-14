/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.BaoCaoDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BaoCaoDAO {

    Connect conn;

    public BaoCaoDAO() {
    }

    public ArrayList<BaoCaoDTO> getBaoCaosByOrderId(String orderId) {
        ArrayList<BaoCaoDTO> orderDTOs = new ArrayList<BaoCaoDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select op.order_id,op.order_date,op.status,op.product_id,op.product_name,op.order_quantity,Tag.tag_id,Tag.tag_gate_in,Tag.tag_date_in,Tag.tag_gate_out,Tag.tag_date_out,(select User_App.full_name from User_App where op.created_by = User_App.user_id) cr_usename,(select User_App.full_name from User_App where op.completed_by = User_App.user_id) cpl_usename \n"
                + "from (select temp.order_id,temp.product_id,Product.product_name,temp.order_date,temp.order_quantity,temp.created_by,temp.completed_by,temp.status from  \n"
                + "(select Order_Detail.order_id,Order_Detail.product_id,Order_Detail.order_quantity,Order_Product.created_by,Order_Product.completed_by,Order_Product.order_date,Order_Product.status \n"
                + "from Order_Detail left join Order_Product \n"
                + "on Order_Detail.order_id = Order_Product.order_id) temp left join Product \n"
                + "on temp.product_id = Product.product_id) op left join Tag on Tag.order_id = op.order_id and Tag.product_id = op.product_id \n"
                + "where op.order_id = '" + orderId + "';";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                BaoCaoDTO dto = new BaoCaoDTO();
                dto.setOrder_id(conn.rs.getString(1));
                dto.setOrder_date(conn.rs.getString(2));
                dto.setStatus(conn.rs.getInt(3));
                dto.setProduct_id(conn.rs.getString(4));
                dto.setProduct_name(conn.rs.getString(5));
                dto.setOrder_quantity(conn.rs.getInt(6));
                dto.setTag_id(conn.rs.getString(7));
                dto.setTag_gate_in(conn.rs.getString(8));
                dto.setTag_date_in(conn.rs.getString(9));
                dto.setTag_gate_out(conn.rs.getString(10));
                dto.setTag_date_out(conn.rs.getString(11));
                dto.setUser_created(conn.rs.getString(12));
                dto.setUser_completed(conn.rs.getString(13));
                orderDTOs.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("BaoCaoDAO.getList.executeQuery error.");
        }
        try {
            conn.getConn().close();
        } catch (SQLException e) {
            System.out.println("BaoCaoDAO.getList.close error.");
        }
        return orderDTOs;
    }
}
