/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library_management.dto.SystemConfigDTO;
import library_management.utils.DBUtils;

/**
 *
 * @author Slayer
 */
public class SystemConfigDAO {
    public ArrayList<SystemConfigDTO> listConfigs() throws SQLException, ClassNotFoundException {
        ArrayList<SystemConfigDTO> configs = new ArrayList<>();
        Connection con = null;

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT id, config_key, config_value, description FROM system_config";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SystemConfigDTO config = new SystemConfigDTO();
                config.setId(rs.getInt("id"));
                config.setConfigKey(rs.getString("config_key"));
                config.setConfigValue(rs.getString("config_value"));
                config.setDescription(rs.getString("description"));
                configs.add(config);
            }
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return configs;
    }
    
    public boolean updateConfigValue(String key, String newValue) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean updated = false;

        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE system_config SET config_value = ? WHERE config_key = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, newValue);
            stmt.setString(2, key);
            
            updated = stmt.executeUpdate() > 0;
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return updated;
    }
}
