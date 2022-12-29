package com.example.restapi.Student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service //COMPONENT CAN ALSO BE USED HERE
public class StudentService {
	
	public final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	List<Student> getStudents() {
		return studentRepository.findAll();
				
	}

	public void registerNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
	
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("Email already exists!");
		}
		
		studentRepository.save(student);
		
	}

	public void deleteStudent(Long id) {
		boolean exists = studentRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("Student with id "+id+" does not exists!");
		}
		studentRepository.deleteById(id);
		
	}

	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalStateException("Student with id "+id+" does not exists!"));
		
		if(name!=null
			&& name.length() > 0 && !Objects.equals(name, student.getName())){
				student.setName(name);
		}
	
		if(email!=null
			&& email.length() > 0 && !Objects.equals(email, student.getEmail())){
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("Email already exists!");
			}else {
				student.setEmail(email);
			}
		}
		
	}
	
}