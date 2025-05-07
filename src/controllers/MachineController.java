package controllers;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Dao.MachineDao;
import net_management.DatabaseConnection;
import ultilz.Utilz;

public class MachineController {
	MachineDao dao;
	
	public MachineController() {
		dao = new MachineDao();
	}
	
	public void insertIntoMachineTable(DefaultTableModel defaultTable_pc) throws ClassNotFoundException {
		DatabaseConnection db = new DatabaseConnection();
		List<Integer> list = dao.getAllMachineId();
		if (list == null) {
			System.out.println("Lỗi kết nối");
			System.exit(0);
		}
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			
			Vector v = new Vector(4);
			v.add(0, list.get(i));
			v.add(1, dao.getMachineAccountId(list.get(i)));
			v.add(2, dao.getMachineState(list.get(i)));
			
			int time = dao.getMachineTime(list.get(i));
			Utilz t = new Utilz();
			t.SecToTime(time);
			String remainingTime = t.GetHour() + ":" + t.GetMin() + ":" + t.GetSec();
			
			v.add(3, remainingTime);
			v.add(4, "0:0:0");
			if (dao.getMachineState(list.get(i)) == 0) {
				v.set(2, "OFF");
			} else {
				v.set(2, "ON");
//				OpenPC(list.get(i).toString());
			}
			defaultTable_pc.addRow(v);
		}
	}
}
