/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LastIdDAO;
import DAO.ProductDAO;
import DTO.ProductDTO;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ProductBUS {
//    ArrayList<ProductDTO> productDTOs = new ArrayList<>();
    ProductDAO productDAO = new ProductDAO();
    Utils ult = new Utils();
    LastIdDAO lastIdDAO = new LastIdDAO();
    
    public ArrayList<ProductDTO> getList () {
        return productDAO.getList();
    }
    
    public boolean updateProductQuantity (ProductDTO productDTO) {
        if (productDAO.updateProductQuantity(productDTO)) {
            System.out.println("Update product success BUS");
            return true;
        }
        System.out.println("Update product fail BUS");
        return false;
    }
    
    public boolean updateProductsQuantity (ArrayList<ProductDTO> productDTOs) {
        if (productDAO.updateProductsQuantity(productDTOs)) {
            System.out.println("Update products success BUS");
            return true;
        }
        System.out.println("Update products fail BUS");
        return false;
    }
    
    public boolean insertProduct (ProductDTO productDTO) {
        productDTO.setProductId(ult.initProductId());
        if (productDAO.insertProduct(productDTO)) {
            String temp[] = productDTO.getProductId().split("_");
            lastIdDAO.updateProductId(temp[1]);
            System.out.println("temp: " + temp[1]);
            System.out.println("insert success ProductBUS");
            return true;
        }
        System.out.println("insert fail ProductBUS");
        return false;
    }
    
    public boolean updateProduct (ProductDTO productDTO) {
        if (productDAO.updateProduct(productDTO)) {
            System.out.println("update success ProductBUS");
            return true;
        }
        System.out.println("update fail ProductBUS");
        return false;
    }
    
    public boolean deleteProduct (String productId) {
        if (productDAO.deleteProduct(productId)) {
            System.out.println("delete success ProductBUS");
            return true;
        }
        System.out.println("delete fail ProductBUS");
        return false;
    }
}
