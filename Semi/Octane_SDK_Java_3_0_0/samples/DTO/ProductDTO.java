/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author User
 */
public class ProductDTO {
    private String productId;
    private String productName;
    private int productQuantity;
    private String productDetail;

    public ProductDTO() {
    }

    public ProductDTO(String productId, String productName, int productQuantity, String productDetail) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productDetail = productDetail;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productId=" + productId + ", productName=" + productName + ", quantity=" + productQuantity + ", detail=" + productDetail + "}\n";
    }
}
