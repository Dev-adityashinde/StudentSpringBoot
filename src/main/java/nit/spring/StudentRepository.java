package nit.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import nit.spring.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
