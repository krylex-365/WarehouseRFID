/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.UserBUS;
import DTO.UserDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class UserForm extends javax.swing.JFrame {

    DefaultTableModel tbModelUser;
    int rowUser = -2;
    String userId = "", userName = "";
    int role = 0;
    UserBUS userBUS = new UserBUS();
    ArrayList<UserDTO> userDTOs = new ArrayList<>();

    /**
     * Creates new form UserForm
     */
    public UserForm() {
        initComponents();
        jTableUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setVisible(false);
//        initTableUser();
    }

    public void clear() {
        userId = "";
        userName = "";
        rowUser = -2;
        jTextUsername.setText("");
        jTextFullname.setText("");
        jTextPhoneNum.setText("");
        jTextMail.setText("");
        jBtnThem.setEnabled(true);
        jBtnSua.setEnabled(false);
        jBtnXoa.setEnabled(false);
        jBtnHuy.setEnabled(false);
        jBtnBlock.setVisible(true);
        jBtnBlock.setEnabled(false);
        jBtnUnblock.setVisible(false);
        jBtnUnblock.setEnabled(false);
        jCheckBoxMkMoi.setEnabled(false);
        jCheckBoxMkMoi.setSelected(false);
        jPasswordField.setText("");
        jPasswordField.setEditable(true);
        jPasswordField.setBackground(new Color(255, 255, 255));
        jTableUser.clearSelection();
    }

    public void tableModelUser(DefaultTableModel model, ArrayList<UserDTO> userDTOs) {
        for (UserDTO user : userDTOs) {
            Vector row = new Vector();
            row.add(user.getUserId());
            row.add(user.getUserName());
            row.add(user.getFullName());
            row.add(user.getPhoneNum());
            row.add(user.getMail());
            if (user.getRole() == 1) {
                row.add("Admin");
            } else if (user.getRole() == 2) {
                row.add("Staff");
            } else {
                row.add("Blocked");
            }
            model.addRow(row);
        }
    }

    public void initTableUser() {
        clear();
        userDTOs = userBUS.getList();
        tbModelUser.setRowCount(0);
        tableModelUser(tbModelUser, userDTOs);
        jTableUser.setModel(tbModelUser);
    }

    public String validation() {
        String validate = "";
        if (jTextUsername.getText().equals("") || jTextFullname.getText().equals("")
                || jTextPhoneNum.getText().equals("") || jTextMail.getText().equals("") || jPasswordField.getText().equals("")) {
            validate += "Các trường thông tin không được bỏ trống!\n";
            return validate;
        } else {
            String tkPattern = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
            String hotenPattern = "^[(AÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐaáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵđ|a-z|A-Z|\\s|'.]{1,80}$";
            String sdtPattern = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
            String emailPattern = "^[a-zA-Z0-9][a-zA-Z0-9_\\.]{4,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";

            boolean flag1 = Pattern.matches(tkPattern, jTextUsername.getText());
            if (!flag1) {
                validate += "Tên tài khoản không hợp lệ!\n";
            }
            if (jPasswordField.getText().length() < 6) {
                validate += "Mật khẩu tối thiểu 6 ký tự!\n";
            }
            boolean flag2 = Pattern.matches(hotenPattern, jTextFullname.getText());
            if (!flag2) {
                validate += "Họ tên không hợp lệ!\n";
            }
            boolean flag3 = Pattern.matches(sdtPattern, jTextPhoneNum.getText());
            if (!flag3) {
                validate += "Số điện thoại không hợp lệ!\n";
            }
            boolean flag4 = Pattern.matches(emailPattern, jTextMail.getText());
            if (!flag4) {
                validate += "Email không hợp lệ!\n";
            }
        }
        return validate;
    }

    public void themVector(DefaultTableModel model, UserDTO userDTO) {
        Vector newrow = new Vector();
        newrow.add(userDTO.getUserId());
        newrow.add(userDTO.getUserName());
        newrow.add(userDTO.getFullName());
        newrow.add(userDTO.getPhoneNum());
        newrow.add(userDTO.getMail());
        newrow.add("Staff");
        model.addRow(newrow);
    }

    public void suaVector(DefaultTableModel model, int row, UserDTO userDTO) {
        model.setValueAt(userDTO.getUserName(), row, 1);
        model.setValueAt(userDTO.getFullName(), row, 2);
        model.setValueAt(userDTO.getPhoneNum(), row, 3);
        model.setValueAt(userDTO.getMail(), row, 4);
    }

    public void xoaVector(DefaultTableModel model, int row) {
        model.removeRow(row);
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
        jBtnHuy = new javax.swing.JButton();
        jBtnThem = new javax.swing.JButton();
        jBtnSua = new javax.swing.JButton();
        jBtnBlock = new javax.swing.JButton();
        jBtnXoa = new javax.swing.JButton();
        jBtnUnblock = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUser = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextUsername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFullname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextPhoneNum = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextMail = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jCheckBoxMkMoi = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ NGƯỜI DÙNG");
        jLabel1.setToolTipText("");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 13, 290, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("DANH SÁCH NGƯỜI DÙNG");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 163, 40));

        jBtnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnHuy.setText("HỦY");
        jBtnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 90, 30));

        jBtnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnThem.setText("THÊM");
        jBtnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnThemActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 90, 30));

        jBtnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnSua.setText("SỬA");
        jBtnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 90, 30));

        jBtnBlock.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnBlock.setText("BLOCK");
        jBtnBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBlockActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnBlock, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 90, 30));

        jBtnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnXoa.setText("XÓA");
        jBtnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 90, 30));

        jBtnUnblock.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnUnblock.setText("UNBLOCK");
        jBtnUnblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUnblockActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnUnblock, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 90, 30));

        Vector tableCol = new Vector();
        tableCol.add("User ID");
        tableCol.add("Username");
        tableCol.add("Full Name");
        tableCol.add("Phone");
        tableCol.add("Mail");
        tableCol.add("Role");

        tbModelUser = new DefaultTableModel (tableCol,0){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }
        };
        jTableUser.setModel (tbModelUser);
        jTableUser.setShowGrid(true);
        jTableUser.setFocusable(false);
        jTableUser.setIntercellSpacing(new Dimension(0,0));
        jTableUser.setRowHeight(25);
        jTableUser.getTableHeader().setOpaque(false);
        jTableUser.setFillsViewportHeight(true);
        //        jTableUser.getTableHeader().setBackground(new Color(145,209,242));
        //        jTableUser.getTableHeader().setForeground(new Color(51, 51, 51));
        jTableUser.getTableHeader().setFont (new Font("Dialog", Font.BOLD, 13));
        //        jTableUser.setSelectionBackground(new Color(76,121,255));
        jTableUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableUser.setGridColor(new java.awt.Color(83, 86, 88));
        jTableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUserMouseClicked(evt);
            }
        });
        jTableUser.getColumn (tableCol.elementAt (0)).setPreferredWidth (100);
        jTableUser.getColumn (tableCol.elementAt (1)).setPreferredWidth (150);
        jTableUser.getColumn (tableCol.elementAt (2)).setPreferredWidth (150);
        jTableUser.getColumn (tableCol.elementAt (3)).setPreferredWidth (120);
        jTableUser.getColumn (tableCol.elementAt (4)).setPreferredWidth (150);
        jTableUser.getColumn (tableCol.elementAt (5)).setPreferredWidth (100);
        jTableUser.setAutoResizeMode (javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTableUser);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 420, 360));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Username");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, 30));
        jPanel1.add(jTextUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 200, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Password");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 90, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Full name");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 90, 30));
        jPanel1.add(jTextFullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 200, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Phone number");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 90, 30));
        jPanel1.add(jTextPhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 200, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Mail");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 90, 30));
        jPanel1.add(jTextMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 200, 30));

        jPasswordField.setBackground(new java.awt.Color(214, 217, 223));
        jPanel1.add(jPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 200, 30));

        jCheckBoxMkMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMkMoiActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBoxMkMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 20, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("THÔNG TIN NGƯỜI DÙNG");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 70, 170, 40));

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

    private void jBtnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHuyActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jBtnHuyActionPerformed

    private void jBtnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSuaActionPerformed
        // TODO add your handling code here:
        String check = validation();
        if (check.equals("")) { //validation
            for (UserDTO a : userDTOs) {
                if (a.getUserName().equals(jTextUsername.getText()) && !a.getUserName().equals(userName)) {
                    JOptionPane.showMessageDialog(this, "Sửa user thất bại! Username đã tồn tại!");
                    return;
                }
            }
            boolean checkPw = false;
            if (jCheckBoxMkMoi.isSelected()) {
                checkPw = true;
            }
            UserDTO userDTO = new UserDTO((String) tbModelUser.getValueAt(rowUser, 0), (String) jTextUsername.getText(), jPasswordField.getText(),
                    (String) jTextFullname.getText(), (String) jTextPhoneNum.getText(), (String) jTextMail.getText(), 2);
            if (userBUS.updateUser(userDTO, checkPw)) {
                suaVector(tbModelUser, rowUser, userDTO);
                JOptionPane.showMessageDialog(this, "Sửa user thành công!");
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa user thất bại!");
            }
        } else {
            JOptionPane.showMessageDialog(this, check);
        }
    }//GEN-LAST:event_jBtnSuaActionPerformed

    private void jBtnBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBlockActionPerformed
        // TODO add your handling code here:
        if (userId != null || !userId.equals("")) {
            if (userBUS.updateStatusUser(userId, role - 4)) {
                tbModelUser.setValueAt("Blocked", rowUser, 5);
                JOptionPane.showMessageDialog(this, "Block user thành công!");
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Block user thất bại!");
            }
        }
    }//GEN-LAST:event_jBtnBlockActionPerformed

    private void jTableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUserMouseClicked
        // TODO add your handling code here:
        int row = jTableUser.getSelectedRow();
        if (row == rowUser) {
            clear();
            return;
        }
        if (row != -1) {
            rowUser = row;
            userId = (String) jTableUser.getValueAt(row, 0);
            userName = (String) jTableUser.getValueAt(row, 1);
            jTextUsername.setText(userName);
            jTextFullname.setText((String) jTableUser.getValueAt(row, 2));
            jTextPhoneNum.setText((String) jTableUser.getValueAt(row, 3));
            jTextMail.setText((String) jTableUser.getValueAt(row, 4));
            jPasswordField.setText("******");
            if (tbModelUser.getValueAt(rowUser, 5).equals("Blocked")) {
                jBtnBlock.setVisible(false);
                jBtnBlock.setEnabled(false);
                jBtnUnblock.setVisible(true);
                jBtnUnblock.setEnabled(true);
                jBtnXoa.setEnabled(true);
                role = -2;
            } else if (tbModelUser.getValueAt(rowUser, 5).equals("Admin")) {
                jBtnBlock.setVisible(true);
                jBtnBlock.setEnabled(false);
                jBtnUnblock.setVisible(false);
                jBtnUnblock.setEnabled(false);
                jBtnXoa.setEnabled(false);
                role = 1;
            } else {
                jBtnBlock.setVisible(true);
                jBtnBlock.setEnabled(true);
                jBtnUnblock.setVisible(false);
                jBtnUnblock.setEnabled(false);
                jBtnXoa.setEnabled(true);
                role = 2;
            }
            jBtnThem.setEnabled(false);
            jBtnSua.setEnabled(true);
            jBtnHuy.setEnabled(true);
            jCheckBoxMkMoi.setEnabled(true);
            jPasswordField.setEditable(false);
            jPasswordField.setBackground(new Color(214, 217, 223));
        }
    }//GEN-LAST:event_jTableUserMouseClicked

    private void jBtnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnThemActionPerformed
        // TODO add your handling code here:
        String check = validation();
        if (check.equals("")) { //validation
            for (UserDTO a : userDTOs) {
                if (a.getUserName().equals(jTextUsername.getText())) {
                    JOptionPane.showMessageDialog(this, "Thêm user thất bại! Username đã tồn tại!");
                    return;
                }
            }
            UserDTO userDTO = new UserDTO("", (String) jTextUsername.getText(), jPasswordField.getText(),
                    (String) jTextFullname.getText(), (String) jTextPhoneNum.getText(), (String) jTextMail.getText(), 2);
            if (userBUS.insertUser(userDTO)) {
                themVector(tbModelUser, userDTO);
                JOptionPane.showMessageDialog(this, "Thêm user thành công!");
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm user thất bại!");
            }
        } else {
            JOptionPane.showMessageDialog(this, check);
        }
    }//GEN-LAST:event_jBtnThemActionPerformed

    private void jBtnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnXoaActionPerformed
        // TODO add your handling code here:
        if (userId != null || !userId.equals("")) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa user?",
                    "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                if (userBUS.removeUser(userId)) {
                    xoaVector(tbModelUser, rowUser);
                    JOptionPane.showMessageDialog(this, "Xóa user thành công!");
                    clear();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa user thất bại!");
                }
            }
        }
    }//GEN-LAST:event_jBtnXoaActionPerformed

    private void jBtnUnblockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUnblockActionPerformed
        // TODO add your handling code here:
        if (userId != null || !userId.equals("")) {
            if (userBUS.updateStatusUser(userId, role + 4)) {
                tbModelUser.setValueAt("Staff", rowUser, 5);
                JOptionPane.showMessageDialog(this, "Unblock user thành công!");
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Unblock user thất bại!");
            }
        }
    }//GEN-LAST:event_jBtnUnblockActionPerformed

    private void jCheckBoxMkMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMkMoiActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxMkMoi.isSelected()) {
            jPasswordField.setEditable(true);
            jPasswordField.setBackground(new Color(255, 255, 255));
            jPasswordField.setText("");
        }
        if (!jCheckBoxMkMoi.isSelected()) {
            jPasswordField.setEditable(false);
            jPasswordField.setBackground(new Color(214, 217, 223));
            jPasswordField.setText("******");
        }
    }//GEN-LAST:event_jCheckBoxMkMoiActionPerformed

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

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
            java.util.logging.Logger.getLogger(UserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBlock;
    private javax.swing.JButton jBtnHuy;
    private javax.swing.JButton jBtnSua;
    private javax.swing.JButton jBtnThem;
    private javax.swing.JButton jBtnUnblock;
    private javax.swing.JButton jBtnXoa;
    private javax.swing.JCheckBox jCheckBoxMkMoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUser;
    private javax.swing.JTextField jTextFullname;
    private javax.swing.JTextField jTextMail;
    private javax.swing.JTextField jTextPhoneNum;
    private javax.swing.JTextField jTextUsername;
    // End of variables declaration//GEN-END:variables
}
