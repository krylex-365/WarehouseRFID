package com.example.sdksamples;

import GUI.Dashboard;
import GUI.LoginForm;
import GUI.NhapDlForm;
import com.impinj.octane.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.util.Scanner;
import java.util.Set;

public class ReadTags {
    public static LoginForm l;
    public static Dashboard d;

    public static void main(String[] args) {

//    public void main() {
        try {
//            String hostname = System.getProperty(SampleProperties.hostname);
            String hostname = "169.254.96.13"; //169.254.96.13 //169.254.153.74 // 169.254.1.1

            if (hostname == null) {
                throw new Exception("Must specify the '"
                        + SampleProperties.hostname + "' property");
            }

            ImpinjReader reader = new ImpinjReader();

            System.out.println("Connecting");
            reader.connect(hostname);

            Settings settings = reader.queryDefaultSettings();

            ReportConfig report = settings.getReport();
            report.setIncludeAntennaPortNumber(true);
            report.setMode(ReportMode.Individual);

            // The reader can be set into various modes in which reader
            // dynamics are optimized for specific regions and environments.
            // The following mode, AutoSetDenseReader, monitors RF noise and interference and then automatically
            // and continuously optimizes the reader's configuration
            settings.setReaderMode(ReaderMode.AutoSetDenseReader);

            // set some special settings for antenna 1
            AntennaConfigGroup antennas = settings.getAntennas();
            antennas.disableAll();
            antennas.enableById(new short[]{
                1
            });
            antennas.getAntenna((short) 1).setIsMaxRxSensitivity(false);
            antennas.getAntenna((short) 1).setIsMaxTxPower(false);
            antennas.getAntenna((short) 1).setTxPowerinDbm(20.0);
            antennas.getAntenna((short) 1).setRxSensitivityinDbm(-70);

//            reader.setTagReportListener(new Vd());
            MainRead mread = new MainRead();
//            NhapDlForm test = new NhapDlForm();
            l = new LoginForm();
            d = new Dashboard();
//            d.setVisible(false);
            mread.setInputForm(d.getInputTag());
            mread.setOutputForm(d.getListOrder());
            reader.setTagReportListener(mread);

            System.out.println("Applying Settings");
            reader.applySettings(settings);

            System.out.println("Starting");
            reader.start();

            System.out.println("Press Enter to exit.");
            Scanner s = new Scanner(System.in);
            s.nextLine();

            reader.stop();
            reader.disconnect();
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }

    public static void visibleDashboard() {
        d.setVisible(true);
        d.setHello();
        d.checkRole();
    }
    
    public static void visibleLogin() {
        l.setVisible(true);
        l.getjTxtUsername().setText("");
        l.getjTxtPassword().setText("");
    }
}

//class Vd implements TagReportListener
//{
//
//    @Override
//    public void onTagReported(ImpinjReader reader, TagReport tr)
//    {
//        List<Tag> tags = tr.getTags();
//        for (Tag t : tags)
//        {
//            System.out.print(" EPC: " + t.getEpc().toString());
//        }
//    }
//
//}
class ReadTagExample implements TagReportListener {

    public static HashMap<String, Tag> tagMap = new HashMap<>();
    boolean flag = false;
//    @Override
//    public void onTagReported(ImpinjReader reader, TagReport tr)
//    {
//        tr.getTags().forEach(tag -> System.out.println("> " + tag.getEpc()));
//    }

    @Override
    public void onTagReported(ImpinjReader reader, TagReport tr) {
        List<Tag> tags = tr.getTags();
//        Set<String> epcSet = new HashSet<>();
        for (Tag t : tags) {
//            System.out.println("dl l?y dc la" + t.getEpc().toString());
//            epcSet.add(t.getEpc().toString());
            tagMap.put(t.getEpc().toString(), t);
        }
    }

    public static void main(String[] args) {
        List<String> tagsString = new ArrayList<String>();
        tagsString.add("abc");
        tagsString.add("abc");
        tagsString.add("def");
        tagsString.add("abc");
        tagsString.add("def");
        tagsString.add("mno");
        tagsString.add("klj");
        tagsString.add("mno");
        tagsString.add("abc");
        Set<String> temp = new HashSet<String>(tagsString);
        NhapDlForm test = new NhapDlForm(temp);
        System.out.println("ok");
    }
}
