CREATE DATABASE IF NOT EXISTS grocery_shop;
USE grocery_shop;

CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    description TEXT,
    image_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    phone VARCHAR(20),
    address TEXT,
    role ENUM('CUSTOMER', 'ADMIN') DEFAULT 'CUSTOMER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status ENUM('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED') DEFAULT 'PENDING',
    delivery_address TEXT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO products (name, category, price, quantity, description) VALUES
('Basmati Rice (5kg)', 'Grains & Staples', 320.00, 100, 'Premium aged basmati rice'),
('Whole Wheat Atta (10kg)', 'Grains & Staples', 450.00, 80, 'Stone ground whole wheat flour'),
('Toor Dal (1kg)', 'Pulses & Lentils', 140.00, 150, 'Premium quality toor dal'),
('Fresh Tomatoes (1kg)', 'Vegetables', 40.00, 200, 'Farm fresh red tomatoes'),
('Onions (1kg)', 'Vegetables', 35.00, 300, 'Fresh onions'),
('Amul Butter (500g)', 'Dairy', 270.00, 60, 'Pure pasteurized butter'),
('Full Cream Milk (1L)', 'Dairy', 65.00, 500, 'Fresh full cream milk'),
('Olive Oil (1L)', 'Cooking Oil', 650.00, 40, 'Extra virgin olive oil'),
('Green Tea (100 bags)', 'Beverages', 350.00, 90, 'Organic green tea bags'),
('Fresh Apples (1kg)', 'Fruits', 180.00, 120, 'Kashmir red apples');

INSERT INTO users (username, email, password_hash, full_name, role) VALUES
('admin', 'admin@freshmart.com', 'admin123hash', 'Admin User', 'ADMIN');