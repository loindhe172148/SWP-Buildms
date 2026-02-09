package vn.edu.fpt.swp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.fpt.swp.model.User;
import vn.edu.fpt.swp.model.Warehouse;
import vn.edu.fpt.swp.service.UserService;
import vn.edu.fpt.swp.service.WarehouseService;

import java.io.IOException;
import java.util.List;

/**
 * Controller for User management
 * Handles UC-USER-001, UC-USER-002, UC-USER-003, UC-USER-004
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
    
    private UserService userService;
    private WarehouseService warehouseService;
    
    @Override
    public void init() throws ServletException {
        userService = new UserService();
        warehouseService = new WarehouseService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "list";
        }
        
        // Check admin access
        if (!isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }
        
        switch (action) {
            case "list":
                showList(request, response);
                break;
            case "add":
                //showAddForm(request, response);
                break;
            case "edit":
                //showEditForm(request, response);
                break;
            case "toggle":
                //toggleStatus(request, response);
                break;
            default:
                showList(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        
        // Check admin access
        if (!isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }
        
        switch (action) {
            case "add":
                //processAdd(request, response);
                break;
            case "edit":
                //processEdit(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/user?action=list");
        }
    }
    
    /**
     * UC-USER-004: Display user list with filters
     */
    private void showList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String keyword = request.getParameter("keyword");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String warehouseIdParam = request.getParameter("warehouseId");
        
        Long warehouseId = null;
        if (warehouseIdParam != null && !warehouseIdParam.isEmpty()) {
            try {
                warehouseId = Long.parseLong(warehouseIdParam);
            } catch (NumberFormatException e) {
                // Ignore invalid warehouse ID
            }
        }
        
        List<User> users;
        
        if ((keyword != null && !keyword.trim().isEmpty()) || 
            (role != null && !role.trim().isEmpty()) ||
            (status != null && !status.trim().isEmpty()) ||
            warehouseId != null) {
            users = userService.searchUsers(keyword, role, status, warehouseId);
        } else {
            users = userService.getAllUsers();
        }
        
        // Get warehouses for filter dropdown and to display warehouse names
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        
        // Set warehouse names for each user
        for (User user : users) {
            if (user.getWarehouseId() != null) {
                for (Warehouse wh : warehouses) {
                    if (wh.getId() == user.getWarehouseId().intValue()) {
                        request.setAttribute("warehouseName_" + user.getId(), wh.getName());
                        break;
                    }
                }
            }
        }
        
        // Get current user ID for highlighting
        User currentUser = getCurrentUser(request);
        
        request.setAttribute("users", users);
        request.setAttribute("warehouses", warehouses);
        request.setAttribute("keyword", keyword);
        request.setAttribute("role", role);
        request.setAttribute("status", status);
        request.setAttribute("warehouseId", warehouseId);
        request.setAttribute("currentUserId", currentUser != null ? currentUser.getId() : null);
        request.setAttribute("roles", userService.getValidRoles());
        
        request.getRequestDispatcher("/WEB-INF/views/user/list.jsp")
               .forward(request, response);
    }
    
    /**
     * Check if current user is Admin
     */
    private boolean isAdmin(HttpServletRequest request) {
        User user = getCurrentUser(request);
        if (user == null) return false;
        return "Admin".equals(user.getRole());
    }
    
    /**
     * Get current logged in user
     */
    private User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return null;
        return (User) session.getAttribute("user");
    }
}
