/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.utils;

/**
 *
 * @author quang
 */
public class IntUtils {
    public static boolean isNumber(String numberStr) {
        try {
            Integer.parseInt(numberStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
