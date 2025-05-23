package BUS;

import DAO.Interface.IProductDAO;
import lombok.Setter;
import DTO.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductBUS {
    @Setter
    private IProductDAO productDAO;

    public ProductBUS() {
    }
//Chức năng: Trả về toàn bộ danh sách sản phẩm.
//Sử dụng: Khi hiển thị danh sách tất cả sản phẩm trong kho.
    public List<Product> findAllProduct(){
        try {
            return this.productDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
// Lọc sản phẩm theo ProductType (loại sản phẩm).
//Sử dụng: Ví dụ: Lọc chỉ hiển thị nước uống, đồ ăn, phụ kiện,...
    public  List<Product> findProductByType(Product.ProductType type) {
        try {
            return this.productDAO.filterByTypeProduct(type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Tìm một sản phẩm theo id.
    //Sử dụng: Khi cần hiển thị thông tin chi tiết sản phẩm.
    public Product findProductById(int id){
        try {
         return   productDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Tạo mới một sản phẩm trong CSDL.
    //Sử dụng: Khi thêm sản phẩm mới vào hệ thống.
    public void create(Product product) throws SQLException {
        this.productDAO.create(product);
    }
//Cập nhật thông tin sản phẩm.
//Sử dụng: Khi chỉnh sửa giá, tên, mô tả, loại của sản phẩm.
    public void update(Product product) throws SQLException {
        this.productDAO.update(product);
    }
// Xóa sản phẩm theo id.
//Sử dụng: Khi người quản lý muốn loại bỏ sản phẩm khỏi hệ thống.
    public void delete(int integer) throws SQLException {
        this.productDAO.delete(integer);
    }

    public Product findById(int id) throws SQLException {
        return this.productDAO.findById(id);
    }

    public Product findByName(String name) throws SQLException {
        return this.productDAO.findByName(name);
    }

    public List<Product> findAll() throws SQLException {
        return this.productDAO.findAll();
    }

    public List<Product> filterByTypeProduct(Product.ProductType type) throws SQLException {
        return this.productDAO.filterByTypeProduct(type);
    }
//ìm kiếm danh sách sản phẩm theo tên gần đúng (like)
//Sử dụng: Cho chức năng tìm kiếm gợi ý hoặc tìm kiếm mờ.
    public List<Product> findListByName(String name) throws SQLException{
        return this.productDAO.findListByName(name);
    }
}
