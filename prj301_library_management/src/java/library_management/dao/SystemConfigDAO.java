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
    public String getConfigValueByKey(String configKey) throws SQLException, ClassNotFoundException {
        String configvalue = "";
        Connection con = null;

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT config_value FROM system_config where config_key = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, configKey);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                configvalue = rs.getString("config_value");
            }
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return configvalue;
    }
    public boolean updateConfigValue(SystemConfigDTO systemConfig) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean updated = false;

        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE system_config SET config_value = ?, description = ? WHERE id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, systemConfig.getConfigValue());
            stmt.setString(2, systemConfig.getDescription());
            stmt.setInt(3, systemConfig.getId());
            
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
    
    public void addSystemConfig(SystemConfigDTO config) throws SQLException {
        String sql = "INSERT INTO system_config (config_key, config_value, description) VALUES (?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, config.getConfigKey());
            stmt.setString(2, config.getConfigValue());
            stmt.setString(3, config.getDescription());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteSystemConfig(int id) throws SQLException {
        String sql = "DELETE FROM system_config WHERE id = ?";
        try (Connection conn = DBUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
