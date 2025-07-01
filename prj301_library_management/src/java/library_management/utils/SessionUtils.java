/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.utils;

import jakarta.servlet.http.HttpSession;
import library_management.dto.UserDTO;

/**
 *
 * @author quang
 */
public class SessionUtils {

    public static UserDTO getLoggedUser(HttpSession session) {
        return (UserDTO) session.getAttribute("user");
    }

    public static Integer getLoggedUserId(HttpSession session) {
        UserDTO user = getLoggedUser(session);
        return user != null ? user.getId() : null;
    }

    public static String getLoggedUserName(HttpSession session) {
        UserDTO user = getLoggedUser(session);
        return user != null ? user.getName() : null;
    }

    public static String getLoggedUserEmail(HttpSession session) {
        UserDTO user = getLoggedUser(session);
        return user != null ? user.getEmail() : null;
    }

    public static String getLoggedUserPassword(HttpSession session) {
        UserDTO user = getLoggedUser(session);
        return user != null ? user.getPassword() : null;
    }

    public static String getLoggedUserRole(HttpSession session) {
        UserDTO user = getLoggedUser(session);
        return user != null ? user.getRole() : null;
    }

    public static String getLoggedUserStatus(HttpSession session) {
        UserDTO user = getLoggedUser(session);
        return user != null ? user.getStatus() : null;
    }

}
