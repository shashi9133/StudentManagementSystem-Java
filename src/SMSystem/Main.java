package SMSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		StudentService service = new StudentService();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			 System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
	            System.out.println("1. Add Student");
	            System.out.println("2. View All Students");
	            System.out.println("3. Search Student");
	            System.out.println("4. Update Student");
	            System.out.println("5. Delete Student");
	            System.out.println("6. Sort Students");
	            System.out.println("7. Exit");
	            System.out.print("Enter choice: ");
	            int ch = sc.nextInt();
	            
	            switch(ch) {
	            
	            case 1:
	            	System.out.println("Enter Roll No: ");
	            	int roll = sc.nextInt();
	            	
	            	System.out.println("Enter Name: ");
	            	String name = sc.next();
	            	
	            	System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    
                    service.addStudent(roll, name, age, marks);
                    break;
                    
	            case 2:
	            	List<Student> all = service.readAllStudents();
	            	System.out.println("\n--- ALL STUDENTS ---");
	            	for(Student s : all) {
	            		System.out.println(s.getRollNo() + " | " +s.getName() + " | " + s.getAge() + " | " + s.getMarks());
	            		
	            	}
	            	break;
	            	
	            case 3:
	            	System.out.println("\n1. Search by Roll No");
	            	System.out.println("2. Search by Name");
	            	System.out.println("Enter choice: ");
	            	int choice = sc.nextInt();
	            	
	            	if(choice == 1) {
	            		System.out.println("Enter Roll No: ");
	            		int r = sc.nextInt();
	            		
	            		Student s = service.searchByRoll(r);
	            		if(s == null)
	            			System.out.println("Student not found");
	            		else
	            			System.out.println(s.getRollNo() + " | " + s.getName() + " | " + s.getAge() + " | " + s.getMarks());
	            		
	            	} else {
	            		System.out.println("Enter Name: ");
	            		String n = sc.next();
	            		
	            		List<Student> list = service.searchByName(n);
	            		
	            		if(list.isEmpty()) {
	            			System.out.println("student not found");
	            		} else {
	            			for(Student s : list) {
	            				System.out.println(s.getRollNo() + " | " + s.getName() + " | " +s.getAge() + " | " + s.getMarks());
	            			}
	            		}
	            	}
	            	break;
	            	
	            case 4:
	            	 System.out.print("Enter Roll No to Update: ");
	                    int rUpdate = sc.nextInt();

	                    System.out.print("Enter New Name: ");
	                    String newName = sc.next();

	                    System.out.print("Enter New Age: ");
	                    int newAge = sc.nextInt();

	                    System.out.print("Enter New Marks: ");
	                    double newMarks = sc.nextDouble();

	                    service.updateStudent(rUpdate, newName, newAge, newMarks);
	                    break;
	                    
	            case 5:
	            	System.out.println("Enter Roll No to Delete: ");
	            	int rDelete = sc.nextInt();
	            	
	            	service.deleteStudent(rDelete);
	            	break;
	            	
	            case 6:
	            	 System.out.println("\n1. Sort by Name");
	                    System.out.println("2. Sort by Roll No");
	                    System.out.println("3. Sort by Marks (High to Low)");
	                    System.out.print("Enter choice: ");
	                    int sortChoice = sc.nextInt();

	                    List<Student> sorted = new ArrayList<>();

	                    if (sortChoice == 1)
	                        sorted = service.sortByName();
	                    else if (sortChoice == 2)
	                        sorted = service.sortByRoll();
	                    else
	                        sorted = service.sortByMarks();

	                    System.out.println("\n----- SORTED STUDENTS -----");
	                    for (Student s : sorted) {
	                        System.out.println(s.getRollNo() + " | " + s.getName() + " | " + s.getAge() + " | " + s.getMarks());
	                    }
	                    break;
	                    
	            case 7:
	            	System.out.println("Exiting.. Goodbye!");
	            	return;
	            	
	            	default:
	            		System.out.println("Invalid choice!");
	            }
		}
	}

}
