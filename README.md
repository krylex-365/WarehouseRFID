# Warehouse: Quản lý xuất kho với RFID
1. Yêu cầu
   - Hàng giả định được gắn chip RFID.
   - Tạo lệnh xuất hàng trên ứng dụng.
   - Khi hàng đi qua cổng xuất sẽ ghi nhận lại thông tin của hàng.
   - Xuất báo cáo các đơn hàng xuất kho.
2. Giải pháp
   - Sử dụng thiết bị đầu đọc ImpinJ UHF R420 để đọc các tag RFID trên sản phẩm.
   - Desktop Application được lập trình bằng ngôn ngữ Java.
   - Cơ sở dữ liệu Microsoft SQL Server để lưu trữ các thông tin/ dữ liệu.
3. Kỹ thuật liên quan
   - Nền tảng: Desktop Application
   - Ngôn ngữ lập trình: Java
   - IDE: Apache NetBeans
   - JDK: 16, 17
   - ImpinJ SDK: OctaneSDKJava 3.0
   - Mô hình lập trình: 3-layer
   - Database: Microsoft SQL Server

# Mô tả chức năng
1. Quản lý người dùng (Quyền Admin)
   - Thêm người dùng
   - Sửa thông tin người dùng
   - Khóa/ mở khóa người dùng
   - Xóa người dùng
2. Quản lý sản phẩm
   - Xem danh sách sản phẩm/tag
   - Thêm sản phẩm (Quyền Admin)
   - Sửa thông tin sản phẩm (tên, chi tiết) (Quyền Admin)
   - Xóa sản phẩm (Quyền Admin)
3. Gán tag đã quét vào các sản phẩm
   - Quét từng tag vào gán cho từng sản phẩm
   - Import bằng file Excel
4. Tạo đơn xuất kho
   - Chọn các sản phẩm vào đơn xuất muốn tạo
5. Quản lý xuất kho
   - Thực hiện xuất hàng theo đơn
   - Hoàn trả sản phẩm xuất sai đơn
   - Xóa đơn xuất (ở trạng thái chưa xuất kho)
6. Báo cáo "các đơn hàng xuất được tạo" theo khoảng thời gian

# Giao diện ứng dụng
1. Giao diện Đăng nhập
> ![image](https://user-images.githubusercontent.com/63896058/170010416-2dabdb4c-002e-4852-b103-5cf889af0e25.png)

2. Giao diện chính (Quyền Admin)
> ![image](https://user-images.githubusercontent.com/63896058/170011132-588eff6b-0cce-4e54-b3d6-2554fa6b9eda.png)

3. Giao diện chính (Quyền Staff)
> ![image](https://user-images.githubusercontent.com/63896058/170011286-880ded3c-66f1-41c5-bd26-6d491eb5f71c.png)

4. Giao diện Quản lý người dùng (Quyền Admin)
> ![image](https://user-images.githubusercontent.com/63896058/170011344-066aa189-b2af-4b48-b334-3a2a47a63a5c.png)

5. Giao diện Quản lý sản phẩm (Quyền Admin)
> ![image](https://user-images.githubusercontent.com/63896058/170011704-038afaf8-1beb-49b5-b6a1-396e9ae5d064.png)
> ![image](https://user-images.githubusercontent.com/63896058/170011769-7f37fdce-247a-4ed3-bf0a-486c90cb63bb.png)

6. Giao diện Quản lý sản phẩm (Quyền Staff)
> ![image](https://user-images.githubusercontent.com/63896058/170011876-36c85cf6-ddd8-4dea-ac44-c811d50d3d45.png)

7. Giao diện Gán tag
> ![image](https://user-images.githubusercontent.com/63896058/170011919-1b9c1e42-dd6e-4718-b375-d64efb17fbc1.png)

8. Giao diện Tạo đơn xuất kho
> ![image](https://user-images.githubusercontent.com/63896058/170011983-6cc16509-e008-4f1e-a617-764e504e9019.png)

9. Giao diện Quản lý xuất kho
> ![image](https://user-images.githubusercontent.com/63896058/170012078-58a00328-1365-4725-b0f4-7223767dbfa5.png)

10. Giao diện Tìm kiếm và báo cáo các đơn hàng xuất
> ![image](https://user-images.githubusercontent.com/63896058/170012193-da6fd01e-cb8e-4cbc-8342-ee4ad6b05e4b.png)

# File báo cáo
> ![image](https://user-images.githubusercontent.com/63896058/170012887-d1aafe24-bb1d-440b-a00e-9cfa5b86a879.png)
> ![image](https://user-images.githubusercontent.com/63896058/170012917-4c8c6a56-3120-43fe-883a-cad0b0e439fd.png)
