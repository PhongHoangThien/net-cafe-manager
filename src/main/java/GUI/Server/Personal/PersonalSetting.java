package GUI.Server.Personal;

import BUS.AccountBUS;
import BUS.ComputerUsageBUS;
import BUS.EmployeeBUS;
import BUS.InvoiceBUS;
import DTO.ComputerUsage;
import DTO.ComputerUsageFilter;
import DTO.Employee;
import GUI.Server.MainUI;
import Utils.ServiceProvider;

import javax.swing.*;
import java.sql.SQLException;

public class PersonalSetting extends javax.swing.JPanel {
    private EmployeeBUS employeeService ;
    private ComputerUsageBUS computerUsageBUS;
    private AccountBUS accountBUS;
    private InvoiceBUS invoiceBUS;


    public PersonalSetting() {
        employeeService = ServiceProvider.getInstance().getService(EmployeeBUS.class);
        computerUsageBUS = ServiceProvider.getInstance().getService(ComputerUsageBUS.class);
        accountBUS = ServiceProvider.getInstance().getService(AccountBUS.class);
        invoiceBUS = ServiceProvider.getInstance().getService(InvoiceBUS.class);
        initComponents();
        reDesign();

    }

    private void reDesign() {
        var employee = MainUI.getCurrentUser();
        try {
            var computerUsage = computerUsageBUS.findByFilter(
                    ComputerUsageFilter.builder().isEmployeeUsing(true).usedByAccountId(employee.getAccount().getId()).build());
            jTextFieldName.setText(employee.getName());
            jTextFieldUsername.setText(employee.getAccount().getUsername());
            jTextFieldSalary.setText(employee.getSalaryPerHour() + "");
            jTextFieldAddress.setText(employee.getAddress());
            jTextFieldPhoneNumber.setText(employee.getPhoneNumber());
            jTextAreaOther.setText(employee.getOtherInformation());
            var totalMoney = computerUsage.stream().mapToDouble(usage -> usage.getTotalMoney()).sum();
            var totalHour =0.0;
            for (var usage : computerUsage) {
                if (usage.getEndAt() == null){
                    break;
                }
                var minuteDiff = (usage.getEndAt().getTime() - usage.getCreatedAt().getTime()) / 1000 / 60;
                totalHour += minuteDiff*1.0 / 60;
            }
            var totalHourString = String.format("%.2f", totalHour);
            var totalMoneyString = String.format("%.2fđ", totalMoney);
            jTextFieldTotalTimeWork.setText(totalHourString);
            jTextFieldTotalSalary.setText(totalMoneyString);
            var currentMonth = java.time.LocalDate.now().getMonthValue();
            var currentYear = java.time.LocalDate.now().getYear();
            var currentMonthSalary = computerUsage.stream().filter(usage -> {
                var createdAt = usage.getCreatedAt();
                if (createdAt == null) {
                    return false;
                }
                var month = createdAt.getMonth() + 1;
                var year = createdAt.getYear() + 1900;
                return month == currentMonth && year == currentYear;
            }).mapToDouble(ComputerUsage::getTotalMoney).sum();
            var currentMonthSalaryString = String.format("%.2fđ", currentMonthSalary);
            var currentMonthTimeWork = computerUsage.stream().filter(usage -> {
                var createdAt = usage.getCreatedAt();
                if (createdAt == null) {
                    return false;
                }
                var month = createdAt.getMonth() + 1;
                var year = createdAt.getYear() + 1900;
                return month == currentMonth && year == currentYear;
            }).mapToDouble(usage -> {
                if (usage.getEndAt() == null) {
                    return 0;
                }
                var minuteDiff = (usage.getEndAt().getTime() - usage.getCreatedAt().getTime()) / 1000 / 60;
                return minuteDiff*1.0 / 60;
            }).sum();
            var currentMonthTimeWorkString = String.format("%.2f", currentMonthTimeWork);
            jTextFieldSalaryInMonth.setText(currentMonthSalaryString);
            jTextFieldTimeInMonth.setText(currentMonthTimeWorkString);
            jTextFieldInvoice.setText(invoiceBUS.countExportInvoiceSellByEmployeeId(employee.getId()) + "");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldSalary = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPhoneNumber = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaOther = new javax.swing.JTextArea();
        jPanel16 = new javax.swing.JPanel();
        jButtonChangePass = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldTotalTimeWork = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldTotalSalary = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldTimeInMonth = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldSalaryInMonth = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldInvoice = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Chỉnh sửa thông tin"), javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 10, 1));
        jPanel5.setMaximumSize(new java.awt.Dimension(32767, 65));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(292, 60));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel2.setText("Họ và tên: ");
        jPanel5.add(jLabel2, java.awt.BorderLayout.NORTH);

        jTextFieldName.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldName.setText("jTextField1");
        jPanel5.add(jTextFieldName, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 10, 1));
        jPanel6.setMaximumSize(new java.awt.Dimension(32767, 65));
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(292, 60));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel3.setText("Tên tài khoản:");
        jPanel6.add(jLabel3, java.awt.BorderLayout.NORTH);

        jTextFieldUsername.setEditable(false);
        jTextFieldUsername.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldUsername.setText("jTextField1");
        jTextFieldUsername.setFocusable(false);
        jTextFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsernameActionPerformed(evt);
            }
        });
        jPanel6.add(jTextFieldUsername, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 10, 1));
        jPanel7.setMaximumSize(new java.awt.Dimension(32767, 65));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(292, 60));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel4.setText("Lương trên giờ:");
        jPanel7.add(jLabel4, java.awt.BorderLayout.NORTH);

        jTextFieldSalary.setEditable(false);
        jTextFieldSalary.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldSalary.setText("jTextField1");
        jTextFieldSalary.setFocusable(false);
        jTextFieldSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSalaryActionPerformed(evt);
            }
        });
        jPanel7.add(jTextFieldSalary, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 10, 1));
        jPanel8.setMaximumSize(new java.awt.Dimension(32767, 65));
        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(292, 60));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel5.setText("Địa chỉ nhà: ");
        jPanel8.add(jLabel5, java.awt.BorderLayout.NORTH);

        jTextFieldAddress.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldAddress.setText("jTextField1");
        jPanel8.add(jTextFieldAddress, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel8);

        jPanel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 10, 1));
        jPanel9.setMaximumSize(new java.awt.Dimension(32767, 65));
        jPanel9.setOpaque(false);
        jPanel9.setPreferredSize(new java.awt.Dimension(292, 60));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel6.setText("Số điện thoại liên lạc:");
        jPanel9.add(jLabel6, java.awt.BorderLayout.NORTH);

        jTextFieldPhoneNumber.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldPhoneNumber.setText("jTextField1");
        jTextFieldPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhoneNumberActionPerformed(evt);
            }
        });
        jPanel9.add(jTextFieldPhoneNumber, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel9);

        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 10, 1));
        jPanel10.setMaximumSize(new java.awt.Dimension(32767, 150));
        jPanel10.setPreferredSize(new java.awt.Dimension(292, 100));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel7.setText("Khác:");
        jPanel10.add(jLabel7, java.awt.BorderLayout.NORTH);

        jTextAreaOther.setColumns(30);
        jTextAreaOther.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextAreaOther.setRows(5);
        jScrollPane1.setViewportView(jTextAreaOther);

        jPanel10.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel10);

        jPanel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 10, 1));
        jPanel16.setMaximumSize(new java.awt.Dimension(32767, 70));
        jPanel16.setPreferredSize(new java.awt.Dimension(292, 100));
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonChangePass.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jButtonChangePass.setText("Đổi mật khẩu");
        jButtonChangePass.setPreferredSize(new java.awt.Dimension(168, 36));
        jButtonChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangePassActionPerformed(evt);
            }
        });
        jPanel16.add(jButtonChangePass);

        jButtonReset.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jButtonReset.setText("Reset");
        jButtonReset.setPreferredSize(new java.awt.Dimension(100, 36));
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });
        jPanel16.add(jButtonReset);

        jButtonSave.setBackground(new java.awt.Color(51, 153, 255));
        jButtonSave.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jButtonSave.setText("Lưu");
        jButtonSave.setPreferredSize(new java.awt.Dimension(100, 36));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jPanel16.add(jButtonSave);

        jPanel2.add(jPanel16);

        jPanel1.add(jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Thống kê chung"), javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        jPanel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 20, 1));
        jPanel11.setMaximumSize(new java.awt.Dimension(32767, 70));
        jPanel11.setPreferredSize(new java.awt.Dimension(292, 100));
        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));

        jLabel8.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel8.setText("Tổng số giờ làm: ");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 20));
        jPanel11.add(jLabel8);

        jTextFieldTotalTimeWork.setEditable(false);
        jTextFieldTotalTimeWork.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldTotalTimeWork.setText("jTextField1");
        jTextFieldTotalTimeWork.setFocusable(false);
        jPanel11.add(jTextFieldTotalTimeWork);

        jPanel3.add(jPanel11);

        jPanel12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 20, 1));
        jPanel12.setMaximumSize(new java.awt.Dimension(32767, 70));
        jPanel12.setPreferredSize(new java.awt.Dimension(292, 100));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jLabel9.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel9.setText("Tổng tiền nhận:");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 20));
        jPanel12.add(jLabel9);

        jTextFieldTotalSalary.setEditable(false);
        jTextFieldTotalSalary.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldTotalSalary.setText("jTextField1");
        jTextFieldTotalSalary.setFocusable(false);
        jPanel12.add(jTextFieldTotalSalary);

        jPanel3.add(jPanel12);

        jPanel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 20, 1));
        jPanel13.setMaximumSize(new java.awt.Dimension(32767, 70));
        jPanel13.setPreferredSize(new java.awt.Dimension(292, 100));
        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));

        jLabel10.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel10.setText("Số giờ làm tháng này:");
        jLabel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 20));
        jPanel13.add(jLabel10);

        jTextFieldTimeInMonth.setEditable(false);
        jTextFieldTimeInMonth.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldTimeInMonth.setText("jTextField1");
        jTextFieldTimeInMonth.setFocusable(false);
        jTextFieldTimeInMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTimeInMonthActionPerformed(evt);
            }
        });
        jPanel13.add(jTextFieldTimeInMonth);

        jPanel3.add(jPanel13);

        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 20, 1));
        jPanel14.setMaximumSize(new java.awt.Dimension(32767, 70));
        jPanel14.setPreferredSize(new java.awt.Dimension(292, 100));
        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.LINE_AXIS));

        jLabel11.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel11.setText("Số tiền lương tháng này:");
        jLabel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 20));
        jPanel14.add(jLabel11);

        jTextFieldSalaryInMonth.setEditable(false);
        jTextFieldSalaryInMonth.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldSalaryInMonth.setText("jTextField1");
        jTextFieldSalaryInMonth.setFocusable(false);
        jTextFieldSalaryInMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSalaryInMonthActionPerformed(evt);
            }
        });
        jPanel14.add(jTextFieldSalaryInMonth);

        jPanel3.add(jPanel14);

        jPanel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 20, 1));
        jPanel15.setMaximumSize(new java.awt.Dimension(32767, 70));
        jPanel15.setPreferredSize(new java.awt.Dimension(292, 100));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.LINE_AXIS));

        jLabel12.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel12.setText("Số đơn đã bán:");
        jLabel12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 20));
        jPanel15.add(jLabel12);

        jTextFieldInvoice.setEditable(false);
        jTextFieldInvoice.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldInvoice.setText("jTextField1");
        jTextFieldInvoice.setFocusable(false);
        jTextFieldInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInvoiceActionPerformed(evt);
            }
        });
        jPanel15.add(jTextFieldInvoice);

        jPanel3.add(jPanel15);

        jPanel1.add(jPanel3);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Nunito", 1, 24)); // NOI18N
        jLabel1.setText("Thông tin người dùng");
        jPanel4.add(jLabel1);

        add(jPanel4, java.awt.BorderLayout.NORTH);
    }

    private void jTextFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsernameActionPerformed
        // TODO add your handling code here:
    }

    private void jTextFieldSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSalaryActionPerformed
        // TODO add your handling code here:
    }

    private void jTextFieldPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumberActionPerformed
        // TODO add your handling code here:
    }

    private void jTextFieldTimeInMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTimeInMonthActionPerformed
        // TODO add your handling code here:
    }

    private void jTextFieldSalaryInMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSalaryInMonthActionPerformed
        // TODO add your handling code here:
    }

    private void jTextFieldInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInvoiceActionPerformed
        // TODO add your handling code here:
    }

    private void jButtonChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangePassActionPerformed
        var oldPass= JOptionPane.showInputDialog(this, "Nhập mật khẩu cũ", "Đổi mật khẩu", JOptionPane.INFORMATION_MESSAGE);
        if(MainUI.getCurrentUser().getAccount().getPassword().equals(oldPass)){
            var newPass= JOptionPane.showInputDialog(this, "Nhập mật khẩu mới", "Đổi mật khẩu", JOptionPane.INFORMATION_MESSAGE);
            if(newPass.isBlank()){
                JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống", "Đổi mật khẩu", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            MainUI.getCurrentUser().getAccount().setPassword(newPass);
            try {
                accountBUS.update( MainUI.getCurrentUser().getAccount());
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công", "Đổi mật khẩu", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Mật khẩu cũ không đúng", "Đổi mật khẩu", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        jTextFieldName.setText(MainUI.getCurrentUser().getName());
        jTextFieldUsername.setText(MainUI.getCurrentUser().getAccount().getUsername());
        jTextFieldPhoneNumber.setText(MainUI.getCurrentUser().getPhoneNumber());
        jTextFieldAddress.setText(MainUI.getCurrentUser().getAddress());
        jTextAreaOther.setText(MainUI.getCurrentUser().getOtherInformation());
    }

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        if(jTextFieldName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
        }
        if(jTextFieldPhoneNumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
        }
        if(jTextFieldAddress.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
        }
        var newEm = new Employee(MainUI.getCurrentUser());
        newEm.setName(jTextFieldName.getText());
        newEm.setPhoneNumber(jTextFieldPhoneNumber.getText());
        newEm.setAddress(jTextFieldAddress.getText());
        newEm.setOtherInformation(jTextAreaOther.getText());
        try {
            employeeService.updateEmployee(newEm);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Cập nhật", JOptionPane.INFORMATION_MESSAGE);
            MainUI.setCurrentUser(newEm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private javax.swing.JButton jButtonChangePass;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaOther;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldInvoice;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPhoneNumber;
    private javax.swing.JTextField jTextFieldSalary;
    private javax.swing.JTextField jTextFieldSalaryInMonth;
    private javax.swing.JTextField jTextFieldTimeInMonth;
    private javax.swing.JTextField jTextFieldTotalSalary;
    private javax.swing.JTextField jTextFieldTotalTimeWork;
    private javax.swing.JTextField jTextFieldUsername;
}
