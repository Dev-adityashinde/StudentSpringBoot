package nit.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nit.spring.StudentRepository;
import nit.spring.model.Student;

@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private StudentRepository repo;
	
	@Override
	public Integer saveStudent(Student s) {
		/*Student s1 = repo.save(s);
		Integer id = s1.getStdId();
		return id;*/
		return repo.save(s).getStdId();
	}

	@Override
	public void updateStudent(Student s) {
		repo.save(s);
	}

	@Override
	public void deleteStudent(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<Student> getOneStudent(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<Student> getAllStudent() {
		return repo.findAll();
	}

	@Override
	public boolean isStudentExist(Integer id) {
		return repo.existsById(id);
	}

}
