package DTO;

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
    private static final long serialVersionUID = 432430624324L; // Mã phiên bản Serializable

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
}
