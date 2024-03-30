package model.entities;

import java.io.Serializable;
import java.util.Date;

public class TaskList implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String task;
	private Date deadLine;
	
	public TaskList() {
		
	}

	public TaskList( String task, Date deadLine) {
		this.task = task;
		this.deadLine = deadLine;
	}
	public TaskList(Integer id, String task, Date deadLine) {
		this.task = task;
		this.deadLine = deadLine;
		this.id = id;
	}

	@Override
	public String toString() {
		return "TaskList [id=" + id + ", task=" + task + ", deadLine=" + deadLine + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskList other = (TaskList) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	
}
