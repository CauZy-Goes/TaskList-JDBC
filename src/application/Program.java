package application;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            System.out.println("6. Listar tarefas atrasadas");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice) {
                case 1:
                	taskListDao.insert(instanceTaskList());
                	System.out.println("Tarefa adicionada !");
                    break;
                case 2:
                	taskListDao.deleteById(remove());
                	System.out.println("Tarefa removida !");
                    break;
                case 3:
                    List<TaskList> list = taskListDao.findAll();
                    for(TaskList obj : list) {
                    	System.out.println(obj);
                    }
                    break;
                case 4:
                    taskListDao.updated(instanceTaskList());
                    System.out.println("Tarefa modificada !");
                    break;
                case 5:
                    taskListDao.findById(remove());
                    break;
                case 6:
                	running = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

	private static Integer remove() {
		try {
		System.out.println("Digite o Id da tarefa que deseja remover: ");
    	return scan.nextInt();
		}
		catch(InputMismatchException e) { //erro de colocar string em int
			System.out.println("Input error");
			e.printStackTrace();
		}
		return null;
	}

	private static TaskList instanceTaskList() {
		try {
			System.out.print("Id da tarefa: ");
	    	int id = scan.nextInt();
	    	scan.nextLine();
			System.out.print("Nome da tarefa: ");
	    	String nome = scan.nextLine();
	    	System.out.print("Prazo da tarefa (DD/MM/YYYY):");
	    	Date data;
			data = sdf.parse(scan.next());
			return new TaskList(id,nome,data);
		} catch (ParseException e) {
			System.out.println("Data invalida");
			e.printStackTrace();
		}
		catch(InputMismatchException e) { //erro de colocar string em int
			System.out.println("Input error");
			e.printStackTrace();
		}
		return null;
	}
}
