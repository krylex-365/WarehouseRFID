/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.OrderBUS;
import BUS.OrderDetailBUS;
import BUS.ProductBUS;
import BUS.TagBUS;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;
import DTO.ProductDTO;
import DTO.TagDTO;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.example.sdksamples.MainRead;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DanhSachXuatForm extends javax.swing.JFrame {

    DefaultTableModel tbModelOrder, tbModelDetail;
//    Set<String> tags;
    ProductBUS productBUS = new ProductBUS();
    TagBUS tagBUS = new TagBUS();
    OrderBUS orderBUS = new OrderBUS();
    OrderDetailBUS orderDetailBUS = new OrderDetailBUS();
    int rowOrder = -2, rowDetail = -2;
    boolean isScanning = false;
    String orderId = "";
    public static String errorScan = "";
    public static ArrayList<TagDTO> tagDTOs = new ArrayList<>();
    public static HashMap<String, Integer> detailScan = new HashMap<>();
    ArrayList<OrderDetailDTO> details;
    ArrayList<ProductDTO> products;
    ArrayList<String> productsNot = new ArrayList<>();
//    ArrayList<String> productsNew = new ArrayList<>();
    HashMap<String, Integer> detailNew = new HashMap<>();
    ArrayList<ProductDTO> productsUpdate;
//    ArrayList<String> productsAdd = new ArrayList<>();
    int flag = 0, count2 = 0, count3 = 0; //test data

    /**
     * Creates new form DanhSachXuatForm
     */
    public DanhSachXuatForm() {
        initComponents();
//        initTableOrder();
        this.setVisible(false);
        jTableOrder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableDetail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        jBtnTest.setVisible(false);
    }

    public void clear() {
        tbModelDetail.setRowCount(0);
        jBtnRevert.setEnabled(false);
        jBtnQuet.setEnabled(false);
        jBtnHuy.setEnabled(false);
        jBtnXuat.setEnabled(false);
        jBtnXoa.setEnabled(false);
        rowOrder = -2;
        rowDetail = -2;
        isScanning = false;
        orderId = "";
        if (tagDTOs != null || !tagDTOs.isEmpty()) {
            tagDTOs.clear();
        }
        if (detailScan != null || !detailScan.isEmpty()) {
            detailScan.clear();
        }
        errorScan = "";
//        jTableOrder.setRowSelectionAllowed(true);
        jTableOrder.setEnabled(true);
    }

    public void tableModelOrder(DefaultTableModel model) {
        orderBUS = new OrderBUS();
        for (OrderDTO order : orderBUS.getList()) {
            Vector row = new Vector();
            row.add(order.getOrderId());
            row.add(order.getOrderDate());
            if (order.getStatus() == 2) {
                row.add("Chờ xuất");
            } else if (order.getStatus() == 3) {
                row.add("Hoàn tất");
            }
            model.addRow(row);
        }
    }

    public void initTableOrder() {
        clear();
//        orderDetailBUS = new OrderDetailBUS();
        details = orderDetailBUS.getList();
//        productBUS = new ProductBUS();
        products = productBUS.getList();
        tbModelOrder.setRowCount(0);
        tableModelOrder(tbModelOrder);
        jTableOrder.setRowSorter(null);
        jTableOrder.setAutoCreateRowSorter(true);
        jTableOrder.setModel(tbModelOrder);
    }

    public void tableModelDetail(DefaultTableModel model) {
        Vector row;
        for (OrderDetailDTO detail : details) {
            if (detail.getOrderId().equals(orderId)) {
                row = new Vector();
                if (tbModelOrder.getValueAt(rowOrder, 2).equals("Hoàn tất")) {
                    row.add("ok");
                } else {
                    row.add("missing " + detail.getOrderQuantity());
                }
                row.add(detail.getOrderDetailId());
                row.add(detail.getProductId());
                for (ProductDTO product : products) {
                    if (product.getProductId().equals(detail.getProductId())) {
                        row.add(product.getProductName());
                    }
                }
                row.add(detail.getOrderQuantity());
                model.addRow(row);
//                details.add(new OrderDetailDTO(detail.getOrderDetailId(), orderId, detail.getProductId(), detail.getOrderQuantity()));
            }
        }
    }

    public void initTableDetail() {
        tbModelDetail.setRowCount(0);
        tableModelDetail(tbModelDetail);
        jTableDetail.setModel(tbModelDetail);
    }

    public void checkError() {
        if (!errorScan.equals("")) {
            MainRead.flag = 0;
            JOptionPane.showMessageDialog(this, errorScan);
            jBtnXuat.setEnabled(false);
            errorScan = "";
        }
    }

    public void newDetail(String product_id, boolean error) {
        Vector row;
        row = new Vector();
        if (error) {
            row.add("invalid");
        } else {
            row.add("ok");
        }
        row.add("");
        row.add(product_id);
        for (ProductDTO product : products) {
            if (product.getProductId().equals(product_id)) {
                row.add(product.getProductName());
            }
        }
        row.add(detailScan.get(product_id));
        tbModelDetail.addRow(row);
    }

    public void updateDetail(String product_id) {
        int quantity;
        for (int i = 0; i < tbModelDetail.getRowCount(); i++) {
            if (tbModelDetail.getValueAt(i, 2).equals(product_id)) {
                quantity = detailScan.get(product_id);
                tbModelDetail.setValueAt(quantity, i, 4);
                return;
            }
        }
    }

    public void checkScan(String product_id) {
//        String error = "";
        int quantity, count = 0, quantityOrder;
        for (int i = 0; i < tbModelDetail.getRowCount(); i++) {
            if (product_id.equals(tbModelDetail.getValueAt(i, 2))) {
                count++;
            }
        }
        if (count == 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Sản phẩm " + product_id + " không thuộc đơn hàng!\n"
                    + "Bạn muốn thêm sản phẩm?",
                    "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                newDetail(product_id, false);
//                productsNew.add(product_id);
                detailNew.put(product_id, detailScan.get(product_id));
                jBtnRevert.setEnabled(true);
            } else {
//                flag = 0;
//                MainRead.flag = 0;
                newDetail(product_id, true);
                productsNot.add(product_id);
                jBtnRevert.setEnabled(true);
            }
            return;
        }
        if (detailNew.containsKey(product_id)) {
            detailNew.put(product_id, detailScan.get(product_id));
            updateDetail(product_id);
            return;
        } else if (productsNot.contains(product_id)) {
            updateDetail(product_id);
            return;
        }
        for (int i = 0; i < tbModelDetail.getRowCount(); i++) {
            if (detailScan.containsKey(tbModelDetail.getValueAt(i, 2))) {
                quantity = detailScan.get(tbModelDetail.getValueAt(i, 2));
                quantityOrder = Integer.parseInt(String.valueOf(tbModelDetail.getValueAt(i, 4)));
                if (quantity == quantityOrder) {
                    tbModelDetail.setValueAt("ok", i, 0);
                    java.awt.Toolkit.getDefaultToolkit().beep();
//                    break;
                } else if (quantity > quantityOrder) {
//                    int confirm = JOptionPane.showConfirmDialog(this, "Sản phẩm " + (String) tbModelDetail.getValueAt(i, 2) + " bị dư " + (quantity - quantityOrder) + "!\n"
//                            + "Bạn muốn thêm?",
//                            "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
//                    if (confirm == JOptionPane.OK_OPTION) {
//                        updateTable(product_id);
//                    } else {
                    tbModelDetail.setValueAt("redundant " + (quantity - quantityOrder), i, 0);
                    JOptionPane.showMessageDialog(this, "Sản phẩm " + (String) tbModelDetail.getValueAt(i, 2) + " bị dư " + (quantity - quantityOrder) + "!");
                    flag = 0;
                    MainRead.flag = 0;
                    jBtnRevert.setEnabled(true);
//                    }
//                    break;
//                    int beepCount = 1;
//                    for (int b = 0; b < beepCount; ++b) {
//                        // Ring the bell again using the Toolkit 
//                        java.awt.Toolkit.getDefaultToolkit().beep();
//                        try {
//                            Thread.sleep(600); // introduce delay
//                        } catch (InterruptedException e) {
//                        }
//                        java.awt.Toolkit.getDefaultToolkit().beep();
//                    }
                } else if (quantity < quantityOrder) {
                    tbModelDetail.setValueAt("missing " + (quantityOrder - quantity), i, 0);
//                    break;
                }
            }
        }
    }

    public void updateRemoveDetail(String product_id, boolean error) {
        int quantity, quantityOrder;
        for (int i = 0; i < tbModelDetail.getRowCount(); i++) {
            if (tbModelDetail.getValueAt(i, 2).equals(product_id)) {
                quantity = detailScan.get(tbModelDetail.getValueAt(i, 2));
                tbModelDetail.setValueAt(quantity, i, 4);
                if (quantity == 0 && error) {
                    productsNot.remove(product_id);
                    tbModelDetail.removeRow(i);
                } else if (quantity == 0 && !error) {
                    detailNew.remove(product_id);
//                    productsNew.remove(product_id);
                    tbModelDetail.removeRow(i);
                }
                return;
            }
        }
    }

    public void checkScanRevert(String product_id, String tagId) {
//        String error = "";
        int quantity, count = 0, quantityOrder;
//        System.out.println("proid: " + product_id);
//        for (int i = 0; i < tbModelDetail.getRowCount(); i++) {
//            if (product_id.equals(tbModelDetail.getValueAt(i, 2))) {
//                count++;
//            }
//        }
//        if (count == 0) {
//            System.out.println("vo day 1");
//        }
        if (detailNew.containsKey(product_id)) {
            detailNew.put(product_id, detailScan.get(product_id));
            updateRemoveDetail(product_id, false);
            return;
        } else if (productsNot.contains(product_id)) {
            updateRemoveDetail(product_id, true);
            return;
        }
//        System.out.println("vo day 2");
        for (int i = 0; i < tbModelDetail.getRowCount(); i++) {
            if (detailScan.containsKey(tbModelDetail.getValueAt(i, 2))) {
                quantity = detailScan.get(tbModelDetail.getValueAt(i, 2));
                quantityOrder = Integer.parseInt(String.valueOf(tbModelDetail.getValueAt(i, 4)));
                if (quantity == quantityOrder) {
                    tbModelDetail.setValueAt("ok", i, 0);
//                    break;
                } else if (quantity > quantityOrder) {
                    tbModelDetail.setValueAt("redundant " + (quantity - quantityOrder), i, 0);
//                    break;
                } else if (quantity < quantityOrder) {
                    tbModelDetail.setValueAt("missing " + (quantityOrder - quantity), i, 0);
//                    break;
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOrder = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDetail = new javax.swing.JTable();
        jBtnXuat = new javax.swing.JButton();
        jBtnQuet = new javax.swing.JButton();
        jBtnHuy = new javax.swing.JButton();
        jBtnXoa = new javax.swing.JButton();
        jBtnRevert = new javax.swing.JButton();
        jBtnTest = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ ĐƠN XUẤT");
        jLabel1.setToolTipText("");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 13, 243, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("DANH SÁCH ĐƠN");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 163, 40));

        Vector tableCol = new Vector();
        tableCol.add("Order ID");
        tableCol.add("Order Date");
        tableCol.add("Status");

        tbModelOrder = new DefaultTableModel (tableCol,0){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }
        };
        jTableOrder.setModel (tbModelOrder);
        jTableOrder.setShowGrid(true);
        jTableOrder.setFocusable(false);
        jTableOrder.setIntercellSpacing(new Dimension(0,0));
        jTableOrder.setRowHeight(25);
        jTableOrder.getTableHeader().setOpaque(false);
        jTableOrder.setFillsViewportHeight(true);
        //        jTableOrder.getTableHeader().setBackground(new Color(145,209,242));
        //        jTableOrder.getTableHeader().setForeground(new Color(51, 51, 51));
        jTableOrder.getTableHeader().setFont (new Font("Dialog", Font.BOLD, 13));
        //        jTableOrder.setSelectionBackground(new Color(76,121,255));
        jTableOrder.setAutoCreateRowSorter(true);
        jTableOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableOrder.setGridColor(new java.awt.Color(83, 86, 88));
        jTableOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableOrderMouseClicked(evt);
            }
        });
        jTableOrder.getColumn (tableCol.elementAt (0)).setPreferredWidth (100);
        jTableOrder.getColumn (tableCol.elementAt (1)).setPreferredWidth (150);
        jTableOrder.getColumn (tableCol.elementAt (2)).setPreferredWidth (100);
        jTableOrder.setAutoResizeMode (javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTableOrder);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 360, 290));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("CHI TIẾT ĐƠN");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 163, 40));

        Vector tableColDetail = new Vector();
        tableColDetail.add("Status");
        tableColDetail.add("OrderDetail ID");
        tableColDetail.add("Product ID");
        tableColDetail.add("Product Name");
        tableColDetail.add("Quantity");

        tbModelDetail = new DefaultTableModel (tableColDetail,0){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }
        };
        jTableDetail.setModel (tbModelDetail);
        jTableDetail.setShowGrid(true);
        jTableDetail.setFocusable(false);
        jTableDetail.setIntercellSpacing(new Dimension(0,0));
        jTableDetail.setRowHeight(25);
        jTableDetail.getTableHeader().setOpaque(false);
        jTableDetail.setFillsViewportHeight(true);
        //        jTableOrder.getTableHeader().setBackground(new Color(145,209,242));
        //        jTableOrder.getTableHeader().setForeground(new Color(51, 51, 51));
        jTableDetail.getTableHeader().setFont (new Font("Dialog", Font.BOLD, 13));
        //        jTableOrder.setSelectionBackground(new Color(76,121,255));
        jTableDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableDetail.setGridColor(new java.awt.Color(83, 86, 88));
        jTableDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDetailMouseClicked(evt);
            }
        });
        jTableDetail.getColumn (tableColDetail.elementAt (0)).setPreferredWidth (100);
        jTableDetail.getColumn (tableColDetail.elementAt (1)).setPreferredWidth (100);
        jTableDetail.getColumn (tableColDetail.elementAt (2)).setPreferredWidth (100);
        jTableDetail.getColumn (tableColDetail.elementAt (3)).setPreferredWidth (150);
        jTableDetail.getColumn (tableColDetail.elementAt (4)).setPreferredWidth (100);
        jTableDetail.setAutoResizeMode (javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(jTableDetail);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 400, 290));

        jBtnXuat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnXuat.setText("THỰC HIỆN XUẤT");
        jBtnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnXuatActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 430, -1, -1));

        jBtnQuet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnQuet.setText("QUÉT HÀNG XUẤT");
        jBtnQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnQuetActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnQuet, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 430, 140, -1));

        jBtnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnHuy.setText("HỦY QUÉT");
        jBtnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, 120, -1));

        jBtnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnXoa.setText("XÓA ĐƠN");
        jBtnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 120, -1));

        jBtnRevert.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnRevert.setText("REVERT");
        jBtnRevert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRevertActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnRevert, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 120, -1));

        jBtnTest.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnTest.setText("TEST");
        jBtnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTestActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnTest, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, 799, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, 483, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOrderMouseClicked
        // TODO add your handling code here:
        if (isScanning) {
            return;
        }
        int row = jTableOrder.getSelectedRow();
        if (row == rowOrder) {
            jTableOrder.clearSelection();
            jBtnQuet.setEnabled(false);
            jBtnXoa.setEnabled(false);
            rowOrder = -2;
            orderId = "";
            tbModelDetail.setRowCount(0);
            return;
        }
        if (row != -1) {
            rowOrder = row;
            orderId = (String) jTableOrder.getValueAt(row, 0);
            initTableDetail();
            if (tbModelOrder.getValueAt(rowOrder, 2).equals("Hoàn tất")) {
                orderId = "";
                jBtnHuy.setEnabled(false);
                jBtnQuet.setEnabled(false);
                jBtnXuat.setEnabled(false);
                jBtnXoa.setEnabled(false);
                return;
            }
            jBtnQuet.setEnabled(true);
            jBtnXoa.setEnabled(true);
        }
        System.out.println("flag: " + MainRead.flag);
    }//GEN-LAST:event_jTableOrderMouseClicked

    private void jTableDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDetailMouseClicked
        // TODO add your handling code here:
        int row = jTableDetail.getSelectedRow();
        if (row == rowDetail) {
            jTableDetail.clearSelection();
            rowDetail = -2;
            return;
        }
        if (row != -1) {
            rowDetail = row;
        }
    }//GEN-LAST:event_jTableDetailMouseClicked

    public String checkQuantity() {
        String error = "";
        ProductDTO pNew;
        for (ProductDTO p : products) {
            if (detailNew.containsKey(p.getProductId())) {
                int pUpdateQuantity = p.getProductQuantity() - detailNew.get(p.getProductId());
                if (pUpdateQuantity < 0) {
                    error += p.getProductId() + "\n";
                } else {
                    pNew = new ProductDTO(p.getProductId(), p.getProductName(), pUpdateQuantity, p.getProductDetail());
                    productsUpdate.add(p);
                }
            }
        }
        if (!error.equals("")) {
            error = "Số lượng còn lại của (các) sản phẩm không thể đáp ứng:\n" + error + "---------------------------------------------------------------\n";
        }
        return error;
    }

    private void jBtnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnXuatActionPerformed
        // TODO add your handling code here:
        String error = checkQuantity();
        int count = 0;
        for (int i = 0; i < tbModelDetail.getRowCount(); i++) {
            if (tbModelDetail.getValueAt(i, 0).equals("ok")) {
                count++;
            } else if (tbModelDetail.getValueAt(i, 0).toString().contains("redundant")) {
                error += "Sản phẩm " + tbModelDetail.getValueAt(i, 2) + " bị dư\n";
            } else if (tbModelDetail.getValueAt(i, 0).toString().contains("missing")) {
                error += "Sản phẩm " + tbModelDetail.getValueAt(i, 2) + " chưa hoàn tất\n";
            }
        }
        for (String s : productsNot) {
            error += "Sản phẩm " + s + " không thuộc đơn hàng!\n";
        }
        if (!error.equals("")) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }
        if (count == tbModelDetail.getRowCount()) {
            if (!detailNew.isEmpty()) {
                // orderDetailBUS.addOrderDetail(detailNew, orderId) && productBUS.updateProductsQuantity(productsUpdate)
            }
            //orderBUS.updateOrderCompleted(orderId, Dashboard.userLogin.getUserId())
            if (true) { // cập nhật đơn
                for (TagDTO a : tagDTOs) {
                    a.setOrderId(orderId);
                }
                //tagBUS.updateTagsOut(tagDTOs)
                if (true) { // cập nhật date và gate out cho tag
                    tbModelOrder.setValueAt("Hoàn tất", rowOrder, 2);
                    JOptionPane.showMessageDialog(this, "Xuất đơn thành công!");
                    MainRead.flag = 0;
                    MainRead.tagMap.clear();
                    clear();
                }
            }
        }
    }//GEN-LAST:event_jBtnXuatActionPerformed

    private void jBtnQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnQuetActionPerformed
        // TODO add your handling code here:
        if (orderId.equals("")) {
            JOptionPane.showMessageDialog(this, "Order Id null!");
            return;
        }
//        jTableOrder.setRowSelectionAllowed(false);
        jTableOrder.setEnabled(false);
        MainRead.flag = 2;
        MainRead.tagDTOsMR = tagBUS.getList();
//        MainRead.thucThi();
        isScanning = true;
        jBtnXoa.setEnabled(false);
        jBtnHuy.setEnabled(true);
        jBtnQuet.setEnabled(false);
        jBtnXuat.setEnabled(true);
        jBtnRevert.setEnabled(false);
        flag = 2;
        System.out.println("flag: " + MainRead.flag);
    }//GEN-LAST:event_jBtnQuetActionPerformed

    private void jBtnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHuyActionPerformed
        // TODO add your handling code here:
//        tbModelDetail.setRowCount(0);
        initTableDetail();
//        isScanning = false;
        MainRead.flag = 0;
        MainRead.tagMap.clear();
        detailScan.clear();
        jTableOrder.clearSelection();
        clear();
        count2 = 0;
        count3 = 0;
    }//GEN-LAST:event_jBtnHuyActionPerformed

    private void jBtnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnXoaActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa đơn?",
                "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        if (confirm == JOptionPane.OK_OPTION) {
            if (orderId.equals("")) {
                JOptionPane.showMessageDialog(this, "Order Id null!");
                return;
            }
            ArrayList<ProductDTO> tempUpdate = new ArrayList<>();
            ProductDTO productDTO;
            for (OrderDetailDTO detail : details) {
                if (detail.getOrderId().equals(orderId)) {
                    productDTO = new ProductDTO();
                    productDTO.setProductId(detail.getProductId());
                    for (ProductDTO product : products) {
                        if (product.getProductId().equals(detail.getProductId())) {
                            productDTO.setProductQuantity(detail.getOrderQuantity() + product.getProductQuantity());
                        }
                    }
                    tempUpdate.add(productDTO);
//                details.add(new OrderDetailDTO(detail.getOrderDetailId(), orderId, detail.getProductId(), detail.getOrderQuantity()));
                }
            }
            if (!orderDetailBUS.deleteDetail(orderId)) {
                JOptionPane.showMessageDialog(this, "Xóa chi tiết đơn thất bại!");
                return;
            }
            if (!orderBUS.deleteOrder(orderId)) {
                JOptionPane.showMessageDialog(this, "Xóa đơn thất bại!");
                return;
            }
            if (!productBUS.updateProductsQuantity(tempUpdate)) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!");
            }
            tbModelOrder.removeRow(rowOrder);
            JOptionPane.showMessageDialog(this, "Xóa đơn thành công!");
            clear();
        }
    }//GEN-LAST:event_jBtnXoaActionPerformed

    private void jBtnRevertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRevertActionPerformed
        // TODO add your handling code here:
        MainRead.flag = 3;
//        MainRead.tagMap.clear();
//        isScanning = false;
        flag = 3;
        jBtnXoa.setEnabled(false);
        jBtnHuy.setEnabled(true);
        jBtnQuet.setEnabled(true);
        jBtnXuat.setEnabled(true);
        jBtnRevert.setEnabled(false);
    }//GEN-LAST:event_jBtnRevertActionPerformed

    private void jBtnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTestActionPerformed
        // TODO add your handling code here:
        if (flag == 2) {
            count2++;
        } else if (flag == 3) {
            count3++;
        } else if (flag == 0) {
            JOptionPane.showMessageDialog(this, "Nothing to do! Please choose action.");
        }
        System.out.println("flag/count2/count3: " + flag + "/" + count2 + "/" + count3);
        MainRead.testScan(flag, count2, count3);
    }//GEN-LAST:event_jBtnTestActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DanhSachXuatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DanhSachXuatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DanhSachXuatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DanhSachXuatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DanhSachXuatForm().setVisible(true);
            }
        });
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnHuy;
    private javax.swing.JButton jBtnQuet;
    private javax.swing.JButton jBtnRevert;
    private javax.swing.JButton jBtnTest;
    private javax.swing.JButton jBtnXoa;
    private javax.swing.JButton jBtnXuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDetail;
    private javax.swing.JTable jTableOrder;
    // End of variables declaration//GEN-END:variables
}
