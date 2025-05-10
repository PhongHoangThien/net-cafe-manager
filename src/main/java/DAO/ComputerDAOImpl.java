package DAO;

import DAO.Interface.IComputerDAO;
import DTO.Computer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class ComputerDAOImpl extends BaseDAO implements IComputerDAO{
    public  static ComputerDAOImpl getInstance(){
        return new ComputerDAOImpl();
    }
    @Override
    public Computer create(Computer computer) throws SQLException {
        return null;
    }

    @Override
    public Computer update(Computer computer) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;

    }

    @Override
    public Computer findById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<Computer> findAll() throws SQLException {
        return null;
    }

}
