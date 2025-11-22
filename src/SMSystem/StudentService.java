package SMSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class StudentService {

	private final String FILE_PATH = "students.txt";
	
	public List<Student> readAllStudents() {
		
		List<Student> list = new ArrayList<>(); 
			
	 try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
		 
		 String line;
		 
		 while((line = br.readLine()) != null) {
			 
			 String data[] = line.split(",");
			 
			 int roll = Integer.parseInt(data[0].trim());
			 String name = data[1].trim();
			 int age = Integer.parseInt(data[2].trim());
			 double marks = Double.parseDouble(data[3].trim());
			 
			 Student s = new Student(roll, name, age, marks);
			 list.add(s);
			 
		 }
	 } catch(IOException e) {
		 System.out.println("No student file found.. creating new.");
	 }
	 
	 return list;
	}
	
	public void saveAllStudents(List<Student> list) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))){
			
			for(Student s : list) {
				bw.write(s.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	// add new student
	
	public void addStudent(int rollNo, String name, int age, double marks) {
		
		List<Student> list = readAllStudents();
		
		//if roll num already exists
		for(Student s : list) {
			if(s.getRollNo() == rollNo) {
				System.out.println("Roll Number already exists!");
				return;
			}
		}
		
		Student newSt = new Student(rollNo, name, age, marks);
		list.add(newSt);
		
		saveAllStudents(list);
		
		System.out.println("Student added successfully!");
	}
	
	// search student by rollno
	
	public Student searchByRoll(int rollNo) {
		
		List<Student> list = readAllStudents();
		
		for(Student s : list) {
			if(s.getRollNo() == rollNo) {
				return s;
			}
		}
		return null;
	}
	
	//search student by name
	
	public List<Student> searchByName(String name) {
		
		List<Student> list = readAllStudents();
		List<Student> result = new ArrayList<Student>();
		
		for(Student s : list) {
			if(s.getName().equalsIgnoreCase(name)) {
				result.add(s);
			}
		}
		return result;
	}
	
	//update student
	
	public void updateStudent(int rollNo, String name, int age, double marks) {
		
		List<Student> list = readAllStudents();
		boolean found = false;
		
		for(Student s : list) {
			if(s.getRollNo() == rollNo) {
				
				s.setName(name);
				s.setAge(age);
				s.setMarks(marks);
				
				found = true;
				break;
			}
		}
		
		if(!found) {
			System.out.println("Student not found!");
			return;
		}
		
		saveAllStudents(list);
		System.out.println("Student updated successfully!");
	}
	
	//delete
	public void deleteStudent(int rollNo) {
		
		List<Student> list = readAllStudents();
		
		boolean removed = list.removeIf(s -> s.getRollNo() == rollNo);
		
		if(!removed) {
			System.out.println("Roll Number not found!");
			return;
		}
		
		saveAllStudents(list);
		System.out.println("Student deleted successfully!");
	}
	
	//sorting by name
	public List<Student> sortByName() {
		List<Student> list = readAllStudents();
		list.sort(Comparator.comparing(Student::getName));
		return list;
	}
	//sorting by rollNum
	public List<Student> sortByRoll(){
		List<Student> list = readAllStudents();
		list.sort(Comparator.comparingInt(Student::getRollNo));
		return list;
	}
	//sort by marks
	public List<Student> sortByMarks() {
		List<Student> list = readAllStudents();
		list.sort(Comparator.comparingDouble(Student::getMarks).reversed());
		return list;
	}

	
}