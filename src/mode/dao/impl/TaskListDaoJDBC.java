package mode.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.TaskListDao;
import model.entities.TaskList;

public class TaskListDaoJDBC implements TaskListDao {

	private Connection conn;

	public TaskListDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(TaskList taskList) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tasklist " + "(Task, Deadline)" + "VALUES " + "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, taskList.getTask());
			st.setDate(2, new java.sql.Date(taskList.getDeadLine().getTime()));

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					taskList.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void updated(TaskList taskList) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tasklist " + "SET Task = ?, DeadLine = ?" + "WHERE Id = ? ");

			st.setString(1, taskList.getTask());
			st.setDate(2, new java.sql.Date(taskList.getDeadLine().getTime()));
			st.setInt(3, taskList.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM tasklist " + "WHERE Id = ? ");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public TaskList findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tasklist WHERE ID = ? ");

			st.setInt(1, id);

			rs = st.executeQuery();
			if (rs.next()) {
				TaskList obj = instatiateTaskList(rs);
				return obj;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<TaskList> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tasklist");
			
            rs = st.executeQuery();
			
			List<TaskList> list = new ArrayList<>();
			
			
			while(rs.next()) {
				TaskList obj = instatiateTaskList(rs);
				list.add(obj);
			}
			return list;
		}
		catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
		

	private TaskList instatiateTaskList(ResultSet rs) throws SQLException {
		TaskList taskList = new TaskList();
		taskList.setId(rs.getInt("ID"));
		taskList.setTask(rs.getString("Task"));
		taskList.setDeadLine(rs.getDate("DeadLine"));
		return taskList;
	}

}
