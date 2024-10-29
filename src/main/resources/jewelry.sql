-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 29, 2024 lúc 03:06 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `jewelry`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`user_id`) VALUES
(2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_item`
--

CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `cart_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cart_item`
--

INSERT INTO `cart_item` (`id`, `quantity`, `cart_id`, `product_id`) VALUES
(48, 3, 2, 2),
(49, 3, 2, 4),
(50, 3, 2, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `news`
--

CREATE TABLE `news` (
  `news_id` bigint(20) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `image` varchar(20) DEFAULT NULL,
  `title` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `news`
--

INSERT INTO `news` (`news_id`, `content`, `enabled`, `image`, `title`) VALUES
(2, 'Hãy để tình yêu của bạn được khắc ghi vĩnh cửu trong một chiếc nhẫn đầy ý nghĩa, thể hiện sự chân thành và lòng quyết tâm. Nhận phiếu mua hàng trị giá 100.000 đồng khi mua nhẫn cầu hôn từ ngày 20/10/2024 đến hết ngày 20/11/2024, đến KAJ ngay.', b'1', '_news2.jpg', '<p><strong><span style=\"font-size:20px\">Phiếu mua h&agrave;ng&nbsp;<span style=\"color:#003399\"><em>100.000 đồng&nbsp;</em></span>khi mua <span style=\"color:#e67e22\"><em>nhẫn cầu h&ocirc;n</em></span></span></strong></p>\r\n'),
(3, 'Nhân dịp Valentine này, KAJ trân trọng giảm giá lên đến 30% cho các sản phẩm hẹn hò dành cho thành viên bạc trở lên trong hệ thống. Chương trình khuyến mãi diễn ra đến hết ngày 08/03/2025, đến KAJ để nhận ưu đãi ngay.', b'1', '_news3.png', '<p><span style=\"font-size:20px\"><strong>Giảm đến <span style=\"color:#f39c12\">30%</span> nh&acirc;n dịp <span style=\"color:#cc3366\">Valentine</span>&nbsp;</strong></span></p>\r\n');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `product_description` varchar(1000) NOT NULL,
  `product_image` varchar(20) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `product_price` bigint(20) NOT NULL,
  `product_quantity` bigint(20) NOT NULL,
  `product_type_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`product_id`, `enabled`, `product_description`, `product_image`, `product_name`, `product_price`, `product_quantity`, `product_type_id`) VALUES
(2, b'1', 'Giá sản phẩm thay đổi tùy trọng lượng vàng và đá;\r\nTrọng lượng tham khảo : 8.40908;\r\nHàm lượng chất liệu : 9300.', '_product2.png', 'Dây chuyền Vàng Trắng Ý 18K xen kẽ', 20068000, 5, 1),
(4, b'1', 'Loại đá chính: Freshwater Pearl;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 2.38981;\r\nHàm lượng chất liệu: 9300;', '_product4.png', 'Bông tai Bạc đính đá Silver ngọc trai ', 595000, 8, 4),
(5, b'1', 'Loại đá chính: Xoàn mỹ;\r\nLoại đá phụ: Xoàn mỹ;\r\nMàu đá phụ: Trắng;\r\nSố thứ tự của Đá gắn kèm: 00;\r\nTrọng lượng tham khảo: 6.26718;\r\nMàu đá chính: Trắng;\r\nCut (Giác cắt/ Hình dạng kim cương): Facet;\r\nSố viên đá phụ: 54;\r\nSố viên đá chính: 1;\r\nHàm lượng chất liệu: 4160.', '_product5.png', 'Nhẫn Vàng trắng 10K đính đá loại lớn', 5071000, 10, 2),
(6, b'1', 'Loại đá chính: Xoàn mỹ;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 7.67045;\r\nSố viên đá phụ: 10;\r\nSố viên đá chính: 1;\r\nHàm lượng chất liệu: 7500.', '_product6.png', 'Nhẫn Vàng 18K đính đá ECZ cao cấp', 6920000, 3, 2),
(7, b'1', 'Loại đá chính: Kim cương;\r\nLoại đá phụ: Kim cương;\r\nTrọng lượng tham khảo: 3.21633;\r\nHàm lượng chất liệu: 5850.', '_product7.png', 'Bông tai Kim cương Vàng trắng 14K', 18900000, 3, 4),
(8, b'1', 'Loại đá chính: Synthetic;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 27.50384;\r\nSố viên đá phụ: 61;\r\nSố viên đá chính: 1;\r\nHàm lượng chất liệu : 5850.', '_product8.png', 'Vòng tay Vàng trắng 14K đính đá Synthetic', 23347000, 5, 3),
(9, b'1', 'Loại đá chính: Xoàn mỹ;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 3.4239;\r\nHàm lượng chất liệu: 9300.', '_product9.png', 'Nhẫn bạc đính đá Silver hình bông hoa', 455000, 10, 2),
(10, b'1', 'Loại đá chính: Không gắn đá;\r\nLoại đá phụ: Không gắn đá;\r\nTrọng lượng tham khảo: 42.30181;\r\nHàm lượng chất liệu: 7500.', '_product10.png', 'Dây chuyền Vàng trắng Ý 18K sợi to xoắn', 34265000, 3, 1),
(11, b'1', 'Loại đá chính: Không gắn đá;\r\nLoại đá phụ: Không gắn đá;\r\nTrọng lượng tham khảo: 21.13408.', '_product11.png', 'Vòng tay Vàng 14K Mickey alone', 14429000, 7, 3),
(12, b'1', 'Loại đá chính: Không gắn đá;\r\nLoại đá phụ: Không gắn đá;\r\nTrọng lượng tham khảo: 7.42421;', '_product12.png', 'Dây chuyền Vàng 24K sợi mảnh cao cấp', 6846000, 6, 1),
(13, b'1', 'Loại đá chính: Xoàn mỹ;\r\nLoại đá phụ: Không gắn đá;\r\nTrọng lượng tham khảo: 1.85049;\r\nHàm lượng chất liệu : 7500.', '_product13.png', 'Bông tai trẻ em Vàng 18k đính đá', 1640000, 9, 4),
(14, b'1', 'Loại đá chính: Citrine;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 19.76057;\r\nHàm lượng chất liệu: 7500.', '_product14.png', 'Nhẫn Vàng 18K đính đá Citrine ', 22007000, 4, 2),
(15, b'1', 'Loại đá chính: Synthetic;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 5.38254;\r\nSố viên đá phụ: 34;\r\nSố viên đá chính: 2;\r\nHàm lượng chất liệu : 5850.', '_product15.png', 'Bông tai Vàng trắng 14K đính đá Synthetic', 6087000, 6, 4),
(16, b'1', 'Số thứ tự của Đá gắn kèm: 06;\r\nTrọng lượng tham khảo: 3.46178;\r\nHàm lượng chất liệu: 4160.', '_product16.png', 'Dây chuyền Vàng trắng 10K dây đan ', 1546000, 6, 1),
(17, b'1', 'Loại đá chính: Xoàn mỹ;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 30.91436.', '_product17.png', 'Lắc tay Vàng trắng 10K Đính đá ECZ ', 21900000, 5, 3),
(18, b'1', 'Loại đá chính: Kim cương;\r\nLoại đá phụ: Không gắn đá;\r\nTrọng lượng tham khảo: 4.12607;\r\nHàm lượng chất liệu: 5850,', '_product18.png', 'Nhẫn Kim cương Vàng Trắng 14K hình bướm độc đáo', 7190000, 8, 2),
(19, b'1', 'Loại đá chính: Akoya Pearl;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 4.98232;\r\nHàm lượng chất liệu: 4160.', '_product19.png', 'Nhẫn Vàng trắng 10K Đính ngọc trai Akoya', 5984000, 4, 2),
(20, b'1', 'Trọng lượng tham khảo: 32.68;\r\nHàm lượng chất liệu: 9250.', '_product20.png', 'Dây chuyền bạc Ý Silver thiết kế độc đáo ', 1295000, 9, 1),
(21, b'1', 'Loại đá chính: Kim cương;\r\nLoại đá phụ: Kim cương;\r\nTrọng lượng tham khảo: 6.37188;\r\nHàm lượng chất liệu: 5850.', '_product21.png', 'Bông tai Kim cương Vàng trắng 14K tinh tế ', 32900000, 2, 4),
(22, b'1', 'Trọng lượng tham khảo: 15.14224', '_product22.png', 'Nhẫn cưới Vàng 24K Trầu Cau Hạnh Phúc', 15110000, 9, 2),
(23, b'1', 'Trọng lượng tham khảo: 38.81167;\r\nHàm lượng chất liệu: 9990.', '_product23.png', 'Vòng tay Vàng 24K Cỏ May Mắn', 35600000, 4, 3),
(24, b'1', 'Trọng lượng tham khảo: 21.83146;\r\nHàm lượng chất liệu: 9990.', '_product24.png', 'Bông tai cưới Vàng 24K Song Hỷ Lâm Môn', 20458000, 4, 4),
(25, b'1', 'Trọng lượng tham khảo: 58.42;\r\nHàm lượng chất liệu: 9250.', '_product25.png', 'Kiềng Bạc Ý Silver xoắn 2 màu độc đáo', 1690500, 10, 1),
(26, b'1', 'Loại đá chính: Xoàn mỹ;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 60.92387.', '_product26.png', 'Lắc tay Vàng trắng 10K Đính đá ECZ Thời thượng', 49900000, 2, 3),
(27, b'1', 'Trọng lượng tham khảo: 30.91876;\r\nHàm lượng chất liệu: 9990.', '_product27.png', 'Lắc tay Vàng 24K giọt nước mới lạ', 31717000, 3, 3),
(28, b'1', 'Loại đá chính: Tahiti Pearl;\r\nLoại đá phụ: Kim cương;\r\nTrọng lượng tham khảo: 5.23177;\r\nHàm lượng chất liệu : 5850.', '_product28.png', 'Bông tai Vàng trắng 14K Đính ngọc trai Tahit', 23206000, 2, 4),
(29, b'1', 'Loại đá chính: Topaz;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 16.30911;\r\nSố viên đá phụ: 44;\r\nSố viên đá chính: 1.', '_product29.png', 'Nhẫn Vàng trắng 14K đính đá Topaz', 16410000, 5, 2),
(30, b'1', 'Loại đá chính: Tourmaline;\r\nLoại đá phụ: Xoàn mỹ;\r\nTrọng lượng tham khảo: 11.3995.', '_product30.png', 'Nhẫn Vàng 14K đính đá Tourmaline', 14685000, 2, 2),
(31, b'1', 'Số thứ tự của Đá gắn kèm: 02;\r\nTrọng lượng tham khảo: 87.1168;\r\nHàm lượng chất liệu: 7500.', '_product31.png', 'Dây chuyền Vàng 18K thiết kế độc đáo', 65338000, 2, 1),
(32, b'1', 'Loại đá chính: Xoàn mỹ;\r\nTrọng lượng tham khảo: 4.98132;\r\nHàm lượng chất liệu: 4160;', '_product32.png', 'Bông tai Vàng trắng 10K Đính đá ECZ', 4990000, 10, 4),
(33, b'1', 'Trọng lượng tham khảo: 66.59518;\r\nHàm lượng chất liệu: 7500.', '_product33.png', 'Kiềng cưới Vàng 18K Trầu Cau Hạnh Phúc', 52905000, 2, 1),
(34, b'1', 'Số thứ tự của Đá gắn kèm: 13;\r\nTrọng lượng tham khảo: 30.', '_product34.png', 'Kiềng cưới Vàng 24K đơn giản sang trọng', 26765000, 5, 1),
(35, b'1', 'Phong cách: Fashion;\r\nTrọng lượng tham khảo: 33.432;\r\nHàm lượng chất liệu: 9250.', '_product35.png', 'Dây chuyền Bạc Ý Silver thiết kế mới lạ', 1256500, 9, 1),
(36, b'1', 'Trọng lượng tham khảo: 6.266;\r\nHàm lượng chất liệu: 5850.', '_product36.png', 'Nhẫn Vàng 14K Disney Jasmine', 4792000, 6, 2),
(37, b'1', 'Giá sản phẩm thay đổi tùy trọng lượng vàng và đá; \r\nTrọng lượng tham khảo : 8.40908; \r\nHàm lượng chất liệu : 9300.', '_product37.png', 'Dây chuyền Vàng Trắng Ý 18K xen kẽ', 20068000, 3, 1),
(38, b'1', 'Giá sản phẩm thay đổi tùy trọng lượng vàng và đá; Trọng lượng tham khảo : 8.40908; Hàm lượng chất liệu : 9300.', '_product38.png', 'Dây chuyền Vàng Trắng Ý 18K xen kẽ', 20068000, 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_type`
--

CREATE TABLE `product_type` (
  `product_type_id` bigint(20) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `product_type_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product_type`
--

INSERT INTO `product_type` (`product_type_id`, `enabled`, `product_type_name`) VALUES
(1, b'1', 'Dây chuyền'),
(2, b'1', 'Nhẫn'),
(3, b'1', 'Vòng tay'),
(4, b'1', 'Bông tai');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `role_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`user_id`, `birthday`, `email`, `fullname`, `gender`, `password`, `username`) VALUES
(1, '2003-07-10', 'khaai142003@gmail.com', 'Đoàn Khả Ái', 1, '$2a$12$funGOYd9sxiYQmZZFZYNIOc4Pbp0RXtHT3bD4GiX0vn0ieYQtakYO', 'doankhaai'),
(2, '2003-07-10', 'ai@gmail.com', 'Nguyễn Văn A', 1, '$2a$12$mnwdiZGzbZDZw3hfG1x2a.Q04L.EjR2Hytq6AnAOA9qF.JtQ7v9d2', 'abc');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`user_id`);

--
-- Chỉ mục cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  ADD KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`);

--
-- Chỉ mục cho bảng `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`news_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `FKlabq3c2e90ybbxk58rc48byqo` (`product_type_id`);

--
-- Chỉ mục cho bảng `product_type`
--
ALTER TABLE `product_type`
  ADD PRIMARY KEY (`product_type_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- Chỉ mục cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT cho bảng `news`
--
ALTER TABLE `news`
  MODIFY `news_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `product_type`
--
ALTER TABLE `product_type`
  MODIFY `product_type_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Các ràng buộc cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  ADD CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`user_id`),
  ADD CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FKlabq3c2e90ybbxk58rc48byqo` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`product_type_id`);

--
-- Các ràng buộc cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
