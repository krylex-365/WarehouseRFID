/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author Admin
 */

import com.itextpdf.text.log.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;

/**
 * @author Hyung
 */
public class HashPass {

    public static String MD5Hash(String pass) {
        try {
            MessageDigest digs = MessageDigest.getInstance("MD5");
            digs.update(pass.getBytes());
            byte[] result = digs.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Loi hash pass!!!" + ex.getMessage());
        }
        return "";
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String a;
        while (true) {
            a = s.nextLine();
            if (a.equals("ok")) {
                break;
            }
            System.out.println("K?t Qu?: " + MD5Hash(a));
        }
    }
}
