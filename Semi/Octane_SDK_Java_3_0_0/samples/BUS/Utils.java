package BUS;

import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import DAO.LastIdDAO;
import DTO.OrderDetailDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
//import java.io.File;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

public class Utils {

    public LastIdDAO maDLCuoi;

    public Utils() {
//        maDLCuoi = new LastIdDAO();
    }

    ////////////////Tạo user ID
    public String initUserId() {
        maDLCuoi = new LastIdDAO();
        String temp = maDLCuoi.getUserId();
//        System.out.println("temp order: " + temp);
        int ma = Integer.parseInt(temp);
        ma++;
        String init = "USER_";
        init += String.valueOf(ma);
        return init;
    }

    ////////////////Tạo product ID
    public String initProductId() {
        maDLCuoi = new LastIdDAO();
        String temp = maDLCuoi.getProductId();
//        System.out.println("temp order: " + temp);
        int ma = Integer.parseInt(temp);
        ma++;
        String init = "PRO_";
        init += String.valueOf(ma);
        return init;
    }

    ////////////////Tạo order ID
    public String initOrderId() {
        maDLCuoi = new LastIdDAO();
        String temp = maDLCuoi.getOrderId();
//        System.out.println("temp order: " + temp);
        int ma = Integer.parseInt(temp);
        ma++;
        String init = "ORDER_";
        init += String.valueOf(ma);
        return init;
    }

    ////////////////Tạo order detail ID
    public void initOrderDetailId(ArrayList<OrderDetailDTO> orderDetailDTOs) {
        maDLCuoi = new LastIdDAO();
        String temp = maDLCuoi.getOrderDetailId();
        int ma = Integer.parseInt(temp);
        String init = "ORDETAIL_";
        for (OrderDetailDTO ord : orderDetailDTOs) {
            ma++;
            ord.setOrderDetailId(init + String.valueOf(ma));
        }
//        System.out.println("Ult OrderDetails: " + orderDetailDTOs);
    }

    ////////////////Lấy ngày hiện tại
    public String initDateNow() {
        final Date currentTime = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        System.out.println("GMT time: " + sdf.format(currentTime));
        String date = sdf.format(currentTime).toString();
        return date;
    }

    public Date getDateWithoutTimeUsingFormat() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    }

    ////////////////Lấy ngày đầu tiên của tháng kế tiếp
    public String firstDayOfnextMonth(String ngay) {
        String thanglamviec = getMonth(ngay);
        String namlamviec = getYear(ngay);
        int temp1 = Integer.parseInt(thanglamviec);
        temp1++;
        thanglamviec = String.valueOf(temp1);
        if (thanglamviec.length() == 1) {
            thanglamviec = "0" + thanglamviec;
        }
        if (thanglamviec.equals("13")) {
            int temp2 = Integer.parseInt(namlamviec);
            temp2++;
            namlamviec = String.valueOf(temp2);
            thanglamviec = "01";
        }
        String thoigian = namlamviec + "-" + thanglamviec + "-01";
        return thoigian;
    }

    /////////////////Tính số ngày so với hiện tại
    public int totalDays(String thoigian) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String hientai = initDateNow();
        System.out.println("thoigian: " + thoigian + "\nhientai: " + hientai);
        int total = -1;
        try {
            Date date1 = myFormat.parse(thoigian);
            Date date2 = myFormat.parse(hientai);
            long diff = date2.getTime() - date1.getTime();
            System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            total = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return total;
    }

    /////////////////Tính số ngày với 2 tham số truyền vào
    public int totalDays2(String dateFrom, String dateTo) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String hientai = dateTo;
        System.out.println("thoigian: " + dateFrom + "\nhientai: " + hientai);
        int total = -1;
        try {
            Date date1 = myFormat.parse(dateFrom);
            Date date2 = myFormat.parse(hientai);
            long diff = date2.getTime() - date1.getTime();
            System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            total = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return total;
    }

    ////////////////Tách năm
    public static String getYear(String name) {
        int pos = name.indexOf("-");
        //System.out.println (pos);
        if (pos == -1) {
            return null;
        }
        if (pos == name.length() - 1) {
            return null;
        }
        return name.substring(0, pos);
    }

    ////////////////Tách tháng
    public static String getMonth(String name) {
        int pos = name.indexOf("-");
        //System.out.println (pos);
        int pos1 = name.indexOf("-", pos + 1);
        //System.out.println (pos1);
        if (pos == -1) {
            return null;
        }
        if (pos == name.length() - 1) {
            return null;
        }
        return name.substring(pos + 1, pos1);
    }

    ////////////////Tách ngày
    public static String getDay(String name) {
        int pos = name.indexOf("-");
        //System.out.println (pos);
        int pos1 = name.indexOf("-", pos + 1);
        //System.out.println (pos1);
        if (pos == -1) {
            return null;
        }
        if (pos == name.length() - 1) {
            return null;
        }
        return name.substring(pos1 + 1, name.length());
    }

    ////////////////Thêm 0 vào ngày có length = 1
    public String them0(String num) {
        if (num.length() >= 2) {
            return num;
        } else {
            num = "0" + num;
            return num;
        }
    }

    ////////////////Tháng -> ngày cuối cùng của tháng
    public int lastDayOfMonth(String date) {
        int monthcheck = Integer.parseInt(getMonth(date));
        int year = Integer.parseInt(getYear(date));
        switch (monthcheck) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 30;
        }
    }

    public Date stringToDate(String strDate) {
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = myFormat.parse(strDate);
            return date;
        } catch (ParseException e) {
            return new Date(0, 0, 0);
        }
    }

    public static void afterDay(StringBuilder message, String title1, String date1, String title2, String date2) {
        int result = date1.compareTo(date2);
        if (result <= 0) {
            message.append(title1 + " phải sau " + title2 + "\n");
        }
    }

    public static void beforeOrEquals(StringBuilder message, String title1, String date1, String title2, String date2) {
        int result = date1.compareTo(date2);
        if (result > 0) {
            message.append(title1 + " phải trước hoặc bằng " + title2 + "\n");
        }
    }

    public void play() {
        String mp3FileToPlay = "./warning-sound.mp3";
        Player jlPlayer;
        try {
            FileInputStream fileInputStream = new FileInputStream(mp3FileToPlay);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
            try {
                jlPlayer.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Problem playing mp3 file " + mp3FileToPlay);
            System.out.println(e.getMessage());
        }
    }
}

class main {

    public static void main(String[] args) throws ParseException {
        Utils ult = new Utils();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//        Date tgBD = simpleDateFormat.parse("12/17/2021");
//        Date tgKT = simpleDateFormat1.parse("2021-12-14");
//        if (tgBD.before(tgKT)) {
//            System.out.println("dsds");
//        } else {
//            System.out.println("No");
//        }
//        System.out.println(ut.totalDays("2020-06-02"));
//        DiaDiemBUS diaDiemBUS = new DiaDiemBUS();
//        String maDD = diaDiemBUS.CapPhat(new LastIdDAO().getMaDiaDiemLast());
//        diaDiemBUS.themDiaDiem(maDD, "dsfsdgfds");
//        diaDiemBUS.xoaDiaDiem("DD000002");
//        String ngString = "1988-12-17";
//        int hientai = Calendar.getInstance().get(Calendar.YEAR);
//        int year = Integer.parseInt(ngString.substring(0, 4));
//        int namsinh = hientai - year;
//        System.out.println(namsinh);
//        String aString = "056200008901";
//        String bString = "^(0\\d{11})$";
//        boolean flag4 = Pattern.matches(bString, aString);
//        if (!flag4) {
//            System.out.println("Email không hợp lệ!\n");
//
//        } else {
//            System.out.println("hop le");
//        }
//
//        System.out.println("now: " + ult.initDateNow());
//        int beepCount = 1;
//        for (int i = 0; i < beepCount; ++i) {
//            System.out.println("Beep : " + i);
//            // Ring the bell again using the Toolkit 
//            java.awt.Toolkit.getDefaultToolkit().beep();
//            try {
//                Thread.sleep(600); // introduce delay
//            } catch (InterruptedException e) {
//            }
//            java.awt.Toolkit.getDefaultToolkit().beep();
//            try {
//                Thread.sleep(1000); // introduce delay
//            } catch (InterruptedException e) {
//            }
//        }
//        System.out.println("now: " + ult.initDateNow());
//        System.out.println("now: " + ult.initDateNow());
//        String bip = "bip.mp3";
//        Media hit = new Media(new File(bip).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(hit);
//        mediaPlayer.play();
//        ult.play();
//        try {
//            java.applet.AudioClip clip
//                    = java.applet.Applet.newAudioClip(
//                            new java.net.URL(
//            "file://C:/Users/User/Documents/GitHub/Semi/warning-sound.mp3"));
//            clip.play();
//        } catch (java.net.MalformedURLException murle) {
//            System.out.println(murle);
//        }
    }
}
