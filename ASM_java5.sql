Create DATABASE ASM_java5;
GO
drop database ASM_java5
USE ASM_java5;
GO

CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) UNIQUE NOT NULL,
    password NVARCHAR(255) NOT NULL,
    full_name NVARCHAR(100),
    email NVARCHAR(100) UNIQUE,
    phone NVARCHAR(15),
    address NVARCHAR(255),
    role NVARCHAR(20) DEFAULT 'USER', -- USER hoặc ADMIN
    created_at DATETIME DEFAULT GETDATE()
);
GO

CREATE TABLE Categories (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL UNIQUE,
    description NVARCHAR(255),
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- Bảng Sản Phẩm (Products)
CREATE TABLE Products (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    description NVARCHAR(1000),
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT DEFAULT 0,
    image_url NVARCHAR(255),
    category_id INT,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (category_id) REFERENCES Categories(id) ON DELETE SET NULL
);
GO

CREATE TABLE Orders (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT,
    total_price DECIMAL(10,2) NOT NULL,
    status NVARCHAR(20) DEFAULT 'Pending', -- Pending, Completed, Cancelled
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);
GO

CREATE TABLE OrderDetails (
    id INT IDENTITY(1,1) PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Products(id) ON DELETE CASCADE
);
GO

CREATE TABLE Cart (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT,
    product_id INT,
    quantity INT NOT NULL DEFAULT 1,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Products(id) ON DELETE CASCADE
);
GO
CREATE TABLE checkouts (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    status NVARCHAR(20) NOT NULL DEFAULT 'Pending', -- Pending, Completed, Cancelled
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);
select * from Checkouts

INSERT INTO Users (username, password, full_name, email, phone, address, role) VALUES
('admin', 'admin123', 'Administrator', 'admin@example.com', '0123456789', '123 Admin Street', 'ADMIN'),
('john_doe', 'password123', 'John Doe', 'john@example.com', '0987654321', '456 User Lane', 'USER'),
('jane_smith', 'securepass', 'Jane Smith', 'jane@example.com', '0912345678', '789 Customer Blvd', 'USER');
select * from Users

INSERT INTO Categories (name, description) VALUES
('Clothing', 'All kinds of clothing'),
('Shoes', 'Various types of shoes'),
('Accessories', 'Fashion accessories');

INSERT INTO Products (name, description, price, stock_quantity, image_url, category_id) VALUES
('iPhone 16', 'Thiết kế sang trọng', 1500000, 50, 'https://cdnv2.tgdd.vn/mwg-static/common/News/1569702/iphone%2016%20pro%20-%203.jpg', 1),
('iPhone 16 Pro Max', 'Thiết kế sang trọng, tinh sảo', 399000, 30, 'https://hoanghamobile.com/Uploads/2024/09/10/iphone-16-pro-sa-mac-1.png', 1),
('Sam Sung S23 Ultra', 'Samsung Galaxy S23 Ultra Hàn là một bước nhảy vọt tuyệt vời', 5900000, 20, 'https://didongmoi.com.vn/upload/images/product/samsung/samsung-galaxy-s23-ultra-5g-han-3.jpg', 2),
('Samsung Galaxy Z Flip6', 'Sang xịn mịn', 8900000, 15, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/a/samsung-galaxy-z-flip-6-xanh-la-6_2_1.png', 3),
('Samsung Galaxy S24 Ultra', 'Hỗ trợ bút S Pen, camera 200MP', 27990000, 40, 'https://didongmoi.com.vn/upload/images/product/samsung/samsung-galaxy-s24-ultra-gia-re-4.jpg', 1),
('Google Pixel 8 Pro', 'Camera AI tiên tiến, Android gốc', 23990000, 30, 'https://cdn2.cellphones.com.vn/x/media/catalog/product/g/o/google-pixel-8-pro_7_.png', 1),
('Xiaomi 14 Ultra', 'Snapdragon 8 Gen 3, màn hình 2K', 19990000, 20, 'https://dienthoaipro.com/wp-content/uploads/2024/09/xiaomi-14-ultra_3.webp', 2),
('OnePlus 12', 'Màn hình AMOLED 144Hz, sạc nhanh 100W', 17990000, 25, 'https://dienthoaihay.vn/images/products/2023/12/13/original/oneplus-12-trang_1702458821.jpg.jpg', 3),
('MacBook Pro M3', 'Chip M3 Max, màn hình mini-LED', 59990000, 15, 'https://example.com/macbookpro.jpg', 1),
('Dell XPS 15', 'Intel Core i9, màn hình 4K OLED', 45990000, 20, 'https://example.com/dellxps15.jpg', 2),
('Asus ROG Zephyrus G16', 'AMD Ryzen 9, RTX 4090', 48990000, 10, 'https://example.com/rogzephyrus.jpg', 3),
('HP Spectre x360', 'Thiết kế 2-in-1, màn hình cảm ứng', 34990000, 12, 'https://example.com/hpspectre.jpg', 1),
('Lenovo ThinkPad X1 Carbon', 'Bền bỉ, bảo mật cao', 31990000, 18, 'https://example.com/thinkpadx1.jpg', 2),
('iPad Pro M2', 'Chip M2, màn hình mini-LED', 32990000, 25, 'https://example.com/ipadprom2.jpg', 3),
('Samsung Galaxy Tab S9 Ultra', 'Màn hình AMOLED 14.6 inch', 28990000, 30, 'https://example.com/tabs9ultra.jpg', 1),
('Xiaomi Pad 6 Pro', 'Màn hình 144Hz, Snapdragon 8+', 14990000, 22, 'https://example.com/miPad6Pro.jpg', 2),
('Lenovo Tab P12 Pro', 'Bút stylus, màn hình 2K', 16990000, 15, 'https://example.com/lenovop12.jpg', 3),
('Huawei MatePad Pro 13.2', 'HarmonyOS, bút M-Pencil', 18990000, 18, 'https://example.com/matepadpro.jpg', 1),
('Apple Watch Ultra 2', 'Thiết kế titanium, pin lâu', 24990000, 30, 'https://example.com/applewatchultra.jpg', 2),
('Samsung Galaxy Watch 6', 'Tizen OS, đo ECG', 10990000, 40, 'https://example.com/galaxywatch6.jpg', 1),
('Garmin Fenix 7', 'GPS đa chế độ, siêu bền', 21990000, 25, 'https://example.com/garminfenix7.jpg', 2),
('Huawei Watch GT 4', 'Pin 2 tuần, đo SpO2', 9990000, 35, 'https://example.com/huaweiwatchgt4.jpg', 3),
('Xiaomi Watch S2', 'Màn AMOLED, chống nước 5ATM', 5990000, 50, 'https://example.com/xiaomiwatchs2.jpg', 1);


INSERT INTO Orders (user_id, total_price, status) VALUES
(2, 99.98, 'Completed'),
(3, 59.99, 'Pending');

INSERT INTO OrderDetails (order_id, product_id, quantity, price) VALUES
(1, 1, 2, 15.99),
(1, 3, 1, 59.99),
(2, 2, 1, 39.99);

INSERT INTO Cart (user_id, product_id, quantity) VALUES
(2, 4, 1),
(3, 1, 2);

select * from users
select * from products
ALTER TABLE products ALTER COLUMN image_url VARCHAR(MAX);
