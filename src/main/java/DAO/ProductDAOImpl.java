package DAO;

import DAO.Interface.IProductDAO;
import DTO.Product;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

// Lớp triển khai các thao tác CRUD với bảng Product trong CSDL
public class ProductDAOImpl extends BaseDAO implements IProductDAO {

    // Thêm sản phẩm mới vào bảng Product
    @Override
    public Product create(Product product) throws SQLException {
        var preprapedStament = DBHelper
                .getConnection()
                .prepareStatement(
                        "INSERT INTO Product(name, price, type, stock, description, image, createdAt, deletedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS // Trả về ID tự tăng sau khi insert
                );

        // Gán giá trị cho các cột
        preprapedStament.setString(1, product.getName());
        preprapedStament.setDouble(2, product.getPrice());
        preprapedStament.setInt(3, product.getType().ordinal()); // enum -> số nguyên
        preprapedStament.setInt(4, product.getStock());
        preprapedStament.setString(5, product.getDescription());
        preprapedStament.setString(6, product.getImage());
        preprapedStament.setDate(7, new java.sql.Date(new java.util.Date().getTime())); // createdAt = thời gian hiện tại
        preprapedStament.setDate(8, null); // deletedAt = null (chưa bị xoá)

        var result = preprapedStament.executeUpdate();

        // Nếu thêm thành công, lấy ID vừa tạo và trả về sản phẩm mới tạo
        if (result > 0) {
            var resultSet = preprapedStament.getGeneratedKeys();
            if (resultSet.next()) {
                var newId = resultSet.getInt(1);
                return this.findById(newId);
            }
        }

        return null;
    }

    // Cập nhật thông tin sản phẩm (theo ID)
    @Override
    public Product update(Product product) throws SQLException {
        var preparedStatement = this.prepareStatement(
                "UPDATE product SET " +
                        " name = ?, " +
                        " price = ?, " +
                        " type = ?, " +
                        " stock = ?, " +
                        " description = ?, " +
                        " image = ?, " +
                        " createdAt = ? " + // Cập nhật cả createdAt (nếu muốn giữ nguyên cần truyền đúng)
                        " WHERE id = ? "
        );

        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getType().ordinal());
        preparedStatement.setInt(4, product.getStock());
        preparedStatement.setString(5, product.getDescription());
        preparedStatement.setString(6, product.getImage());
        preparedStatement.setDate(7, new java.sql.Date(product.getCreatedAt().getTime()));
        preparedStatement.setInt(8, product.getId());

        var result = preparedStatement.executeUpdate();
        preparedStatement.close();

        return result > 0 ? this.findById(product.getId()) : null;
    }

    // Xoá sản phẩm (xoá mềm = cập nhật deletedAt thay vì xoá thật)
    @Override
    public boolean delete(Integer integer) throws SQLException {
        var preparedStatement = this.prepareStatement("UPDATE product SET deletedAt = ? WHERE id = ?");
        preparedStatement.setDate(1, new java.sql.Date(new java.util.Date().getTime())); // Gán thời gian xoá
        preparedStatement.setInt(2, integer);

        var result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result > 0;
    }

    // Tìm sản phẩm theo ID, chỉ lấy sản phẩm chưa bị xoá (deletedAt IS NULL)
    @Override
    public Product findById(Integer integer) throws SQLException {
        var preparedStatement = this.prepareStatement("SELECT * FROM Product p WHERE p.id = ? and p.deletedAt is null");
        preparedStatement.setInt(1, integer);

        var resultSet = preparedStatement.executeQuery();
        var products = DBHelper.toList(resultSet, Product.class);

        preparedStatement.close();
        return products.size() > 0 ? products.get(0) : null;
    }

    // Trả về toàn bộ sản phẩm chưa bị xoá
    @Override
    public List<Product> findAll() throws SQLException {
        var statement = this.createStatement();
        var resultSet = statement.executeQuery("SELECT * FROM product p WHERE p.deletedAt is null");

        var products = DBHelper.toList(resultSet, Product.class);
        statement.close();
        return products;
    }

    // Tìm sản phẩm theo tên chính xác (exact match), chỉ lấy sản phẩm chưa xoá
    @Override
    public Product findByName(String name) throws SQLException {
        var statement = this.prepareStatement("SELECT * FROM product p WHERE p.name = ? and p.deletedAt is null");
        statement.setString(1, name);

        var resultSet = statement.executeQuery();
        var products = DBHelper.toList(resultSet, Product.class);

        return products.size() > 0 ? products.get(0) : null;
    }

    // Lọc sản phẩm theo loại (enum ProductType), chỉ lấy sản phẩm chưa bị xoá
    @Override
    public List<Product> filterByTypeProduct(Product.ProductType type) throws SQLException {
        var statement = this.createStatement();
        var resultSet = statement.executeQuery(
                "SELECT * FROM Product p WHERE p.type = " + type.ordinal() + " and p.deletedAt is null"
        );

        var products = DBHelper.toList(resultSet, Product.class);
        statement.close();
        return products;
    }

    // Tìm danh sách sản phẩm có tên gần đúng (dùng LIKE %name%), chỉ lấy sản phẩm chưa bị xoá
    @Override
    public List<Product> findListByName(String name) throws SQLException {
        var statement = this.createStatement();
        var resultSet = statement.executeQuery(
                "SELECT * FROM Product p WHERE p.name LIKE N'%" + name + "%' AND p.deletedAt is null"
        );

        var products = DBHelper.toList(resultSet, Product.class);
        statement.close();
        return products;
    }
}
