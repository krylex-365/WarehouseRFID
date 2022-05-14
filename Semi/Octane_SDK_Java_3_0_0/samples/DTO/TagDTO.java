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
public class TagDTO {
    private String tagId;
    private String productId;
    private String tagGateIn;
    private String tagDateIn;
    private String tagGateOut;
    private String tagDateOut;
    private String orderId;

    public TagDTO() {
    }

    public TagDTO(String tagId, String productId, String tagGateIn, String tagDateIn, String tagGateOut, String tagDateOut, String orderId) {
        this.tagId = tagId;
        this.productId = productId;
        this.tagGateIn = tagGateIn;
        this.tagDateIn = tagDateIn;
        this.tagGateOut = tagGateOut;
        this.tagDateOut = tagDateOut;
        this.orderId = orderId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTagGateIn() {
        return tagGateIn;
    }

    public void setTagGateIn(String tagGateIn) {
        this.tagGateIn = tagGateIn;
    }

    public String getTagDateIn() {
        return tagDateIn;
    }

    public void setTagDateIn(String tagDateIn) {
        this.tagDateIn = tagDateIn;
    }

    public String getTagGateOut() {
        return tagGateOut;
    }

    public void setTagGateOut(String tagGateOut) {
        this.tagGateOut = tagGateOut;
    }

    public String getTagDateOut() {
        return tagDateOut;
    }

    public void setTagDateOut(String tagDateOut) {
        this.tagDateOut = tagDateOut;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "TagDTO{" + "tagId=" + tagId + ", productId=" + productId + ", tagGateIn=" + tagGateIn + ", tagDateIn=" + tagDateIn + ", tagGateOut=" + tagGateOut + ", tagDateOut=" + tagDateOut + ", orderId=" + orderId + "}\n";
    }
}
