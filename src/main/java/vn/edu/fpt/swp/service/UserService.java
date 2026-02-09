package vn.edu.fpt.swp.service;

import vn.edu.fpt.swp.dao.UserDAO;
import vn.edu.fpt.swp.model.User;
import vn.edu.fpt.swp.util.PasswordUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Service layer for User management operations
 */
public class UserService {
    
    private final UserDAO userDAO;
    private static final List<String> VALID_ROLES = Arrays.asList("Admin", "Manager", "Staff", "Sales");
    
    public UserService() {
        this.userDAO = new UserDAO();
    }
    
    /**
     * Get all users
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }
    
    /**
     * Get users by role
     * @param role Role to filter
     * @return Filtered list
     */
    public List<User> getUsersByRole(String role) {
        return userDAO.findByRole(role);
    }
    
    /**
     * Get users by status
     * @param status Status to filter
     * @return Filtered list
     */
    public List<User> getUsersByStatus(String status) {
        return userDAO.findByStatus(status);
    }
    
    /**
     * Search users with filters
     * @param keyword Search keyword (username, email, name)
     * @param role Role filter
     * @param status Status filter
     * @param warehouseId Warehouse filter
     * @return Filtered list
     */
    public List<User> searchUsers(String keyword, String role, String status, Long warehouseId) {
        return userDAO.search(keyword, role, status, warehouseId);
    }
    
    /**
     * Validate user fields
     * @param user User to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is required");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            throw new IllegalArgumentException("Role is required");
        }
        if (!isValidRole(user.getRole())) {
            throw new IllegalArgumentException("Invalid role. Must be Admin, Manager, Staff, or Sales");
        }
        
        // Trim values
        user.setUsername(user.getUsername().trim());
        user.setName(user.getName().trim());
        user.setEmail(user.getEmail().trim().toLowerCase());
    }
    
    /**
     * Check if role is valid
     * @param role Role to check
     * @return true if valid
     */
    public boolean isValidRole(String role) {
        return VALID_ROLES.contains(role);
    }
    
    /**
     * Check if email format is valid
     * @param email Email to check
     * @return true if valid
     */
    private boolean isValidEmail(String email) {
        if (email == null) return false;
        // Simple email validation
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
    
    /**
     * Get list of valid roles
     * @return List of valid role names
     */
    public List<String> getValidRoles() {
        return VALID_ROLES;
    }
}
