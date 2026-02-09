package vn.edu.fpt.swp.service;

import vn.edu.fpt.swp.dao.WarehouseDAO;
import vn.edu.fpt.swp.model.Warehouse;

import java.util.List;

/**
 * Service class for warehouse operations
 */
public class WarehouseService {
    private final WarehouseDAO warehouseDAO;
    
    public WarehouseService() {
        this.warehouseDAO = new WarehouseDAO();
    }
    
    /**
     * Get all warehouses
     * @return List of all warehouses
     */
    public List<Warehouse> getAllWarehouses() {
        return warehouseDAO.getAll();
    }
    
}
