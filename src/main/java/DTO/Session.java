package DTO;

<<<<<<< HEAD
=======


<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session implements java.io.Serializable {
    @Serial
<<<<<<< HEAD
    private static final long serialVersionUID = 432430624324L; // Mã phiên bản Serializable
=======
    private static final long serialVersionUID = 432430624324L;
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)

    // Constructor copy từ phiên session khác
    public Session(Session session){
        this.id = session.id;
        this.totalTime = session.totalTime;
        this.usedTime = session.usedTime;
        this.usedCost = session.usedCost;
        this.serviceCost = session.serviceCost;
        this.startTime = session.startTime;
        this.prepaidAmount = session.prepaidAmount;
        this.usingBy = session.usingBy;
        this.usingByAccount = session.usingByAccount;
        this.computerID = session.computerID;
        this.usingComputer = session.usingComputer;
    }

<<<<<<< HEAD
    private Integer id;                 // ID phiên làm việc
    private int totalTime = 0;          // Tổng thời gian sử dụng (giây)
    private int usedTime = 0;           // Thời gian đã sử dụng (giây)
    private double usedCost = 0;        // Chi phí đã dùng (VND)
    private int serviceCost = 0;        // Chi phí dịch vụ (VND)
    private Date startTime = new Date();// Thời điểm bắt đầu phiên làm việc
    private double prepaidAmount = 0;   // Số tiền trả trước (VND)
    private Integer usingBy = null;     // ID người dùng
    private Account usingByAccount;     // Thông tin tài khoản người dùng
    private Integer computerID = null;  // ID máy tính sử dụng
    private Computer usingComputer;     // Thông tin máy tính đang dùng
=======
    private Integer id;

    private int totalTime=0; // seconds

    private int usedTime = 0; // seconds


    private double usedCost = 0; // VND


    private int serviceCost = 0; // VND

    private Date startTime = new Date();

    private double prepaidAmount = 0; // VND
    private Integer usingBy = null;
    private Account usingByAccount;

    private Integer computerID = null;
    private Computer usingComputer;


>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
}
