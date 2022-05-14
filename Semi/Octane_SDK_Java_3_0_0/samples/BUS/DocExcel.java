package BUS;

import DTO.ProductDTO;
import DTO.TagDTO;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DocExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Đọc Excel", FileDialog.LOAD);
    DefaultTableModel modelpb = new DefaultTableModel();
    public HashMap<String, Integer> productCount = new HashMap<>();
    TagBUS tagBUS = new TagBUS();
    ProductBUS productBUS = new ProductBUS();
//    public DangNhapHienHanh dnhh = new DangNhapHienHanh();

    public DocExcel() {

    }

    private String getFile() {
        fd.setFile("*.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }

    //Đọc file excel Tag
    public String docFileExcelTag(ArrayList<TagDTO> tagDTOs, ArrayList<ProductDTO> productDTOs) {
        String noti = "", success = "", fail = "";
        boolean flag = false;
        ArrayList<TagDTO> tags = new ArrayList<>();
        ArrayList<ProductDTO> productUpdate = new ArrayList<>();
        TagDTO tagDTO;
        fd.setTitle("Nhập dữ liệu tag từ Excel");
        String url = getFile();
        if (url == null) {
            return noti;
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();
            int i = 1;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                i = 1;
                tagDTO = new TagDTO("", "", "", null, "", null, "");
                while (cellIterator.hasNext()) {
                    switch (i) {
                        case 1 -> {
                            tagDTO.setTagId(cellIterator.next().getStringCellValue());
                        }
                        case 2 -> {
                            tagDTO.setProductId(cellIterator.next().getStringCellValue());
                        }
                        case 3 -> {
                            tagDTO.setTagDateIn(cellIterator.next().getStringCellValue());
                        }
                        case 4 -> {
                            tagDTO.setTagGateIn(cellIterator.next().getStringCellValue());
                        }
                    }
                    i++;
                }
                if (!tagDTOs.contains(tagDTO)) {
                    tags.add(tagDTO);
                    if (tagBUS.insertTag(tagDTO)) {
                        flag = true;
                        success += tagDTO.getTagId() + "\n";
                        if (productCount.containsKey(tagDTO.getProductId())) {
                            productCount.put(tagDTO.getProductId(), productCount.get(tagDTO.getProductId()) + 1);
                        } else {
                            productCount.put(tagDTO.getProductId(), 1);
                        }
                    } else {
                        fail += tagDTO.getTagId() + "\n";
                    }
                } else {
                    fail += tagDTO.getTagId() + "\n";
                }
            }
            if (flag) {
                for (ProductDTO p : productDTOs) {
                    if (productCount.containsKey(p.getProductId())) {
                        p.setProductQuantity(productCount.get(p.getProductId()) + p.getProductQuantity());
                        productUpdate.add(p);
                    }
                }
                productBUS.updateProductsQuantity(productUpdate);
            }
            if (!success.equals("")) {
                noti += "- Import thành công tag:\n" + success;
            }
            if (!fail.equals("")) {
                noti += "- Import thất bại, đã tồn tại tag:\n" + fail;
            }
            System.out.println("tags: " + tags);
//            JOptionPane.showMessageDialog(null, "Import success!");
        } catch (Exception ex) {
            noti += "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                noti += "Lỗi khi đóng inputstream: " + ex.getMessage();
            }
        }
        return noti;
    }
}
