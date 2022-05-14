/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sdksamples;

import BUS.UserBUS;
import BUS.Utils;
import DTO.TagDTO;
import GUI.DanhSachXuatForm;
import GUI.Dashboard;
import GUI.NhapDlForm;
import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hyung
 */
public class MainRead implements TagReportListener {

    public static HashMap<String, Tag> tagMap = new HashMap<>();
    public static int flag = 0;
    public static ArrayList<TagDTO> tagDTOsMR = new ArrayList<>();
    NhapDlForm inputForm;
    public static DanhSachXuatForm outputForm;
    TagDTO tagDTO;
    Utils ult = new Utils();
//    String error = "";

    @Override
    public void onTagReported(ImpinjReader reader, TagReport tr) {
        if (flag == 1) {
            // nhập tag
            List<Tag> tags = tr.getTags();
            for (Tag t : tags) {
                if (!tagMap.containsKey(t.getEpc().toString())) {
                    tagMap.put(t.getEpc().toString(), t);
                    tagDTO = new TagDTO();
                    tagDTO.setTagId(t.getEpc().toString());
//                    if (reader.getName() != null) {
//                        tagDTO.setTagGateIn(reader.getName());
//                    } else {
//                        tagDTO.setTagGateIn(reader.getAddress());
//                    }
                    tagDTO.setTagGateIn("Antenna " + String.valueOf(t.getAntennaPortNumber()));
//                    System.out.println("name: " + tagDTO.getTagGateIn());
                    tagDTO.setTagDateIn(ult.initDateNow());
                    inputForm.tagDTOs.add(tagDTO);
//                    System.out.println("ok??????????????????????????");
                    inputForm.initTagAuto();
                }
            }
        } else if (flag == 2) {
            // xuất hàng
            List<Tag> tags = tr.getTags();
            for (Tag t : tags) {
                if (!tagMap.containsKey(t.getEpc().toString())) {
                    tagMap.put(t.getEpc().toString(), t);
                    tagDTO = new TagDTO();
                    tagDTO.setTagId(t.getEpc().toString());
                    tagDTO.setTagGateOut("Antenna " + String.valueOf(t.getAntennaPortNumber()));
                    tagDTO.setTagDateOut(ult.initDateNow());
                    for (TagDTO dto : tagDTOsMR) {
                        if (dto.getTagId().equals(tagDTO.getTagId())) {
                            if (dto.getOrderId() != null && !dto.getOrderId().equals("")) {
                                outputForm.errorScan += "Tag " + tagDTO.getTagId() + " thuộc đơn hàng khác!";
                                outputForm.checkError();
                                return;
                            }
                            tagDTO.setProductId(dto.getProductId());
                            ////////
                            outputForm.tagDTOs.add(tagDTO);
                            if (outputForm.detailScan.containsKey(tagDTO.getProductId())) {
                                outputForm.detailScan.put(tagDTO.getProductId(), outputForm.detailScan.get(tagDTO.getProductId()) + 1);
                            } else {
                                outputForm.detailScan.put(tagDTO.getProductId(), 1);
                            }
                            outputForm.checkScan(tagDTO.getProductId());
                            return;
                        }
                    }
                    outputForm.errorScan += "Tag " + tagDTO.getTagId() + " không tồn tại trong kho!";
                    outputForm.checkError();
                }
            }
        } else if (flag == 3) {
            // revert hàng
            List<Tag> tags = tr.getTags();
            for (Tag t : tags) {
                if (tagMap.containsKey(t.getEpc().toString())) {
                    tagMap.remove(t.getEpc().toString());
                    tagDTO = new TagDTO();
                    tagDTO.setTagId(t.getEpc().toString());
                    tagDTO.setTagGateOut("Antenna " + String.valueOf(t.getAntennaPortNumber()));
                    tagDTO.setTagDateOut(ult.initDateNow());
                    for (TagDTO dto : tagDTOsMR) {
                        if (dto.getTagId().equals(tagDTO.getTagId())) {
                            if (dto.getOrderId() != null && !dto.getOrderId().equals("")) {
                                outputForm.errorScan += "Tag " + tagDTO.getTagId() + " thuộc đơn hàng khác!";
                                outputForm.checkError();
                                return;
                            }
                            tagDTO.setProductId(dto.getProductId());
                            for (int i = 0; i < outputForm.tagDTOs.size(); i++) {
                                if (outputForm.tagDTOs.get(i).getTagId().equals(tagDTO.getTagId())) {
                                    outputForm.tagDTOs.remove(i);
                                    break;
                                }
                            }
                            if (outputForm.detailScan.containsKey(tagDTO.getProductId())) {
                                outputForm.detailScan.put(tagDTO.getProductId(), outputForm.detailScan.get(tagDTO.getProductId()) - 1);
                            }
                            outputForm.checkScanRevert(tagDTO.getProductId(), tagDTO.getTagId());
                            return;
                        }
                    }
                    outputForm.errorScan += "Tag " + tagDTO.getTagId() + " không tồn tại trong kho!";
                    outputForm.checkError();
                }
            }
        }
    }

//tag: 3500 0000 1000 0010 0000 1759
//tag: 00B0 7A14 2C2B 2848 0800 0166
//tag: 35E0 1700 4FF4 3406 0000 0673
//tag: 300F 4F57 3AD0 01C0 8369 A28F
//tag: 3008 33B2 DDD9 0140 0000 0009
//tag: 3500 0000 1000 0010 0000 1476
//tag: 0000 0000 0000 0000 0000 1314
//tag: 300F 4F57 3AD0 01C0 8369 A241
//tag: 300F 4F57 3AD0 01C0 8369 A230
//tag: 3039 6062 83B9 5D80 0012 9F61
//tag: 300F 4F57 3AD0 01C0 8369 A245
//tag: 3008 33B2 DDD9 06C0 0000 0000
//tag: 300F 4F57 3AD0 01C0 8369 A249
//tag: 3008 33B2 DDD9 0140 0000 002D
    public static void main(String[] args) {
//        List<String> tagsString = new ArrayList<String>();
//        tagsString.add("3500 0000 1000 0010 0000 1759");
//        tagsString.add("00B0 7A14 2C2B 2848 0800 0166");
//        tagsString.add("35E0 1700 4FF4 3406 0000 0673");
//        tagsString.add("300F 4F57 3AD0 01C0 8369 A28F");
//        tagsString.add("3008 33B2 DDD9 0140 0000 0009");
//        tagsString.add("3008 33B2 DDD9 0140 0000 0009");
//        tagsString.add("3008 33B2 DDD9 0140 0000 0009");
//        tagsString.add("3500 0000 1000 0010 0000 1476");
//        tagsString.add("0000 0000 0000 0000 0000 1314");
//        tagsString.add("300F 4F57 3AD0 01C0 8369 A241");
//        tagsString.add("300F 4F57 3AD0 01C0 8369 A230");
//        tagsString.add("300F 4F57 3AD0 01C0 8369 A230");
//        tagsString.add("3039 6062 83B9 5D80 0012 9F61");
//        tagsString.add("300F 4F57 3AD0 01C0 8369 A245");
//        tagsString.add("3008 33B2 DDD9 06C0 0000 0000");
//        tagsString.add("300F 4F57 3AD0 01C0 8369 A249");
//        tagsString.add("3008 33B2 DDD9 0140 0000 002D");
//        Set<String> temp = new HashSet<String>(tagsString);
//        NhapDlForm test = new NhapDlForm(temp);
//        System.out.println("ok");

//        HashMap<String, Integer> h = new HashMap<>();
//        for (String s : tagsString) {
//            if (h.containsKey(s)) {
//                h.put(s, h.get(s) + 1);
//            } else {
//                h.put(s, 1);
//            }
//        }
//        System.out.println("h: " + h);
        UserBUS userBUS = new UserBUS();
        MainRead mread = new MainRead();
        Dashboard d = new Dashboard();
        mread.setInputForm(d.getInputTag());
        mread.setOutputForm(d.getListOrder());
        d.userLogin = userBUS.checkLogin("admin", "admin");
        d.setVisible(true);
        d.setHello();
        d.checkRole();
//        d.getjBtnLogout().setEnabled(false);
    }

    public static void thucThi() {
        TagDTO test = new TagDTO();
        test.setTagId("abc");
        for (TagDTO dto : tagDTOsMR) {
            if (dto.getTagId().equals(test.getTagId())) {
                test.setProductId(dto.getProductId());
                break;
            }
        }
        outputForm.tagDTOs.add(test);
        if (outputForm.detailScan.containsKey(test.getProductId())) {
            outputForm.detailScan.put(test.getProductId(), outputForm.detailScan.get(test.getProductId()) + 1);
        } else {
            outputForm.detailScan.put(test.getProductId(), 1);
        }
        outputForm.checkScan(test.getProductId());
        test = new TagDTO();
        test.setTagId("def");
        for (TagDTO dto : tagDTOsMR) {
            if (dto.getTagId().equals(test.getTagId())) {
                test.setProductId(dto.getProductId());
                break;
            }
        }
        outputForm.tagDTOs.add(test);
        if (outputForm.detailScan.containsKey(test.getProductId())) {
            outputForm.detailScan.put(test.getProductId(), outputForm.detailScan.get(test.getProductId()) + 1);
        } else {
            outputForm.detailScan.put(test.getProductId(), 1);
        }
        outputForm.checkScan(test.getProductId());
    }

    public static void flagIf2(String epc) {
        TagDTO tagDTO;
        Utils ult = new Utils();
        Tag t = new Tag();
        if (!tagMap.containsKey(epc)) {
            tagMap.put(epc, t);
            tagDTO = new TagDTO();
            tagDTO.setTagId(epc);
            tagDTO.setTagGateOut("Antenna " + String.valueOf(t.getAntennaPortNumber()));
            tagDTO.setTagDateOut(ult.initDateNow());
            for (TagDTO dto : tagDTOsMR) {
                if (dto.getTagId().equals(tagDTO.getTagId())) {
                    if (dto.getOrderId() != null && !dto.getOrderId().equals("")) {
                        outputForm.errorScan += "Tag " + tagDTO.getTagId() + " thuộc đơn hàng khác!";
                        outputForm.checkError();
                        return;
                    }
                    tagDTO.setProductId(dto.getProductId());
                    System.out.println("tag: " + tagDTO);
                    ////////
                    outputForm.tagDTOs.add(tagDTO);
                    if (outputForm.detailScan.containsKey(tagDTO.getProductId())) {
                        outputForm.detailScan.put(tagDTO.getProductId(), outputForm.detailScan.get(tagDTO.getProductId()) + 1);
                    } else {
                        outputForm.detailScan.put(tagDTO.getProductId(), 1);
                    }
                    outputForm.checkScan(tagDTO.getProductId());
                    return;
                }
            }
            outputForm.errorScan += "Tag " + tagDTO.getTagId() + " không tồn tại trong kho!";
            outputForm.checkError();
        }
    }

    public static void flagIf3(String epc) {
        TagDTO tagDTO;
        Utils ult = new Utils();
        Tag t = new Tag();
        if (tagMap.containsKey(epc)) {
            tagMap.remove(epc);
            tagDTO = new TagDTO();
            tagDTO.setTagId(epc);
            tagDTO.setTagGateOut("Antenna " + String.valueOf(t.getAntennaPortNumber()));
            tagDTO.setTagDateOut(ult.initDateNow());
            for (TagDTO dto : tagDTOsMR) {
                if (dto.getTagId().equals(tagDTO.getTagId())) {
                    if (dto.getOrderId() != null && !dto.getOrderId().equals("")) {
                        outputForm.errorScan += "Tag " + tagDTO.getTagId() + " thuộc đơn hàng khác!";
                        outputForm.checkError();
                        return;
                    }
                    tagDTO.setProductId(dto.getProductId());
                    for (int i = 0; i < outputForm.tagDTOs.size(); i++) {
                        if (outputForm.tagDTOs.get(i).getTagId().equals(tagDTO.getTagId())) {
                            outputForm.tagDTOs.remove(i);
                            break;
                        }
                    }
                    if (outputForm.detailScan.containsKey(tagDTO.getProductId())) {
                        outputForm.detailScan.put(tagDTO.getProductId(), outputForm.detailScan.get(tagDTO.getProductId()) - 1);
                    }
                    outputForm.checkScanRevert(tagDTO.getProductId(), tagDTO.getTagId());
                    return;
                }
            }
            outputForm.errorScan += "Tag " + tagDTO.getTagId() + " không tồn tại trong kho!";
            outputForm.checkError();
        }
    }

    public static void testScan(int flag, int stepIf2, int stepIf3) {
        if (flag == 2) {
            switch (stepIf2) {
                case 1 -> {
                    flagIf2("E300 PRO3 1"); //PRO3 ok
                }
                case 2 -> {
                    flagIf2("E400 PRO4 3"); //PRO4 ok
                }
                case 3 -> {
                    flagIf2("E400 PRO4 4"); //PRO4 ok
                }
//                case 4 -> {
//                    flagIf2("E400 PRO4 5"); //PRO4 redundant
//                }
                case 4 -> {
                    flagIf2("E600 PRO6 3"); //PRO6 ok
                }
                case 5 -> {
                    flagIf2("E500 PRO5 2"); //PRO5 error
                }
                case 6 -> {
                    flagIf2("E500 PRO5 3"); //PRO5 error
                }
                case 7 -> {
                    flagIf2("E999 PRO1 1"); //PRO1 error
                }
                case 8 -> {
                    flagIf2("E100 PRO1 1"); //PRO1 error
                }
                case 9 -> {
                    flagIf2("E999 PRO1 2"); //PRO1 error
                }
            }
        } else if (flag == 3) {
            switch (stepIf3) {
//                case 1 -> {
//                    flagIf3("E400 PRO4 5"); //PRO4 back
//                }
                case 1 -> {
                    flagIf3("E500 PRO5 2"); //PRO5 error
                }
                case 2 -> {
                    flagIf3("E500 PRO5 3"); //PRO5 error
                }
                case 3 -> {
                    flagIf3("E999 PRO1 1"); //PRO1 error
                }
            }
        }
    }

    public static HashMap<String, Tag> getTagMap() {
        return tagMap;
    }

    public static void setTagMap(HashMap<String, Tag> tagMap) {
        MainRead.tagMap = tagMap;
    }

    public static int getFlag() {
        return flag;
    }

    public static void setFlag(int flag) {
        MainRead.flag = flag;
    }

    public NhapDlForm getInputForm() {
        return inputForm;
    }

    public void setInputForm(NhapDlForm inputForm) {
        this.inputForm = inputForm;
    }

    public DanhSachXuatForm getOutputForm() {
        return outputForm;
    }

    public void setOutputForm(DanhSachXuatForm outputForm) {
        this.outputForm = outputForm;
    }

}
