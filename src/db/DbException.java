package db;

public class DbException extends RuntimeException { //nao obriga a tratar
	
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) {
		super(msg);
	}
}
