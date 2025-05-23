package BUS;

import DAO.Interface.IComputerDAO;
import DAO.Interface.ISessionDAO;
import Io.Server;
import lombok.NoArgsConstructor;
import lombok.Setter;
import DTO.Computer;

import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor
public class ComputerBUS {
    @Setter
    private ISessionDAO sessionDAO;
    @Setter
    private IComputerDAO computerDAO;
    public List<Computer> updateListComputerStatus(List<Computer> computers){
//// Duyệt qua từng máy và kiểm tra xem có client đang kết nối hay không
        computers= computers.stream().peek(c->{
            var isConnect = Server.getInstance().getClients().stream().filter(l->l.getMachineId()==c.getId()).findFirst().orElse(null)!=null;
            //// Nếu có client kết nối thì trạng thái là LOCKED, ngược lại là OFF
            c.setStatus(isConnect?Computer.ComputerStatus.LOCKED.ordinal():Computer.ComputerStatus.OFF.ordinal());
        }).toList();
        return  computers.stream().peek(c->{
            try {
               // // Sau đó tiếp tục kiểm tra nếu máy đang có session (đang được sử dụng), thì đặt trạng thái là USING
                c.setStatus((sessionDAO.findByComputerId(c.getId())==null?c.getStatus():Computer.ComputerStatus.USING).ordinal());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }
    //Lấy danh sách toàn bộ máy
    public List<Computer> getAllComputers() throws SQLException {
        return computerDAO.findAll();
    }
    // Lấy thông tin 1 máy tính theo ID

    public Computer getComputerById(int id) throws SQLException {
        return computerDAO.findById(id);
    }
    // Cập nhật thông tin máy tính
    public void updateComputer(Computer computer) throws SQLException {
        computerDAO.update(computer);
    }
    //  Xóa máy tính theo ID
    public void deleteComputer(int id) throws SQLException {
        computerDAO.delete(id);
    }
    // addComputer – Thêm máy mới
    public void addComputer(Computer computer) throws SQLException {
        computerDAO.create(computer);
    }

}
