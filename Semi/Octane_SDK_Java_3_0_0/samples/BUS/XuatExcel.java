package BUS;
//
//import BUS.NhanVienBUS;
//import BUS.PhongBanBUS;
//import DTO.ChiTietNhanVienDTO;
//import DTO.NhanVienDTO;
//import DTO.PhongBanDTO;

import DTO.BaoCaoDTO;
import java.awt.Color;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class XuatExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);
    Utils ult = new Utils();

    private String getFile() {
        fd.setFile("donxuat.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }

    public HSSFColor setColor(HSSFWorkbook workbook, Color color) {
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor hssfColor = null;
        try {
            hssfColor = palette.findSimilarColor(color.getRed(), color.getGreen(), color.getBlue());
            System.out.println("Color is: " + hssfColor.getIndex());
        } catch (Exception e) {
            System.out.println("Color error!!");
        }
        return hssfColor;
    }

    // Xuất file Excel 
    @SuppressWarnings("empty-statement")
    public void xuatFileExcelDonHang(ArrayList<BaoCaoDTO> baoCaoDTOs, String dateFrom, String dateTo, String fullname) {
        fd.setTitle("Xuất đơn hàng ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            //Create a Work Book
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Đơn Xuất");//Tên sheet
            
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight((short) 220);

            HSSFCellStyle styleTitle = workbook.createCellStyle(); // Style cho Title
            HSSFFont fontTitle = workbook.createFont();
            fontTitle.setBold(true);
            fontTitle.setFontHeight((short) 300);
            styleTitle.setBorderTop(BorderStyle.THIN);
            styleTitle.setBorderBottom(BorderStyle.THIN);
            styleTitle.setBorderRight(BorderStyle.THIN);
            styleTitle.setBorderLeft(BorderStyle.THIN);
            styleTitle.setAlignment(HorizontalAlignment.CENTER);
            styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);
            styleTitle.setFillForegroundColor((short) 51);
            styleTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleTitle.setFont(fontTitle);

            Row rowTitle = sheet.createRow(1);
            rowTitle.setHeight((short) 800);
            HSSFCell cellTitleCell = (HSSFCell) rowTitle.createCell(1);
            cellTitleCell.setCellValue("BÁO CÁO DANH SÁCH ĐƠN XUẤT KHO TỪ " + dateFrom + " ĐẾN " + dateTo);
            cellTitleCell.setCellStyle(styleTitle);
            CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 13);
            rowTitle.getSheet().addMergedRegion(cellRangeAddress);
            RegionUtil.setBorderTop(BorderStyle.DOUBLE, cellRangeAddress, sheet);
            RegionUtil.setBorderBottom(BorderStyle.DOUBLE, cellRangeAddress, sheet);
            RegionUtil.setBorderLeft(BorderStyle.DOUBLE, cellRangeAddress, sheet);
            RegionUtil.setBorderRight(BorderStyle.DOUBLE, cellRangeAddress, sheet);

            ////Style cho ng nhap
            HSSFCellStyle styleName = workbook.createCellStyle(); // Style cho Title
            styleName.setAlignment(HorizontalAlignment.LEFT);
//            styleName.setBorderTop(BorderStyle.THIN);
//            styleName.setBorderBottom(BorderStyle.THIN);
//            styleName.setBorderRight(BorderStyle.THIN);
//            styleName.setBorderLeft(BorderStyle.THIN);
            styleName.setVerticalAlignment(VerticalAlignment.CENTER);
            styleName.setFont(font);
//            styleName.setFillForegroundColor((short) 29);
//            styleName.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row rowNhap = sheet.createRow(3);
            rowNhap.setHeight((short) 600);
            HSSFCell cellNhap = (HSSFCell) rowNhap.createCell(1);
            cellNhap.setCellValue("NGƯỜI LẬP BÁO CÁO: " + fullname);
            cellNhap.setCellStyle(styleName);
            CellRangeAddress cellRangeAddress1 = new CellRangeAddress(3, 3, 1, 3);
            rowNhap.getSheet().addMergedRegion(cellRangeAddress1);
            RegionUtil.setBorderTop(BorderStyle.DOUBLE, cellRangeAddress1, sheet);
            RegionUtil.setBorderBottom(BorderStyle.DOUBLE, cellRangeAddress1, sheet);
            RegionUtil.setBorderLeft(BorderStyle.DOUBLE, cellRangeAddress1, sheet);
            RegionUtil.setBorderRight(BorderStyle.DOUBLE, cellRangeAddress1, sheet);
            
//            Row rowDate = sheet.createRow(3);
//            rowDate.setHeight((short) 600);
            HSSFCell cellDate = (HSSFCell) rowNhap.createCell(5);
            cellDate.setCellValue("NGÀY LẬP: " + ult.initDateNow());
            cellDate.setCellStyle(styleName);
            CellRangeAddress cellRangeAddressDate = new CellRangeAddress(3, 3, 5, 7);
            rowNhap.getSheet().addMergedRegion(cellRangeAddressDate);
            RegionUtil.setBorderTop(BorderStyle.DOUBLE, cellRangeAddressDate, sheet);
            RegionUtil.setBorderBottom(BorderStyle.DOUBLE, cellRangeAddressDate, sheet);
            RegionUtil.setBorderLeft(BorderStyle.DOUBLE, cellRangeAddressDate, sheet);
            RegionUtil.setBorderRight(BorderStyle.DOUBLE, cellRangeAddressDate, sheet);

            /// Style header
            HSSFCellStyle styleHeader = workbook.createCellStyle();
            styleHeader.setBorderTop(BorderStyle.THIN);
            styleHeader.setBorderBottom(BorderStyle.THIN);
            styleHeader.setBorderRight(BorderStyle.THIN);
            styleHeader.setBorderLeft(BorderStyle.THIN);
            styleHeader.setAlignment(HorizontalAlignment.CENTER);
            styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
            styleHeader.setFont(font);
            styleHeader.setFillForegroundColor((short) 11);
            styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row row = sheet.createRow(5);
            row.setHeight((short) 400);
            HSSFCell cell2 = (HSSFCell) row.createCell(1, CellType.STRING);
            cell2.setCellValue("Mã Đơn");
            cell2.setCellStyle(styleHeader);
            HSSFCell cell3 = (HSSFCell) row.createCell(2, CellType.STRING);
            cell3.setCellValue("Ngày Tạo Đơn");
            cell3.setCellStyle(styleHeader);
            HSSFCell cell4 = (HSSFCell) row.createCell(3, CellType.STRING);
            cell4.setCellValue("Người Tạo Đơn");
            cell4.setCellStyle(styleHeader);
            HSSFCell cell5 = (HSSFCell) row.createCell(4, CellType.STRING);
            cell5.setCellValue("Người Xuất Đơn");
            cell5.setCellStyle(styleHeader);
            HSSFCell cell6 = (HSSFCell) row.createCell(5, CellType.STRING);
            cell6.setCellValue("Trạng Thái");
            cell6.setCellStyle(styleHeader);
            HSSFCell cell7 = (HSSFCell) row.createCell(6, CellType.STRING);
            cell7.setCellValue("Mã Sản Phẩm");
            cell7.setCellStyle(styleHeader);
            HSSFCell cell8 = (HSSFCell) row.createCell(7, CellType.STRING);
            cell8.setCellValue("Tên Sản Phẩm");
            cell8.setCellStyle(styleHeader);
            HSSFCell cell9 = (HSSFCell) row.createCell(8, CellType.STRING);
            cell9.setCellValue("Số Lượng Đặt");
            cell9.setCellStyle(styleHeader);
            HSSFCell cell10 = (HSSFCell) row.createCell(9, CellType.STRING);
            cell10.setCellValue("Mã Tag");
            cell10.setCellStyle(styleHeader);
            HSSFCell cell11 = (HSSFCell) row.createCell(10, CellType.STRING);
            cell11.setCellValue("Cổng Vào");
            cell11.setCellStyle(styleHeader);
            HSSFCell cell12 = (HSSFCell) row.createCell(11, CellType.STRING);
            cell12.setCellValue("Ngày Vào");
            cell12.setCellStyle(styleHeader);
            HSSFCell cell13 = (HSSFCell) row.createCell(12, CellType.STRING);
            cell13.setCellValue("Cổng Xuất");
            cell13.setCellStyle(styleHeader);
            HSSFCell cell14 = (HSSFCell) row.createCell(13, CellType.STRING);
            cell14.setCellValue("Ngày Xuất");
            cell14.setCellStyle(styleHeader);

            // Style rows
            HSSFCellStyle style = workbook.createCellStyle();
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            /////////////////////////////////////////
            HSSFCellStyle style2 = workbook.createCellStyle();
            style2.setBorderTop(BorderStyle.THIN);
            style2.setBorderBottom(BorderStyle.THIN);
            style2.setBorderRight(BorderStyle.THIN);
            style2.setBorderLeft(BorderStyle.THIN);
            style2.setAlignment(HorizontalAlignment.CENTER);
            style2.setVerticalAlignment(VerticalAlignment.CENTER);
            style2.setFillForegroundColor((short) 42);
            style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (int i = 1; i <= 14; ++i) {
                switch (i) {
                    case 2, 3, 4, 7, 11, 13 -> sheet.setColumnWidth(i, 25 * 256);
                    case 9 -> sheet.setColumnWidth(i, 25 * 350);
                    default -> sheet.setColumnWidth(i, 25 * 150);
                }
            }

            // INIT
            int rowPrint = 6, beginOrder = 6, endOrder = 6, beginProduct = 6, endProduct = 6, count = 0;
            String orderId = "", productId = "";
            // PRINT ROW
            for (BaoCaoDTO k : baoCaoDTOs)
            {
                Row rowData = sheet.createRow(rowPrint);
                rowData.setHeight((short) 400);
                if (!orderId.equals(k.getOrder_id())) {
                    if (!productId.equals(k.getProduct_id())) {
                        productId = k.getProduct_id();
                        if (endProduct > beginProduct) {
                            for (int u = 6; u <= 8; u++) {
                                CellRangeAddress cellRangeAddress3 = new CellRangeAddress(beginProduct, endProduct, u, u);
                                rowData.getSheet().addMergedRegion(cellRangeAddress3);
                            }
                        }
                        beginProduct = rowPrint;
                    } else {
                        if (endProduct > beginProduct) {
                            for (int u = 6; u <= 8; u++) {
                                CellRangeAddress cellRangeAddress3 = new CellRangeAddress(beginProduct, endProduct, u, u);
                                rowData.getSheet().addMergedRegion(cellRangeAddress3);
                            }
                        }
                        beginProduct = rowPrint;
                    }
                    orderId = k.getOrder_id();
                    if (endOrder > beginOrder) {
                        for (int u = 1; u <= 5; u++) {
                            CellRangeAddress cellRangeAddress2 = new CellRangeAddress(beginOrder, endOrder, u, u);
                            rowData.getSheet().addMergedRegion(cellRangeAddress2);
                        }
                    }
                    beginOrder = rowPrint;
                    count++;
                } else {
                    if (!productId.equals(k.getProduct_id())) {
                        productId = k.getProduct_id();
                        if (endProduct > beginProduct) {
                            for (int u = 6; u <= 8; u++) {
                                CellRangeAddress cellRangeAddress3 = new CellRangeAddress(beginProduct, endProduct, u, u);
                                rowData.getSheet().addMergedRegion(cellRangeAddress3);
                            }
                        }
                        beginProduct = rowPrint;
                    } else {
                        endProduct = rowPrint;
                    }
                    endOrder = rowPrint;
                }
                // PRINT COLUMN
                for (int j = 1; j <= 13; ++j)
                {
                    HSSFCell cellData = (HSSFCell) rowData.createCell(j);
                    if (count % 2 == 0) {
                        cellData.setCellStyle(style2);
                    } else {
                        cellData.setCellStyle(style);
                    }
                    switch (j) {
                        case 1 ->  {
                            cellData.setCellValue(k.getOrder_id());
                        }
                        case 2 ->  {
                            cellData.setCellValue(k.getOrder_date());
                        }
                        case 3 ->  {
                            cellData.setCellValue(k.getUser_created());
                        }
                        case 4 ->  {
                            cellData.setCellValue(k.getUser_completed());
                        }
                        case 5 ->  {
                            if (k.getStatus() == 2) {
                                cellData.setCellValue("Chờ xuất");
                            } else {
                                cellData.setCellValue("Hoàn tất");
                            }
                        }
                        case 6 ->  {
                            cellData.setCellValue(k.getProduct_id());
                        }
                        case 7 ->  {
                            cellData.setCellValue(k.getProduct_name());
                        }
                        case 8 ->  {
                            cellData.setCellValue(k.getOrder_quantity());
                        }
                        case 9 ->  {
                            cellData.setCellValue(k.getTag_id());
                        }
                        case 10 ->  {
                            cellData.setCellValue(k.getTag_gate_in());
                        }
                        case 11 ->  {
                            cellData.setCellValue(k.getTag_date_in());
                        }
                        case 12 ->  {
                            cellData.setCellValue(k.getTag_gate_out());
                        }
                        case 13 ->  {
                            cellData.setCellValue(k.getTag_date_out());
                        }
                    }
                }
                if (rowPrint - 5 == baoCaoDTOs.size()) {
                    if (endOrder > beginOrder) {
                        for (int u = 1; u <= 5; u++) {
                            CellRangeAddress cellRangeAddress4 = new CellRangeAddress(beginOrder, endOrder, u, u);
                            rowData.getSheet().addMergedRegion(cellRangeAddress4);
                        }
                    }
                    if (endProduct > beginProduct) {
                        for (int u = 6; u <= 8; u++) {
                            CellRangeAddress cellRangeAddress5 = new CellRangeAddress(beginProduct, endProduct, u, u);
                            rowData.getSheet().addMergedRegion(cellRangeAddress5);
                        }
                    }
                }
                rowPrint++;
            }
            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
