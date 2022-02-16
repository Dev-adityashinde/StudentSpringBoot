package nit.spring.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nit.spring.model.Student;
import nit.spring.service.IStudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private IStudentService service;
	
	//1. Show Register page
	@GetMapping("/register")
	public String showReg(Model model)
	{
		//Form Backing Object
		model.addAttribute("student", new Student());
		return "StudentRegister";
	}
	
	//2. Save Student
	@PostMapping("/save")
	public String save(
			@ModelAttribute Student student,
			Model model) 
	{
		Integer id = service.saveStudent(student);
		model.addAttribute("message", "Student '"+id+"' saved");
		//clear form
		model.addAttribute("student", new Student());
		return "StudentRegister";
	}
	
	//3. display all students
	@GetMapping("/all")
	public String showAll(Model model) {
		List<Student> list = service.getAllStudent();
		model.addAttribute("list", list);
		return "StudentData";
	}
	
	//4. Delete one student
	//.../delete?id=__
	@GetMapping("/delete")
	public String delete(
			@RequestParam Integer id,
			Model model)
	{
		String message=null;
		if(service.isStudentExist(id)) {
			service.deleteStudent(id);
			message = "Student '"+id+"' Deleted";
		}else {
			message = "Student '"+id+"' not exist";
		}
		model.addAttribute("message", message);
		//fetch latest table data
		List<Student> list = service.getAllStudent();
		model.addAttribute("list", list);
		//send to UI
		return "StudentData";
	}
	
	//5. Show Edit Page
	//.../edit?id=__
	/**
	 * If given input id exist in database
	 * then goto Edit Page, else come to same page
	 * back to Data(all)
	 */
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Integer id,
			Model model) 
	{
		String page = null;
		Optional<Student> opt = service.getOneStudent(id);
		
		if(opt.isPresent()) {
			//if exist--goto edit page
			model.addAttribute("student", opt.get());
			page = "StudentEdit";
		}else { 
			//given id not exist in DB
			page ="redirect:all";
		}
		return page;
	}
	
	//6. Do Update
	@PostMapping("/update")
	public String update(
			@ModelAttribute Student student
			) 
	{
		//task-- read id, if id exist then update,send message
		// else--redirect to all page
		service.updateStudent(student);
		return "redirect:all";
	}
	
}