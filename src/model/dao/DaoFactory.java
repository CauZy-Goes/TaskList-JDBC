package model.dao;

import db.DB;
import mode.dao.impl.TaskListDaoJDBC;

public class DaoFactory {
	
	public static TaskListDao createTaskListDao() {
		return new TaskListDaoJDBC(DB.getConnection());
	}

}
