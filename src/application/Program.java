package application;



import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.TaskListDao;
import model.entities.TaskList;

public class Program {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {
		
		TaskListDao taskListDao = DaoFactory.createTaskListDao();
		
		boolean running = true;
        while (running) {
            System.out.println("\nGerenciador de Tarefas:");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Remover Tarefa");
            System.out.println("3. Listar Tarefas");
            System.out.println("4. Renomear Tarefa");
            System.out.println("5. Filtrar pelo Id");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            
            System.out.println();

            int choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice) {
                case 1:
                	taskListDao.insert(insertTask());
                	System.out.println("Tarefa adicionada !");
                    break;
                case 2:
                	taskListDao.deleteById(removeTask());
                	System.out.println("Tarefa removida !");
                    break;
                case 3:
                    List<TaskList> list = taskListDao.findAll();
                    for(TaskList obj : list) {
                    	System.out.println(obj);
                    }
                    break;
                case 4:
                    taskListDao.updated(updatedTask());
                    System.out.println("Tarefa modificada !");
                    break;
                case 5:
                    System.out.println(taskListDao.findById(findById()));
                    break;
                case 6:
                	running = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

	private static Integer findById() {
		try {
			System.out.print("Digite o Id da tarefa que deseja visualizar: ");
	    	int id = scan.nextInt();
	    	scan.nextLine();
	    	return id;
			}
			catch(InputMismatchException e) { 
				System.out.println("Input error");
				e.printStackTrace();
			}
		return null;
	}

	private static TaskList updatedTask() {
		try {
			System.out.print("Digite o Id da tarefa que deseja modificar: ");
	    	int id = scan.nextInt();
	    	scan.nextLine();
	    	System.out.print("Digite o novo nome da tarefa:");
	    	String nome = scan.nextLine();
	    	return new TaskList(id, nome);
			}
			catch(InputMismatchException e) { 
				System.out.println("Input error");
				e.printStackTrace();
			}
		return null;
	}

	private static TaskList insertTask() {
		try {
	    	System.out.print("Digite o nome da tarefa:");
	    	String nome = scan.nextLine();
	    	return new TaskList(nome);
			}
			catch(InputMismatchException e) { 
				System.out.println("Input error");
				e.printStackTrace();
			}
		return null;
	}
	

	private static Integer removeTask() {
		try {
		System.out.print("Digite o Id da tarefa que deseja remover: ");
    	return scan.nextInt();
		}
		catch(InputMismatchException e) { 
			System.out.println("Input error");
			e.printStackTrace();
		}
		return null;
	}

	
}
