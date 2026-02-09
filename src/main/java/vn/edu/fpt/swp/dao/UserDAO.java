package vn.edu.fpt.swp.dao;

import vn.edu.fpt.swp.model.User;
import vn.edu.fpt.swp.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for User entity
 */
public class UserDAO {
    
 
    /**
     * Get all users
     * @return List of all users
     */
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, name, email, passwordHash, role, status, warehouseId, createdAt, lastLogin " +
                     "FROM Users ORDER BY name";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     * Search users with filters
     * @param keyword Search by username or email
     * @param role Role filter
     * @param status Status filter
     * @param warehouseId Warehouse filter
     * @return Filtered list of users
     */
    public List<User> search(String keyword, String role, String status, Long warehouseId) {
        List<User> users = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT id, username, name, email, passwordHash, role, status, warehouseId, createdAt, lastLogin " +
            "FROM Users WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (username LIKE ? OR email LIKE ? OR name LIKE ?)");
            String pattern = "%" + keyword.trim() + "%";
            params.add(pattern);
            params.add(pattern);
            params.add(pattern);
        }
        
        if (role != null && !role.trim().isEmpty()) {
            sql.append(" AND role = ?");
            params.add(role);
        }
        
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND status = ?");
            params.add(status);
        }
        
        if (warehouseId != null) {
            sql.append(" AND warehouseId = ?");
            params.add(warehouseId);
        }
        
        sql.append(" ORDER BY name");
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(mapResultSetToUser(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     * Get users by role
     * @param role Role to filter
     * @return List of users with given role
     */
    public List<User> findByRole(String role) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, name, email, passwordHash, role, status, warehouseId, createdAt, lastLogin " +
                     "FROM Users WHERE role = ? ORDER BY name";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, role);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(mapResultSetToUser(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     * Get users by status
     * @param status Status to filter
     * @return List of users with given status
     */
    public List<User> findByStatus(String status) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, name, email, passwordHash, role, status, warehouseId, createdAt, lastLogin " +
                     "FROM Users WHERE status = ? ORDER BY name";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(mapResultSetToUser(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     * Map ResultSet to User object
     * @param rs ResultSet
     * @return User object
     */
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("passwordHash"));
        user.setRole(rs.getString("role"));
        user.setStatus(rs.getString("status"));
        
        Long warehouseId = rs.getLong("warehouseId");
        if (!rs.wasNull()) {
            user.setWarehouseId(warehouseId);
        }
        
        Timestamp createdAt = rs.getTimestamp("createdAt");
        if (createdAt != null) {
            user.setCreatedAt(createdAt.toLocalDateTime());
        }
        
        Timestamp lastLogin = rs.getTimestamp("lastLogin");
        if (lastLogin != null) {
            user.setLastLogin(lastLogin.toLocalDateTime());
        }
        
        return user;
    }
}
