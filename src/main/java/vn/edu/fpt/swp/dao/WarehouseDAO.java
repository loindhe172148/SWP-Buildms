package vn.edu.fpt.swp.dao;

import vn.edu.fpt.swp.model.Warehouse;
import vn.edu.fpt.swp.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Warehouse entity
 */
public class WarehouseDAO {
    
    /**
     * Get all warehouses
     * @return List of all warehouses
     */
    public List<Warehouse> getAll() {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT Id, Name, Location, CreatedAt FROM Warehouses ORDER BY Name";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                warehouses.add(mapResultSetToWarehouse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return warehouses;
    }
    
    /**
     * Map ResultSet row to Warehouse object
     */
    private Warehouse mapResultSetToWarehouse(ResultSet rs) throws SQLException {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(rs.getLong("Id"));
        warehouse.setName(rs.getString("Name"));
        warehouse.setLocation(rs.getString("Location"));
        
        Timestamp ts = rs.getTimestamp("CreatedAt");
        if (ts != null) {
            warehouse.setCreatedAt(ts.toLocalDateTime());
        }
        
        return warehouse;
    }
}
