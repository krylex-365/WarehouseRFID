/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class BaoCaoDTO {
    private String order_id;
    private String order_date;
    private String user_created;
    private String user_completed;
    private int status;
    private String product_id;
    private String product_name;
    private int order_quantity;
    private String tag_id;
    private String tag_gate_in;
    private String tag_date_in;
    private String tag_gate_out;
    private String tag_date_out;

    public BaoCaoDTO() {
    }

    public BaoCaoDTO(String order_id, String order_date, String user_created, String user_completed, int status, String product_id, String product_name, int order_quantity, String tag_id, String tag_gate_in, String tag_date_in, String tag_gate_out, String tag_date_out) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.user_created = user_created;
        this.user_completed = user_completed;
        this.status = status;
        this.product_id = product_id;
        this.product_name = product_name;
        this.order_quantity = order_quantity;
        this.tag_id = tag_id;
        this.tag_gate_in = tag_gate_in;
        this.tag_date_in = tag_date_in;
        this.tag_gate_out = tag_gate_out;
        this.tag_date_out = tag_date_out;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getUser_created() {
        return user_created;
    }

    public void setUser_created(String user_created) {
        this.user_created = user_created;
    }

    public String getUser_completed() {
        return user_completed;
    }

    public void setUser_completed(String user_completed) {
        this.user_completed = user_completed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(int order_quantity) {
        this.order_quantity = order_quantity;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_gate_in() {
        return tag_gate_in;
    }

    public void setTag_gate_in(String tag_gate_in) {
        this.tag_gate_in = tag_gate_in;
    }

    public String getTag_date_in() {
        return tag_date_in;
    }

    public void setTag_date_in(String tag_date_in) {
        this.tag_date_in = tag_date_in;
    }

    public String getTag_gate_out() {
        return tag_gate_out;
    }

    public void setTag_gate_out(String tag_gate_out) {
        this.tag_gate_out = tag_gate_out;
    }

    public String getTag_date_out() {
        return tag_date_out;
    }

    public void setTag_date_out(String tag_date_out) {
        this.tag_date_out = tag_date_out;
    }

    @Override
    public String toString() {
        return "BaoCaoDTO{" + "order_id=" + order_id + ", order_date=" + order_date + ", user_created=" + user_created + ", user_completed=" + user_completed + ", status=" + status + ", product_id=" + product_id + ", product_name=" + product_name + ", order_quantity=" + order_quantity + ", tag_id=" + tag_id + ", tag_gate_in=" + tag_gate_in + ", tag_date_in=" + tag_date_in + ", tag_gate_out=" + tag_gate_out + ", tag_date_out=" + tag_date_out + "}\n";
    }
}
