package model.dao;

import db.DB;
import model.dao.impl.TaskListDaoJDBC;

public class DaoFactory {
	
	public static TaskListDao createTaskListDao() {
		return new TaskListDaoJDBC(DB.getConnection());
	}

}
