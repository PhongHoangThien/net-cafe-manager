package DAO;

import DAO.Interface.IComputerUsageDAO;
import DTO.ComputerUsage;
import DTO.ComputerUsageFilter;
import Utils.Helper;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ComputerUsageImpl extends BaseDAO implements IComputerUsageDAO {

    @Override
    public ComputerUsage create(ComputerUsage computerUsage) throws SQLException {
        return null;
    }

    @Override
    public ComputerUsage update(ComputerUsage computerUsage) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public ComputerUsage findById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<ComputerUsage> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<ComputerUsage> findByFilter(ComputerUsageFilter filter) throws Exception {
        return null;
    }
}