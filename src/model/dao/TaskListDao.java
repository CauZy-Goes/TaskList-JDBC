package model.dao;

import java.util.List;

import model.entities.TaskList;

public interface TaskListDao {
	
	void insert(TaskList taskList);
	void updated(TaskList taskList);
	void deleteById(Integer id);
	TaskList findById(Integer id);
	List<TaskList> findAll();
}
