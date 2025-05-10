package DAO;

import DAO.Interface.IProductDAO;
import DTO.Product;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductDAOImpl extends BaseDAO implements IProductDAO {

    @Override
    public Product create(Product product) throws SQLException {
        return null;
    }

    @Override
    public Product update(Product product) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public Product findById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return null;
    }

    @Override
    public Product findByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<Product> filterByTypeProduct(Product.ProductType type) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findListByName(String name) throws SQLException {
        return null;
    }
}
