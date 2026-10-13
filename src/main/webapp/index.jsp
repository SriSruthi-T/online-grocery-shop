<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Online Grocery Shopping - Home</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background: #f5f5f5; }
        .header { background: #2e7d32; color: white; padding: 20px; text-align: center; }
        .header h1 { margin: 0; }
        .nav { background: #1b5e20; padding: 10px; text-align: center; }
        .nav a { color: white; margin: 0 15px; text-decoration: none; font-weight: bold; }
        .container { max-width: 1200px; margin: 20px auto; padding: 0 20px; }
        .hero { background: #4caf50; color: white; padding: 60px 20px; text-align: center; border-radius: 8px; }
        .hero h2 { font-size: 2em; margin-bottom: 10px; }
        .categories { display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px; margin-top: 30px; }
        .category-card { background: white; padding: 30px; text-align: center; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .category-card:hover { transform: translateY(-5px); box-shadow: 0 5px 15px rgba(0,0,0,0.2); }
        .category-card h3 { color: #2e7d32; }
        .footer { background: #2e7d32; color: white; text-align: center; padding: 20px; margin-top: 40px; }
    </style>
</head>
<body>
    <div class="header">
        <h1>🛒 FreshMart - Online Grocery Shopping</h1>
        <p>Fresh groceries delivered to your doorstep</p>
    </div>
    <div class="nav">
        <a href="/online-grocery-shop/products/">All Products</a>
        <a href="/online-grocery-shop/cart/">Shopping Cart</a>
        <a href="/online-grocery-shop/products/search?query=">Search</a>
    </div>
    <div class="container">
        <div class="hero">
            <h2>Welcome to FreshMart!</h2>
            <p>Shop fresh fruits, vegetables, dairy, and groceries online.</p>
        </div>
        <h2 style="text-align:center; margin-top:30px;">Shop by Category</h2>
        <div class="categories">
            <div class="category-card"><h3>🥦 Vegetables</h3><p>Farm fresh vegetables</p></div>
            <div class="category-card"><h3>🍎 Fruits</h3><p>Seasonal & exotic fruits</p></div>
            <div class="category-card"><h3>🥛 Dairy</h3><p>Milk, cheese & more</p></div>
            <div class="category-card"><h3>🌾 Grains</h3><p>Rice, atta & cereals</p></div>
        </div>
    </div>
    <div class="footer">
        <p>&copy; 2026 FreshMart Online Grocery Shopping. DevOps Lab Project.</p>
    </div>
</body>
</html>