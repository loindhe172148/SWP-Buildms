# Smart Warehouse Management System (Smart WMS)

A Java-based web application for managing warehouse operations using JSP, Servlets, and SQL Server. Features include authentication, role-based access control, inventory management, and sales-driven fulfillment workflows.

## 🔐 Authentication System

**NEW:** Smart WMS now includes comprehensive authentication and authorization!

- **Secure Login/Registration**: SHA-256 password hashing with salt
- **Role-Based Access Control**: Admin, Manager, Staff, and Sales roles
- **Session Management**: 30-minute timeout for security
- **Protected Resources**: Authorization filter checks permissions

### Quick Start

1. Run database migration: `database/auth_migration.sql`
2. Login at `/auth?action=login` with test credentials:
   - Username: `admin` / Password: `password123` (Admin)
   - Username: `manager` / Password: `password123` (Manager)
   - Username: `staff` / Password: `password123` (Staff)
   - Username: `sales` / Password: `password123` (Sales)

**⚠️ Change passwords immediately!**

📖 **Full Documentation**: See [AUTHENTICATION.md](document/AUTHENTICATION.md)

## Project Structure

```
buildms/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── vn/edu/fpt/swp/
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── CategoryController.java
│   │   │       │   ├── CustomerController.java
│   │   │       │   ├── DashboardController.java
│   │   │       │   ├── InboundController.java
│   │   │       │   ├── InventoryController.java
│   │   │       │   ├── LocationController.java
│   │   │       │   ├── MovementController.java
│   │   │       │   ├── OutboundController.java
│   │   │       │   ├── ProductController.java
│   │   │       │   ├── SalesOrderController.java
│   │   │       │   ├── TransferController.java
│   │   │       │   ├── UserController.java
│   │   │       │   └── WarehouseController.java
│   │   │       ├── dao/
│   │   │       │   ├── CategoryDAO.java
│   │   │       │   ├── CustomerDAO.java
│   │   │       │   ├── InventoryDAO.java
│   │   │       │   ├── LocationDAO.java
│   │   │       │   ├── ProductDAO.java
│   │   │       │   ├── RequestDAO.java
│   │   │       │   ├── RequestItemDAO.java
│   │   │       │   ├── SalesOrderDAO.java
│   │   │       │   ├── SalesOrderItemDAO.java
│   │   │       │   ├── UserDAO.java
│   │   │       │   └── WarehouseDAO.java
│   │   │       ├── model/
│   │   │       │   ├── Category.java
│   │   │       │   ├── Customer.java
│   │   │       │   ├── Inventory.java
│   │   │       │   ├── Location.java
│   │   │       │   ├── Product.java
│   │   │       │   ├── Request.java
│   │   │       │   ├── RequestItem.java
│   │   │       │   ├── SalesOrder.java
│   │   │       │   ├── SalesOrderItem.java
│   │   │       │   ├── User.java
│   │   │       │   └── Warehouse.java
│   │   │       ├── service/
│   │   │       │   ├── AuthService.java
│   │   │       │   ├── CategoryService.java
│   │   │       │   ├── CustomerService.java
│   │   │       │   ├── InboundService.java
│   │   │       │   ├── InventoryService.java
│   │   │       │   ├── LocationService.java
│   │   │       │   ├── MovementService.java
│   │   │       │   ├── OutboundService.java
│   │   │       │   ├── ProductService.java
│   │   │       │   ├── SalesOrderService.java
│   │   │       │   ├── TransferService.java
│   │   │       │   ├── UserService.java
│   │   │       │   └── WarehouseService.java
│   │   │       ├── filter/
│   │   │       │   └── AuthFilter.java
│   │   │       └── util/
│   │   │           ├── DBConnection.java
│   │   │           └── PasswordUtil.java
│   │   │
│   │   ├── resources/
│   │   │
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── views/
│   │       │   │   ├── common/
│   │       │   │   ├── auth/
│   │       │   │   │   ├── login.jsp
│   │       │   │   │   ├── register.jsp
│   │       │   │   │   └── change-password.jsp
│   │       │   │   ├── category/
│   │       │   │   ├── customer/
│   │       │   │   ├── error/
│   │       │   │   ├── inbound/
│   │       │   │   ├── inventory/
│   │       │   │   ├── location/
│   │       │   │   ├── movement/
│   │       │   │   ├── outbound/
│   │       │   │   ├── product/
│   │       │   │   │   ├── list.jsp
│   │       │   │   │   ├── add.jsp
│   │       │   │   │   ├── edit.jsp
│   │       │   │   │   └── details.jsp
│   │       │   │   ├── sales-order/
│   │       │   │   ├── transfer/
│   │       │   │   ├── user/
│   │       │   │   ├── warehouse/
│   │       │   │   └── dashboard.jsp
│   │       │   └── web.xml
│   │       ├── assets/
│   │       ├── dist/
│   │       ├── fonts/
│   │       ├── js/
│   │       ├── libs/
│   │       └── index.jsp
│   │
│   └── test/
│
├── database/
│   ├── schema.sql
│   ├── user_seed.sql
│   └── full_seed_data.sql
│
├── document/
│   ├── SRS.md
│   ├── AUTHENTICATION.md
│   ├── AUTH_QUICK_REF.md
│   └── detail-design/
│       ├── UC-AUTH-*.md
│       ├── UC-CAT-*.md
│       ├── UC-CUS-*.md
│       ├── UC-INB-*.md
│       ├── UC-INV-*.md
│       ├── UC-LOC-*.md
│       ├── UC-MOV-*.md
│       ├── UC-OUT-*.md
│       ├── UC-PRD-*.md
│       ├── UC-SO-*.md
│       ├── UC-TRF-*.md
│       ├── UC-USER-*.md
│       └── UC-WH-*.md
│
├── template/
│   ├── index.html
│   ├── assets/
│   ├── fonts/
│   ├── html/
│   ├── js/
│   └── libs/
│
└── pom.xml

```

## Architecture

This project follows the **MVC (Model-View-Controller)** pattern:

- **Model**: Entity classes representing database tables (Product.java)
- **View**: JSP pages for user interface
- **Controller**: Servlets handling HTTP requests (ProductController.java)

### Layers:

1. **Presentation Layer** (JSP Views)
   - User interface
   - Display data and forms

2. **Controller Layer** (Servlets)
   - Handle HTTP requests
   - Route requests to appropriate services
   - Prepare data for views

3. **Service Layer**
   - Business logic
   - Data validation
   - Transaction management

4. **Data Access Layer** (DAOs)
   - Database operations (CRUD)
   - SQL queries
   - Data mapping

5. **Model Layer** (Entities)
   - Data representation
   - Domain objects

## Technologies Used

- **Java 21**
- **Jakarta EE 10** (Servlets 6.0, JSP 3.1)
- **JSTL** (JSP Standard Tag Library)
- **SQL Server** (Database with JDBC driver)
- **Maven** (Build tool)
- **Apache Tomcat 10+** (Web server)
- **SHA-256** (Password hashing with salt)
- **Bootstrap 5** (Frontend UI framework)

## 🔐 Authentication & Authorization
- **Secure Login**: Username/password with SHA-256 hashing
- **Role-Based Access**: Admin, Manager, Staff, Sales roles
- **Session Management**: 30-minute inactivity timeout
- **Protected Routes**: Authorization filter for all resources
- **Password Management**: Change password and admin reset

### 📦 Product Management
- **List Products**: View all products with search functionality
- **Create Product**: Add new products to inventory
- **View Product**: Display detailed product information
- **Update Product**: Edit product information
- **Delete Product**: Soft delete products (marks as inactive)

### 👤 User Management (Admin Only)
- **User CRUD**: Create, read, update, delete users
- **Role Assignment**: Assign roles to users
- **Password Reset**: Admin can reset user passwords
- **User Status**: Activate/deactivate user accounts
- **Warehouse Assignment**: Assign users to warehouses

### 🏭 Warehouse Management
- **Create Warehouse**: Add new warehouse locations
- **Update Warehouse**: Edit warehouse details
- **View Warehouses**: List all warehouses

### 📍 Location Management
- **Create Location**: Add storage locations within warehouses
- **Update Location**: Modify location details
- **Toggle Status**: Activate/deactivate locations
- **View Locations**: List all locations by warehouse

### 👥 Customer Management
- **Create Customer**: Register new customers
- **Update Customer**: Modify customer information
- **Toggle Status**: Activate/deactivate customers
- **View Customers**: List all customers

### 📊 Inventory Management
- **View by Warehouse**: See inventory organized by warehouse
- **View by Product**: See product inventory across warehouses
- **Search Inventory**: Find products in specific locations

### 📦 Category Management
- **Create Category**: Add product categories
- **Update Category**: Modify category information
- **Delete Category**: Remove categories
- **View Categories**: List all product categories

### 💼 Sales Order Management
- **Create Sales Order**: Generate new sales orders
- **Confirm Sales Order**: Approve pending orders
- **Generate Outbound**: Create outbound requests from sales orders
- **Cancel Sales Order**: Cancel orders when needed

### 🔄 Transfer Management
- **Create Transfer Request**: Initialize warehouse transfers
- **Execute Transfer Outbound**: Process outbound transfers
- **Execute Transfer Inbound**: Complete inbound transfers

### ↔️ Movement Management
- **Create Internal Movement**: Initiate internal movements
- **Execute Internal Movement**: Process location-to-location movements

### 📥 Inbound Management
- **Create Inbound Request**: Register incoming stock
- **Approve Inbound Request**: Authorize inbound operations
- **Execute Inbound Request**: Complete inbound operations

### 📤 Outbound Management
- **Approve Outbound Request**: Authorize outbound operations
- **Execute Outbound Request**: Process outbound fulfillment
- **Internal Outbound**: Handle internal outbound requests

## Setup Instructions

### 1. Database Setup
```bash
# Create database and run schema
sqlcmd -S localhost -i database/schema.sql

# Run user seed data
sqlcmd -S localhost -d smartwms_db -i database/user_seed.sql

# Run full seed data
sqlcmd -S localhost -d smartwms_db -i database/full_seed_data.sql
```

### 2. Configure Database Connection
Edit `src/main/resources/db.properties`:
```properties
db.url=jdbc:sqlserver://localhost;databaseName=smartwms_db;encrypt=true;trustServerCertificate=true
db.username=your_username
db.password=your_password
db.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

**Benefits of Properties File:**
- No need to recompile when changing database credentials
- Easy to configure different environments (dev/staging/prod)
- Keeps sensitive data separate from source code

### 3. Build Project
```bash
mvn clean package
```

### 4. Deploy to Tomcat
Copy `target/buildms.war` to Tomcat's `webapps/` directory.

### 5. Access Application
- URL: `http://localhost:8080/buildms/`
- Login: Use test credentials from user_seed.sql

## Documentation

- **[SRS.md](document/SRS.md)** - Software Requirements Specification
- **[AUTHENTICATION.md](document/AUTHENTICATION.md)** - Authentication system details
- **[AUTH_QUICK_REF.md](document/AUTH_QUICK_REF.md)** - Authentication quick reference
- **[Detail Design Documents](document/detail-design/)** - Detailed use case specifications
- **[schema.sql](database/schema.sql)** - Database schema
- **[user_seed.sql](database/user_seed.sql)** - User data
- **[full_seed_data.sql](database/full_seed_data.sql)** - Complete test data

## License

This project is for educational purposes.
